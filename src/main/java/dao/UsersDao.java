package dao;

import bean.response.User;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLQuery;

import java.sql.SQLException;

public class UsersDao {
    public User getUser(String username, String passwd) {
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLQuery(conn);
                query.setSql("SELECT * FROM users");
                query.Execute(res -> {
                    while (res.next()) {
//                        这里写用户查询
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
