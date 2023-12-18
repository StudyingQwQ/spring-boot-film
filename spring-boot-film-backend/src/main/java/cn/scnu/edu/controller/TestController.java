package cn.scnu.edu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "测试用接口", description = "用于测压或者其他(有需要时)")
public class TestController {
    /**
     * 测试接口
     * @return 测试返回值
     */
    @GetMapping("/hello")
    public String test(){
        return "Hello World!";
    }
}
