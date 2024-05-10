package cn.devzyh.toolbox.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单
 */
@Data
public class LoginForm implements Serializable {

    private String password;

}
