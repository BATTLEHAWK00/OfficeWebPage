package utils.stdio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {
    public static void Log(String msg) {
        System.out.printf(
                "%s | %s\n",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                msg
        );
    }

    public static void LogErr(String msg) {
        System.err.printf(
                "%s | %s\n",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                msg
        );
    }
}
