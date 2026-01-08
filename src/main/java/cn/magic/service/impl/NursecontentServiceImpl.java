package cn.magic.service.impl;

import cn.magic.entity.Nursecontent;
import cn.magic.entity.Nurselevelitem;
import cn.magic.mapper.NursecontentMapper;
import cn.magic.mapper.NurselevelitemMapper;
import cn.magic.service.NursecontentService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NursecontentServiceImpl extends ServiceImpl<NursecontentMapper, Nursecontent>
        implements NursecontentService {
    @Autowired
    private NursecontentMapper nursecontentMapper;
    @Autowired
    private NurselevelitemMapper nurselevelitemMapper;
    // 查询护理级别项目列表
    @Override
    public ResultVo<List<Nursecontent>> listNurseItemByLevel(Integer levelId) throws Exception {
        // 先查询级别的项目配置-只查询item_id
        QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
        qw.eq("level_id", levelId);
        qw.select("item_id"); // 只查询item_id
        List<Object> objs = nurselevelitemMapper.selectObjs(qw);
        List<Integer> itemIds = objs.stream().map(obj -> (Integer) obj).collect(java.util.stream.Collectors.toList());
        List<Nursecontent> nursecontents = new ArrayList<>();
        // 判断是否有记录
        if (itemIds.size() > 0) {
            // 查询护理项目信息
            nursecontents = nursecontentMapper.selectBatchIds(itemIds);
        }
        return ResultVo.ok(nursecontents);
    }
    // 更新护理项目
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<String> updateNurseItem(Nursecontent nursecontent) throws Exception {
        // 如果修改状态为--停用，需要直接剔除护理级别护理项目列表中的对应的记录，保证列表中的项目都是可用状态
        if (nursecontent.getStatus() == 2) {
            // 查询当前护理项目是否在护理级别护理项目列表中，如果在就需要进行剔除
            QueryWrapper<Nurselevelitem> qwCount = new QueryWrapper<>();
            qwCount.eq("item_id", nursecontent.getId());
            long count = nurselevelitemMapper.selectCount(qwCount);
            if (count > 0) {
                QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
                qw.eq("item_id", nursecontent.getId());
                int row = nurselevelitemMapper.delete(qw);
                // 更新护理项目
                boolean temp = updateById(nursecontent);
                if (!(temp && row > 0)) {
                    throw new Exception("更新失败");
                }
                return ResultVo.ok("更新成功");
            }
        }
        // 更新护理项目
        updateById(nursecontent);
        return ResultVo.ok("更新成功");
    }
    // 删除护理项目
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<String> delNurseItem(Integer id) throws Exception {
        Nursecontent nursecontent = new Nursecontent();
        nursecontent.setIsDeleted(1);
        nursecontent.setId(id);
        // 查询当前护理项目是否在护理级别护理项目列表中，如果在就需要进行剔除
        QueryWrapper<Nurselevelitem> qwCount = new QueryWrapper<>();
        qwCount.eq("item_id", id);
        long count = nurselevelitemMapper.selectCount(qwCount);
        if (count > 0) {
            QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
            qw.eq("item_id", id);
            int row = nurselevelitemMapper.delete(qw);
            // 更新逻辑删除标志为"1"隐藏
            boolean temp = updateById(nursecontent);
            if (!(temp && row > 0)) {
                throw new Exception("更新失败");
            }
            return ResultVo.ok("更新成功");
        }
        // 更新逻辑删除标志为"1"隐藏
        updateById(nursecontent);
        return ResultVo.ok("更新成功");
    }
}
