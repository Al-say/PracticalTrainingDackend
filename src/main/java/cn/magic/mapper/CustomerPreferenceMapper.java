package cn.magic.mapper;

import cn.magic.entity.CustomerPreference;
import cn.magic.vo.CustomerPreferenceVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface CustomerPreferenceMapper extends BaseMapper<CustomerPreference> {
    Page<CustomerPreferenceVo> selectCustomerPreferenceVo(@Param("page") Page<CustomerPreferenceVo> page,
                                                          @Param("customerName") String customerName) throws Exception;
}
