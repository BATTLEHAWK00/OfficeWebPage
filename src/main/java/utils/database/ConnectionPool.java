package utils.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Stack;

@FunctionalInterface
interface GetConnection {
    Connection getConn() throws SQLException;
}

public class ConnectionPool {
    final Stack<Connection> connPool = new Stack<>();
    int MAX_SIZE;
    GetConnection getConnection;

    public ConnectionPool(int initsize, int maxsize, GetConnection getConnection) {
        MAX_SIZE = maxsize;
        this.getConnection = getConnection;
        try {
            while (connPool.size() < initsize)
                connPool.push(getConnection.getConn());
        } catch (Exception e) {
            System.err.println("创建数据库连接时发生错误：");
            System.err.println(e.getMessage());
        }
    }

    public void push(Connection conn) {
        try {
            if (connPool.size() >= MAX_SIZE) {
                conn.close();
                return;
            }
            connPool.push(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection pop() throws SQLException {
        try {
            while (connPool.peek().isClosed())
                connPool.pop();
            return connPool.pop();
        } catch (EmptyStackException e) {
            System.out.println("数据库连接池已满,考虑增加连接池容量");
            return getConnection.getConn();
        }
    }

    public int size() {
        return connPool.size();
    }

    public int maxSize() {
        return MAX_SIZE;
    }
}