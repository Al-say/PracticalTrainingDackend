package cn.magic.controller;

import cn.magic.dto.NurseRecordDTO;
import cn.magic.dto.OutwardDTO;
import cn.magic.entity.Nurserecord;
import cn.magic.service.NurserecordService;
import cn.magic.service.OutwardService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.NurseRecordsVo;
import cn.magic.vo.OutwardVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//护理记录控制器
@RestController
@RequestMapping("/nurserecord")
public class NurserecordController {
    @Autowired
    private NurserecordService nurserecordService; //护理记录服务
    @Autowired
    private OutwardService outwardService;  //外出记录服务
    //添加护理记录
    @PostMapping("/addNurseRecord")
    public ResultVo<String> addNurseRecord(@RequestBody Nurserecord nurserecord) throws Exception {
        return nurserecordService.addNurseRecord(nurserecord);
    }
    //客户护理记录信息动态查询（分页）
    @GetMapping("/listNurseRecordsVo")
    public ResultVo<Page<NurseRecordsVo>> listNurseRecordsVo(NurseRecordDTO nurseRecordDTO) throws Exception {
        return nurserecordService.queryNurseRecordsVo(nurseRecordDTO);
    }
    //删除客户护理记录
    @DeleteMapping("/removeCustomerRecord/{id}")
    public ResultVo<String> removeCustomerRecord(@PathVariable("id") Integer id) throws Exception {
        Nurserecord nurserecord = new Nurserecord();
        nurserecord.setIsDeleted(1);
        nurserecord.setId(id);
        nurserecordService.updateById(nurserecord);
        return ResultVo.ok("删除成功");
    }
    //查询外出记录
    @PostMapping("/queryOutwardVo")
    public ResultVo<Page<OutwardVo>> queryOutwardVo(@RequestBody OutwardDTO outwardDTO) throws Exception {
        return outwardService.queryOutwardVo(outwardDTO);
    }
}
