package utils.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    static final String URL = "jdbc:mysql://localhost:3306/office?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWD = "439920010428YXL.";
    static final int POOL_SIZE_INIT = 8;
    static final int POOL_SIZE_MAX = 16;
    static ConnectionPool connPool;

    public static void getConnection(DatabaseAction action) {
        try {
            Connection conn = connPool.pop();
            System.out.printf("%d/%d\n", connPool.size(), connPool.maxSize());
            action.action(conn);
            connPool.push(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connPool = new ConnectionPool(
                    POOL_SIZE_INIT,
                    POOL_SIZE_MAX,
                    () -> DriverManager.getConnection(URL, USERNAME, PASSWD)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}