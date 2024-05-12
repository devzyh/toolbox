package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.AppConstant;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 工具控制器
 */
@Controller
public class ToolController {

    /**
     * JSON编辑器
     */
    @GetMapping(value = {"/json", "/json/"})
    public String tool() {
        return AppConstant.ViewPath.JSON;
    }

    /**
     * XML编辑器
     */
    @GetMapping(value = {"/xml", "/xml/"})
    public String xml() {
        return AppConstant.ViewPath.XML;
    }

    /**
     * 文本差异对比
     */
    @GetMapping(value = {"/diff", "/diff/"})
    public String diff() {
        return AppConstant.ViewPath.DIFF;
    }

    /**
     * XSLT编辑器
     */
    @GetMapping(value = {"/xslt", "/xslt/"})
    public String xslt() {
        return AppConstant.ViewPath.XSLT;
    }

    /**
     * 文本处理
     */
    @GetMapping(value = {"/code", "/code/", "/code/{type}", "/code/{key}/"})
    public String code(@PathVariable(required = false) String type, Model model) {
        if (StrUtil.isNotBlank(type)) {
            type = type.toLowerCase();
            if (!AppConstant.ViewKey.SUPPORT_TYPES.contains(type)) {
                type = null;
            }
        }
        model.addAttribute(AppConstant.ViewKey.TYPE, type);
        return AppConstant.ViewPath.CODE;
    }

    /**
     * Unix时间戳
     */
    @GetMapping(value = {"/unix", "/unix/"})
    public String unix() {
        return AppConstant.ViewPath.UNIX;
    }

}
