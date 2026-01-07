package cn.magic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

//客户喜好查询条件
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerPreferenceDTO {
    private Integer customerPreferenceId;  //喜好编号
    private Integer customerId; //客户编号
    private String customerName; //顾客姓名
    private Integer pageSize; //每页显示多少页码
    private Integer curPage;//当前页
}
