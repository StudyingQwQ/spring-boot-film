package cn.scnu.edu.controller;

import cn.scnu.edu.entity.RestBean;
import cn.scnu.edu.entity.dto.Account;
import cn.scnu.edu.entity.dto.Film;
import cn.scnu.edu.service.AccountService;
import cn.scnu.edu.service.FilmService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@Tag(name = "报表生成", description = "导出用户和电影报表。")
public class ExcelController {
    @Resource
    private FilmService filmService;
    @Resource
    private AccountService accountService;
    /**
     * 获取电影报表
     * @param response 响应
     * @return 得到电影报表
     */
    @GetMapping("/film")
    public RestBean<Void> filmExport(HttpServletResponse response) throws IOException {
        List<Film> list = filmService.getFilm();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet；charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("电影报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), Film.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("sheet1")
                .doWrite(list);
        return RestBean.success();
    }

    /**
     * 获取用户报表
     * @param response 响应
     * @return 得到用户报表
     */
    @GetMapping("/account")
    public RestBean<Void> accountExport(HttpServletResponse response) throws IOException {
        List<Account> list = accountService.getAccounts();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet；charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), Account.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("sheet1")
                .doWrite(list);
        return RestBean.success();
    }
}
