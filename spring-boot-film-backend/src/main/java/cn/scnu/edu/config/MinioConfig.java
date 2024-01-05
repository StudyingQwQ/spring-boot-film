package cn.scnu.edu.config;

import cn.scnu.edu.entity.dto.MinioPojo;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Resource
    private MinioPojo minioPojo;
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioPojo.getUrl())  //传入url地址
                //传入用户名和密码
                .credentials(minioPojo.getUsername(), minioPojo.getPassword())
                .build();  //完成MinioClient的初始化
    }
}
