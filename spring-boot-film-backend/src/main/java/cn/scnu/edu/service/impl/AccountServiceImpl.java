package cn.scnu.edu.service.impl;

import cn.scnu.edu.entity.dto.Account;
import cn.scnu.edu.entity.vo.request.ConfirmResetVO;
import cn.scnu.edu.entity.vo.request.EmailRegisterVO;
import cn.scnu.edu.entity.vo.request.EmailResetVO;
import cn.scnu.edu.mapper.AccountMapper;
import cn.scnu.edu.service.AccountService;
import cn.scnu.edu.utils.Const;
import cn.scnu.edu.utils.FlowUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 账户信息处理相关服务
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    FlowUtils utils;
    @Resource
    AmqpTemplate amqpTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    PasswordEncoder encoder;

    /**
     * 从数据库中通过用户名或邮箱查找用户详细信息
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果用户未找到则抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
    /**
     * 生成注册验证码存入Redis中，并将邮件发送请求提交到消息队列等待发送
     * @param type 类型
     * @param email 邮件地址
     * @param ip 请求IP地址
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String registerEmailVerifyCode(String type, String email, String ip) {
        // 限流,拿IP当锁
        synchronized (ip.intern()){
            if(!this.verifyLimit(ip)){
                return "请求频繁，请稍候再试";
            }
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
            Map<String,Object> data = Map.of("type",type,"email",email,"code",code);
            amqpTemplate.convertAndSend("mail",data);
            //验证码3分钟有效
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA+email,String.valueOf(code),3, TimeUnit.MINUTES);
            return null;
        }
    }

    /**
     * 邮件验证码注册账号操作，需要检查验证码是否正确以及邮箱、用户名是否存在重名
     * @param vo 注册基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String registerEmailAccount(EmailRegisterVO vo) {
        String email = vo.getEmail();
        String username = vo.getUsername();
        String key = Const.VERIFY_EMAIL_DATA+email;
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) return "请先获取验证码";
        if (!code.equals(vo.getCode())) return "验证码错误";
        if (existsAccountByEmail(email)) return "该邮箱已被注册";
        if (existsAccountByUsername(username)) return "该用户名已被注册";
        String password = encoder.encode(vo.getPassword());
        Account account = new Account(null,vo.getUsername(), password, vo.getEmail(), "user", new Date());
        if(this.save(account)){
            stringRedisTemplate.delete(key);
            return null;
        }
        return "注册失败,请联系管理员";
    }

    /**
     * 邮件验证码重置密码操作，需要检查验证码是否正确
     * @param vo 重置基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetConfirm(ConfirmResetVO vo) {
        String email = vo.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA+email);
        if (code==null) return "请先获取验证码";
        if (!code.equals(vo.getCode())) return "验证码错误";
        return null;
    }

    /**
     * 重置密码确认操作，验证验证码是否正确
     * @param vo 验证基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetEmailAccountPassword(EmailResetVO vo) {
        String email = vo.getEmail();
        String verify = this.resetConfirm(new ConfirmResetVO(email,vo.getCode()));
        if (verify!=null) return verify;
        String password = encoder.encode(vo.getPassword());
        boolean update = this.update().eq("email",email).set("password",password).update();
        if (update) {
            stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA+email);
        }
        return null;
    }

    /**
     * 通过用户名或邮件地址查找用户
     * @param text 用户名或邮件
     * @return 账户实体
     */
    public Account findAccountByNameOrEmail(String text){
        return this.query().eq("username",text).or()
                .eq("email",text)
                .one();
    }
    /**
     * 查询指定邮箱的用户是否已经存在
     * @param email 邮箱
     * @return 是否存在
     */
    private boolean existsAccountByEmail(String email){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email", email));
    }

    /**
     * 查询指定用户名的用户是否已经存在
     * @param username 用户名
     * @return 是否存在
     */
    private boolean existsAccountByUsername(String username){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username", username));
    }

    /**
     * 针对IP地址进行邮件验证码获取限流
     * @param ip 地址
     * @return 是否通过验证
     */
    private boolean verifyLimit(String ip){
        String key = Const.VERIFY_EMAIL_LIMIT +ip;
        return utils.limitOnceCheck(key, 60);
    }

}
