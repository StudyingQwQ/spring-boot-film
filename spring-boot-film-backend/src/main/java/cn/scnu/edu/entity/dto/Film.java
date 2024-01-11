package cn.scnu.edu.entity.dto;

import cn.scnu.edu.entity.BaseData;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
    @ExcelProperty("电影编号")
    @ColumnWidth(5)
    @TableId(type = IdType.AUTO)
    Integer id;
    @ExcelProperty("电影名称")
    @ColumnWidth(15)
    String name;
    @ExcelProperty("电影简介")
    @ColumnWidth(50)
    String intro;
    @ExcelProperty("电影类型")
    @ColumnWidth(10)
    String type;
    @ExcelProperty("电影主演")
    @ColumnWidth(10)
    String actor;
    @ExcelProperty("电影地区")
    @ColumnWidth(10)
    String region;
    @ExcelProperty("电影好评")
    @ColumnWidth(10)
    double good;
    @ExcelProperty("电影周热度")
    @ColumnWidth(10)
    Integer wheat;
    @ExcelProperty("电影月热度")
    @ColumnWidth(10)
    Integer mheat;
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("电影上映时间")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date time;
    @ExcelProperty("电影封面")
    @ColumnWidth(50)
    String picurl;
    @ExcelProperty("电影播放地址")
    @ColumnWidth(50)
    String filmurl;
}
