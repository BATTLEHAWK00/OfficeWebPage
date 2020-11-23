package utils.jdbcutils.connection;

import utils.LoggerUtil;
import utils.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static DBConnector instance;
    final int POOL_SIZE_INIT = 16;
    final int POOL_SIZE_MAX = 32;
    ConnectionPool connPool;

    public static DBConnector get() {
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }

    public void getConnection(DatabaseAction action) throws SQLException {
        Connection conn = connPool.pop();
        //System.out.printf("%d/%d\n", connPool.size(), connPool.maxSize());
        action.action(conn);
        connPool.push(conn);
    }

    public void Init() {
        try {
            LoggerUtil.Log("数据库连接初始化...");
            Properties prop = PropertiesUtil.GetPropFromResource("jdbc_connection.properties");
            Class.forName(prop.getProperty("driverClassName"));
            connPool = new ConnectionPool(
                    Integer.parseInt(prop.getProperty("poolInitialSize")),
                    Integer.parseInt(prop.getProperty("poolMaxSize")),
                    () -> DriverManager.getConnection(prop.getProperty("url"),
                            prop.getProperty("username"),
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