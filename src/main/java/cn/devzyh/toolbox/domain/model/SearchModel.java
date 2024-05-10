package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 搜索页数据
 */
@Data
public class SearchModel {

    /**
     * 页面标题
     */
    private String pageTitle;

    /**
     * 搜索类型
     */
    private String searchType;

    /**
     * 搜索关键字
     */
    private String searchKey;

    /**
     * 分页对象
     */
    private Page<?> page;

    /**
     * 数据列表
     */
    private List<UrlModel> urls;

    /**
     * 可选：链接类型
     */
    private String linkType;

    /**
     * 是否存在多页
     *
     * @return
     */
    public boolean hasPage() {
        return this.page.getPages() > 0;
    }

    /**
     * 上一页地址
     *
     * @return
     */
    public String previousPageUrl() {
        String url = "/" + this.searchType;
        // 链接分类
        if (StrUtil.isNotBlank(this.linkType)) {
            url = url + "/" + this.linkType;
        }
        // 关键字
        if (StrUtil.isNotBlank(this.searchKey)) {
            url = url + "?key=" + this.searchKey;
        }
        // 页码
        long page = 1;
        if (this.page.hasPrevious()) {
            page = this.page.getCurrent() - 1;
        }
        url = url + (url.contains("?") ? "&" : "?") + "page=" + page;
        return url;
    }

    /**
     * 下一页地址
     *
     * @return
     */
    public String nextPageUrl() {
        String url = "/" + this.searchType;
        // 链接分类
        if (StrUtil.isNotBlank(this.linkType)) {
            url = url + "/" + this.linkType;
        }
        // 关键字
        if (StrUtil.isNotBlank(this.searchKey)) {
            url = url + "?key=" + this.searchKey;
        }
        // 页码
        long page = this.page.getPages();
        if (this.page.hasNext()) {
            page = this.page.getCurrent() + 1;
        }
        url = url + (url.contains("?") ? "&" : "?") + "page=" + page;
        return url;
    }

}
