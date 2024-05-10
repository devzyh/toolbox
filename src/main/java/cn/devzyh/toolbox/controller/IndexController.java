package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final LinkService linkService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(WebConstant.Global.DATA, linkService.findHomeModel());
        model.addAttribute(WebConstant.Global.OPENS, linkService.findOpenMap());
        return WebConstant.View.INDEX;
    }

}
