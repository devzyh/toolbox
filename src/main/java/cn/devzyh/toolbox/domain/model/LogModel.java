package cn.devzyh.toolbox.domain.model;

import cn.devzyh.toolbox.domain.entity.Log;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 日志页数据
 */
@Data
public class LogModel {

    /**
     * 分页数据
     */
    Page<Log> page;

}
