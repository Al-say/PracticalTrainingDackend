package cn.magic.service.impl;

import cn.magic.entity.Food;
import cn.magic.mapper.FoodMapper;
import cn.magic.service.FoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
}
