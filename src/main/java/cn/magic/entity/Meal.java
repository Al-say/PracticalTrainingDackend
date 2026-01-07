package cn.magic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//套餐信息
@Data
@TableName("meal")
public class Meal {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; // 主键
    private String weekDay; // 星期几
    private Integer foodId; //食品ID
    private Integer mealType; //食品类型（1.早餐、2.午餐、3.晚餐）
    private String taste; // 口味（多糖、多盐、少糖、少盐）
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
}
