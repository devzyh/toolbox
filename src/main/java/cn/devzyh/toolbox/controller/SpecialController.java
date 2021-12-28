package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 特殊控制器
 */
@Controller
public class SpecialController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/cm")
    public String index(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "caom";
    }
}
