package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.entity.Link;
import cn.devzyh.toolbox.domain.model.IndexModel;
import cn.devzyh.toolbox.domain.model.LinkModel;
import cn.devzyh.toolbox.domain.model.SearchModel;
import cn.devzyh.toolbox.domain.model.UrlModel;
import cn.devzyh.toolbox.domain.query.LinkQuery;
import cn.devzyh.toolbox.mapper.LinkMapper;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 首页链接-业务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkMapper linkMapper;

    private final ConfigService configService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public Link get(Long id) {
        return linkMapper.selectById(id);
    }

    /**
     * 存在检查
     *
     * @param id
     * @return
     */
    public boolean exist(Long id) {
        if (id == null) {
            return false;
        }
        return linkMapper.exists(Wrappers.lambdaQuery(Link.class).eq(Link::getId, id));
    }

    /**
     * 保存数据
     *
     * @param link
     */
    public Link save(Link link) {
        if (exist(link.getId())) {
            linkMapper.updateById(link);
        } else {
            linkMapper.insert(link);
        }
        log.info("保存链接数据:{}", StrUtil.toJson(link));
        return link;
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void remove(Long id) {
        linkMapper.deleteById(id);
        log.info("删除链接数据:{}", id);
    }

    /**
     * 加载友链信息
     */
    public List<Link> findFriendLinks() {
        LambdaQueryWrapper<Link> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Link::getType, ConfigConstant.LinkType.FRIEND);
        wrapper.orderByAsc(Link::getId);
        return linkMapper.selectList(wrapper);
    }

    /**
     * 查询首页数据
     *
     * @return
     */
    public IndexModel findHomeModel() {
        IndexModel hm = new IndexModel();
        List<Link> all = linkMapper.selectList(null);
        hm.setEditor(all.stream()
                .filter(it -> it.getType().equalsIgnoreCase(ConfigConstant.LinkType.EDITOR))
                .sorted(Comparator.comparing(Link::getSort))
                .limit(WebConstant.Page.DEFAULT_HOME_SIZE)
                .collect(Collectors.toList()));
        hm.setHelper(all.stream()
                .filter(it -> it.getType().equalsIgnoreCase(ConfigConstant.LinkType.HELPER))
                .sorted(Comparator.comparing(Link::getSort))
                .limit(WebConstant.Page.DEFAULT_HOME_SIZE)
                .collect(Collectors.toList()));
        hm.setDocument(all.stream()
                .filter(it -> it.getType().equalsIgnoreCase(ConfigConstant.LinkType.DOCUMENT))
                .sorted(Comparator.comparing(Link::getSort))
                .limit(WebConstant.Page.DEFAULT_HOME_SIZE)
                .collect(Collectors.toList()));
        hm.setBlog(all.stream()
                .filter(it -> it.getType().equalsIgnoreCase(ConfigConstant.LinkType.BLOG))
                .sorted(Comparator.comparing(Link::getSort))
                .limit(WebConstant.Page.DEFAULT_HOME_SIZE)
                .collect(Collectors.toList()));
        return hm;
    }

    /**
     * 搜索首页链接
     *
     * @param query
     * @return
     */
    public SearchModel search(LinkQuery query) {
        LinkModel lm = findLinks(query);

        SearchModel sm = new SearchModel();
        sm.setPage(lm.getPage());
        sm.setUrls(toUrls(lm.getPage().getRecords()));
        sm.setSearchKey(query.getKey());
        sm.setSearchType(WebConstant.SearchType.LINK);
        sm.setPageTitle("搜索”" + sm.getSearchKey() + "“的结果");
        return sm;
    }

    /**
     * 按类型列出链接
     *
     * @param type
     * @param current
     * @return
     */
    public SearchModel listLink(String type, long current) {
        LambdaQueryWrapper<Link> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Link::getType, type);
        wrapper.orderByAsc(Link::getSort);
        Page<Link> page = Page.of(current, WebConstant.Page.DEFAULT_PAGE_SIZE);
        page = linkMapper.selectPage(page, wrapper);

        SearchModel sm = new SearchModel();
        sm.setPage(page);
        sm.setSearchType(WebConstant.SearchType.LINK);
        sm.setPageTitle(configService.getData(type));
        sm.setUrls(toUrls(page.getRecords()));
        sm.setLinkType(type);
        return sm;
    }

    /**
     * Link转Url
     *
     * @param links
     */
    List<UrlModel> toUrls(List<Link> links) {
        List<UrlModel> urls = new LinkedList<>();
        for (Link link : links) {
            UrlModel url = new UrlModel();
            url.setTitle(link.getTitle());
            url.setUrl(link.getUrl());
            url.setRemark(link.getRemark());
            List<String> tags = new LinkedList<>();
            tags.add(link.getType());
            url.setTags(tags);
            urls.add(url);
        }
        return urls;
    }

    /**
     * 检索链接数据
     *
     * @param query
     * @return
     */
    public LinkModel findLinks(LinkQuery query) {
        LambdaQueryWrapper<Link> wrapper = Wrappers.lambdaQuery();

        // 精准匹配类型
        if (StrUtil.isNotBlank(query.getType())) {
            wrapper.eq(Link::getType, query.getType());
        }
        // 模糊查询标题或备注
        if (StrUtil.isNotBlank(query.getKey())) {
            wrapper.apply("(UPPER(title) LIKE CONCAT('%', {0}, '%') OR UPPER(remark) LIKE CONCAT('%', {0}, '%'))",
                    query.getKey().toUpperCase());
        }
        // 排序
        wrapper.orderByAsc(Link::getType);
        wrapper.orderByAsc(Link::getSort);

        Page<Link> page = Page.of(query.getPage(), WebConstant.Page.DEFAULT_PAGE_SIZE);
        page = linkMapper.selectPage(page, wrapper);

        LinkModel lm = new LinkModel();
        lm.setPage(page);
        lm.setTypes(findTypes());
        return lm;
    }

    /**
     * 字段翻译
     *
     * @param links
     */
    List<Link> translate(List<Link> links) {
        Map<String, String> types = configService.getCodeMap(ConfigConstant.Type.LINK_TYPE);
        Map<String, String> opens = configService.getCodeMap(ConfigConstant.Type.OPEN_TYPE);
        for (Link link : links) {
            link.setType(types.get(link.getType()));
            link.setOpen(opens.get(link.getOpen()));
        }
        return links;
    }

    /**
     * 查询链接类型
     *
     * @return
     */
    public List<Config> findTypes() {
        return configService.findConfigByType(ConfigConstant.Type.LINK_TYPE);
    }

    /**
     * 查询打开类型
     *
     * @return
     */
    public List<Config> findOpens() {
        return configService.findConfigByType(ConfigConstant.Type.OPEN_TYPE);
    }

    /**
     * 查询打开类型
     *
     * @return
     */
    public Map<String, String> findOpenMap() {
        return configService.getCodeMap(ConfigConstant.Type.OPEN_TYPE);
    }

    /**
     * 保存链接数据
     *
     * @param link
     * @return
     */
    public String saveLink(Link link, boolean add) {
        if (StrUtil.isBlank(link.getTitle())) {
            return MessageConstant.Link.TITLE_NULL;
        }

        if (StrUtil.isBlank(link.getUrl())) {
            return MessageConstant.Link.URL_NULL;
        }

        if (StrUtil.isBlank(link.getType())) {
            return MessageConstant.Link.TYPE_NULL;
        }

        if (StrUtil.isBlank(link.getOpen())) {
            return MessageConstant.Link.OPEN_NULL;
        }

        if (link.getSort() == null) {
            return MessageConstant.Link.SORT_NULL;
        }

        if (add) {
            link.setId(null);
        } else {
            if (link.getId() < 1) {
                return MessageConstant.Link.ID_NULL;
            }
            if (!exist(link.getId())) {
                return MessageConstant.Link.NOT_EXIST;
            }
        }

        save(link);
        return MessageConstant.SUCCESS;
    }

    /**
     * 删除链接数据
     *
     * @param id
     * @return
     */
    public String removeLink(Long id) {
        if (!exist(id)) {
            return MessageConstant.Link.NOT_EXIST;
        }

        remove(id);
        return MessageConstant.SUCCESS;
    }

}
