package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//出院记录
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("outward")
public class Outward {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;  // 主键
    private Integer isDeleted; // 逻辑删除标记（0：显示，1：隐藏）
    private Integer customerId; // 客户Id
    private String outgoingreason; // 外出事由
    private Date outgoingtime; // 外出时间
    private Date expectedreturntime; // 预计回院时间
    private Date actualreturntime; // 实际回院时间
    private String escorted; // 陪同人
    private String relation; // 与老人关系
    private String escortedtel; // 陪同人电话
    private Integer auditstatus; // 审批状态 0-已提交 1-同意 2-拒绝
    private String auditperson; // 审批人
    private String remarks; // 备注
    private Date audittime; // 审批时间
}
