package cn.devzyh.toolbox.mapper;

import cn.devzyh.toolbox.domain.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 首页链接-数据层
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
