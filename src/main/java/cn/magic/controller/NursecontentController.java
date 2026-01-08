package cn.magic.controller;

import cn.magic.dto.NurseItemDTO;
import cn.magic.entity.Nursecontent;
import cn.magic.service.NursecontentService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//护理项目管理
@RestController
@RequestMapping("/nursecontent")
public class NursecontentController {
    @Autowired
    private NursecontentService nursecontentService;
    //新增护理项目
    @PostMapping("/addNurseItem")
    public ResultVo<String> addNurseItem(@RequestBody Nursecontent nursecontent) throws Exception {
        nursecontent.setIsDeleted(0);
        nursecontentService.save(nursecontent);
        return ResultVo.ok("新增成功");
    }
    //查询护理项目-分页 findNurseItem
    @GetMapping("/findNurseItemPage")
    public ResultVo<Page<Nursecontent>> findNurseItemPage(NurseItemDTO nurseItemDTO) throws Exception {
        // 处理空值默认情况
        int current = 1; // 默认第一页
        int size = nurseItemDTO.getPageSize() == null ? 6 : nurseItemDTO.getPageSize();

        // Page构造函数第一个参数是 current(当前页)，第二个是 size(每页条数)
        Page<Nursecontent> page = new Page<>(current, size);
        QueryWrapper<Nursecontent> qw = new QueryWrapper<>();
        if (nurseItemDTO.getItemName() != null && !nurseItemDTO.getItemName().isEmpty()) {
            qw.like("nursing_name", "%" + nurseItemDTO.getItemName() + "%");
        }
        qw.eq("status", nurseItemDTO.getStatus());
        qw.eq("is_deleted", 0);
        nursecontentService.page(page, qw);
        return ResultVo.ok(page);
    }
    //修改护理项目
    @PostMapping("/updateNurseItem")
    public ResultVo<String> updateNurseItem(@RequestBody Nursecontent nursecontent) throws Exception {
        return nursecontentService.updateNurseItem(nursecontent);
    }
    //删除护理项目
    @DeleteMapping("/delNurseItem/{id}")
    public ResultVo<String> delNurseItem(@PathVariable("id") Integer id) throws Exception {
        return nursecontentService.delNurseItem(id);
    }
}
