package cn.devzyh.toolbox.util;

import org.springframework.util.DigestUtils;

/**
 * MD5工具类
 */
public class Md5Util {

    /**
     * 获取MD5值
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    public static String password(String pwd) {
        return md5("pwd@" + pwd);
    }

}
