package cn.devzyh.toolbox.controller;

import cn.devzyh.toolbox.domain.dto.CryptDTO;
import cn.devzyh.toolbox.service.TtxCryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开放接口控制器
 */
@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
public class OpenController {

    private final TtxCryptService ttxCryptService;

    /**
     * TTX加密
     */
    @PostMapping("/ttx/encrypt")
    public String ttxEncrypt(CryptDTO dto) {
        return ttxCryptService.encrypt(dto);
    }

    /**
     * TTX解密
     */
    @PostMapping("/ttx/decrypt")
    public String ttxDecrypt(CryptDTO dto) {
        return ttxCryptService.decrypt(dto);
    }

}
