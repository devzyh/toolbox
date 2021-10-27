package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 工具控制器
 */
@Controller
public class ToolController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/json")
    public String tool(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/json";
    }

    @GetMapping("/xml")
    public String xml(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/xml";
    }

    @GetMapping("/diff")
    public String diff(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/diff";
    }

    @GetMapping("/xslt")
    public String xslt(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/xslt";
    }

    @GetMapping("/code")
    public String code(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/code";
    }

    @GetMapping("/cron")
    public String cron(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        return "tool/cron";
    }
}
