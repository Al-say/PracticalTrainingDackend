package cn.magic.controller;

import cn.magic.dto.BackdownDTO;
import cn.magic.entity.Backdown;
import cn.magic.entity.Bed;
import cn.magic.entity.Customer;
import cn.magic.service.BackdownService;
import cn.magic.service.BedService;
import cn.magic.service.CustomerService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 退住管理控制器
@RestController
@RequestMapping("/backdown")
public class BackdownController {
    @Autowired
    private BackdownService backdownService;
    @Autowired
    private BedService bedService;
    @Autowired
    private CustomerService customerService;

    // 函数注释 行间注释 添加日志 生成单测 代码解释 接口文档 调优建议
    //查询退住信息
    @PostMapping("/listBackdown")
    public ResultVo<Page<BackdownVo>> listBackdown(@RequestBody BackdownDTO backdownDTO) throws Exception {
        return backdownService.listBackdownVo(backdownDTO);
    }

    // 函数注释 行间注释 添加日志 生成单测 代码解释 接口文档 调优建议
    //添加退住审批
    @PostMapping("/addBackdown")
    public ResultVo<String> addBackdown(@RequestBody Backdown backdown) throws Exception {
        backdownService.save(backdown);
        return ResultVo.ok("添加成功");
    }
    //审批退住
    @PostMapping("/examineBackdown")
    public ResultVo<String> examineBackdown(@RequestBody Backdown backdown) throws Exception {;
        Backdown bd = backdownService.getById(backdown.getId());
        // 审批通过      auditstatus
        if (backdown.getAuditstatus() == 1) {
            // 修改床铺记录，对应床铺状态改为空闲
            Customer cs = customerService.getById(bd.getCustomerId());
            Bed bed = new Bed();
            bed.setId(cs.getBedId());
            bed.setBedStatus(1);
            bedService.updateById(bed);
        }
        return backdownService.examineBackdown(backdown);
    }
    //撤回退住申请
    @DeleteMapping("/delBackdown/{id}")
    public ResultVo<String> delBackdown(@PathVariable("id") Integer id) throws Exception {
        UpdateWrapper<Backdown> updateWrapper = new UpdateWrapper<Backdown>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        backdownService.update(updateWrapper);
        return ResultVo.ok("撤回成功");
    }
}
