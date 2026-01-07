package cn.magic.service;

import cn.magic.dto.BedDetailsDTO;
import cn.magic.dto.ExchangeDTO;
import cn.magic.entity.BedDetails;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedDetailsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BedDetailsService extends IService<BedDetails> {
    //查询床位详情
    ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception;
    //床位详情交换
    ResultVo<String> exchangeBed(ExchangeDTO exchangeDTO) throws Exception;
}
