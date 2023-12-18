package cn.scnu.edu.config;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.dto.Account;
import cn.scnu.edu.filter.JwtAuthorizeFilter;
import cn.scnu.edu.service.AccountService;
import cn.scnu.edu.utils.JwtUtils;
import cn.scnu.edu.entity.vo.response.AuthrizeVO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfiguration {
    @Resource
    JwtUtils utils;
    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;

    @Resource
    AccountService service;
    /**
     * 针对于 SpringSecurity 6 的新版配置方法
     * @param http 配置器
     * @return 自动构建的内置过滤器链
     * @throws Exception 可能的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf->conf
                        .requestMatchers("/api/auth/**","/error").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(conf->conf
                        .loginProcessingUrl("/api/auth/login")
                        .failureHandler(this::onAuthenticationFailure)
                        .successHandler(this::onAuthenticationSuccess)
                )
                .logout(conf->conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf->conf
                        .authenticationEntryPoint(this::onUnauthorized)
                        .accessDeniedHandler(this::onAccessDeny)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf->conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * 登录成功处理，将后端数据转化为前端数据并存入token
     * @param request 请求
     * @param response 响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        User user = (User) authentication.getPrincipal();
        Account account = service.findAccountByNameOrEmail(user.getUsername());
        String token = utils.createJwt(user, account.getId(), account.getUsername());
        // 后端数据转化为前端数据
        AuthrizeVO vo = account.asViewObject(AuthrizeVO.class, v -> {
            v.setExpire(utils.expireTime());
            v.setToken(token);
        });

        response.getWriter().write(RestBean.success(vo).asJsonString());
    }
    /**
     * 退出登录处理，将对应的Jwt令牌列入黑名单不再使用
     * @param request 请求
     * @param response 响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if(utils.invlidateJwt(authorization)){
            writer.write(RestBean.success().asJsonString());
        }else {
            writer.write(RestBean.faliure(400,"退出失败").asJsonString());
        }
    }
    /**
     * 登录失败处理
     * @param request 请求
     * @param response 响应
     * @param exception 验证实体
     * @throws IOException 可能的异常
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }
    /**
     * 403处理
     * @param request 请求
     * @param response 响应
     * @param accessDeniedException 验证实体
     * @throws IOException 可能的异常
     */
    public void onAccessDeny(HttpServletRequest request,
                              HttpServletResponse response,
                              AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.forbidden(accessDeniedException.getMessage()).asJsonString());
    }
    /**
     * 401处理
     * @param request 请求
     * @param response 响应
     * @param exception 验证实体
     * @throws IOException 可能的异常
     */
    public void onUnauthorized(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }
}
