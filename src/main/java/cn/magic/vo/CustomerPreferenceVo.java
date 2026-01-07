package cn.magic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
//顾客偏好信息
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerPreferenceVo {
    private Integer id; // 主键
    private Integer customerId; //顾客ID
    private String preferences; //饮食喜好
    private String attention; //注意事项
    private String remark; //备注
    private Integer isDeleted; //逻辑删除的标记（0：显示；1：隐藏）
    private String customerName; //顾客姓名
    private Integer customerSex; //顾客性别
    private Integer customerAge; //顾客年龄
}
