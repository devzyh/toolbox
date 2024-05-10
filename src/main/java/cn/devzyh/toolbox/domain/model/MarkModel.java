package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.entity.Mark;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 文章收藏页数据
 */
@Data
public class MarkModel {

    /**
     * 分页数据
     */
    private Page<Mark> page;

    /**
     * 类型
     */
    private List<Config> sources;

}
