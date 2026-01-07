package cn.magic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//BedDetailsVo对象
@Data
@EqualsAndHashCode(callSuper = false)
public class BedDetailsVo {
    private Integer id; // 主键
    private Integer isDelete;  // 逻辑删除标记（0：显示；1：隐藏）
    private Date startDate; //床位起始日期
    private Date endDate; //床位结束日期
    private String bedDetails; //床位详情信息
    private Integer bedId;  //床位id
    private Integer roomNo;
    private Integer customerId;
    private Integer customerSex;  //顾客性别
    private String customerName;  //顾客姓名
}
