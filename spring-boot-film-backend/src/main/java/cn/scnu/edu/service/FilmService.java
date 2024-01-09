package cn.scnu.edu.service;

import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FilmService extends IService<Film> {
    boolean addFilm(FilmAddVO vo);
}
