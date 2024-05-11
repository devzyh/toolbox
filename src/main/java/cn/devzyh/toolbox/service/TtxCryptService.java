package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.domain.dto.CryptDTO;
import cn.devzyh.toolbox.util.TtxCryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 */
@Slf4j
@Service
public class TtxCryptService {

    /**
     * 加密
     */
    public String encrypt(CryptDTO dto) {
        return TtxCryptUtil.encrypt(dto.getInput(), dto.getSecret());
    }

    /**
     * 解密
     */
    public String decrypt(CryptDTO dto) {
        return TtxCryptUtil.decrypt(dto.getInput(), dto.getSecret());
    }

}
