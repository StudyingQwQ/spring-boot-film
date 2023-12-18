package cn.scnu.edu.service;

import cn.scnu.edu.entity.dto.Account;
import cn.scnu.edu.entity.vo.request.ConfirmResetVO;
import cn.scnu.edu.entity.vo.request.EmailRegisterVO;
import cn.scnu.edu.entity.vo.request.EmailResetVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String ip);
    String registerEmailAccount(EmailRegisterVO vo);
    String resetEmailAccountPassword(EmailResetVO vo);
    String resetConfirm(ConfirmResetVO vo);
}
