package utils.jdbcutils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQL������װ
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
     * ִ��SQL���
     *
     * @param action ��ѯ����ص�����
     * @throws SQLException
     */
    private void execute(SQLAction action) throws SQLException {
        try {
            statement = conn.prepareStatement(sql);
            action.action();
        } catch (SQLException e) {
            System.err.println("Sql���ִ��ʧ�ܣ�");
            System.err.println("Sql�������:");
            System.err.println(sql);
            System.out.println("������Ϣ:");
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
            System.err.println("���ݿ��ѯʧ�ܣ�������Ϣ:");
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
