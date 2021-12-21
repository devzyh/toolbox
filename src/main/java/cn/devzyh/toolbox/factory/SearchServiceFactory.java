package cn.devzyh.toolbox.factory;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.service.SearchService;
import cn.devzyh.toolbox.service.impl.SearchArticleServiceImpl;
import cn.devzyh.toolbox.service.impl.SearchFavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class SearchServiceFactory {

    @Autowired
    private WebApplicationContext ctx;

    /**
     * 获取搜索服务类实现
     *
     * @param type
     * @return
     */
    public SearchService getService(String type) {
        ConfigConstant.SearchType searchType = ConfigConstant.SearchType.valueOf(type.toUpperCase());
        SearchService service = null;
        switch (searchType) {
            case ARTICLE:
                service = ctx.getBean(SearchArticleServiceImpl.class);
                break;
            case FAVORITE:
                service = ctx.getBean(SearchFavoriteServiceImpl.class);
                break;
            default:
                break;
        }
        return service;
    }
}
