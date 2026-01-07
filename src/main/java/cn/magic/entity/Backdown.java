package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

// 退住记录
@Data
@TableName("backdown")
public class Backdown {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Integer isDeleted; // 逻辑删除标记（0：显示，1：隐藏）
    private Integer customerId; // 客户Id
    private String remarks; // 备注
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date retreattime; // 退住时间
    private Integer retreattype; // 退住类型 0-正常退住 1-死亡退住 2-保留床位
    private String retreatreason; // 退住原因
    private Integer auditstatus; // 审批状态 0-已提交 1-同意 2-拒绝
    private String auditperson; // 审批人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date audittime; // 审批时间
}
