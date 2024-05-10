package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.domain.entity.MarkTag;
import cn.devzyh.toolbox.domain.entity.Tag;
import cn.devzyh.toolbox.mapper.MarkTagMapper;
import cn.devzyh.toolbox.mapper.TagMapper;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网址标签-业务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapper tagMapper;

    private final MarkTagMapper markTagMapper;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public Tag get(Long id) {
        return tagMapper.selectById(id);
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
        return tagMapper.exists(Wrappers.lambdaQuery(Tag.class).eq(Tag::getId, id));
    }

    /**
     * 保存数据
     *
     * @param tag
     */
    public Tag save(Tag tag) {
        if (exist(tag.getId())) {
            tagMapper.updateById(tag);
        } else {
            tagMapper.insert(tag);
        }
        log.info("保存标签数据:{}", StrUtil.toJson(tag));
        return tag;
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void remove(Long id) {
        tagMapper.deleteById(id);
        log.info("删除标签数据:{}", id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Tag> findAll() {
        return tagMapper.selectList(null);
    }


    /**
     * 保存标签数据
     *
     * @param tag
     * @return
     */
    public String saveTag(Tag tag) {
        if (StrUtil.isBlank(tag.getName())) {
            return MessageConstant.Tag.NAME_NULL;
        }

        if (tag.getId() != null && !exist(tag.getId())) {
            return MessageConstant.Link.NOT_EXIST;
        }

        save(tag);
        return MessageConstant.SUCCESS;
    }

    /**
     * 删除标签数据
     *
     * @param id
     * @return
     */
    public String removeTag(Long id) {
        if (!exist(id)) {
            return MessageConstant.Tag.NOT_EXIST;
        }

        // 关联检查
        if (markTagMapper.exists(Wrappers.lambdaQuery(MarkTag.class).eq(MarkTag::getTagId, id))) {
            return MessageConstant.Tag.EXIST_MARK;
        }

        remove(id);
        return MessageConstant.SUCCESS;
    }

    /**
     * 查询关联标签名称
     *
     * @param markId
     * @return
     */
    List<String> findTagNamesByMarkId(Long markId) {
        List<String> names = new LinkedList<>();
        List<Tag> tags = tagMapper.findTagsByMarkId(markId);
        for (Tag tag : tags) {
            names.add(tag.getName());
        }
        return names;
    }

    /**
     * 查询关联标签ID
     *
     * @param markId
     * @return
     */
    List<Long> findTagIdsByMarkId(Long markId) {
        return markTagMapper.selectList(Wrappers.lambdaQuery(MarkTag.class)
                        .eq(MarkTag::getMarkId, markId))
                .stream().map(MarkTag::getTagId).collect(Collectors.toList());
    }

    /**
     * 保存收藏标签
     *
     * @param markId
     * @param tagIds
     */
    void saveMarkTags(Long markId, List<Long> tagIds) {
        markTagMapper.delete(Wrappers.lambdaQuery(MarkTag.class).eq(MarkTag::getMarkId, markId));
        for (Long tagId : tagIds) {
            MarkTag mt = new MarkTag();
            mt.setMarkId(markId);
            mt.setTagId(tagId);
            markTagMapper.insert(mt);
        }
    }

}
