package cn.magic.mapper;

import cn.magic.entity.Nurserecord;
import cn.magic.vo.NurseRecordsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface NurserecordMapper extends BaseMapper<Nurserecord> {
    /**
     * 查询护理记录
     * @param page   // 分页参数
     * @param customerId // 客户ID
     * @return // 护理记录
     * @throws Exception // 异常
     */
    Page<NurseRecordsVo> selectNurseRecordsVo(@Param("page") Page<NurseRecordsVo> page,
                                              @Param("customerId") Integer customerId) throws Exception;
}
