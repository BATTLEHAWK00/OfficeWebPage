package utils.jdbcutils.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuery {
    private final Connection conn;
    private String sql;
    private Statement statement;
    private ResultSet resultSet;

    public SQLQuery(Connection connection) {
        conn = connection;
    }

    public SQLQuery(Connection connection, String sql) {
        conn = connection;
        this.sql = sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void Execute(SQLQueryAction action) throws SQLException {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

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
}
