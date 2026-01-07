package cn.magic.service;

import cn.magic.dto.MealDTO;
import cn.magic.entity.Meal;
import cn.magic.utils.ResultVo;
import cn.magic.vo.MealVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MealService extends IService<Meal> {
    ResultVo<List<MealVo>> listMealVoPage(MealDTO mealDTO) throws Exception;
}
