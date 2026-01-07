package cn.magic.mapper;

import cn.magic.entity.Backdown;
import cn.magic.vo.BackdownVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface BackdownMapper extends BaseMapper<Backdown> {
    Page<BackdownVo> selectBackdownVo(@Param("page") Page<BackdownVo> page,
                                      @Param("userId") Integer userId);

}
