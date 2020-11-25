package utils;

import java.util.UUID;

public class StringUtil {
    /**
     * 生成随机UUID
     *
     * @param length 长度
     * @return 返回UUID字符串
     */
    public static String getUUID(int length) {
        String uuid = UUID.randomUUID().toString();
        uuid.replace("-", "");
        return uuid.substring(0, length);
    }
}
