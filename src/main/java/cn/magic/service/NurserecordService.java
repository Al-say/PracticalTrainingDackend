package cn.magic.service;

import cn.magic.dto.NurseRecordDTO;
import cn.magic.entity.Nurserecord;
import cn.magic.utils.ResultVo;
import cn.magic.vo.NurseRecordsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface NurserecordService extends IService<Nurserecord> {
    //添加护理记录
    ResultVo<String> addNurseRecord(Nurserecord nurserecord) throws Exception;
    //查询护理记录
    ResultVo<Page<NurseRecordsVo>> queryNurseRecordsVo(NurseRecordDTO nurseRecordDTO) throws Exception;
}
