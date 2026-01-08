package cn.magic.service;

import cn.magic.dto.OutwardDTO;
import cn.magic.entity.Outward;
import cn.magic.utils.ResultVo;
import cn.magic.vo.OutwardVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OutwardService extends IService<Outward> {
    //审批外出
    ResultVo<String> examineOutward(Outward outward) throws Exception;
    //查询外出信息
    ResultVo<Page<OutwardVo>> queryOutwardVo(OutwardDTO outwardDTO) throws Exception;
}
