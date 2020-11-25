package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 获取指定Properties文件对象
     *
     * @param path Properties文件路径
     * @return 返回Properties对象
     * @throws IOException
     */
    public static Properties GetPropFromResource(String path) throws IOException {
        URL res = PropertiesUtil.class.getClassLoader().getResource(path);
        Properties prop = new Properties();
        prop.load(new FileReader(res.getPath()));
        return prop;
    }
}
