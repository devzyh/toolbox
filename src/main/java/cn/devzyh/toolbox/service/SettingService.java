package cn.devzyh.toolbox.service;

import cn.devzyh.toolbox.constant.ConfigConstant;
import cn.devzyh.toolbox.constant.MessageConstant;
import cn.devzyh.toolbox.domain.entity.Config;
import cn.devzyh.toolbox.domain.form.PasswordForm;
import cn.devzyh.toolbox.domain.form.SettingForm;
import cn.devzyh.toolbox.util.Md5Util;
import cn.devzyh.toolbox.util.StrUtil;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * 系统配置-业务层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SettingService {

    private final ConfigService configService;

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 修改密码
     *
     * @param form
     * @return
     */
    public String changePassword(PasswordForm form) {
        if (StrUtil.isBlank(form.getOriginalPassword())) {
            return MessageConstant.Auth.PASSWORD_NULL;
        }
        if (StrUtil.isBlank(form.getPassword())) {
            return MessageConstant.Auth.NEW_PASSWORD_NULL;
        }
        if (form.getOriginalPassword().equals(form.getPassword())) {
            return MessageConstant.Auth.PASSWORD_REPEAT;
        }

        String localPassword = configService.getData(ConfigConstant.QueryKey.SYS_AUTH);
        String originalPassword = Md5Util.password(form.getOriginalPassword());
        if (!originalPassword.equalsIgnoreCase(localPassword)) {
            return MessageConstant.Auth.PASSWORD_ERROR;
        }

        configService.updatePassword(form.getPassword());

        return MessageConstant.SUCCESS;
    }

    /**
     * 保存设置信息
     *
     * @param form
     * @return
     */
    public String saveSetting(SettingForm form) {
        BeanMap map = BeanMap.create(form);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Config config = configService.get(key);
            String val = map.get(key).toString();
            config.setData(val);
            configService.save(config);
        }

        return MessageConstant.SUCCESS;
    }

    /**
     * 导出系统数据
     */
    public String export(HttpServletResponse response) {
        log.info("开始导出数据");

        // 获取数据
        List<Object> result = SqlRunner.db().selectObjs("SCRIPT");
        if (result == null || result.isEmpty()) {
            return "导出数据为空";
        }

        // 提取SQL
        String rn = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        for (Object obj : result) {
            String row = obj.toString();
            if (!row.startsWith("INSERT INTO")) {
                continue;
            }

            String[] split = row.split(rn);
            String insertPrefix = "";
            for (int i = 0; i < split.length; i++) {
                String line = split[i];
                if (i == 0) {
                    insertPrefix = line.replace(rn, " ");
                    continue;
                }
                builder.append(insertPrefix);
                if (i < split.length - 1) {
                    line = line.substring(0, line.lastIndexOf(",")) + ";";
                }
                builder.append(line);
                builder.append(rn);
            }
            builder.append(rn);
        }

        String fileName = "data_" + LocalDateTime.now().format(DF) + ".sql";
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        String msg = "数据导出成功";
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(builder.toString().getBytes(StandardCharsets.UTF_8));
            os.close();
        } catch (IOException e) {
            log.error("备份数据异常", e);
            msg = "数据导出失败：" + e.getMessage();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("数据流关闭异常", e);
                }
            }
        }

        log.info("数据导出完成");

        return msg;
    }

}
