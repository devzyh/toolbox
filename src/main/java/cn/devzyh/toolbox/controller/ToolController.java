package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.AppConstant;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.ResourceAccessException;

/**
 * 工具控制器
 */
@Controller
public class ToolController {

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
    @GetMapping(value = {"/code", "/code/{type}"})
    public String code(@PathVariable(required = false) String type, Model model) {
        if (StrUtil.isBlank(type)) {
            type = AppConstant.SUPPORT_TYPES.keySet().stream().findFirst().orElseThrow();
        } else {
            type = type.toLowerCase();
        }

        if (!AppConstant.SUPPORT_TYPES.containsKey(type)) {
            throw new ResourceAccessException(type);
        }

        model.addAttribute(AppConstant.ViewKey.TYPE, type);
        model.addAttribute(AppConstant.ViewKey.TYPE_TEXT, AppConstant.SUPPORT_TYPES.get(type));
        model.addAttribute(AppConstant.ViewKey.SUPPORT_TYPES, AppConstant.SUPPORT_TYPES);
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
