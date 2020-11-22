package utils.stringutils;

import java.util.UUID;

public class StringUtil {
    public static String getUUID(int length) {
        String uuid = UUID.randomUUID().toString();
        uuid.replace("-", "");
        return uuid.substring(0, length);
    }
}
