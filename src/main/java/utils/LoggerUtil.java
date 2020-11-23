package utils;

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

    public static void Logf(String msg, Object... args) {
        System.out.printf(
                "%s | %s\n",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                String.format(msg, args)
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
