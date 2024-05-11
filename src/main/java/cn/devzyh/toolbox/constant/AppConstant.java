package cn.devzyh.toolbox.constant;

import cn.devzyh.toolbox.domain.app.AppData;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统常量
 */
public class AppConstant {

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
     * 模板路径
     */
    public static class ViewPath {
        public static String INDEX = "index";
        public static String LIST = "list";
        public static String ERROR = "error";
        public static String JSON = "json";
        public static String XML = "xml";
        public static String DIFF = "diff";
        public static String XSLT = "xslt";
        public static String UNIX = "unix";
        public static String CODE = "code";
    }

    /**
     * 加密页面支持的类型
     */
    public static final List<String> CODE_TYPES = Arrays.asList("url", "base64", "md5", "unicode", "ttx");

}
