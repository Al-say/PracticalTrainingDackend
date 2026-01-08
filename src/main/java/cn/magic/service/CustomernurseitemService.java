package cn.magic.service;

import cn.magic.dto.CustomerNurseItemDTO;
import cn.magic.entity.Customernurseitem;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerNurseItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CustomernurseitemService extends IService<Customernurseitem> {
    //添加客户护理项目
    ResultVo<String> addItemToCustomer(List<Customernurseitem> customernurseitems) throws Exception;
    //删除客户护理项目
    ResultVo<String> removeCustomerLevelAndItem(Integer levelId, Integer customerId) throws Exception;
    //查询客户护理项目
    ResultVo<Page<CustomerNurseItemVo>> listCustomerItem(CustomerNurseItemDTO customerNurseItemDTO) throws Exception;
}
