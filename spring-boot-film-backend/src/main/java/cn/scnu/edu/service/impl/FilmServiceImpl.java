package cn.scnu.edu.service.impl;

import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.entity.vo.request.FilmAddVO;
import cn.scnu.edu.entity.vo.request.RequestParamVO;
import cn.scnu.edu.mapper.FilmMapper;
import cn.scnu.edu.service.FilmService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 电影信息处理相关服务
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
    @Override
    public boolean addFilm(FilmAddVO vo) throws ParseException {
        String name = vo.getName();
        if(existsFilmByName(name)){
            return false;
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateformat.parse(vo.getTime());
        Film film = new Film(null, name, vo.getIntro(), vo.getType(), vo.getActor(), vo.getRegion(), vo.getGood(), vo.getWheat(), vo.getMheat(), date, "", "");
        return this.save(film);
    }

    @Override
    public boolean deleteFilm(String name) {
        if(!existsFilmByName(name)){
            this.remove(Wrappers.<Film>query().eq("name", name));
            return true;
        }
        return false;
    }

    @Override
    public List<Film> getFilm() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public Page<Film> getFilmList(RequestParamVO vo) {
        if(vo.getPageNum()==null||vo.getPageSize()==null){
            return null;
        }
        Page<Film> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        QueryWrapper<Film> queryWrapper = new QueryWrapper<>();
        if(vo.getKeyword()!=null)
            queryWrapper.like("name", vo.getKeyword());
        if(vo.getActorKeyword()!=null)
            queryWrapper.like("actor", vo.getActorKeyword());
        return this.baseMapper.selectPage(page, queryWrapper);

    }

    private boolean existsFilmByName(String name){
        return this.baseMapper.exists(Wrappers.<Film>query().eq("name", name));
    }
}
