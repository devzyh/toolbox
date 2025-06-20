package cn.devzyh.toolbox.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 加解密请求参数
 */
@Data
public class CryptDto implements Serializable {

    private String input;

    private String secret;

}
