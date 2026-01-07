package cn.magic.service;

import cn.magic.dto.CustomerDTO;
import cn.magic.entity.Customer;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomerService extends IService<Customer> {
    //添加客户
    ResultVo<String> addCustomer(Customer customer) throws Exception;
    // 客户信息查询
    ResultVo<Page<CustomerVo>> FindCustomer(CustomerDTO customerDTO) throws Exception;
    //客户信息删除
    ResultVo<String> removeCustomer(Integer id, Integer bedId) throws Exception;
    //修改客户信息
    ResultVo<String> editCustomer(Customer customer) throws Exception;
}
