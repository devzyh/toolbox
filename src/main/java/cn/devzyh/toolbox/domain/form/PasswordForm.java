package cn.devzyh.toolbox.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码表单
 */
@Data
public class PasswordForm implements Serializable {

    private String originalPassword;

    private String password;

}
