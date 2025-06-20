package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.constant.AppConstant;
import cn.devzyh.toolbox.domain.app.LinkGroup;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.ResourceAccessException;

/**
 * 核心控制器
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String index() {
        return AppConstant.ViewPath.INDEX;
    }

    /**
     * 列表页
     */
    @GetMapping("/more/{code}")
    public String more(@PathVariable String code, Model model) {
        LinkGroup group = null;
        for (LinkGroup cg : AppConstant.APP_DATA.getLinks()) {
            if (StrUtil.equalsIgnoreCase(code, cg.getCode())) {
                group = cg;
                break;
            }
        }

        if (group == null) {
            throw new ResourceAccessException(code);
        }

        model.addAttribute(AppConstant.ViewKey.MORE, group);
        return AppConstant.ViewPath.MORE;
    }

}
