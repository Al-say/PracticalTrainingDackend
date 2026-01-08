package cn.magic.controller;

import cn.magic.entity.Nursecontent;
import cn.magic.entity.Nurselevel;
import cn.magic.entity.Nurselevelitem;
import cn.magic.service.NursecontentService;
import cn.magic.service.NurselevelService;
import cn.magic.service.NurselevelitemService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//护理级别管理
@RestController
@RequestMapping("/nurselevel")
public class NurselevelController {
    @Autowired
    private NurselevelService nurselevelService; //护理级别
    @Autowired
    private NurselevelitemService nurselevelitemService; //护理级别与项目中间表
    @Autowired
    private NursecontentService nursecontentService; //护理内容
    //添加护理级别
    @PostMapping("/addNurseLevel")
    public ResultVo<String> addNurseLevel(@RequestBody Nurselevel nurselevel) throws Exception {
        nurselevelService.save(nurselevel);
        return ResultVo.ok("添加护理级别");
    }
    //更新护理级别
    @PostMapping("/updateNurseLevel")
    public ResultVo<String> updateNurseLevel(@RequestBody Nurselevel nurselevel) throws Exception {
        nurselevelService.updateById(nurselevel);
        return ResultVo.ok("更新护理级别");
    }
    //删除护理级别
    @DeleteMapping("/removeNurseLevel/{id}")
    public ResultVo<String> removeNurseLevel(@PathVariable("id") Integer id) throws Exception {
        nurselevelService.removeById(id);
        return ResultVo.ok("删除护理级别");
    }
    //查询护理级别列表
    @GetMapping("/listNurseLevel")
    public ResultVo<List<Nurselevel>> listNurseLevel(Nurselevel nurselevel) throws Exception {
        QueryWrapper<Nurselevel> qw = new QueryWrapper<>();
        if (nurselevel.getLevelStatus() != null) {
            qw.eq("level_status", nurselevel.getLevelStatus());
        }
        return ResultVo.ok(nurselevelService.list(qw));
    }
    //根据护理级别查询护理项目-不分页
    @GetMapping("/listNurseItemByLevel")
    public ResultVo<List<Nursecontent>> listNurseItemByLevel(Integer levelId) throws Exception {
        return nursecontentService.listNurseItemByLevel(levelId);
    }
    //添加护理级别中的护理项目
    @PostMapping("/addItemToLevel")
    public ResultVo<String> addItemToLevel(@RequestBody Nurselevelitem nurselevelitem) throws Exception {
        QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
        qw.eq("level_id", nurselevelitem.getLevelId());
        qw.eq("item_id", nurselevelitem.getItemId());
        long row = nurselevelitemService.count(qw);
        if (row > 0) {
            return ResultVo.fail("当前级别已存在相同项目");
        }
        nurselevelitemService.save(nurselevelitem);
        return ResultVo.ok("添加成功");
    }
    //删除护理级别中的护理项目
    @GetMapping("/removeNurseLevelItem")
    public ResultVo<String> removeNurseLevelItem(Integer levelId, Integer itemId) throws Exception {
        QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
        qw.eq("level_id", levelId);
        qw.eq("item_id", itemId);
        nurselevelitemService.remove(qw);
        return ResultVo.ok("删除成功");
    }
}
