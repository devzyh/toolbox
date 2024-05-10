package cn.devzyh.toolbox.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * 字符串工具类
 */
public class StrUtil {

    /**
     * 内容格式化
     */
    private static final ObjectMapper JSON = new JsonMapper();

    /**
     * 是否为空字符串
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 是否为非空字符串
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 对象JSON字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

}
