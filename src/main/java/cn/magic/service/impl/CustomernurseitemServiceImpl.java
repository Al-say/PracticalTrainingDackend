package cn.magic.service.impl;

import cn.magic.dto.CustomerNurseItemDTO;
import cn.magic.entity.Customer;
import cn.magic.entity.Customernurseitem;
import cn.magic.mapper.CustomerMapper;
import cn.magic.mapper.CustomernurseitemMapper;
import cn.magic.service.CustomernurseitemService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerNurseItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomernurseitemServiceImpl extends ServiceImpl<CustomernurseitemMapper, Customernurseitem>
        implements CustomernurseitemService {
    @Autowired
    private CustomernurseitemMapper customernurseitemMapper; //客户护理设置
    @Autowired
    private CustomerMapper customerMapper; //客户
    //添加客户护理项目
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<String> addItemToCustomer(List<Customernurseitem> customernurseitems) throws Exception {
        if (customernurseitems.size() > 0) {
            // 验证每个护理项目都有有效的客户ID
            for (Customernurseitem item : customernurseitems) {
                if (item.getCustomerId() == null) {
                    return ResultVo.fail("客户ID不能为空，请确保已选择客户");
                }
                // 验证客户是否存在
                Customer customer = customerMapper.selectById(item.getCustomerId());
                if (customer == null) {
                    return ResultVo.fail("客户ID " + item.getCustomerId() + " 不存在");
                }
            }
            // 判断是选择级别添加级别中的护理项目,还是单独购买护理项目
            if (customernurseitems.get(0).getLevelId() != null) { //级别附带项目
                Integer customerId = customernurseitems.get(0).getCustomerId();
                Integer levelId = customernurseitems.get(0).getLevelId();
                //设置客户护理级别
                Customer customer = new Customer();
                customer.setId(customerId);
                customer.setLevelId(levelId);
                int row1 = customerMapper.updateById(customer);
                if (row1 <= 0) {
                    throw new Exception("更新客户护理级别失败，客户ID: " + customerId);
                }
                //批量给用户添加护理项目
                boolean temp = saveBatch(customernurseitems);
                if (!temp) {
                    throw new Exception("添加护理项目失败");
                }
            } else { //单独购买护理项目
                saveBatch(customernurseitems);
            }
            return ResultVo.ok("护理项目配置成功");
        }
        return ResultVo.fail("请选择需要添加的护理项目");
    }
    //删除客户护理项目
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<String> removeCustomerLevelAndItem(Integer levelId, Integer customerId) throws Exception {
        //更新客户级别为null
        UpdateWrapper<Customer> uw1 = new UpdateWrapper<>();
        uw1.set("level_id", null);
        uw1.eq("id", customerId);
        int row1 = customerMapper.update(null, uw1);

        //删除客户当前级别的所有护理项目
//        UpdateWrapper uw2 = new UpdateWrapper();
//        uw2.set("is_deleted", 1);
//        uw2.eq("level_id", levelId);
//        uw2.eq("customer_id", customerId); // 修正字段名: custormer_id -> customer_id
//        int row2 = customernurseitemMapper.update(null, uw2);
        QueryWrapper<Customernurseitem> uw2 = new QueryWrapper<>();
        uw2.eq("level_id", levelId).eq("customer_id", customerId);
        int row2 = customernurseitemMapper.delete(uw2);
        if (!(row1 > 0 && row2 > 0)) {
            throw new Exception("护理项目配置失败");
        }
        return ResultVo.ok("移除成功");
    }
    //查询客户护理项目
    @Override
    public ResultVo<Page<CustomerNurseItemVo>> listCustomerItem(CustomerNurseItemDTO customerNurseItemDTO) throws Exception {
        if (customerNurseItemDTO.getPageSize() == null) {
            customerNurseItemDTO.setPageSize(1);
        }
        Page<CustomerNurseItemVo> page = new Page<>(customerNurseItemDTO.getPageSize(), 6);
        customernurseitemMapper.selectCustomerItemVo(page, customerNurseItemDTO.getCustomerId());
        return ResultVo.ok(page);
    }
}
