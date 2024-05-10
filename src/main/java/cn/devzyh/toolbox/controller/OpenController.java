package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.domain.form.CryptForm;
import cn.devzyh.toolbox.util.TtxCryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台接口控制器
 */
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OpenController {

    /**
     * TTX加密
     *
     * @param form
     * @return
     */
    @PostMapping("/ttx/encrypt")
    public String ttxEncrypt(CryptForm form) {
        return TtxCryptUtil.encrypt(form.getInput(), form.getPassword());
    }

    /**
     * TTX解密
     *
     * @param form
     * @return
     */
    @PostMapping("/ttx/decrypt")
    public String ttxDecrypt(CryptForm form) {
        return TtxCryptUtil.decrypt(form.getInput(), form.getPassword());
    }

}
