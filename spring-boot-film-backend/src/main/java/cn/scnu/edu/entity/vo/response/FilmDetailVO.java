package cn.scnu.edu.entity.vo.response;

import lombok.Data;

import java.util.Date;
/**
 * 电影的详情信息响应
 */
@Data
public class FilmDetailVO {
    String name;
    String intro;
    String type;
    String actor;
    String region;
    String good;
    Date time;
}
