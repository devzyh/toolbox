package cn.devzyh.toolbox.constant;

/**
 * 配置表相关常量
 */
public class ConfigConstant {

    /**
     * 配置大类
     */
    public enum Item {
        SITE_INFO("SITE_INFO"), // 站点信息
        ARTICLE_TAG("ARTICLE_TAG"), // 文章标签
        ARTICLE_SOURCE("ARTICLE_SOURCE"), // 文章来源平台
        ARTICLE_SOURCE_IMAGE("ARTICLE_SOURCE_IMAGE"), // 文章来源平台图像
        FAVORITE_ITEM_IMAGE("FAVORITE_ITEM_IMAGE"); // 收藏分组图像

        private final String item;

        Item(String item) {
            this.item = item;
        }

        public String getValue() {
            return this.item;
        }
    }

    /**
     * 搜索类型
     */
    public enum SearchType {
        ARTICLE("article"), // 文章数据源
        FAVORITE("favorite"); // 收藏数据源

        private final String type;

        SearchType(String type) {
            this.type = type;
        }

        public String getValue() {
            return this.type;
        }
    }
}
