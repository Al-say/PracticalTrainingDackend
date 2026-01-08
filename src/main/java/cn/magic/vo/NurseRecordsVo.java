package cn.magic.vo;

import lombok.Data;

import java.util.Date;
// 护理记录视图
@Data
public class NurseRecordsVo {
    private Integer id; // 主键
    private Integer isDeleted; // 逻辑删除标记（0：显示；1：隐藏）
    private Integer customerId; // 客户ID
    private Integer itemId; // 护理项目编号
    private Date nursingTime;   // 护理时间
    private String nursingContent; //护理内容
    private Integer nursingCount; // 护理次数
    private String userId; // 护理人
    private String nickname; // 护理人姓名
    private String phoneNumber; // 护理人电话
    private String serialNumber; // 护理项目编号
    private String nursingName; // 护理项目名称
}
