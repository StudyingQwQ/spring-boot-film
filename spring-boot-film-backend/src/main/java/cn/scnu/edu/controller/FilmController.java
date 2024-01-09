package cn.scnu.edu.controller;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import cn.scnu.edu.service.FilmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/film")
@Tag(name = "电影相关操作", description = "电影添加，电影查询，电影删除，电影修改")
public class FilmController {
    @Resource
    FilmService filmService;
    /**
     * 电影上传
     * @param vo 电影信息
     * @return 是否上传成功
     */
    @PostMapping("/addFilm")
    public RestBean<String> addFilm(@RequestBody FilmAddVO vo){
        if(filmService.addFilm(vo))
            return RestBean.success("电影添加成功");
        return RestBean.faliure(400,"电影添加失败");
    }
}
