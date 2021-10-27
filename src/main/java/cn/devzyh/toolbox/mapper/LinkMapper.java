package cn.devzyh.toolbox.mapper;

import cn.devzyh.toolbox.domain.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LinkMapper {

    /**
     * 查询所有工具
     *
     * @return
     */
    List<Link> findAll();
}
