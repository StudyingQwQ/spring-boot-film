package cn.scnu.edu.entity.vo.request;

import lombok.Data;

import java.util.Date;

/**
 * 电影添加表单实体
 */
@Data
public class FilmAddVO {
    String name;
    String intro;
    String type;
    String actor;
    String region;
    double good;
    Integer wheat;
    Integer mheat;
    String time;
}
