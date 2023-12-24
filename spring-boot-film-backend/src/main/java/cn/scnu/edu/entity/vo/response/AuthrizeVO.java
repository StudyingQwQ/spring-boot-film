package cn.scnu.edu.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * 登录验证成功的用户信息响应
 */
@Data
public class AuthrizeVO {
    String username;
    String role;
    String token;
    Integer points;
    Date expire;
}
