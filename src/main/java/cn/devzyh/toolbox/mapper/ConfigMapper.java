package cn.devzyh.toolbox.mapper;

import cn.devzyh.toolbox.domain.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置-数据层
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}
