package cn.devzyh.toolbox.mapper;

import cn.devzyh.toolbox.domain.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收藏标签-数据层
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询指定收藏关联标签
     *
     * @param markId
     * @return
     */
    List<Tag> findTagsByMarkId(Long markId);

}
