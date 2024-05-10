package cn.devzyh.toolbox.config;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.service.ConfigService;
import cn.devzyh.toolbox.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@RequiredArgsConstructor
public class WebControllerAdvice {

    private final ConfigService configService;

    private final LinkService linkService;

    /**
     * 绑定到全局Model中
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(HttpServletRequest request, Model model) {
        // 开放接口跳过
        if (request.getRequestURI().startsWith(WebConstant.Path.REST_PREFIX)) {
            return;
        }

        // 基本信息
        configService.loadSystem(model);

        // 后台跳过友链
        if (request.getRequestURI().startsWith(WebConstant.Path.ADMIN_PREFIX)) {
            return;
        }

        // 友链数据
        model.addAttribute(WebConstant.Global.FRIENDS, linkService.findFriendLinks());
    }

    /**
     * 统一异常处理
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e) throws Exception {
        e.printStackTrace();
        return new ModelAndView(WebConstant.View.ERROR);
    }

}
