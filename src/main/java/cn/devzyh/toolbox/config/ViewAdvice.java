package cn.devzyh.toolbox.config;

import cn.devzyh.toolbox.constant.AppConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * 视图处理
 */
@Slf4j
@ControllerAdvice
public class ViewAdvice {

    /**
     * 绑定到全局Model中
     */
    @ModelAttribute
    public void addAttributes(Model model) throws JsonProcessingException {
        model.addAllAttributes(AppConstant.VIEW_DATA);
    }

    /**
     * 统一异常处理
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e, Model model) {
        model.addAttribute(AppConstant.ViewKey.ERROR_MSG, e.getMessage());
        model.addAllAttributes(AppConstant.VIEW_DATA);
        log.error("页面请求异常：{}", e.getMessage(), e);
        return new ModelAndView(AppConstant.ViewPath.ERROR);
    }

}
