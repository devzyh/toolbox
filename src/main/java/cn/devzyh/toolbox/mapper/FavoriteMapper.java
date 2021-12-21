package cn.devzyh.toolbox.mapper;

import cn.devzyh.toolbox.domain.Article;
import cn.devzyh.toolbox.domain.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    /**
     * 查询所有工具
     *
     * @return
     */
    List<Favorite> findAll();

    /**
     * 模糊搜索标题
     *
     * @param key
     * @return
     */
    List<Favorite> search(@Param("key") String key);
}
