package cn.scnu.edu.entity.dto;

import cn.scnu.edu.entity.BaseData;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 数据库电影信息
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
    double good;
    Integer wheat;
    Integer mheat;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date time;
    String picurl;
    String filmurl;
}
