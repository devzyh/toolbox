package cn.devzyh.toolbox.constant;

/**
 * 配置表相关常量
 */
public class ConfigConstant {

    /**
     * 配置大类
     */
    public enum Item {
        SITE_INFO("SITE_INFO"); // 站点信息

        private final String item;

        Item(String item) {
            this.item = item;
        }

        public String getValue() {
            return this.item;
        }
    }
}
