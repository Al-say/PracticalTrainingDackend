package cn.magic.service.impl;

import cn.magic.dto.CustomerDTO;
import cn.magic.entity.Bed;
import cn.magic.entity.BedDetails;
import cn.magic.entity.Customer;
import cn.magic.mapper.BedDetailsMapper;
import cn.magic.mapper.BedMapper;
import cn.magic.mapper.CustomerMapper;
import cn.magic.service.CustomerService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private BedDetailsMapper bedDetailsMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BedMapper bedMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> addCustomer(Customer customer) throws Exception {
        //查询床位是否可用
        Bed bed = bedMapper.selectById(customer.getBedId());
        if (bed.getBedStatus() != 1) {
            return ResultVo.fail("该床位已有人");
        }
        customer.setIsDeleted(0); //新加客户默认生效
        customer.setUserId(-1); //新加客户默认无健康管家
        customer.setFilePath("@/assets/tou.png"); // 设置默认头像路径
        //生成客户信息
        int row1 = customerMapper.insert(customer);
        //生成入住详情信息
        BedDetails bedDetails = new BedDetails();
        bedDetails.setBedId(customer.getBedId());
        bedDetails.setStartDate(customer.getCheckinDate());
        bedDetails.setEndDate(customer.getExpirationDate());
        bedDetails.setBedDetails(customer.getBuildingNo() + "#" + bed.getBedNo());
        bedDetails.setCustomerId(customer.getId());
        bedDetails.setIsDeleted(0); //床位生效
        int row2 = bedDetailsMapper.insert(bedDetails);
        // 修改床位状态
        Bed bedUpdate = new Bed();
        bedUpdate.setId(customer.getBedId());
        bedUpdate.setBedStatus(2);
        int row3 = bedMapper.updateById(bedUpdate);
        if (!(row1 > 0 && row2 > 0 && row3 > 0)) {
            throw new Exception("入住失败");
        }
        return ResultVo.ok("入住成功");
    }

    @Override
    public ResultVo<Page<CustomerVo>> FindCustomer(CustomerDTO customerDTO) throws Exception {
        // 处理空值默认情况
        int current = customerDTO.getPageNum() == null ? 1 : customerDTO.getPageNum();
        int size = customerDTO.getPageSize() == null ? 6 : customerDTO.getPageSize();

        // Page构造函数第一个参数是 current(当前页)，第二个是 size(每页条数)
        Page<CustomerVo> page = new Page<>(current, size);
        customerMapper.selectPageVo(page, customerDTO.getCustomerName(), customerDTO.getManType(), customerDTO.getUserId());
        return ResultVo.ok(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> removeCustomer(Integer id, Integer bedId) throws Exception {
        //修改用户is_delete=1 不显示 成为历史老人
        Customer customer = new Customer();
        customer.setId(id);
        customer.setIsDeleted(1);
        int row1 = customerMapper.updateById(customer);
        //修改床位为空闲 - 1
        Bed bed = new Bed();
        bed.setId(bedId);
        bed.setBedStatus(1);
        int row2 = bedMapper.updateById(bed);

        //修改床位详细信息为 is_delete=1 不显示 （可能有多个床位信息 只修改is_delete为0的） 成为历史信息
        BedDetails bedDetails = new BedDetails();
        bedDetails.setIsDeleted(1);
        UpdateWrapper<BedDetails> uw = new UpdateWrapper<>();
        uw.eq("customer_id", id);
        uw.eq("bed_id", bedId);
        uw.eq("is_deleted", 0);
        int row3 = bedDetailsMapper.update(bedDetails, uw);
        if (!(row1 > 0 && row2 > 0 && row3 > 0)) {
            throw new Exception("删除失败");
        }
        return ResultVo.ok("删除成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> editCustomer(Customer customer) throws Exception {
        // 编辑客户信息
        int row1 = customerMapper.updateById(customer);
        // 如果合同到期时间发生改变，更新用户当前生效的床位信息的退住时间为改变的合同日期
        if (customer.getExpirationDate() != null) {
            UpdateWrapper<BedDetails> uw = new UpdateWrapper<>();
            uw.eq("customer_id", customer.getId());
            uw.eq("is_deleted", 0);
            BedDetails bedDetails = new BedDetails();
            bedDetails.setEndDate(customer.getExpirationDate());
            int row2 = bedDetailsMapper.update(bedDetails, uw);
            if (!(row1 > 0 && row2 > 0)) {
                throw new Exception("编辑失败");
            }
        }
        return ResultVo.ok("编辑成功");
    }
}
