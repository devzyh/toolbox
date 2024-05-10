package cn.devzyh.toolbox.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 页面标识常量
 */
public class WebConstant {

    /**
     * 全局标识
     */
    public static class Global {
        public static final String FRIENDS = "friends"; // 友链列表
        public static final String DATA = "data"; // 数据节点
        public static final String ADD = "add"; // 添加数据路径
        public static final String TYPES = "types"; // 类型数据节点
        public static final String OPENS = "opens"; // 方式数据节点
        public static final String SOURCES = "sources"; // 来源数据节点
        public static final String TAGS = "tags"; // 标签数据节点
        public static final String KEY = "key"; // 键值数据节点
    }

    /**
     * 分页常量
     */
    public static class Page {
        public static final long DEFAULT_HOME_SIZE = 12; // 首页页默认数量
        public static final long DEFAULT_PAGE_SIZE = 15; // 每页默认数量
    }

    /**
     * 搜索页类型
     */
    public static class SearchType {
        public static final String LINK = "link"; // 链接
        public static final String MARK = "mark"; // 收藏
    }

    /**
     * 页面路径
     */
    public static class Path {
        public static String LOGIN = "/login";
        public static String DASHBOARD = "/admin/mark";
        public static String REST_PREFIX = "/rest";
        public static String ADMIN_PREFIX = "/admin";
        public static String ADMIN_PATTERN = "/admin/**";
        public static String INIT_SQL = "init/data.sql";
    }

    /**
     * 模板路径
     */
    public static class View {
        /**
         * 前台模板
         */
        public static String INDEX = "index";
        public static String ERROR = "error";
        public static String SEARCH = "search";
        public static String CODE = "tool/code";
        public static String CRON = "tool/cron";
        public static String DIFF = "tool/diff";
        public static String JSON = "tool/json";
        public static String UNIX = "tool/unix";
        public static String XML = "tool/xml";
        public static String XSLT = "tool/xslt";

        /**
         * 后台模板
         */
        public static String LOGIN = "admin/login";
        public static String PASSWORD = "admin/password/index";
        public static String SETTING = "admin/setting/index";
        public static String DICTIONARY = "admin/dictionary/index";
        public static String DICTIONARY_DETAIL = "admin/dictionary/detail";
        public static String LOG = "admin/log/index";
        public static String LINK = "admin/link/index";
        public static String LINK_DETAIL = "admin/link/detail";
        public static String MARK = "admin/mark/index";
        public static String MARK_DETAIL = "admin/mark/detail";
        public static String TAG = "admin/mark/tag";
    }

    /**
     * 重定向参数
     */
    public static class Redirect {
        public static String PREFIX = "redirect:"; // 系统重定向指令
    }

    /**
     * 常量数据
     */
    public static class Data {
        public static final List<String> CODE_SUPPORT_TYPES = Arrays.asList("url", "base64", "md5", "unicode",
                "crypt", "hash", "ttx"); // 加密页面支持的类型
    }

}
