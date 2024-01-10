package cn.scnu.edu.service;

import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import cn.scnu.edu.entity.vo.request.RequestParamVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

public interface FilmService extends IService<Film> {
    boolean addFilm(FilmAddVO vo) throws ParseException;

    boolean deleteFilm(String name);

    List<Film> getFilm();

    Page<Film> getFilmList(RequestParamVO vo);
}
