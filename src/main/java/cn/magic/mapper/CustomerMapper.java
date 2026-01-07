package cn.magic.mapper;

import cn.magic.entity.Customer;
import cn.magic.vo.CustomerVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper extends BaseMapper<Customer> {
    //查询客户信息 参数 分页对象，客户名称，性别，系统健康管家ID
    Page<CustomerVo> selectPageVo(@Param("page") Page<CustomerVo> page,
                                  @Param("customerName") String customerName,
                                  @Param("manType") Integer manType,
                                  @Param("userId") Integer userId);
}
