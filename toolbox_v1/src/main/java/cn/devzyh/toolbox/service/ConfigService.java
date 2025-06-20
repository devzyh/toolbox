package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;

import java.util.Map;

public interface ConfigService {

    /**
     * 获取指定分组对照表
     *
     * @return
     */
    Map<String, String> getConfigMapByItem(ConfigConstant.Item item);


    /**
     * 获取站点信息
     *
     * @return
     */
    Map<String, String> getSiteInfo();

}
