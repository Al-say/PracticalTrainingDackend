package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
// 护理记录表
@Data
@TableName("nurserecord")
public class Nurserecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Integer customerId; // 客户Id
    private Integer itemId; //护理项目Id
    private Date nursingTime; //护理时间
    private String nursingContent; //护理内容
    private Integer nursingCount; //护理数量
    private Integer userId; //护理人员Id
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
}
