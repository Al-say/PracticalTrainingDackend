package cn.magic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//退住记录 BedDetailsVo对象
@Data
@EqualsAndHashCode(callSuper = false)
public class BackdownVo {
    private Integer id; // 主键ID
    private String remarks; //备注
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
    private Integer customerId; //客户ID
    private Date retreattime; //退住时间
    private Date checkinDate; //入住时间
    private String customerName; //客户姓名
    private Integer retreattype; //退住类型 0-正常退住 1-死亡退住 2-保留床位
    private String retreatreason; //退住原因
    private Date audittime; //审批时间
    private String auditperson; //审批人
    private Integer auditstatus; //审批状态：0-已提交；1-同意；2-拒绝
    private Integer bedId; //床位id
    private String bedDetails; //床位信息
}
