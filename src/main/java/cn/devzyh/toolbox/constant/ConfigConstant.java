package cn.devzyh.toolbox.constant;

/**
 * 系统配置常量
 */
public class ConfigConstant {

    /**
     * 配置类型
     */
    public static class Type {
        public static final String SYS_AUTH = "SYS_AUTH"; // 认证信息
        public static final String SYS_WEB = "SYS_WEB"; // 基本信息
        public static final String MARK_SOURCE = "MARK_SOURCE"; // 收藏来源
        public static final String LINK_TYPE = "LINK_TYPE"; // 链接分类
        public static final String OPEN_TYPE = "OPEN_TYPE"; // 打开方式
    }

    /**
     * 查询参数值
     */
    public static class QueryKey {
        public static final String SYS_CONFIG = "SYS_%"; // 系统级配置前缀
        public static final String SYS_AUTH = "auth"; // 身份认证code
        public static final String DASHBOARD = "dashboard"; // 后台默认页面配置
    }

    /**
     * 链接分类
     */
    public static class LinkType {
        public static final String EDITOR = "editor"; // 数据编辑
        public static final String HELPER = "helper"; // 效率工具
        public static final String DOCUMENT = "document"; // 文档链接
        public static final String BLOG = "blog"; // 技术博客
        public static final String FRIEND = "friend"; // 友情链接
    }
}
