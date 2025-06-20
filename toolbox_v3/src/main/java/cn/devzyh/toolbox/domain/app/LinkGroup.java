package cn.devzyh.toolbox.domain.app;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 链接分组
 */
@Data
public class LinkGroup implements Serializable {

    // 分组代码
    private String code;

    // 分组名称
    private String text;

    // 链接集合
    private List<Link> links;

}
