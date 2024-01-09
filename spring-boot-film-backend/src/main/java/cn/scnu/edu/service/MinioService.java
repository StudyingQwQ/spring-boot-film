package cn.scnu.edu.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    String uploadFile(String bucketName, String folderName, MultipartFile file);
    Boolean downloadFile(String url, String path);

    String shareFile(String url, int time);
}
