package utils.jdbcutils.connection;

import utils.LoggerUtil;
import utils.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static final DBConnector instance = new DBConnector();
    ConnectionPool connPool;

    /**
     * 单例模式
     *
     * @return 返回单例
     */
    public static DBConnector get() {
        return instance;
    }

    public void getConnection(DatabaseAction action) throws SQLException {
        Connection conn = connPool.pop();
        //System.out.printf("%d/%d\n", connPool.size(), connPool.maxSize());
        action.action(conn);
        connPool.push(conn);
    }

    /**
     * 数据库连接池初始化
     */
    public void Init() {
        try {
            LoggerUtil.Log("数据库连接初始化...");
            Properties prop = PropertiesUtil.GetPropFromResource("jdbc_connection.properties");
            Class.forName(prop.getProperty("driverClassName"));
            String jdbcUrl = String.format(
                    "jdbc:mysql://%s:%s/%s?serverTimezone=Asia/Shanghai",
                    prop.getProperty("dbHost"),
                    prop.getProperty("dbPort"),
                    prop.getProperty("dbName")
            );
            connPool = new ConnectionPool(
                    //连接池初始大小
                    Integer.parseInt(prop.getProperty("poolInitialSize")),
                    //连接池最大大小
                    Integer.parseInt(prop.getProperty("poolMaxSize")),
                    //连接url
                    () -> DriverManager.getConnection(
                            //URL
                            jdbcUrl,
                            //用户名
                            prop.getProperty("username"),
                            //密码
                            prop.getProperty("password")
                    )
            );
            LoggerUtil.Log("数据库连接初始化完毕！");
            LoggerUtil.Logf(
                    "连接池状态:%d/%d(%.2f%%)",
                    connPool.size(), connPool.maxSize(),
                    (float) connPool.size() / connPool.maxSize() * 100
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}