package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.query.LinkQuery;
import cn.devzyh.toolbox.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 链接页面控制器
 */
@Controller
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;


    /**
     * 链接搜索页
     *
     * @param query
     * @param model
     * @return
     */
    @GetMapping("/link")
    public String search(LinkQuery query, Model model) {
        model.addAttribute(WebConstant.Global.DATA, linkService.search(query));
        return WebConstant.View.SEARCH;
    }

    /**
     * 指定类型链接页
     *
     * @param type
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/link/{type}")
    public String listByType(@PathVariable("type") String type,
                             @RequestParam(required = false, defaultValue = "1") Long page,
                             Model model) {
        model.addAttribute(WebConstant.Global.DATA, linkService.listLink(type, page));
        return WebConstant.View.SEARCH;
    }

}
