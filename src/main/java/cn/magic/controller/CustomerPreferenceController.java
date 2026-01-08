package cn.magic.controller;

import cn.magic.dto.CustomerPreferenceDTO;
import cn.magic.entity.Customer;
import cn.magic.entity.CustomerPreference;
import cn.magic.service.CustomerPreferenceService;
import cn.magic.service.CustomerService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerPreferenceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customerpreference")
public class CustomerPreferenceController {
    @Resource
    private CustomerPreferenceService customerPreferenceService;
    @Resource
    private CustomerService customerService;

    //为顾客单个添加喜好
    @PostMapping("/addCustomerperference")
    public ResultVo<String> addCustomerperference(@RequestBody CustomerPreference customerPerference) {
        customerPreferenceService.save(customerPerference);
        return ResultVo.ok("添加顾客喜好");
    }
    //更新顾客喜好
    @PostMapping("/updateCustomerpreference")
    public ResultVo<String> updateCustomerpreference(@RequestBody CustomerPreference customerPerference) {
        customerPreferenceService.updateById(customerPerference);
        return ResultVo.ok("更新顾客喜好");
    }
    //删除顾客喜好
    @DeleteMapping("/removeCustomerpreference/{id}")
    public ResultVo<String> removeCustomerpreference(@PathVariable("id") Integer id) {
        customerPreferenceService.removeById(id);
        return ResultVo.ok("删除顾客喜好");
    }

    //顾客喜好查询（分页）/可以根据顾客姓名查询
    @GetMapping("/listCustomerpreferencePage")
    public ResultVo<Page<CustomerPreferenceVo>> listCustomerpreferencePage(CustomerPreferenceDTO customerPreferenceDTO) throws Exception {
        return customerPreferenceService.listCustomerPreferenceVoPage(customerPreferenceDTO);
    }
    
    @GetMapping("/customerList")
    public ResultVo<List<Customer>> customerList() throws Exception {
        List<Customer> list = customerService.list();
        if (list != null) {
            return ResultVo.ok(list);
        } else {
            return ResultVo.fail("查询失败");
        }
    }
}
