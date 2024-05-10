package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.domain.form.LoginForm;
import cn.devzyh.toolbox.util.Md5Util;
import cn.devzyh.toolbox.util.StrUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    ConfigService configService;

    /**
     * 登录设备
     */
    private static String LOGIN_CLIENT = "";

    /**
     * 密码验证
     *
     * @param form
     * @return
     */
    public String login(LoginForm form) {
        if (StrUtil.isBlank(form.getPassword())) {
            return MessageConstant.Auth.PASSWORD_NULL;
        }

        String localPassword = configService.getData(ConfigConstant.QueryKey.SYS_AUTH);
        String password = Md5Util.password(form.getPassword());
        if (!password.equalsIgnoreCase(localPassword)) {
            return MessageConstant.Auth.PASSWORD_ERROR;
        }

        return MessageConstant.SUCCESS;
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    public String logout(HttpServletRequest request) {
        LOGIN_CLIENT = "";
        try {
            request.logout();
            log.info("客户端[" + request.getSession().getId() + "]注销登录");
        } catch (ServletException e) {
            log.error("注销登录异常", e);
        }
        return MessageConstant.SUCCESS;
    }

    /**
     * 认证成功
     */
    public void loginSuccess(HttpServletRequest request, LoginForm form) {
        // 保存Session作为认证信息
        String sessionId = request.getSession().getId();
        LOGIN_CLIENT = sessionId;

        log.info("客户端[" + request.getSession().getId() + "]登录成功");
    }

    /**
     * 客户端授权判断
     *
     * @param request
     * @return
     */
    public static boolean authorized(HttpServletRequest request) {
        return request.getSession().getId().equalsIgnoreCase(LOGIN_CLIENT);
    }
}
