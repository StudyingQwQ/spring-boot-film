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
 * 数据库中的用户信息
 */
@Data
@TableName("db_account")
@AllArgsConstructor
public class Account implements BaseData {
//    @TableId(type = IdType.AUTO)
//    Integer id;
//    String username;
//    String password;
//    String email;
//    String role;
//    Integer points;
//    Date registerTime;
    @ExcelProperty("用户编号")
    @ColumnWidth(5)
    @TableId(type = IdType.AUTO)
    Integer id;
    @ExcelProperty("用户名")
    @ColumnWidth(15)
    String username;
    @ExcelProperty("密码")
    @ColumnWidth(15)
    String password;
    @ExcelProperty("邮箱")
    @ColumnWidth(30)
    String email;
    @ExcelProperty("用户权限")
    @ColumnWidth(10)
    String role;
    @ExcelProperty("积分")
    @ColumnWidth(10)
    Integer points;
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("注册时间")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date registerTime;
}
