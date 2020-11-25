package utils.jdbcutils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQL操作封装
 */
public class SQLOperation {
    private final Connection conn;
    private String sql;
    private PreparedStatement statement;

    public SQLOperation(Connection connection) {
        conn = connection;
    }

    public SQLOperation(Connection connection, String sql) {
        conn = connection;
        this.sql = sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * 执行SQL语句
     *
     * @param action 查询处理回调方法
     * @throws SQLException
     */
    private void execute(SQLAction action) throws SQLException {
        try {
            statement = conn.prepareStatement(sql);
            action.action();
        } catch (SQLException e) {
            System.err.println("Sql语句执行失败！");
            System.err.println("Sql语句内容:");
            System.err.println(sql);
            System.out.println("错误信息:");
            System.err.println(e.getMessage());
            throw e;
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ExecuteQuery(SQLQueryAction action) throws SQLException {
        ResultSet resultSet;
        try {
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("数据库查询失败！错误信息:");
            System.err.println(e.getMessage());
            throw e;
        }
        action.action(resultSet);
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ExecuteNonQuery() throws SQLException {
        execute(() -> {
            statement.execute(sql);
        });
    }

    public int ExecuteUpdate() throws SQLException {
        AtomicInteger effectedRows = new AtomicInteger();
        execute(() -> {
            effectedRows.set(statement.executeUpdate());
        });
        return effectedRows.get();
    }

    interface SQLAction {
        void action() throws SQLException;
    }
}
