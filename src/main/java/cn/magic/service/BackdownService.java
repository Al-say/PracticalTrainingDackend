package cn.magic.service;

import cn.magic.dto.BackdownDTO;
import cn.magic.entity.Backdown;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BackdownVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
//退住记录Service
public interface BackdownService extends IService<Backdown> {
    //退住记录列表
    ResultVo<Page<BackdownVo>> listBackdownVo(BackdownDTO backdownDTO) throws Exception;
    //审批退住记录
    ResultVo<String> examineBackdown(Backdown backdown) throws Exception;
}
