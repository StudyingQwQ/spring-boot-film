package cn.scnu.edu.controller;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.dto.MinioPojo;
import cn.scnu.edu.service.MinioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Minio操作
 */
//todo:后续需要限制只有管理员才可以调用该方法
@RestController
@RequestMapping("/api/minio")
@Tag(name = "Minio操作", description = "后台文件上传下载(删除有些bug,还没做)")
public class MinioController {
    @Resource
    private MinioService minioService;
    @Resource
    private MinioPojo minioPojo;

    /**
     * 文件上传
     * @param file 文件
     * @return 是否上传成功
     */
    @PostMapping("/uploadFile")
    public RestBean<String> uploadObject(MultipartFile file, @RequestParam String folderName) {
        String url = minioService.uploadFile(minioPojo.getBucketName(), folderName, file);
        return RestBean.success(url);
    }


    /**
     * 文件下载
     * @param url 文件名称,格式为"2023-12-24/21软工-《Java Web开发》期末作品要求.docx",后续命名方式会更新
     * @param path 文件下载路径
     * @return 是否注册成功
     */
    @PostMapping("/downloadFile")
    public RestBean<String> getObject(@RequestParam String url
            ,@RequestParam String path){
        boolean flag = minioService.downloadFile(url,path);
        if(flag) return RestBean.success("下载成功");
        else return RestBean.faliure(500,"下载失败");
    }

    /**
     * 生成文件分享url
     * @param url 文件名称,格式为"2023-12-24/21软工-《Java Web开发》期末作品要求.docx",后续命名方式会更新
     * @param time 文件分享时间，单位为小时
     * @return 是否注册成功
     */
    @GetMapping("/shareFile")
    public RestBean<String> shareFile(@RequestParam String url, @RequestParam int time){
        return RestBean.success(minioService.shareFile(url,time));
    }
}
