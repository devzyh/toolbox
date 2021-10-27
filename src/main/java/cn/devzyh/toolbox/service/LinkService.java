package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.domain.Link;

import java.util.List;
import java.util.Map;

/**
 * 工具页面服务层
 */
public interface LinkService {

    /**
     * 获取分组及工具详情
     *
     * @return
     */
    Map<String, List<Link>> getLinks();
}
