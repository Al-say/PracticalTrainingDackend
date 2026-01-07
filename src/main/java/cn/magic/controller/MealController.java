package cn.magic.controller;

import cn.magic.dto.MealDTO;
import cn.magic.entity.Meal;
import cn.magic.service.MealService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.MealVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//餐食信息控制层
@RestController
@RequestMapping("/meal")
public class MealController {
    @Resource
    private MealService mealService;

    //添加膳食
    @PostMapping("/addMeal")
    public ResultVo<String> addMeal(@RequestBody Meal meal) throws Exception {
        mealService.save(meal);
        return ResultVo.ok("添加膳食成功！！！");
    }
    //更新膳食
    @PostMapping("/updateMeal")
    public ResultVo<String> updateMeal(@RequestBody Meal meal) throws Exception {
        mealService.updateById(meal);
        return ResultVo.ok("更新膳食成功！！！");
    }
    //删除膳食
    @DeleteMapping("/removeMeal/{id}")
    public ResultVo<String> removeMeal(@PathVariable("id") Integer id) throws Exception {
        mealService.removeById(id);
        return ResultVo.ok("删除膳食成功！！！");
    }
    //膳食查询（分页）/可以根据星期查询，根据膳食类型（早餐/午餐/晚餐）
    @PostMapping("/listMealPage")
    public ResultVo<List<MealVo>> listMealPage(@RequestBody MealDTO mealDTO) throws Exception {
        return mealService.listMealVoPage(mealDTO);
    }
}
