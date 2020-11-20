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

    public ConnectionPool(int initsize, int maxsize, GetConnection getConnection) throws SQLException {
        MAX_SIZE = maxsize;
        this.getConnection = getConnection;
        while (connPool.size() < initsize)
            connPool.push(getConnection.getConn());
    }

    public void push(Connection conn) throws SQLException {
        if (connPool.size() >= MAX_SIZE) {
            conn.close();
            return;
        }
        connPool.push(conn);
    }

    public Connection pop() throws SQLException {
        try {
            return connPool.pop();
        } catch (EmptyStackException e) {
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