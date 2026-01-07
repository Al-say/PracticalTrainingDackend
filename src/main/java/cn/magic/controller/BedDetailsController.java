package cn.magic.controller;

import cn.magic.dto.BedDetailsDTO;
import cn.magic.dto.ExchangeDTO;
import cn.magic.entity.BedDetails;
import cn.magic.service.BedDetailsService;
import cn.magic.utils.ResultVo;
import cn.magic.vo.BedDetailsVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//床位详情控制类
@RestController
@RequestMapping("/bedDetails")
public class BedDetailsController {
    @Autowired
    private BedDetailsService bedDetailsService;
    //床位详情列表动态查询（分页）
    @GetMapping("/listBedDetailsVoPage")
    public ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception {
        return bedDetailsService.listBedDetailsVoPage(bedDetailsDTO);
    }
    //更新床位使用详情-只能修改床位使用结束时间
    @PostMapping("/updateBedDetails")
    public ResultVo<String> updateBedDetails(@RequestBody BedDetails beddetails) throws Exception {
        bedDetailsService.updateById(beddetails);
        return ResultVo.ok("编辑成功");
    }
    //床位调换
    @PostMapping("/exchangeBed")
    public ResultVo<String> exchangeBed(@RequestBody ExchangeDTO exchangeDTO) throws Exception {
        return bedDetailsService.exchangeBed(exchangeDTO);
    }
    //删除记录
    @DeleteMapping("/delBedDetails/{id}")
    public ResultVo<String> delBedDetails(@PathVariable("id") Integer id) throws Exception {
        bedDetailsService.removeById(id);
        return ResultVo.ok("删除成功");
    }
}
