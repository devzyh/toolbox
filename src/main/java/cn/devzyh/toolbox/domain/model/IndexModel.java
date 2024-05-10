package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.domain.entity.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 首页数据
 */
@Data
public class IndexModel {

    /**
     * 编辑工具
     */
    private List<Link> editor;
    /**
     * 效率工具
     */
    private List<Link> helper;
    /**
     * 文档链接
     */
    private List<Link> document;
    /**
     * 技术博客
     */
    private List<Link> blog;

}
