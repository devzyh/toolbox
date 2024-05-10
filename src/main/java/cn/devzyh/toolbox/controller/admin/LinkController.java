package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Link;
import cn.devzyh.toolbox.domain.model.LinkModel;
import cn.devzyh.toolbox.domain.query.LinkQuery;
import cn.devzyh.toolbox.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 首页链接页面控制器
 */
@Controller("AdminLink")
@RequestMapping("/admin/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;


    /**
     * 列表页
     *
     * @return
     */
    @GetMapping
    public String link(LinkQuery query, Model model) {
        LinkModel lm = linkService.findLinks(query);
        model.addAttribute(WebConstant.Global.DATA, lm);
        return WebConstant.View.LINK;
    }

    /**
     * 详情页
     *
     * @return
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        Link link = new Link();
        if (WebConstant.Global.ADD.equals(id)) {
            link.setSort(0);
        } else {
            link = linkService.get(Long.parseLong(id));
        }
        model.addAttribute(WebConstant.Global.DATA, link);
        model.addAttribute(WebConstant.Global.TYPES, linkService.findTypes());
        model.addAttribute(WebConstant.Global.OPENS, linkService.findOpens());
        return WebConstant.View.LINK_DETAIL;
    }

    /**
     * 保存数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/{id}")
    public String save(@PathVariable String id, Link link) {
        return linkService.saveLink(link, WebConstant.Global.ADD.equals(id));
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        return linkService.removeLink(id);
    }

}
