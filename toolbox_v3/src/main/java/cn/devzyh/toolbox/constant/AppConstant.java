package cn.devzyh.toolbox.constant;

import cn.devzyh.toolbox.domain.app.AppData;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统常量
 */
public class AppConstant {

    // 文本处理页面支持的类型
    public static Map<String, String> SUPPORT_TYPES = new LinkedHashMap<>();

    static {
        SUPPORT_TYPES.put("url", "URL编码");
        SUPPORT_TYPES.put("base64", "BASE64编码");
        SUPPORT_TYPES.put("md5", "MD5加密");
        SUPPORT_TYPES.put("unicode", "Unicode编码");
        SUPPORT_TYPES.put("ttx", "TTX加解密");
    }

    /**
     * 数据文件地址
     */
    public static final String DATA_FILE = "init/data.json";

    /**
     * 全局系统数据
     */
    public static AppData APP_DATA = null;

    /**
     * 全局视图数据
     */
    public static Map<String, Object> VIEW_DATA = null;

    /**
     * JSON处理对象
     */
    public static final JsonMapper JSON = new JsonMapper();

    /**
     * 模板数据
     */
    public static class ViewKey {
        // 错误信息
        public static String ERROR_MSG = "message";
        // 链接页数据
        public static String MORE = "more";
        // 文本处理类型
        public static String TYPE = "type";
        // 类型对应名称
        public static String TYPE_TEXT = "typeText";
        // 支持类型数据
        public static String SUPPORT_TYPES = "supportTypes";
    }

    /**
     * 模板路径
     */
    public static class ViewPath {
        public static String INDEX = "index";
        public static String MORE = "more";
        public static String ERROR = "error";
        public static String JSON = "tool/json";
        public static String XML = "tool/xml";
        public static String DIFF = "tool/diff";
        public static String XSLT = "tool/xslt";
        public static String CODE = "tool/code";
        public static String UNIX = "tool/unix";
    }

}
