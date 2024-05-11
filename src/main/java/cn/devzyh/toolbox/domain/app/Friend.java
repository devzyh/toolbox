package cn.devzyh.toolbox.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 友情链接
 */
@Data
public class Friend implements Serializable {

    // 文本
    private String text;

    // 链接
    private String href;

    // 打开方式
    private String target;

}
