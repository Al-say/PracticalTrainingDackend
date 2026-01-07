package cn.magic.service.impl;

import cn.magic.dto.MealDTO;
import cn.magic.entity.Meal;
import cn.magic.mapper.MealMapper;
import cn.magic.service.MealService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.MealVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements MealService {
    @Resource
    private MealMapper mealMapper;

    @Override
    public ResultVo<List<MealVo>> listMealVoPage(MealDTO mealDTO) throws Exception {
        List<MealVo> list = mealMapper.selectMealVo(mealDTO.getWeekDay(), mealDTO.getMealType());
        return ResultVo.ok(list);
    }
}
