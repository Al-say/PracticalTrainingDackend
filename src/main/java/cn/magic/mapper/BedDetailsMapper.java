package cn.magic.mapper;

import cn.magic.dto.BedDetailsDTO;
import cn.magic.entity.BedDetails;
import cn.magic.vo.BedDetailsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface BedDetailsMapper extends BaseMapper<BedDetails> {
    //查询床位详情
    Page<BedDetailsVo> selectBedDetailsVo(@Param("page") Page<BedDetailsVo> page,
                                          @Param("detailsDTO") BedDetailsDTO detailsDTO);

}
