package cn.magic.controller;

import cn.magic.entity.Bed;
import cn.magic.service.BedService;
import cn.magic.utils.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//床位控制类 床位管理
@RestController
@RequestMapping("/bed")
public class BedController {
    @Autowired
    private BedService bedService; //床位业务类
    //查询床位信息
    @GetMapping("/findBed")
    public ResultVo<List<Bed>> findBed(Bed bed) {
        QueryWrapper<Bed> qw = new QueryWrapper<>();
        if (bed.getRoomNo() != null) {
            qw.eq("room_no", bed.getRoomNo()); //根据房间编号查询
        }
        if (bed.getBedStatus() != null) {
            qw.eq("bed_status", bed.getBedStatus()); //根据床位状态查询
        }
        List<Bed> list = bedService.list(qw); //查询
        if (list.size() == 0) {
            return ResultVo.fail("没有查询到数据");
        }
        return ResultVo.ok(list);
    }
}
