package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 工具控制器
 */
@Controller
@RequiredArgsConstructor
public class ToolController {

    /**
     * JSON编辑器
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/json", "/json/{key}"})
    public String tool(@PathVariable(required = false) String key, Model model) {
        model.addAttribute(WebConstant.Global.KEY, key);
        return WebConstant.View.JSON;
    }

    /**
     * XML编辑器
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/xml", "/xml/{key}"})
    public String xml(@PathVariable(required = false) String key, Model model) {
        model.addAttribute(WebConstant.Global.KEY, key);
        return WebConstant.View.XML;
    }

    /**
     * 文本差异对比
     *
     * @param model
     * @return
     */
    @GetMapping("/diff")
    public String diff(Model model) {
        return WebConstant.View.DIFF;
    }

    /**
     * XSLT编辑器
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/xslt", "/xslt/{key}"})
    public String xslt(@PathVariable(required = false) String key, Model model) {
        model.addAttribute(WebConstant.Global.KEY, key);
        return WebConstant.View.XSLT;
    }

    /**
     * 编码/加密/解密
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/code", "/code/{key}"})
    public String code(@PathVariable(required = false) String key, Model model) {
        if (StrUtil.isNotBlank(key)) {
            key = key.toLowerCase();
            if (!WebConstant.Data.CODE_SUPPORT_TYPES.contains(key)) {
                key = null;
            }
        }
        model.addAttribute(WebConstant.Global.KEY, key);
        return WebConstant.View.CODE;
    }

    /**
     * Unix时间戳
     *
     * @param model
     * @return
     */
    @GetMapping("/unix")
    public String unix(Model model) {
        return WebConstant.View.UNIX;
    }

}
