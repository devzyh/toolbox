package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.ViewConstant;
import cn.devzyh.toolbox.factory.SearchServiceFactory;
import cn.devzyh.toolbox.service.ConfigService;
import cn.devzyh.toolbox.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录控制器
 */
@Controller
public class AuthController {

    @GetMapping("auth")
    public String auth(Model model) {
        return "manage/auth";
    }
}
