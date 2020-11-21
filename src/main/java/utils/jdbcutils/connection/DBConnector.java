package utils.jdbcutils.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector instance;
    final String URL = "jdbc:mysql://localhost:3306/web_demo?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWD = "439920010428YXL.";
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
            System.out.println("���ݿ����ӳ�ʼ��...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connPool = new ConnectionPool(
                    POOL_SIZE_INIT,
                    POOL_SIZE_MAX,
                    () -> DriverManager.getConnection(URL, USERNAME, PASSWD)
            );
            System.out.println("���ݿ����ӳ�ʼ�����");
            System.out.printf(
                    "���ӳ�״̬:%d/%d(%.2f%%)\n",
                    connPool.size(), connPool.maxSize(),
                    (float) connPool.size() / connPool.maxSize() * 100
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}