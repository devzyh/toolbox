package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;

import java.util.Map;

public interface ConfigService {

    /**
     * 获取站点信息
     *
     * @return
     */
    Map<String, String> getSiteInfo();

    /**
     * 获取文章标签对照
     *
     * @return
     */
    Map<String, String> getArticleTag();

    /**
     * 获取文章来源对照
     *
     * @return
     */
    Map<String, String> getArticleSource();

    /**
     * 获取文章图像对照
     *
     * @return
     */
    Map<String, String> getArticleSourceImage();

}
