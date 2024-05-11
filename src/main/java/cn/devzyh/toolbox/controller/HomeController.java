package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 核心控制器
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String index() {
        return AppConstant.ViewPath.INDEX;
    }

    /**
     * 列表页
     */
    @GetMapping("/more/{type}")
    public String more(@PathVariable String type) {
        return AppConstant.ViewPath.INDEX;
    }

}
