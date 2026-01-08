package cn.magic.service.impl;

import cn.magic.dto.NurseRecordDTO;
import cn.magic.entity.Customernurseitem;
import cn.magic.entity.Nurserecord;
import cn.magic.mapper.CustomernurseitemMapper;
import cn.magic.mapper.NurserecordMapper;
import cn.magic.service.NurserecordService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.NurseRecordsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NurserecordServiceImpl extends ServiceImpl<NurserecordMapper, Nurserecord> 
        implements NurserecordService {
    @Autowired
    private CustomernurseitemMapper customernurseitemMapper; // 用户护理项目
    @Autowired 
    private NurserecordMapper nurserecordMapper; //护理记录
    // 添加护理记录
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<String> addNurseRecord(Nurserecord nurserecord) throws Exception {
        //生成护理记录
        nurserecord.setIsDeleted(0);//默认生效
        boolean temp = save(nurserecord);
        //查询当前用户的护理项目余量
        QueryWrapper<Customernurseitem> qw = new QueryWrapper<>();
        qw.eq("customer_id", nurserecord.getCustomerId());
        qw.eq("item_id", nurserecord.getItemId());
        qw.eq("is_deleted", 0);
        Customernurseitem customernurseitem = customernurseitemMapper.selectOne(qw);
        //修改用户护理项目数量
        UpdateWrapper<Customernurseitem> uw = new UpdateWrapper<>();
        uw.set("nurse_number", customernurseitem.getNurseNumber() - nurserecord.getNursingCount()); //更新护士数量为原数量减去当前护理次数
        uw.eq("customer_id", nurserecord.getCustomerId());
        uw.eq("item_id", nurserecord.getItemId());
        uw.eq("is_deleted", 0);
        int row = customernurseitemMapper.update(null, uw);
        if (!(temp && row > 0)) {
            throw new Exception("护理记录生成失败");
        }
        return ResultVo.ok("护理记录生成成功");
    }
    // 查询护理记录
    @Override
    public ResultVo<Page<NurseRecordsVo>> queryNurseRecordsVo(NurseRecordDTO nurseRecordDTO) throws Exception {
        Page<NurseRecordsVo> page = new Page<>(nurseRecordDTO.getPageSize(), 6);
        nurserecordMapper.selectNurseRecordsVo(page, nurseRecordDTO.getCustomerId());
        return ResultVo.ok(page);
    }
}
