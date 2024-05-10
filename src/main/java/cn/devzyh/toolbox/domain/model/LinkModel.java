package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.entity.Link;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 首页链接页数据
 */
@Data
public class LinkModel {

    /**
     * 分页数据
     */
    private Page<Link> page;

    /**
     * 类型
     */
    private List<Config> types;

}
