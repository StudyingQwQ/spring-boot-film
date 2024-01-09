package cn.scnu.edu.entity.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Minio配置
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioPojo {
    private String url;
    private String username;
    private String password;
    private String bucketName;
}
