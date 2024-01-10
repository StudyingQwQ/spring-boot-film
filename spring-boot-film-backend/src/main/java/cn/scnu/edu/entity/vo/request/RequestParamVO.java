package cn.scnu.edu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestParamVO {
    Integer pageNum;
    Integer pageSize;
    String keyword;
}
