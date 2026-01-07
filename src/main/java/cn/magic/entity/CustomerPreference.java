package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

//膳食管理表对象
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("customerpreference")
public class CustomerPreference {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private Integer customerId; // 顾客Id
    private String preferences; // 饮食偏好
    private String attention; // 注意事项
    private String remark; // 备注
    private Integer isDeleted; // 逻辑删除标记（0：显示，1：隐藏）
}
