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
     * ����ģʽ
     *
     * @return ���ص���
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
     * ���ݿ����ӳس�ʼ��
     */
    public void Init() {
        try {
            LoggerUtil.Log("���ݿ����ӳ�ʼ��...");
            Properties prop = PropertiesUtil.GetPropFromResource("jdbc_connection.properties");
            Class.forName(prop.getProperty("driverClassName"));
            String jdbcUrl = String.format(
                    "jdbc:mysql://%s:%s/%s?serverTimezone=Asia/Shanghai",
                    prop.getProperty("dbHost"),
                    prop.getProperty("dbPort"),
                    prop.getProperty("dbName")
            );
            connPool = new ConnectionPool(
                    //���ӳس�ʼ��С
                    Integer.parseInt(prop.getProperty("poolInitialSize")),
                    //���ӳ�����С
                    Integer.parseInt(prop.getProperty("poolMaxSize")),
                    //����url
                    () -> DriverManager.getConnection(
                            //URL
                            jdbcUrl,
                            //�û���
                            prop.getProperty("username"),
                            //����
                            prop.getProperty("password")
                    )
            );
            LoggerUtil.Log("���ݿ����ӳ�ʼ����ϣ�");
            LoggerUtil.Logf(
                    "���ӳ�״̬:%d/%d(%.2f%%)",
                    connPool.size(), connPool.maxSize(),
                    (float) connPool.size() / connPool.maxSize() * 100
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}