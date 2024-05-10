package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Mark;
import cn.devzyh.toolbox.domain.model.MarkModel;
import cn.devzyh.toolbox.domain.query.MarkQuery;
import cn.devzyh.toolbox.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * 收藏页面控制器
 */
@Controller("AdminMark")
@RequestMapping("/admin/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;


    /**
     * 列表页
     *
     * @return
     */
    @GetMapping
    public String mark(MarkQuery query, Model model) {
        MarkModel mm = markService.findMarks(query);
        model.addAttribute(WebConstant.Global.DATA, mm);
        return WebConstant.View.MARK;
    }

    /**
     * 详情页
     *
     * @return
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        Mark mark = new Mark();
        if (WebConstant.Global.ADD.equals(id)) {
            mark.setTags(Collections.emptyList());
        } else {
            mark = markService.get(Long.parseLong(id));
        }
        model.addAttribute(WebConstant.Global.DATA, mark);
        model.addAttribute(WebConstant.Global.SOURCES, markService.findSources());
        model.addAttribute(WebConstant.Global.TAGS, markService.findTags());
        return WebConstant.View.MARK_DETAIL;
    }

    /**
     * 保存数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/{id}")
    public String save(@PathVariable String id, Mark mark) {
        return markService.saveMark(mark, WebConstant.Global.ADD.equals(id));
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
        return markService.removeMark(id);
    }

}
