package cn.devzyh.toolbox.config;

import cn.devzyh.toolbox.constant.AppConstant;
import cn.devzyh.toolbox.domain.app.AppData;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 系统加载
 */
@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        try {
            AppConstant.APP_DATA = AppConstant.JSON.readValue(ResourceUtil.getStream(AppConstant.DATA_FILE), AppData.class);
            AppConstant.VIEW_DATA = Convert.convert(Map.class, AppConstant.APP_DATA);
            log.info("系统数据加载完成");
        } catch (IOException e) {
            log.error("系统数据加载失败：{}", e.getMessage(), e);
        }
    }

}
