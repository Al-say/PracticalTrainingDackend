package cn.magic.dto;

import lombok.Data;
//餐饮查询条件
@Data
public class MealDTO {
    private Integer mealId; // 食谱编号
    private String weekDay; // 星期
    //private Integer pageSize; // 页码
    private Integer mealType; // 餐饮类型（早餐/午餐/晚餐）
}
