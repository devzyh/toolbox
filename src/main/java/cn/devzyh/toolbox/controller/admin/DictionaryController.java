package cn.devzyh.toolbox.controller.admin;

import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.model.DictionaryModel;
import cn.devzyh.toolbox.domain.query.DictionaryQuery;
import cn.devzyh.toolbox.service.ConfigService;
import cn.devzyh.toolbox.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典页面控制器
 */
@Controller("AdminDictionary")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    private final ConfigService configService;


    /**
     * 列表页
     *
     * @return
     */
    @GetMapping("/dictionary")
    public String dictionary(DictionaryQuery query, Model model) {
        DictionaryModel dm = dictionaryService.findDictionaries(query);
        model.addAttribute(WebConstant.Global.DATA, dm);
        return WebConstant.View.DICTIONARY;
    }

    /**
     * 详情页
     *
     * @return
     */
    @GetMapping("/dictionary/{code}")
    public String detail(@PathVariable String code, Model model) {
        Config c = new Config();
        if (!WebConstant.Global.ADD.equals(code)) {
            c = configService.get(code);
        }
        model.addAttribute(WebConstant.Global.DATA, c);
        model.addAttribute(WebConstant.Global.TYPES, dictionaryService.findTypes());
        return WebConstant.View.DICTIONARY_DETAIL;
    }

    /**
     * 保存数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/dictionary/{code}")
    public String save(@PathVariable String code, Config config) {
        return dictionaryService.save(config, WebConstant.Global.ADD.equals(code));
    }

    /**
     * 删除数据
     *
     * @param code
     * @return
     */
    @ResponseBody
    @DeleteMapping("/dictionary/{code}")
    public String remove(@PathVariable String code) {
        return dictionaryService.remove(code);
    }

}
