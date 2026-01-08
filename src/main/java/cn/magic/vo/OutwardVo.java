package cn.magic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
//OutWard对象描述
@Data
@EqualsAndHashCode(callSuper = false)
public class OutwardVo  {
    private Integer id; // 主键
    private String remarks; // 备注
    private Integer isDeleted; // 逻辑删除标记（0：显示；1：隐藏）
    private Integer createUserId; // 创建人ID
    private String outgoingreason; // 外出事由
    private Date outgoingtime; // 外出时间
    private Date expectedreturntime; // 预计回院时间
    private Date actualreturntime; // 实际回院时间
    private String escorted; // 陪同人
    private String relation; // 与老人关系
    private String escortedtel; // 陪同人电话
    private Integer auditstatus; // 审批状态 0-已提交 1-同意 2-拒绝
    private String auditperson; // 审批人
    private Date audittime; // 审批时间
    private String nickname; // 护理人姓名
    private String phoneNumber; // 护理人电话
    private String serialNumber; // 护理项目编号
    private String nursingName; // 护理项目名称
    private String customerName; // 客户姓名
}
