package cn.scnu.edu.controller;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import cn.scnu.edu.entity.vo.request.RequestParamVO;
import cn.scnu.edu.service.FilmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


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
    @PostAuthorize("hasRole('ADMIN')")
    @PostMapping("/addFilm")
    public RestBean<String> addFilm(@RequestBody FilmAddVO vo) throws ParseException {
        if(filmService.addFilm(vo))
            return RestBean.success("电影添加成功");
        return RestBean.faliure(400,"电影添加失败");
    }

//    @PostMapping("/deleteFilm")
//    public RestBean<String> deleteFilm(@RequestParam String name){
//        if(filmService.deleteFilm(name))
//            return RestBean.success("电影删除成功");
//        return RestBean.faliure(400,"电影删除失败或电影不存在");
//
//    }
    /**
     * 电影列表
     * @return 电影所有信息，前端信息请自行选择
     */
    @GetMapping("/getFilm")
    public RestBean<List<Film>> getFilm(){
        return RestBean.success(filmService.getFilm());
    }

    /**
     * 电影分页查询主演和电影名
     * @param vo 包含了页号，页面大小和主演查询关键字和电影名称关键字
     * @return 电影所有信息，前端信息请自行选择
     */
    @PostMapping("/list")
    public RestBean<?> filmList(@RequestBody RequestParamVO vo){
        if (filmService.getFilmList(vo) == null) {
            return RestBean.faliure(400,"查询失败");
        }
        return RestBean.success(filmService.getFilmList(vo));
    }
    /**
     * 电影按地区和类型获取列表
     * @param type 类型
     * @param key 查询关键字
     * @return 电影所有信息，前端信息请自行选择
     */
    @PostMapping("/getFilmListbyType")
    public RestBean<?> getFilmListbyType(@RequestParam String type,@RequestParam String key){
        return RestBean.success(filmService.getFilmListbyType(type,key));
    }
    /**
     * 电影按不同热度排序
     * @param type 排序关键字
     * @return 电影所有信息，前端信息请自行选择
     */
    @PostMapping("/sortFilmList")
    public RestBean<?> sortFilmList(@RequestParam String type){
        return RestBean.success(filmService.sortFilmList(type));
    }

    @PostMapping("/getFilmbyId")
    public RestBean<?> getFilmbyId(@RequestParam Integer id){
        if (filmService.getFilmbyId(id) == null) {
            return RestBean.faliure(400,"查询失败");
        }
        return RestBean.success(filmService.getFilmbyId(id));
    }
}
