package cn.magic.service.impl;

import cn.magic.dto.BackdownDTO;
import cn.magic.entity.Backdown;
import cn.magic.mapper.BackdownMapper;
import cn.magic.service.BackdownService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BackdownVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
//退住实现类
@Service
public class BackdownServiceImpl extends ServiceImpl<BackdownMapper, Backdown> implements BackdownService {
    @Resource
    private BackdownMapper backdownMapper; //退住记录Mapper
    //退住记录
    @Override
    public ResultVo<Page<BackdownVo>> listBackdownVo(BackdownDTO backdownDTO) throws Exception {
        Page<BackdownVo> page = new Page<>(backdownDTO.getPageSize(), 6);
        backdownMapper.selectBackdownVo(page, backdownDTO.getUserId());
        return ResultVo.ok(page);
    }
    //审批退住
    @Override
    public ResultVo<String> examineBackdown(Backdown backdown) throws Exception {
        UpdateWrapper<Backdown> updateWrapper = new UpdateWrapper<Backdown>();
        updateWrapper.eq("id", backdown.getId());
        updateWrapper.set("auditstatus", backdown.getAuditstatus());
        updateWrapper.set("audittime", backdown.getAudittime());
        updateWrapper.set("auditperson", backdown.getAuditperson());
        backdownMapper.update(backdown, updateWrapper);
        return ResultVo.ok("审批成功");
    }
}
