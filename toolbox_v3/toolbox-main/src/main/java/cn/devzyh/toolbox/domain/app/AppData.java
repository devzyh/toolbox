package cn.devzyh.toolbox.domain.app;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AppData implements Serializable {

    // 网站标题
    private String title;

    // 显示限制
    private Integer limit;

    // 搜索地址
    private String search;

    // 导航信息
    List<Navigation> navs;

    // 链接信息
    List<LinkGroup> links;

    // 友情链接
    List<Friend> friends;

}
