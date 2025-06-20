package cn.devzyh.toolbox.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 链接信息
 */
@Data
public class Link implements Serializable {

    // 图标
    private String icon;

    // 文本
    private String text;

    // 链接
    private String href;

    // 打开方式
    private String target;

    // 备注文本
    private String subText;

}
