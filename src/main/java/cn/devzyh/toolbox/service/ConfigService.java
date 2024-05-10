package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.mapper.ConfigMapper;
import cn.devzyh.toolbox.util.Md5Util;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置-业务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigMapper configMapper;

    /**
     * 获取数据
     *
     * @param code
     * @return
     */
    public Config get(String code) {
        return configMapper.selectById(code);
    }

    /**
     * 存在检查
     *
     * @param code
     * @return
     */
    public boolean exist(String code) {
        if (StrUtil.isBlank(code)) {
            return false;
        }
        return configMapper.exists(Wrappers.lambdaQuery(Config.class).eq(Config::getCode, code));
    }

    /**
     * 保存数据
     *
     * @param config
     */
    public Config save(Config config) {
        if (exist(config.getCode())) {
            configMapper.updateById(config);
        } else {
            configMapper.insert(config);
        }
        log.info("保存配置数据:{}", StrUtil.toJson(config));
        return config;
    }

    /**
     * 删除数据
     *
     * @param code
     */
    public void remove(String code) {
        configMapper.deleteById(code);
        log.info("删除配置数据:{}", code);
    }

    /**
     * 加载系统级信息
     *
     * @param model
     */
    public void loadSystem(Model model) {
        List<Config> configs = findConfigByType(ConfigConstant.Type.SYS_WEB);
        for (Config c : configs) {
            model.addAttribute(c.getCode(), c.getData());
        }
    }

    /**
     * 查询指定类型字典对照
     *
     * @param type
     * @return
     */
    public Map<String, String> getCodeMap(String type) {
        Map<String, String> map = new HashMap<>();
        List<Config> configs = findConfigByType(type);
        for (Config config : configs) {
            map.put(config.getCode(), config.getData());
        }
        return map;
    }

    /**
     * 查询指定类型配置数据
     *
     * @param type
     * @return
     */
    public List<Config> findConfigByType(String type) {
        return configMapper.selectList(Wrappers.lambdaQuery(Config.class).likeLeft(Config::getType, type));
    }

    /**
     * 获取指定code的数据
     *
     * @param code
     * @return
     */
    public String getData(String code) {
        Config opt = configMapper.selectById(code);
        if (opt == null) {
            return code;
        } else {
            return opt.getData();
        }
    }

    /**
     * 获取后台默认页面路径
     *
     * @return
     */
    public String getDashboard() {
        String url = getData(ConfigConstant.QueryKey.DASHBOARD);
        if (StrUtil.isBlank(url)) {
            return WebConstant.Path.DASHBOARD;
        } else {
            return url;
        }
    }

    /***
     * 修改密码
     * @param password
     */
    public void updatePassword(String password) {
        Config config = configMapper.selectById(ConfigConstant.QueryKey.SYS_AUTH);
        if (config == null) {
            return;
        }
        config.setData(Md5Util.password(password));
        configMapper.updateById(config);
    }

}
