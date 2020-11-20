package utils.database.stringutil;

import java.io.IOException;
import java.io.InputStream;

public class StringUtil {
    public static String IStreamToStr(InputStream is) throws IOException {
        return new String(is.readAllBytes());
    }
}
