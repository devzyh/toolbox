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
 * 搜索控制器
 */
@Controller
public class SearchController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private SearchServiceFactory searchServiceFactory;

    @GetMapping("/search/{type}")
    public String tool(@PathVariable String type, @RequestParam(required = false) String key, Model model) {
        if (key == null) {
            key = "";
        }
        SearchService service = searchServiceFactory.getService(type);
        if (service == null) {
            return "redirect:/";
        }
        model.addAllAttributes(configService.getSiteInfo());
        model.addAttribute(ViewConstant.Search.DATA, service.search(key));
        return "search";
    }
}
