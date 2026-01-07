package cn.magic.service.impl;

import cn.magic.dto.BedDetailsDTO;
import cn.magic.dto.ExchangeDTO;
import cn.magic.entity.Bed;
import cn.magic.entity.BedDetails;
import cn.magic.entity.Customer;
import cn.magic.mapper.BedDetailsMapper;
import cn.magic.mapper.BedMapper;
import cn.magic.mapper.CustomerMapper;
import cn.magic.service.BedDetailsService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedDetailsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BedDetailsServiceImpl extends ServiceImpl<BedDetailsMapper, BedDetails>
        implements BedDetailsService {

    @Autowired
    private BedDetailsMapper bedDetailsMapper;
    @Autowired
    private BedMapper bedMapper;
    @Autowired
    private CustomerMapper customerMapper;
    //查询床位详情
    @Override
    public ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception {
        Page<BedDetailsVo> page = new Page<>(bedDetailsDTO.getPageSize(), 8);
        bedDetailsMapper.selectBedDetailsVo(page, bedDetailsDTO);
        return ResultVo.ok(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> exchangeBed(ExchangeDTO exchangeDTO) throws Exception {
        //查询床位是否可用
        Bed bed = bedMapper.selectById(exchangeDTO.getNewBedId());
        //判断床位是否可用
        if (bed.getBedStatus() != 1) {
            return ResultVo.fail("该床位已有人");
        }
        //1修改客户旧床位详情信息：is_deleted为1 床位使用结束时间为当前日期
        BedDetails beddetails = new BedDetails();
        beddetails.setId(exchangeDTO.getId());
        beddetails.setIsDeleted(1);
        beddetails.setEndDate(new Date());//结束时间为当前日期
        int row1 = bedDetailsMapper.updateById(beddetails);
        //2添加新床位记录的记录
        BedDetails newBeddetails = new BedDetails();
        newBeddetails.setIsDeleted(0);
        newBeddetails.setCustomerId(exchangeDTO.getCustomerId());
        newBeddetails.setBedId(exchangeDTO.getNewBedId());
        newBeddetails.setEndDate(exchangeDTO.getEndDate());
        newBeddetails.setStartDate(new Date());//开始时间为当前日期
        newBeddetails.setBedDetails(exchangeDTO.getBuildingNo() + "#" + bed.getBedNo());
        int row2 = bedDetailsMapper.insert(newBeddetails);
        //3修改旧床位的状态为空闲 bed_status=1
        Bed oldBed = new Bed();
        oldBed.setId(exchangeDTO.getOldBedId());
        oldBed.setBedStatus(1);
        int row3 = bedMapper.updateById(oldBed);
        //4修改新床位的状态为有人 bed_status=2
        Bed newBed = new Bed();
        newBed.setId(exchangeDTO.getNewBedId());
        newBed.setBedStatus(2);
        int row4 = bedMapper.updateById(newBed);
        //5修改客户信息 - 新房间号 新床位号 楼号
        Customer customer = new Customer();
        customer.setId(exchangeDTO.getCustomerId());
        customer.setBedId(exchangeDTO.getNewBedId());
        customer.setBuildingNo(exchangeDTO.getBuildingNo());
        customer.setRoomNo(exchangeDTO.getNewRoomNo());
        int row5 = customerMapper.updateById(customer);
        if (!(row1 > 0 && row2 > 0 && row3 > 0 && row4 > 0 && row5 > 0)) {
            throw new Exception("床位调换失败");  // 修正为正确的错误消息
        }
        return ResultVo.ok("床位调换成功");  // 修正为正确的成功消息
    }

}
