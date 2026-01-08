package cn.magic.service;

import cn.magic.entity.Nursecontent;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NursecontentService extends IService<Nursecontent> {
    //通过级别ID获取护理项目
    ResultVo<List<Nursecontent>> listNurseItemByLevel(Integer levelId) throws Exception;
    //修改护理项目
    ResultVo<String> updateNurseItem(Nursecontent nursecontent) throws Exception;
    //删除护理项目
    ResultVo<String> delNurseItem(Integer id) throws Exception;
}
