package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.entity.Mark;
import cn.devzyh.toolbox.domain.entity.Tag;
import cn.devzyh.toolbox.domain.model.MarkModel;
import cn.devzyh.toolbox.domain.model.SearchModel;
import cn.devzyh.toolbox.domain.model.UrlModel;
import cn.devzyh.toolbox.domain.query.MarkQuery;
import cn.devzyh.toolbox.mapper.MarkMapper;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 网址收藏-业务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkMapper markMapper;

    private final ConfigService configService;

    private final TagService tagService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public Mark get(Long id) {
        Mark mark = markMapper.selectById(id);
        if (mark == null) {
            return null;
        }

        mark.setTags(tagService.findTagIdsByMarkId(mark.getId()));
        return mark;
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
        return markMapper.exists(Wrappers.lambdaQuery(Mark.class).eq(Mark::getId, id));
    }

    /**
     * 保存数据
     *
     * @param mark
     */
    public Mark save(Mark mark) {
        if (exist(mark.getId())) {
            markMapper.updateById(mark);
        } else {
            markMapper.insert(mark);
        }
        List<Long> ids = mark.getTags();
        if (ids == null || ids.isEmpty()) {
            return mark;
        }

        // 保存标签
        tagService.saveMarkTags(mark.getId(), ids);
        log.info("保存收藏数据:{}", StrUtil.toJson(mark));
        return mark;
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void remove(Long id) {
        markMapper.deleteById(id);

        // 保存标签
        tagService.saveMarkTags(id, Collections.emptyList());

        log.info("删除收藏数据:{}", id);
    }

    /**
     * 搜索网址收藏
     *
     * @param query
     * @return
     */
    public SearchModel search(MarkQuery query) {
        MarkModel mm = findMarks(query);

        SearchModel sm = new SearchModel();
        sm.setPage(mm.getPage());
        sm.setUrls(toUrls(mm.getPage().getRecords()));
        sm.setSearchKey(query.getKey());
        sm.setSearchType(WebConstant.SearchType.MARK);
        sm.setPageTitle("搜索”" + sm.getSearchKey() + "“的结果");
        return sm;
    }

    /**
     * Mark转Url
     *
     * @param marks
     */
    List<UrlModel> toUrls(List<Mark> marks) {
        List<UrlModel> urls = new LinkedList<>();
        for (Mark link : marks) {
            UrlModel url = new UrlModel();
            url.setTitle(link.getTitle());
            url.setUrl(link.getUrl());
            url.setRemark(link.getRemark());
            List<String> tags = new LinkedList<>();
            tags.add(link.getSource());
            tags.addAll(tagService.findTagNamesByMarkId(link.getId()));
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
    public MarkModel findMarks(MarkQuery query) {
        LambdaQueryWrapper<Mark> wrapper = Wrappers.lambdaQuery();
        // 精准匹配类型
        if (StrUtil.isNotBlank(query.getSource())) {
            wrapper.eq(Mark::getSource, query.getSource());
        }
        // 模糊查询标题或备注
        if (StrUtil.isNotBlank(query.getKey())) {
            wrapper.apply("(UPPER(title) LIKE CONCAT('%', {0}, '%') OR UPPER(remark) LIKE CONCAT('%', {0}, '%'))",
                    query.getKey().toUpperCase());
        }
        // 排序
        wrapper.orderByDesc(Mark::getId);

        Page<Mark> page = Page.of(query.getPage(), WebConstant.Page.DEFAULT_PAGE_SIZE);
        page = markMapper.selectPage(page, wrapper);
        page.setRecords(translate(page.getRecords()));

        MarkModel mm = new MarkModel();
        mm.setPage(page);
        mm.setSources(findSources());
        return mm;
    }

    /**
     * 字段翻译
     *
     * @param marks
     */
    List<Mark> translate(List<Mark> marks) {
        if (marks == null || marks.isEmpty()) {
            return marks;
        }

        Map<String, String> sources = configService.getCodeMap(ConfigConstant.Type.MARK_SOURCE);
        for (Mark mark : marks) {
            mark.setSource(sources.get(mark.getSource()));
        }
        return marks;
    }

    /**
     * 查询来源平台
     *
     * @return
     */
    public List<Config> findSources() {
        return configService.findConfigByType(ConfigConstant.Type.MARK_SOURCE);
    }

    /**
     * 查询来源平台
     *
     * @return
     */
    public List<Tag> findTags() {
        return tagService.findAll();
    }


    /**
     * 保存链接数据
     *
     * @param mark
     * @return
     */
    public String saveMark(Mark mark, boolean add) {
        if (StrUtil.isBlank(mark.getTitle())) {
            return MessageConstant.Mark.TITLE_NULL;
        }

        if (StrUtil.isBlank(mark.getUrl())) {
            return MessageConstant.Mark.URL_NULL;
        }

        if (StrUtil.isBlank(mark.getSource())) {
            return MessageConstant.Mark.SOURCE_NULL;
        }

        if (add) {
            mark.setId(null);
        } else {
            if (mark.getId() < 1) {
                return MessageConstant.Mark.ID_NULL;
            }
            if (!exist(mark.getId())) {
                return MessageConstant.Mark.NOT_EXIST;
            }
        }

        save(mark);
        return MessageConstant.SUCCESS;
    }

    /**
     * 删除链接数据
     *
     * @param id
     * @return
     */
    public String removeMark(Long id) {
        if (!exist(id)) {
            return MessageConstant.Mark.NOT_EXIST;
        }

        remove(id);
        return MessageConstant.SUCCESS;
    }
}
