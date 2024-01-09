package cn.scnu.edu.entity.vo.request;

import lombok.Data;

@Data
public class FilmAddVO {
    String name;
    String intro;
    String type;
    String actor;
    String region;
    String good;
    Integer wheat;
    Integer mheat;
}
