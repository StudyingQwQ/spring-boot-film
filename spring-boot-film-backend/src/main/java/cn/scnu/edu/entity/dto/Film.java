package cn.scnu.edu.entity.dto;

import cn.scnu.edu.entity.BaseData;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 数据库中的电影信息
 */
@Data
@TableName("db_film")
@AllArgsConstructor
public class Film implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    String intro;
    String type;
    String actor;
    String region;
    String good;
    Integer wheat;
    Integer mheat;
    Date time;
    String picurl;
    String filmurl;
}