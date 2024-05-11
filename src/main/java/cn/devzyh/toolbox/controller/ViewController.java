package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.AppConstant;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 视图控制器
 */
@Controller
public class ViewController {

    /**
     * JSON编辑器
     */
    @GetMapping("/json")
    public String tool() {
        return AppConstant.ViewPath.JSON;
    }

    /**
     * XML编辑器
     */
    @GetMapping("/xml")
    public String xml() {
        return AppConstant.ViewPath.XML;
    }

    /**
     * 文本差异对比
     */
    @GetMapping("/diff")
    public String diff() {
        return AppConstant.ViewPath.DIFF;
    }

    /**
     * XSLT编辑器
     */
    @GetMapping("/xslt")
    public String xslt() {
        return AppConstant.ViewPath.XSLT;
    }

    /**
     * 文本处理
     */
    @GetMapping(value = {"/code", "/code/{key}"})
    public String code(@PathVariable(required = false) String key, Model model) {
        if (StrUtil.isNotBlank(key)) {
            key = key.toLowerCase();
            if (!AppConstant.CODE_TYPES.contains(key)) {
                key = null;
            }
        }
        model.addAttribute("key", key);
        return AppConstant.ViewPath.CODE;
    }

    /**
     * Unix时间戳
     */
    @GetMapping("/unix")
    public String unix() {
        return AppConstant.ViewPath.UNIX;
    }

}
