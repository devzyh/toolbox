package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.constant.WebConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.model.DictionaryModel;
import cn.devzyh.toolbox.domain.query.DictionaryQuery;
import cn.devzyh.toolbox.mapper.ConfigMapper;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典-业务层
 */
@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final ConfigMapper configMapper;

    private final ConfigService configService;

    /**
     * 查询数据字典
     *
     * @return
     */
    public DictionaryModel findDictionaries(DictionaryQuery query) {
        LambdaQueryWrapper<Config> wrapper = Wrappers.lambdaQuery();
        // 排除系统类型
        wrapper.notLike(Config::getType, ConfigConstant.QueryKey.SYS_CONFIG);
        // 精准匹配类型
        if (StrUtil.isNotBlank(query.getType())) {
            wrapper.eq(Config::getType, query.getType());
        }
        // 模糊查询编码
        if (StrUtil.isNotBlank(query.getCode())) {
            wrapper.like(Config::getCode, query.getCode());
        }
        // 模糊查询数据
        if (StrUtil.isNotBlank(query.getData())) {
            wrapper.like(Config::getData, query.getData());
        }
        // 排序
        wrapper.orderByAsc(Config::getType);
        wrapper.orderByAsc(Config::getCode);

        Page<Config> page = Page.of(query.getPage(), WebConstant.Page.DEFAULT_PAGE_SIZE);
        page = configMapper.selectPage(page, wrapper);

        DictionaryModel dm = new DictionaryModel();
        dm.setPage(page);
        dm.setTypes(findTypes());
        return dm;
    }

    /**
     * 查询数据字典分组
     *
     * @return
     */
    public List<String> findTypes() {
        LambdaQueryWrapper<Config> wrapper = Wrappers.lambdaQuery();
        // 查询
        wrapper.select(Config::getType);
        // 排除系统类型
        wrapper.notLike(Config::getType, ConfigConstant.QueryKey.SYS_CONFIG);
        // 排除空类型
        wrapper.isNotNull(Config::getType);
        wrapper.ne(Config::getType, "");
        // 分组
        wrapper.groupBy(Config::getType);
        List<String> types = configMapper.selectObjs(wrapper)
                .stream().map(String::valueOf)
                .collect(Collectors.toList());
        return types;
    }

    /**
     * 保存信息
     *
     * @param config
     * @return
     */
    public String save(Config config, boolean add) {
        if (StrUtil.isBlank(config.getCode())) {
            return MessageConstant.Dictionary.CODE_NULL;
        }
        if (StrUtil.isBlank(config.getData())) {
            return MessageConstant.Dictionary.DATA_NULL;
        }

        if (add) {
            if (WebConstant.Global.ADD.equalsIgnoreCase(config.getCode())) {
                return MessageConstant.Dictionary.CODE_EXIST;
            }
            if (configService.exist(config.getCode())) {
                return MessageConstant.Dictionary.CODE_EXIST;
            }
        } else {
            if (!configService.exist(config.getCode())) {
                return MessageConstant.Dictionary.NOT_EXIST;
            }
        }

        configService.save(config);
        return MessageConstant.SUCCESS;
    }

    /**
     * 删除信息
     *
     * @return
     */
    public String remove(String code) {
        if (!configService.exist(code)) {
            return MessageConstant.Dictionary.NOT_EXIST;
        }

        configService.remove(code);
        return MessageConstant.SUCCESS;
    }

}
