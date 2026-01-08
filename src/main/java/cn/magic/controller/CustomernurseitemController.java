package cn.magic.controller;

import cn.magic.dto.CustomerNurseItemDTO;
import cn.magic.entity.Customernurseitem;
import cn.magic.service.CustomernurseitemService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerNurseItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customernurseitem")
public class CustomernurseitemController {
    @Autowired
    private CustomernurseitemService customernurseitemService;
    //为客户添加护理项目
    @PostMapping("/addItemToCustomer")
    public ResultVo<String> addItemToCustomer(@RequestBody List<Customernurseitem> customernurseitems) throws Exception {
        return customernurseitemService.addItemToCustomer(customernurseitems);
    }

    /**
     * 移除客户护理级别级联移除用户当前级别的护理项目
     * @param levelId 护理级别编号
     * @param customerId 用户编号
     * @return
     * @throws Exception
     */
    @DeleteMapping("/removeCustomerLevelAndItem/{levelId}/{customerId}")
    public ResultVo<String> removeCustomerLevelAndItem(@PathVariable("levelId") Integer levelId,
                                               @PathVariable("customerId") Integer customerId) throws Exception {
        return customernurseitemService.removeCustomerLevelAndItem(levelId, customerId);
    }
    //查询某个客户的护理项目列表-分页
    @GetMapping("/listCustomerItem")
    public ResultVo<Page<CustomerNurseItemVo>> listCustomerItem(CustomerNurseItemDTO customerNurseItemDTO) throws Exception {
        return customernurseitemService.listCustomerItem(customerNurseItemDTO);
    }
    //续费
    @PostMapping("/enewNurseItem")
    public ResultVo<String> enewNurseItem(@RequestBody Customernurseitem customernurseitem) throws Exception {
        customernurseitemService.updateById(customernurseitem);
        return ResultVo.ok("续费成功");
    }
    /**
     * 查询用户是否已经配置了某个指定项目
     * @param customerId  用户编号
     * @param itemId 护理项目编号
     * @return
     * @throws Exception
     */
    @GetMapping("/isIncludesItemCustomer")
    public ResultVo<String> isIncludesItemCustomer(Integer customerId, Integer itemId) throws Exception {
        QueryWrapper<Customernurseitem> qw = new QueryWrapper<>();
        qw.eq("item_id", itemId);
        qw.eq("customer_id", customerId);
        qw.eq("is_deleted", 0);
        long row = customernurseitemService.count(qw);
        if (row > 0) {
            return ResultVo.fail("当前用户已存在该项目");
        }
        return ResultVo.ok("");
    }
    //移除客户护理项目
    @DeleteMapping("/removeCustomerItem/{id}")
    public ResultVo<String> removeCustomerItem(@PathVariable("id") Integer id) throws Exception {
        Customernurseitem customernurseitem = new Customernurseitem();
        customernurseitem.setIsDeleted(1);
        customernurseitem.setId(id);
        customernurseitemService.updateById(customernurseitem);
        return ResultVo.ok("移除成功");
    }
}
