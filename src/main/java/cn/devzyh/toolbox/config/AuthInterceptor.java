package cn.devzyh.toolbox.config;


import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 认证拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    /***
     * 在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (AuthService.authorized(request)) {
                return true;
            }
            response.sendRedirect(WebConstant.Path.LOGIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
