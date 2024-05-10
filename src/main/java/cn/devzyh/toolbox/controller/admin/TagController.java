package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Tag;
import cn.devzyh.toolbox.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 日志页面控制器
 */
@Controller("AdminTag")
@RequestMapping("/admin/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;


    /**
     * 修改密码界面
     *
     * @return
     */
    @GetMapping
    public String tag(Model model) {
        model.addAttribute(WebConstant.Global.DATA, tagService.findAll());
        return WebConstant.View.TAG;
    }

    /**
     * 保存数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping
    public String save(Tag tag) {
        return tagService.saveTag(tag);
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
        return tagService.removeTag(id);
    }

}
