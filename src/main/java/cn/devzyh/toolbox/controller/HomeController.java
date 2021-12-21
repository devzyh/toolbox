package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.ViewConstant;
import cn.devzyh.toolbox.service.ConfigService;
import cn.devzyh.toolbox.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/")
    public String index(Model m) {
        m.addAllAttributes(configService.getSiteInfo());
        m.addAttribute(ViewConstant.Index.FAVORITES, favoriteService.getFavorites());
        return "index";
    }
}
