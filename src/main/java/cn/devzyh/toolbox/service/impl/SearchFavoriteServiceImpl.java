package cn.devzyh.toolbox.service.impl;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.domain.Favorite;
import cn.devzyh.toolbox.domain.ResultVO;
import cn.devzyh.toolbox.domain.SearchVO;
import cn.devzyh.toolbox.mapper.FavoriteMapper;
import cn.devzyh.toolbox.service.ConfigService;
import cn.devzyh.toolbox.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 文章搜索服务
 */
@Service
public class SearchFavoriteServiceImpl implements SearchService {

    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private ConfigService configService;

    @Override
    public SearchVO search(String key) {
        // 获取基本信息
        SearchVO searchVo = new SearchVO();
        searchVo.setSearchKey(key);
        searchVo.setSearchType(ConfigConstant.SearchType.ARTICLE.getValue());
        if (key.equals("")) {
            searchVo.setPageTitle("收藏 - ");
        } else {
            searchVo.setPageTitle(key + " - 收藏 - ");
        }

        // 翻译对照
        Map<String, String> imageMap = configService.getConfigMapByItem(ConfigConstant.Item.FAVORITE_ITEM_IMAGE);

        // 获取文章信息
        List<ResultVO> resultVOList = new LinkedList<>();
        List<Favorite> articleList = favoriteMapper.search(key);
        LocalDate now = LocalDate.now();
        for (Favorite favorite : articleList) {
            ResultVO resultVo = new ResultVO();
            resultVo.setTitle(favorite.getName());
            resultVo.setUrl(favorite.getHref());
            resultVo.setPostDate(now);
            resultVo.setDigest(favorite.getRemark());
            resultVo.setAvatar(imageMap.get(favorite.getItem()));
            resultVo.setSource(favorite.getItem());
            resultVo.setTags(Collections.singletonList(favorite.getItem()));
            resultVOList.add(resultVo);
        }
        searchVo.setResultList(resultVOList);
        return searchVo;
    }

}
