package cn.devzyh.toolbox.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 设置信息表单
 */
@Data
public class SettingForm implements Serializable {

    private String title;

    private String url;

    private String git;

    private String roadmap;

    private String cdn;

    private String dashboard;

}
