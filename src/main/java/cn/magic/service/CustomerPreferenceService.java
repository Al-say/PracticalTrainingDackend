package cn.magic.service;

import cn.magic.dto.CustomerPreferenceDTO;
import cn.magic.entity.CustomerPreference;
import cn.magic.utils.ResultVo;
import cn.magic.vo.CustomerPreferenceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomerPreferenceService extends IService<CustomerPreference> {
    ResultVo<Page<CustomerPreferenceVo>> listCustomerPreferenceVoPage(CustomerPreferenceDTO customerPreferenceDTO) throws Exception;
}
