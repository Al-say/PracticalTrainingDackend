package cn.magic.controller;

import cn.magic.utils.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
@RequestMapping("/api")
public class TestController {

    /**
     * 欢迎接口
     */
    @GetMapping("/hello")
    public ResultVo<String> hello() {
        return ResultVo.ok("欢迎使用Spring Boot + MyBatis Plus后端系统！");
    }

    /**
     * 系统状态检查
     */
    @GetMapping("/status")
    public ResultVo<String> status() {
        return ResultVo.ok("系统运行正常");
    }
}
