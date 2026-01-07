package cn.magic.vo;

import lombok.Data;
//套餐信息视图
@Data
public class MealVo {
    private Integer id; // 主键ID
    private String weekDay; // 星期几
    private Integer foodId; //食品ID
    private String foodType; //食品类型
    private String foodName; //食品名称
    private Double price; // 价格
    private Integer isHalal; //是否清真
    private String foodImg; //图片路径
    private Integer mealType; //食品类型（1.早餐、2.午餐、3.晚餐）
    private String taste; //口味（多糖、多盐、少糖、少盐）
    private Integer isDeleted; //逻辑删除标记（0：显示；1：隐藏）
}
