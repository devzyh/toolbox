package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.form.LoginForm;
import cn.devzyh.toolbox.service.AuthService;
import cn.devzyh.toolbox.service.ConfigService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 登录页面控制器
 */
@Controller("AdminLogin")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    private final ConfigService configService;


    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if (AuthService.authorized(request)) {
            return WebConstant.Redirect.PREFIX + configService.getDashboard();
        }
        return WebConstant.View.LOGIN;
    }

    /**
     * 密码验证
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String doLogin(HttpServletRequest request, LoginForm form) {
        String msg = authService.login(form);
        if (MessageConstant.isError(msg)) {
            return msg;
        }

        authService.loginSuccess(request, form);
        return WebConstant.Redirect.PREFIX + configService.getDashboard();
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        authService.logout(request);
        return WebConstant.Redirect.PREFIX + WebConstant.Path.LOGIN;
    }

}
