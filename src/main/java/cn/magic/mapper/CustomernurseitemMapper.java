package cn.magic.mapper;

import cn.magic.entity.Customernurseitem;
import cn.magic.vo.CustomerNurseItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface CustomernurseitemMapper extends BaseMapper<Customernurseitem> {
    Page<CustomerNurseItemVo> selectCustomerItemVo(@Param("page") Page<CustomerNurseItemVo> page,
                                                   @Param("customerId") Integer customerId) throws Exception;

}
