package utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;

public class DBConnector {
    static final String URL = "jdbc:mysql://localhost:3306/office?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWD = "439920010428YXL.";
    static final int POOL_SIZE = 10;
    static Stack<Connection> connPool = new Stack<>();

    public static void getConnection(DatabaseAction action) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            if (!connPool.empty()) {
                conn = connPool.pop();
            } else {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
            }
            action.action(conn);
            if (connPool.size() >= POOL_SIZE)
                conn.close();
            else
                connPool.push(conn);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}