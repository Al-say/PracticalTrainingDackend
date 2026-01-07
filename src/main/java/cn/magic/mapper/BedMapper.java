package cn.magic.mapper;

import cn.magic.entity.Bed;
import cn.magic.vo.BedVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BedMapper extends BaseMapper<Bed> {
    // 查询床位统计信息
    BedVo selectBedCount();
}
