package cn.magic.mapper;

import cn.magic.entity.Outward;
import cn.magic.vo.OutwardVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface OutwardMapper extends BaseMapper<Outward> {
    Page<OutwardVo> selectOutwardVo(@Param("page")Page<OutwardVo> page, @Param("userId") Integer userId);
}
