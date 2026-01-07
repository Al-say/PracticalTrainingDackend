package cn.magic.controller;

import cn.magic.utils.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 */
@RestController
public class IndexController {

    /**
     * 首页
     */
    @GetMapping("/")
    public ResultVo<String> index() {
        return ResultVo.ok("欢迎访问Spring Boot后端API系统！请访问 /api/* 路径使用API接口。");
    }
}
