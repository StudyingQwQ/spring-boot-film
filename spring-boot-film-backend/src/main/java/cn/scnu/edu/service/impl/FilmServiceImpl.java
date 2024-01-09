package cn.scnu.edu.service.impl;

import cn.scnu.edu.entity.dto.Account;
import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import cn.scnu.edu.mapper.FilmMapper;
import cn.scnu.edu.service.FilmService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
    @Override
    public boolean addFilm(FilmAddVO vo) {
        String name = vo.getName();
        if(existsFilmByName(name)){
            return false;
        }
        Film film = new Film(null, name, vo.getIntro(), vo.getType(), vo.getActor(), vo.getRegion(), vo.getGood(), vo.getWheat(), vo.getMheat(), new Date(), "", "");
        return this.save(film);
    }

    private boolean existsFilmByName(String name){
        return this.baseMapper.exists(Wrappers.<Film>query().eq("name", name));
    }
}
