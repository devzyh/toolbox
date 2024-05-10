package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.domain.entity.Config;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 字典页数据
 */
@Data
public class DictionaryModel {

    /**
     * 分页数据
     */
    Page<Config> page;

    /**
     * 类型
     */
    private List<String> types;

}
