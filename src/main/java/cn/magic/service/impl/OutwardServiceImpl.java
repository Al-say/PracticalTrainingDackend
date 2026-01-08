package cn.magic.service.impl;

import cn.magic.dto.OutwardDTO;
import cn.magic.entity.Outward;
import cn.magic.mapper.OutwardMapper;
import cn.magic.service.OutwardService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.OutwardVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OutwardServiceImpl extends ServiceImpl<OutwardMapper, Outward> implements OutwardService {
    @Resource
    private OutwardMapper outwardMapper;

    //护工申请
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> examineOutward(Outward outward) throws Exception {
        // 检查审批状态是否为null
        if (outward.getAuditstatus() == null) {
            return ResultVo.fail("审批状态不能为空");
        }
        // 创建只包含需要更新字段的对象
        Outward updateOutward = new Outward();
        updateOutward.setId(outward.getId());
        updateOutward.setAuditstatus(outward.getAuditstatus());
        updateOutward.setAuditperson(outward.getAuditperson());
        updateOutward.setAudittime(outward.getAudittime());

        // 使用UpdateWrapper指定更新条件
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<Outward>();
        updateWrapper.eq("id", outward.getId());
        // 执行更新操作
        boolean success = update(updateOutward, updateWrapper);
        if (success) {
            return ResultVo.ok("审批成功");
        } else {
            return ResultVo.fail("审批失败，请稍后重试");
        }
    }

    //根据客户找护工
    @Override
    public ResultVo<Page<OutwardVo>> queryOutwardVo(OutwardDTO outwardDTO) throws Exception {
        // 处理空值默认情况
        int current = outwardDTO.getPageNum() == null ? 1 : outwardDTO.getPageNum();
        int size = outwardDTO.getPageSize() == null ? 6 : outwardDTO.getPageSize();

        // Page构造函数第一个参数是 current(当前页)，第二个是 size(每页条数)
        Page<OutwardVo> page = new Page<>(current, size);
        outwardMapper.selectOutwardVo(page, outwardDTO.getUserId());
        return ResultVo.ok(page);
    }
}
