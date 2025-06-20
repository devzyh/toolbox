package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.domain.SearchVO;

/**
 * 搜索服务
 */
public interface SearchService {

    /**
     * 搜索内容
     *
     * @param key 关键词
     * @return
     */
    SearchVO search(String key);
}
