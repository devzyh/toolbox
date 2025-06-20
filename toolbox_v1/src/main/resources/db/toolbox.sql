/*
 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 27/04/2022 16:31:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `digest` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容摘要',
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源平台',
  `created` date NULL DEFAULT NULL COMMENT '发布日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '一文带你入门 JMeter 性能测试！\n', 'https://mp.weixin.qq.com/s/RczbJW_1gX_0s1fNs4K1dw', NULL, 'wxmp', '2021-10-26');
INSERT INTO `article` VALUES (2, '读懂Redis源码，我总结了这7点心得', 'https://mp.weixin.qq.com/s/h-OEbiUUmKvUZqxmjfmgtg', NULL, 'wxmp', '2021-09-27');
INSERT INTO `article` VALUES (3, '七步制作精简镜像', 'https://mp.weixin.qq.com/s/cSWHvIntrebJHzC53SM2lg', NULL, 'wxmp', '2021-10-27');
INSERT INTO `article` VALUES (4, 'Spring中涉及的设计模式总结', 'https://mp.weixin.qq.com/s/m1V_FtlwP4cttcqy7sILMw', NULL, 'wxmp', '2021-09-02');
INSERT INTO `article` VALUES (5, '阿里技术人看什么书？这个春节和你共享书单！', 'https://mp.weixin.qq.com/s/mDLZ7luWknk7TvQIQjZbBg', NULL, 'wxmp', '2021-02-12');
INSERT INTO `article` VALUES (6, '武汉有哪些不错的互联网公司？', 'https://mp.weixin.qq.com/s/fhMkMMqTrAe-8Af6Jk4XAg', NULL, 'wxmp', '2021-10-21');
INSERT INTO `article` VALUES (7, '聊聊订单系统的设计？', 'https://mp.weixin.qq.com/s/k8EXOaqjNIvWMgNzI4fJ3g', NULL, 'wxmp', '2021-09-10');
INSERT INTO `article` VALUES (8, '【Gradle教程】Gradle 基础入门', 'https://www.cnblogs.com/hellxz/p/helloworld-gradle.html', NULL, 'cnblogs', '2020-05-30');
INSERT INTO `article` VALUES (9, '算法执行过程可视化网站', 'https://www.cs.usfca.edu/~galles/visualization/Algorithms.html', NULL, 'direct', '2021-11-02');
INSERT INTO `article` VALUES (10, '经典面试题：在浏览器地址栏输入一个 URL 后回车，背后发生了什么', 'https://www.cnblogs.com/cswiki/p/14429730.html', NULL, 'cnblogs', '2021-02-22');
INSERT INTO `article` VALUES (11, '关于 TCP 三次握手和四次挥手，满分回答在此', 'https://mp.weixin.qq.com/s/u56NcMs68sgi6uDpzJ61yw', NULL, 'wxmp', '2021-01-07');
INSERT INTO `article` VALUES (12, '别再恐惧 IP 协议（万字长文 | 多图预警）\n', 'https://mp.weixin.qq.com/s/NO9RDt1A3T1rz-Q4_Y0gPw', NULL, 'wxmp', '2021-01-15');
INSERT INTO `article` VALUES (13, '48 张图 | 手摸手教你微服务的性能监控、压测和调优\n', 'https://www.cnblogs.com/jackson0714/p/performance.html', NULL, 'cnblogs', '2021-03-17');
INSERT INTO `article` VALUES (14, 'Dubbo 与 Spring Cloud 完美结合\n', 'https://www.cnblogs.com/babycomeon/p/11546737.html', NULL, 'cnblogs', '2019-09-19');
INSERT INTO `article` VALUES (15, '在Git的PR(Pull Request)提示冲突无法merge合并的解决方案', 'https://www.cnblogs.com/xxcanghai/p/12160556.html', NULL, 'cnblogs', '2020-01-07');
INSERT INTO `article` VALUES (16, 'Java：CAS(乐观锁)', 'https://www.jianshu.com/p/ae25eb3cfb5d', NULL, 'jianshu', '2018-07-16');
INSERT INTO `article` VALUES (17, 'java多线程高并发', 'https://www.cnblogs.com/sx66/p/12623437.html', NULL, 'cnblogs', '2020-04-02');
INSERT INTO `article` VALUES (18, '2020年最新78道JVM面试题总结（含答案解析和思维导图）', 'https://www.cnblogs.com/zhuifeng523/p/13172080.html', NULL, 'cnblogs', '2020-06-21');
INSERT INTO `article` VALUES (19, 'Ubuntu20.04 apt方式安装MySQL', 'https://blog.csdn.net/lianghecai52171314/article/details/113807099', NULL, 'csdn', '2021-02-14');
INSERT INTO `article` VALUES (20, 'AOP面试造火箭始末', 'https://www.cnblogs.com/xiekun/p/14471595.html', NULL, 'cnblogs', '2021-03-02');
INSERT INTO `article` VALUES (21, 'MySQL Explain详解', 'https://www.cnblogs.com/tufujie/p/9413852.html', NULL, 'cnblogs', '2018-08-03');
INSERT INTO `article` VALUES (22, 'BigDecimal操作', 'https://blog.csdn.net/javaNiuLei12/article/details/54091258', NULL, 'csdn', '2017-01-05');
INSERT INTO `article` VALUES (23, '5分钟让你掌握Vuex，深入浅出', 'https://www.cnblogs.com/maoqian/p/15428820.html', NULL, 'cnblogs', '2021-10-20');
INSERT INTO `article` VALUES (24, '被敖丙用烂的「数据库调优」连招？真香，淦！', 'https://mp.weixin.qq.com/s?__biz=MzAwNDA2OTM1Ng==&mid=2453143331&idx=1&sn=e387e1b1beb4cd516ee2d67ce934115e&scene=21#wechat_redirect', NULL, 'wxmp', '2020-11-17');
INSERT INTO `article` VALUES (25, '为什么IDEA不推荐你使用@Autowired ？', 'https://mp.weixin.qq.com/s/Nu25_5mMtD8nboPj8m7lyA', NULL, 'wxmp', '2021-11-05');
INSERT INTO `article` VALUES (26, 'LocalDateTime, LocalDate, Instant, Long 相互转换', 'https://blog.csdn.net/qq_19266669/article/details/106743102', NULL, 'csdn', '2020-06-14');
INSERT INTO `article` VALUES (27, '英语零基础，有什么自学方法？', 'https://www.zhihu.com/question/36449108/answer/756802719', NULL, 'zhihu', '2019-07-21');
INSERT INTO `article` VALUES (28, 'BBC减肥十律', 'https://zhuanlan.zhihu.com/p/412545357', NULL, 'zhihu', '2019-09-21');
INSERT INTO `article` VALUES (29, '蓝绿部署、金丝雀发布（灰度发布）、AB测试都是啥？', 'https://mp.weixin.qq.com/s/3ux2NTgtnXG34NjT2HNW-A', NULL, 'wxmp', '2021-11-13');
INSERT INTO `article` VALUES (30, 'Spring Boot 2.6之后，动态权限控制终于可以用起来了！', 'https://mp.weixin.qq.com/s/Tki0SWTq37EYXVNtlgYszQ', NULL, 'wxmp', '2021-11-22');
INSERT INTO `article` VALUES (31, '详细介绍mysql索引类型：FULLTEXT、NORMAL、SPATIAL、UNIQUE', 'https://blog.csdn.net/guo_qiangqiang/article/details/88794971', NULL, 'csdn', '2019-03-25');
INSERT INTO `article` VALUES (32, 'MySQL存储引擎', 'https://www.cnblogs.com/birdy-silhouette/p/15066260.html', NULL, 'cnblogs', '2021-07-27');
INSERT INTO `article` VALUES (33, '妙用Java 8中的 Function接口 消灭if...else（非常新颖的写法）', 'https://mp.weixin.qq.com/s/5FmD0PtPsQFPiImMiLC_eg', NULL, 'wxmp', '2021-12-05');
INSERT INTO `article` VALUES (34, 'MySQL慢查询，一口从天而降的锅！', 'https://blog.csdn.net/qq_39390545/article/details/116139445', NULL, 'csdn', '2021-04-25');
INSERT INTO `article` VALUES (35, 'Redis 实现限流的三种方式', 'https://blog.csdn.net/lmx125254/article/details/90700118', NULL, 'csdn', '2019-05-30');
INSERT INTO `article` VALUES (36, '并发模拟的四种方式 + 工具，超级实用！', 'https://mp.weixin.qq.com/s/B5Kx6fKAj_B78mLPMaFZtw', NULL, 'wxmp', '2021-12-17');
INSERT INTO `article` VALUES (37, 'SQL 优化极简法则，还有谁不会？', 'https://mp.weixin.qq.com/s/NS2trUx9nVN5gNquVVbruw', NULL, 'wxmp', '2021-12-14');
INSERT INTO `article` VALUES (38, 'Java位运算符：Java移位运算符、复合位赋值运算符及位逻辑运算符', 'http://c.biancheng.net/view/784.html', NULL, 'direct', '2021-12-25');
INSERT INTO `article` VALUES (39, '作为技术负责人，如何从0搭建公司后端技术栈', 'https://mp.weixin.qq.com/s/v8E4FKu6fIhXb1v_WtlYZw', NULL, 'wxmp', '2022-01-12');
INSERT INTO `article` VALUES (40, 'Spring MVC中处理Request和Response的策略', 'https://mp.weixin.qq.com/s/DQEI0ZTrJuVjjONOzmnLYg', NULL, 'wxmp', '2022-01-12');
INSERT INTO `article` VALUES (41, '一文教会你领域建模', 'https://mp.weixin.qq.com/s/3x4fK7rtA2US9fxoKpoAiA', NULL, 'wxmp', '2021-12-30');
INSERT INTO `article` VALUES (42, '生成订单30分钟未支付，则自动取消，该怎么实现？', 'https://mp.weixin.qq.com/s/QOQhsXWQDqXsWngQUGOk5w', NULL, 'wxmp', '2022-01-25');
INSERT INTO `article` VALUES (43, '几种常见的JVM调优场景（建议收藏）', 'https://mp.weixin.qq.com/s/35uO5VQnDZGVZXWnaUbaqg', NULL, 'wxmp', '2022-01-24');
INSERT INTO `article` VALUES (44, 'git-stash用法小结', 'https://www.cnblogs.com/tocy/p/git-stash-reference.html', NULL, 'cnblogs', '2016-10-31');
INSERT INTO `article` VALUES (45, 'RabbitMQ 如何保证消息不丢失？', 'https://www.cnblogs.com/cnndevelop/p/12091348.html', NULL, 'cnblogs', '2019-12-24');
INSERT INTO `article` VALUES (46, 'Spring OAuth2 开发指南（一）：体系架构和开发概览', 'https://www.jianshu.com/p/45f6d4d0574a', NULL, 'jianshu', '2021-08-09');
INSERT INTO `article` VALUES (47, 'SQL优化万能公式：5 大步骤 + 10 个案例', 'https://mp.weixin.qq.com/s/YAu_DG1YN_7pEizlZCqhew', NULL, 'wxmp', '2022-03-31');
INSERT INTO `article` VALUES (48, '判断时间是否有重叠', 'https://www.cnblogs.com/luyuqiang/p/14990941.html', NULL, 'cnblogs', '2021-07-09');
INSERT INTO `article` VALUES (49, 'MongoDB 4.x 的权限管理说明', 'https://www.jianshu.com/p/e95c22c4ed43', NULL, 'jianshu', '2020-05-23');

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `article_id` int(11) NOT NULL COMMENT '文章ID',
  `tag_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签代码',
  PRIMARY KEY (`article_id`, `tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES (1, 'test');
INSERT INTO `article_tag` VALUES (2, 'redis');
INSERT INTO `article_tag` VALUES (3, 'docker');
INSERT INTO `article_tag` VALUES (4, 'design_pattern');
INSERT INTO `article_tag` VALUES (4, 'spring');
INSERT INTO `article_tag` VALUES (5, 'book');
INSERT INTO `article_tag` VALUES (5, 'java');
INSERT INTO `article_tag` VALUES (6, 'interview');
INSERT INTO `article_tag` VALUES (7, 'funcational_design');
INSERT INTO `article_tag` VALUES (8, 'tool');
INSERT INTO `article_tag` VALUES (9, 'algorithm');
INSERT INTO `article_tag` VALUES (10, 'network');
INSERT INTO `article_tag` VALUES (11, 'network');
INSERT INTO `article_tag` VALUES (12, 'network');
INSERT INTO `article_tag` VALUES (13, 'microservice');
INSERT INTO `article_tag` VALUES (13, 'test');
INSERT INTO `article_tag` VALUES (14, 'dubbo');
INSERT INTO `article_tag` VALUES (14, 'microservice');
INSERT INTO `article_tag` VALUES (15, 'git');
INSERT INTO `article_tag` VALUES (15, 'tool');
INSERT INTO `article_tag` VALUES (16, 'java');
INSERT INTO `article_tag` VALUES (17, 'interview');
INSERT INTO `article_tag` VALUES (17, 'java');
INSERT INTO `article_tag` VALUES (18, 'interview');
INSERT INTO `article_tag` VALUES (18, 'java');
INSERT INTO `article_tag` VALUES (18, 'jvm');
INSERT INTO `article_tag` VALUES (19, 'linux');
INSERT INTO `article_tag` VALUES (19, 'mysql');
INSERT INTO `article_tag` VALUES (20, 'java');
INSERT INTO `article_tag` VALUES (20, 'spring');
INSERT INTO `article_tag` VALUES (21, 'mysql');
INSERT INTO `article_tag` VALUES (22, 'java');
INSERT INTO `article_tag` VALUES (23, 'vue');
INSERT INTO `article_tag` VALUES (24, 'mysql');
INSERT INTO `article_tag` VALUES (25, 'java');
INSERT INTO `article_tag` VALUES (25, 'spring');
INSERT INTO `article_tag` VALUES (26, 'java');
INSERT INTO `article_tag` VALUES (27, 'study_method');
INSERT INTO `article_tag` VALUES (28, 'study_method');
INSERT INTO `article_tag` VALUES (29, 'test');
INSERT INTO `article_tag` VALUES (30, 'java');
INSERT INTO `article_tag` VALUES (30, 'spring');
INSERT INTO `article_tag` VALUES (31, 'mysql');
INSERT INTO `article_tag` VALUES (32, 'mysql');
INSERT INTO `article_tag` VALUES (33, 'java');
INSERT INTO `article_tag` VALUES (34, 'mysql');
INSERT INTO `article_tag` VALUES (35, 'funcational_design');
INSERT INTO `article_tag` VALUES (35, 'java');
INSERT INTO `article_tag` VALUES (35, 'redis');
INSERT INTO `article_tag` VALUES (36, 'test');
INSERT INTO `article_tag` VALUES (37, 'mysql');
INSERT INTO `article_tag` VALUES (38, 'java');
INSERT INTO `article_tag` VALUES (39, 'project_manage');
INSERT INTO `article_tag` VALUES (40, 'java');
INSERT INTO `article_tag` VALUES (40, 'spring');
INSERT INTO `article_tag` VALUES (41, 'project_manage');
INSERT INTO `article_tag` VALUES (42, 'funcational_design');
INSERT INTO `article_tag` VALUES (43, 'java');
INSERT INTO `article_tag` VALUES (43, 'jvm');
INSERT INTO `article_tag` VALUES (44, 'git');
INSERT INTO `article_tag` VALUES (44, 'tool');
INSERT INTO `article_tag` VALUES (45, 'mq');
INSERT INTO `article_tag` VALUES (45, 'rabbitmq');
INSERT INTO `article_tag` VALUES (46, 'java');
INSERT INTO `article_tag` VALUES (46, 'spring');
INSERT INTO `article_tag` VALUES (47, 'mysql');
INSERT INTO `article_tag` VALUES (48, 'funcational_design');
INSERT INTO `article_tag` VALUES (48, 'mysql');
INSERT INTO `article_tag` VALUES (49, 'mongo');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `item` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数值',
  `seq` int(11) NULL DEFAULT 0 COMMENT '排序',
  UNIQUE INDEX `config_pk`(`item`, `name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配置明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('SITE_INFO', 'title', '软件工具箱', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'cnblogs', '博客园', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'csdn', 'CSDN博客', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'oschina', '开源中国', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'wxmp', '微信公众号', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'java', 'Java', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'test', '测试技术', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'redis', 'Redis', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'docker', 'Docker', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'spring', 'Spring', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'design_pattern', '设计模式', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'cnblogs', 'https://www.cnblogs.com/images/logo.svg', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'wxmp', 'https://open.weixin.qq.com/zh_CN/htmledition/res/assets/res-design-download/icon64_wx_logo.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'csdn', 'https://img1.imgtp.com/2022/03/25/n3zuDzNg.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'oschina', 'https://www.oschina.net/img/logo.svg', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'book', '书籍推荐', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'interview', '面试资料\n', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'funcational_design', '功能设计', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'tool', '工具使用', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'algorithm', '数据结构与算法', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'network', '计算机网络', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'microservice', '微服务治理', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'jvm', 'Java虚拟机', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'git', 'Git', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'linux', 'Linux', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'direct', '直接链接', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'direct', 'https://iphoto.macsc.com/icon/icon/256/20170518/6460/417956.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'dubbo', 'Dubbo', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'jianshu', 'https://www.lgstatic.com/thumbnail_300x300/image1/M00/00/1F/CgYXBlTUWGiAP9kMAAB8zMTX_u0512.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'jianshu', '简书', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'mysql', 'MySQL', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'vue', 'Vue.js', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE', 'zhihu', '知乎', 0);
INSERT INTO `config` VALUES ('ARTICLE_SOURCE_IMAGE', 'zhihu', 'https://pic2.zhimg.com/80/v2-f6b1f64a098b891b4ea1e3104b5b71f6_720w.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'study_method', '学习方法', 0);
INSERT INTO `config` VALUES ('FAVORITE_ITEM_IMAGE', 'editor', 'https://iphoto.macsc.com/icon/icon/256/20181121/25102/1202159.png', 0);
INSERT INTO `config` VALUES ('FAVORITE_ITEM_IMAGE', 'helper', 'https://iphoto.macsc.com/icon/icon/256/20180418/14236/678897.png', 0);
INSERT INTO `config` VALUES ('FAVORITE_ITEM_IMAGE', 'document', 'https://iphoto.macsc.com/icon/icon/256/20191030/65166/2833029.png', 0);
INSERT INTO `config` VALUES ('FAVORITE_ITEM_IMAGE', 'blog', 'https://iphoto.macsc.com/icon/icon/256/20200612/80456/3319052.png', 0);
INSERT INTO `config` VALUES ('FAVORITE_ITEM_IMAGE', 'friend', 'https://iphoto.macsc.com/icon/icon/256/20210519/118614/5047114.png', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'project_manage', '项目管理', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'mq', '消息队列', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'rabbitmq', 'RabbitMQ', 0);
INSERT INTO `config` VALUES ('ARTICLE_TAG', 'mongo', 'MongoDB', 0);

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `item` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图像',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `target` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '_self',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '链接地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 'editor', 'JSON编辑器', '/tool/json', '一款简单的JSON文本编辑工具，可以对数据进行格式化和反格式化', NULL, 1, '_self');
INSERT INTO `favorite` VALUES (2, 'editor', 'XML编辑器', '/tool/xml', '简单的XML美化压缩工具', NULL, 2, '_self');
INSERT INTO `favorite` VALUES (3, 'editor', '文本差异对比', '/tool/diff', '对比两个文本的内容并高亮显示', NULL, 3, '_self');
INSERT INTO `favorite` VALUES (4, 'editor', 'XSLT测试', '/tool/xslt', 'XML数据样式表功能测试', NULL, 4, '_self');
INSERT INTO `favorite` VALUES (5, 'editor', '加密解密', '/tool/code', '支持常见的加密与解密技术', NULL, 5, '_self');
INSERT INTO `favorite` VALUES (6, 'helper', '腾讯翻译君', 'https://fanyi.qq.com/', '全新人工智能翻译， 句子、文章、论文、资料翻译首选', NULL, 1, '_blank');
INSERT INTO `favorite` VALUES (7, 'helper', '变量命名', 'https://unbug.github.io/codelf/', 'Codelf,变量命名,函数命名,方法命名,变量命名神器,GitHub 分组', NULL, 2, '_blank');
INSERT INTO `favorite` VALUES (8, 'helper', 'Github代下载', 'https://ghproxy.com/', 'GitHub 文件 , Releases , archive , gist , raw.githubusercontent.com 文件代理加速下载服务', NULL, 3, '_blank');
INSERT INTO `favorite` VALUES (9, 'helper', '项目文档工具', 'https://docsify.js.org/#/zh-cn/', '一个神奇的文档网站生成器', NULL, 4, '_blank');
INSERT INTO `favorite` VALUES (10, 'helper', 'IDEA试用', 'https://gitee.com/pengzhile/ide-eval-resetter', 'Jetbrains系列产品重置试用方法', NULL, 5, '_blank');
INSERT INTO `favorite` VALUES (11, 'helper', 'Git大全', 'https://gitee.com/all-about-git', '最全面的 Git 资源索引以及常用命令教程', NULL, 6, '_blank');
INSERT INTO `favorite` VALUES (12, 'helper', 'httpbin.org', 'https://httpbin.org', '一个简单的HTPP请求响应网站', NULL, 7, '_blank');
INSERT INTO `favorite` VALUES (13, 'document', 'Apache项目', 'https://www.apache.org/index.html#projects-list', 'Apache基金会官方项目列表', NULL, 1, '_blank');
INSERT INTO `favorite` VALUES (14, 'document', 'Java8文档', 'https://docs.oracle.com/javase/8/docs/api/', 'Java8官方文档', NULL, 2, '_blank');
INSERT INTO `favorite` VALUES (15, 'document', 'Spring文档', 'https://spring.io/projects', 'Spring项目官方文档', NULL, 3, '_blank');
INSERT INTO `favorite` VALUES (16, 'document', 'Mybatis文档', 'https://mybatis.org/mybatis-3/zh/index.html', 'Mybatis官方中文文档', NULL, 4, '_blank');
INSERT INTO `favorite` VALUES (17, 'document', 'Java开发手册', 'https://github.com/alibaba/p3c', '阿里巴巴Java开发手册', NULL, 5, '_blank');
INSERT INTO `favorite` VALUES (18, 'document', 'On Java8', 'https://gitee.com/lingcoder/OnJava8/tree/1ef7ec48e492862300e667e24c245e9b3a5ccd98', '《On Java 8》中文版', NULL, 6, '_blank');
INSERT INTO `favorite` VALUES (19, 'document', 'Java8特性', 'https://github.com/biezhi/learn-java8', 'Java8 改变了我们思考和编码的方式，在这里你可以学习到 Java8 中的核心特性以及使用场景，跟我学习 Java8，让你的编程更轻松', NULL, 7, '_blank');
INSERT INTO `favorite` VALUES (20, 'document', 'Docs4dev', 'https://www.docs4dev.com/docs/zh', '开发文档中文版汇总', NULL, 8, '_blank');
INSERT INTO `favorite` VALUES (21, 'blog', '菜鸟教程', 'https://www.runoob.com', '提供了编程的基础技术教程, 介绍了HTML、CSS、Javascript、Python，Java，Ruby，C，PHP , MySQL等各种编程语言的基础知识', NULL, 1, '_blank');
INSERT INTO `favorite` VALUES (22, 'blog', '阿里云藏经阁', 'https://developer.aliyun.com/ebook', '汇聚阿里巴巴技术实践精华，涵盖云原生、物联网、大数据、AI 等技术领域，深度分享阿里工程师实战经验，顶级技术内容一手掌握', NULL, 2, '_blank');
INSERT INTO `favorite` VALUES (23, 'blog', '程序员DD', 'https://blog.didispace.com/', '内容涵盖Java后端技术、Spring Boot、Spring Cloud、微服务架构、运维开发、系统监控等相关的研究与知识分享', NULL, 3, '_blank');
INSERT INTO `favorite` VALUES (24, 'blog', '酷壳博客', 'https://coolshell.cn/', '享受编程和技术所带来的快乐 – https://coolshell.cn', NULL, 4, '_blank');
INSERT INTO `favorite` VALUES (25, 'blog', '虫洞栈', 'https://bugstack.cn/', 'bugstack 虫洞栈, 重学Java设计模式, 字节码编程, 中间件, Spring, Java基础, 面经手册', NULL, 5, '_blank');
INSERT INTO `favorite` VALUES (26, 'blog', '三太子敖丙', 'https://mp.weixin.qq.com/s/2l8iZeTRfMaUOcgtad1wIQ', '渣男两年文章汇总（附硬核面试点脑图）', NULL, 6, '_blank');
INSERT INTO `favorite` VALUES (27, 'blog', '商城实战项目', 'http://www.macrozheng.com/', 'mall学习教程，架构、业务、技术要点全方位解析', NULL, 7, '_blank');
INSERT INTO `favorite` VALUES (28, 'blog', 'Spring分析', 'https://huifer-spring-analysis.netlify.app', ' Spring 源码分析项目, 本仓库源码均来自v5.2.3版本', NULL, 8, '_blank');
INSERT INTO `favorite` VALUES (29, 'friend', 'HMISTER', 'http://www.hmister.cn/', '不想你惊艳我年少时光 只愿你暖我今后岁月', NULL, 2, '_blank');
INSERT INTO `favorite` VALUES (30, 'editor', 'Cron表达式', '/tool/cron', 'Crontab计划任务表达式在线生成', NULL, 6, '_self');
INSERT INTO `favorite` VALUES (34, 'helper', 'CrxDL', 'https://crxdl.com/', '下载Chrome扩展插件Crx离线安装包', NULL, 8, '_blank');
INSERT INTO `favorite` VALUES (35, 'helper', 'SSL/TLS安全评估', 'https://myssl.com/', '您部署的HTTPS网站安全吗?', NULL, 8, '_blank');
INSERT INTO `favorite` VALUES (36, 'blog', 'Javadoop', 'https://javadoop.com/', '来自魔都的程序员，在互联网摸爬滚打了很多年', NULL, 9, '_blank');
INSERT INTO `favorite` VALUES (37, 'friend', 'DEVZYH', 'http://blog.devzyh.cn/', '不要辜负这个最好的时代', NULL, 1, '_blank');
INSERT INTO `favorite` VALUES (38, 'blog', '凤凰架构', 'http://icyfenix.cn/', '构建可靠的大型分布式系统', NULL, 9, '_blank');
INSERT INTO `favorite` VALUES (39, 'editor', 'Diagram', 'https://app.diagrams.net/', '图表软件和流程图制作工具', NULL, 8, '_blank');
INSERT INTO `favorite` VALUES (41, 'helper', '程序员做饭指南', 'https://cook.aiurs.co/', '程序员在家做饭方法指南。Programmer\'s guide about how to cook at home (Chinese)', NULL, 9, '_blank');
INSERT INTO `favorite` VALUES (42, 'blog', 'SpringForAll社区', 'http://spring4all.com/', '专注服务Java和Spring开发者的技术社区', NULL, 10, '_blank');
INSERT INTO `favorite` VALUES (43, 'blog', 'JavaGuide', 'https://javaguide.cn/', '一份涵盖大部分 Java 程序员所需要掌握的核心知识。', NULL, 11, '_blank');

SET FOREIGN_KEY_CHECKS = 1;
