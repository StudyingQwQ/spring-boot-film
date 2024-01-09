package cn.scnu.edu.service.impl;

import cn.scnu.edu.entity.dto.MinioPojo;
import cn.scnu.edu.service.MinioService;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioPojo minioPojo;
    //todo:后续要实现分片上传
    @Override
    public String uploadFile(String bucketName, String folderName, MultipartFile file) {
        try {
            //判断桶是否存在
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists){
                //如果不存在，就创建桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            //String uuid= UUID.randomUUID().toString();
            String filename = folderName+"/"+file.getOriginalFilename();
            //加一个/表示创建一个文件夹
            minioClient.putObject(PutObjectArgs.builder().
                    bucket(bucketName).
                    object(filename).
                    stream(file.getInputStream(), file.getSize(), -1).
                    //文件上传的类型，如果不指定，那么每次访问时都要先下载文件
                            contentType(file.getContentType()).
                    build());
            //todo:后续命名方式需要更新
            String url= minioPojo.getUrl()+"/"+minioPojo.getBucketName()+"/"+filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }

    @Override
    public Boolean downloadFile(String url, String path) {
        try{
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(minioPojo.getBucketName())
                            .object(url)
                            .filename(path+fileName)
                            .build());
            return true;
        }catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String shareFile(String url, int time) {
        try{
            String shareUrl =
                    minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(minioPojo.getBucketName())
                                    .object(url)
                                    .expiry(60 * 60 * time)//时间，单位为小时
                                    .build());
            return shareUrl;
        }catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
}
