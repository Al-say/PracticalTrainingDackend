package cn.magic.dto;

import lombok.Data;
//护理项目查询条件
@Data
public class NurseItemDTO {
    private Integer status; //状态 1-启用；2-停用
    private Integer pageSize; //页码
    private String itemName; //名称
}
