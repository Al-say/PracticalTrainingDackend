package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
//外出登记查询条件
@Data
@EqualsAndHashCode(callSuper=false)
public class OutwardDTO {
    private Integer userId; //用户编号
    private Integer pageSize; //页码
    private Integer pageNum;  // 当前第几页
}
