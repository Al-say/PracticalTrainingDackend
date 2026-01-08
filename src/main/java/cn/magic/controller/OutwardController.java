package cn.magic.controller;


import cn.magic.dto.OutwardDTO;
import cn.magic.entity.Outward;
import cn.magic.service.OutwardService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.OutwardVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//外出管理
@RestController
@RequestMapping("/outward")
public class OutwardController {
    @Autowired
    private OutwardService outwardService;
    //查询外出记录
    @PostMapping("/queryOutwardVo")
    public ResultVo<Page<OutwardVo>> queryOutwardVo(@RequestBody OutwardDTO outwardDTO) throws Exception {
        return outwardService.queryOutwardVo(outwardDTO);
    }
    //添加外出申请
    @PostMapping("/addOutward")
    public ResultVo<String> addOutward(@RequestBody Outward outward) throws Exception {
        outwardService.save(outward);
        return ResultVo.ok("添加成功");
    }
    //审批外出申请
    @PostMapping("/examineOutward")
    public ResultVo<String> examineOutward(@RequestBody Outward outward) throws Exception {
        return outwardService.examineOutward(outward);
    }
    //撤回外出申请
    @PostMapping("/delOutward")
    public ResultVo<String> delOutward(Integer id) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<Outward>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        outwardService.update(updateWrapper);
        return ResultVo.ok("撤回成功");
    }
    //登记回院时间 actualreturntime
    @PostMapping("/updateBackTime")
    public ResultVo<String> updateBackTime(@RequestBody Outward outward) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<Outward>();
        updateWrapper.eq("id", outward.getId());
        updateWrapper.set("actualreturntime", outward.getActualreturntime());
        outwardService.update(updateWrapper);
        return ResultVo.ok("更新回院时间成功!!!");
    }
}
