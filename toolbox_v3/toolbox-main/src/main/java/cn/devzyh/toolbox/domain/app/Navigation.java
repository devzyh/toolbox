package cn.devzyh.toolbox.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 导航信息
 */
@Data
public class Navigation implements Serializable {

    // 文本
    private String text;

    // 链接
    private String href;

    // 打开方式
    private String target;

}
