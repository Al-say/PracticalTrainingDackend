package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
//BackdownDTO-退住查询条件
@Data
@EqualsAndHashCode(callSuper=false)
public class BackdownDTO {
    private Integer userId; //用户编号
    private Integer pageSize; //页码
}
