package cn.devzyh.toolbox.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * TTX加解密工具类
 */
@Slf4j
public class TtxCryptUtil {

    /**
     * 加密
     *
     * @param content  内容
     * @param password 密钥
     */
    public static String encrypt(String content, String password) {
        if (StrUtil.isEmpty(content)) {
            return content;
        }

        try {
            byte[] result = crypt(content.getBytes(StandardCharsets.UTF_8), password, true);
            if (result == null) {
                return content;
            }

            return byte2hex(result);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * 解密
     *
     * @param content  内容
     * @param password 密钥
     */
    public static String decrypt(String content, String password) {
        if (StrUtil.isEmpty(content)) {
            return content;
        }

        try {
            byte[] result = hex2byte(content);

            result = crypt(result, password, false);
            if (result == null) {
                return content;
            }

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * 加解密实现
     *
     * @param content  输入内容
     * @param password 密钥
     * @param encrypt  是否加密
     */
    private static byte[] crypt(byte[] content, String password, boolean encrypt) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            if (encrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, key);// 加密
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key); // 解密
            }

            return cipher.doFinal(content);
        } catch (Exception e) {
            log.error("错误日志：", e);
            return null;
        }
    }

    /**
     * 二进制转换成十六进制
     *
     * @param bytes 二进制数组
     */
    static String byte2hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex.toUpperCase());
        }

        return builder.toString();
    }

    /**
     * 十六进制转换成二进制
     *
     * @param hex 十六进制转字符串
     */
    static byte[] hex2byte(String hex) {
        if (StrUtil.isBlank(hex)) {
            return null;
        }

        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++) {
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

}
