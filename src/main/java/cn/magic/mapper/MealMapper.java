package cn.magic.mapper;

import cn.magic.entity.Meal;
import cn.magic.vo.MealVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MealMapper extends BaseMapper<Meal> {
    /**
     * 查询用户所选的日期的餐食信息
     * @param page  分页
     * @param weekDay 星期几
     * @param mealType 食品类型（1.早餐、2.午餐、3.晚餐）
     * @return 餐食信息
     * @throws Exception
     */
    List<MealVo> selectMealVo(@Param("week_day") String weekDay,
                              @Param("meal_type") Integer mealType) throws Exception;
}
