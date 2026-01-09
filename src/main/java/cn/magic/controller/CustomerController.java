package cn.magic.controller;

import cn.magic.dto.CustomerDTO;
import cn.magic.entity.Customer;
import cn.magic.service.CustomerService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//客户管理控制类
@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //入住登记
    @PostMapping("/addCustomer")
    public ResultVo<String> addCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.addCustomer(customer);
    }
    //客户信息动态查询（分页）
    @GetMapping("/listPage")
    public ResultVo<Page<CustomerVo>> listPage(CustomerDTO customerDTO) throws Exception {
        return customerService.FindCustomer(customerDTO);
    }
    //删除客户信息http://localhost:10086/customer/delCustomer/44
    @DeleteMapping("/removeCustomer/{id}")
    public ResultVo<String> removeCustomer(@PathVariable("id") Integer id) throws Exception {
        Customer customer = new Customer();
        customer.setIsDeleted(1);
        customer.setId(id);
        customerService.updateById(customer);
        return ResultVo.ok("删除成功");
    }
    //编辑客户信息
    @PostMapping("/editCustomer")
    public ResultVo<String> editCustomer(@RequestBody Customer customer) throws Exception {
        customerService.updateById(customer);
        return ResultVo.ok("编辑成功");
    }
    @GetMapping("/getCustomerList")
    public List<Customer> getCstmList() throws Exception {
        return customerService.list();
    }
    // 【新增】查询所有客户列表（用于下拉框选择）
    @GetMapping("/customerList")
    public ResultVo<List<Customer>> customerList() throws Exception {
        List<Customer> list = customerService.list();
        return ResultVo.ok(list);
    }
}
