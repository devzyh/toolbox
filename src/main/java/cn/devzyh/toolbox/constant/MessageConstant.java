package cn.devzyh.toolbox.constant;

/**
 * 消息常量
 */
public class MessageConstant {

    /**
     * 全局消息
     */
    public static final String SUCCESS = "success"; // 成功

    /**
     * 成功
     *
     * @param msg
     * @return
     */
    public static boolean isSuccess(String msg) {
        return SUCCESS.equalsIgnoreCase(msg);
    }

    /**
     * 错误
     *
     * @param msg
     * @return
     */
    public static boolean isError(String msg) {
        return !isSuccess(msg);
    }

    /**
     * 认证消息
     */
    public static class Auth {
        public static final String PASSWORD_NULL = "登录密码不能为空";
        public static final String PASSWORD_ERROR = "登录密码错误";
        public static final String NEW_PASSWORD_NULL = "修改后密码不能为空";
        public static final String PASSWORD_REPEAT = "修改前后密码相同";
    }

    /**
     * 设置消息
     */
    public static class Setting {
        public static final String PART_NULL = "部分信息为空跳过更新";
    }

    /**
     * 字典消息
     */
    public static class Dictionary {
        public static final String CODE_NULL = "编码不能为空";
        public static final String DATA_NULL = "数据不能为空";
        public static final String NOT_EXIST = "配置项不存在";
        public static final String CODE_EXIST = "当前CODE已存在";
    }

    /**
     * 链接消息
     */
    public static class Link {
        public static final String ID_NULL = "ID不能为空";
        public static final String TITLE_NULL = "标题不能为空";
        public static final String URL_NULL = "地址不能为空";
        public static final String TYPE_NULL = "类型不能为空";
        public static final String OPEN_NULL = "打开方式不能为空";
        public static final String SORT_NULL = "排序不能为空";
        public static final String NOT_EXIST = "链接数据不存在";
    }

    /**
     * 标签消息
     */
    public static class Tag {
        public static final String ID_NULL = "ID不能为空";
        public static final String NAME_NULL = "名称不能为空";
        public static final String NOT_EXIST = "标签数据不存在";
        public static final String EXIST_MARK = "标签下存在关联收藏";
    }

    /**
     * 收藏消息
     */
    public static class Mark {
        public static final String ID_NULL = "ID不能为空";
        public static final String TITLE_NULL = "标题不能为空";
        public static final String URL_NULL = "地址不能为空";
        public static final String SOURCE_NULL = "来源不能为空";
        public static final String NOT_EXIST = "收藏数据不存在";
    }
}
