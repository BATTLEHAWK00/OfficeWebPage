package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SecurityUtil {
    /**
     * 生成MD5
     *
     * @param str 目标字符串
     * @return 返回字符串MD5
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5_Bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md5_Bytes.length; i++) {
                int n = md5_Bytes[i];
                if (n < 0) n += 256;
                if (n < 16) sb.append("0");
                sb.append(Integer.toHexString(n));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得加盐MD5
     *
     * @param str  目标字符串
     * @param salt 盐
     * @return 返回加盐MD5
     */
    public static String getSaltMD5(String str, String salt) {
        String md5 = getMD5(str + salt);
        for (int i = 0; i < 3; i++) {
            md5 = getMD5(md5 + salt);
        }
        return md5;
    }
}
