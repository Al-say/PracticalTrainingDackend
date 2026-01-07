package cn.magic.service.impl;

import cn.magic.dto.CustomerPreferenceDTO;
import cn.magic.entity.CustomerPreference;
import cn.magic.mapper.CustomerPreferenceMapper;
import cn.magic.service.CustomerPreferenceService;

import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerPreferenceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//膳食管理实现类
@Service
public class CustomerPreferenceServiceImpl
        extends ServiceImpl<CustomerPreferenceMapper, CustomerPreference>
        implements CustomerPreferenceService {

    @Autowired
    private CustomerPreferenceMapper customerPreferenceMapper;
    @Override
    public ResultVo<Page<CustomerPreferenceVo>> listCustomerPreferenceVoPage(CustomerPreferenceDTO dto)
            throws Exception {
        //创建分页对象
        Page<CustomerPreferenceVo> page = new Page<>(dto.getCurPage(),dto.getPageSize());
        customerPreferenceMapper.selectCustomerPreferenceVo(page, dto.getCustomerName());
        return ResultVo.ok(page);
    }
}
