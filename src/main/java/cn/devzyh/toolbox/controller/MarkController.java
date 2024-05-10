package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.query.MarkQuery;
import cn.devzyh.toolbox.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;


    /**
     * 收藏搜索页
     *
     * @param query
     * @param model
     * @return
     */
    @GetMapping("/mark")
    public String search(@ModelAttribute MarkQuery query, Model model) {
        model.addAttribute(WebConstant.Global.DATA, markService.search(query));
        return WebConstant.View.SEARCH;
    }

}
