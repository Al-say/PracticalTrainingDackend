package cn.magic.service.impl;

import cn.magic.entity.Bed;
import cn.magic.mapper.BedMapper;
import cn.magic.service.BedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

//床位服务实现类
@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed> implements BedService {
}
