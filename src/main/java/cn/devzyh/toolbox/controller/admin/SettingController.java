package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.form.PasswordForm;
import cn.devzyh.toolbox.domain.form.SettingForm;
import cn.devzyh.toolbox.service.SettingService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 设置页面控制器
 */
@Slf4j
@Controller("AdminSetting")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;


    /**
     * 修改密码界面
     *
     * @return
     */
    @GetMapping("/password")
    public String password() {
        return WebConstant.View.PASSWORD;
    }

    /**
     * 修改密码实现
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/password")
    public String changePassword(PasswordForm form) {
        return settingService.changePassword(form);
    }

    /**
     * 基本设置界面
     *
     * @return
     */
    @GetMapping("/setting")
    public String setting() {
        return WebConstant.View.SETTING;
    }

    /**
     * 保存基本信息
     *
     * @param form
     * @return
     */
    @ResponseBody
    @PostMapping("/setting")
    public String saveSetting(SettingForm form) {
        return settingService.saveSetting(form);
    }

    /**
     * 导出系统数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/export")
    public String export(HttpServletResponse response) {
        return settingService.export(response);
    }

}
