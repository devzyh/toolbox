package cn.devzyh.toolbox.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 加解密请求参数
 */
@Data
public class CryptForm implements Serializable {

    private String input;

    private String password;

}
