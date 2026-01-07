package cn.magic.controller;

import cn.magic.entity.Food;
import cn.magic.service.FoodService;
import cn.magic.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//食物信息控制层
@RestController
@RequestMapping("/food")
public class FoodController {
    @Resource
    private FoodService foodService;
    //查询所有食品列表
    @GetMapping("/listFood")
    public List<Food> listFood() throws Exception {
        return foodService.list();
    }

    //增加食物
    @PostMapping("/addFood")
    public ResultVo<String> addFood(@RequestBody Food food) throws Exception {
        foodService.save(food);
        return ResultVo.ok("添加食物成功！！！");
    }

    //删除食物
    @DeleteMapping("/removeFood/{id}")
    public ResultVo<String> removeFood(@PathVariable("id") Integer id) throws Exception {
        foodService.removeById(id);
        return ResultVo.ok("删除食物成功！！！");
    }
    //修改食物
    @PostMapping("/updateFood")
    public ResultVo<String> updateFood(@RequestBody Food food) throws Exception {
        foodService.updateById(food);
        return ResultVo.ok("修改食物成功！！！");
    }
}
