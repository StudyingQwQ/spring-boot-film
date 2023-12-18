package cn.scnu.edu.controller;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.vo.request.ConfirmResetVO;
import cn.scnu.edu.entity.vo.request.EmailRegisterVO;
import cn.scnu.edu.entity.vo.request.EmailResetVO;
import cn.scnu.edu.service.AccountService;
import cn.scnu.edu.utils.JwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 用于验证相关Controller包含用户的注册、重置密码等操作
 */
@Validated
@RestController
@RequestMapping("/api/auth")
@Tag(name = "登录校验相关", description = "包括用户登录、注册、验证码请求等操作。")
public class AuthorizeController {
    @Resource
    AccountService service;

    @Resource
    JwtUtils utils;
    /**
     * 请求邮件验证码
     * @param email 请求邮件
     * @param type 类型
     * @param request 请求
     * @return 是否请求成功
     */
    @GetMapping("/ask-code")
    public RestBean<Void> askVerifyCOde(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)") String type,
                                        HttpServletRequest request){
        return this.messageHandle(() ->
                service.registerEmailVerifyCode(type, String.valueOf(email), request.getRemoteAddr()));
    }

    /**
     * 进行用户注册操作，需要先请求邮件验证码
     * @param vo 注册信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody @Valid EmailRegisterVO vo){
        return this.messageHandle(() -> service.registerEmailAccount(vo));
    }

//    @PostMapping("/reset-confirm")
//    public RestBean<Void> resetConfirm(@RequestBody @Valid ConfirmResetVO vo){
//        return this.messageHandle(() -> service.resetConfirm(vo));
//    }
//
//    @PostMapping("/reset-password")
//    public RestBean<Void> resetPassword(@RequestBody @Valid EmailResetVO vo){
//        return this.messageHandle(() ->
//                service.resetEmailAccountPassword(vo));
//    }
    /**
     * 执行密码重置确认，检查验证码是否正确
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-confirm")
    public RestBean<Void> resetConfirm(@RequestBody @Valid ConfirmResetVO vo){
        return this.messageHandle(vo, service::resetConfirm);
    }

    /**
     * 执行密码重置操作
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-password")
    public RestBean<Void> resetPassword(@RequestBody @Valid EmailResetVO vo){
        return this.messageHandle(vo, service::resetEmailAccountPassword);
    }

    /**
     * 刷新token
     * @return 新token
     */
    @GetMapping("/refresh-token")
    public RestBean<String> refreshToken(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jwt = utils.createJwt(user, service.findAccountByNameOrEmail(user.getUsername()).getId(), user.getUsername());
        return RestBean.success(jwt);
    }

    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理，是下面的messageHandle的重载
     * @param vo 信息
     * @param function 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<Void> messageHandle(T vo, Function<T, String> function){
        return messageHandle(() -> function.apply(vo));
    }

    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理
     * @param action 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.faliure(400,message);
    }

}
