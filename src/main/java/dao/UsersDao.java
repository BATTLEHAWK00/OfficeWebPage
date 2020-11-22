package dao;

import bean.User;
import dao.exceptions.LoginException;
import dao.exceptions.RegisterException;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;
import utils.stringutils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class UsersDao {
    public User getUser(String username, String passwd) throws LoginException, SQLException {
        AtomicReference<User> user = new AtomicReference<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM user WHERE name='%s' and passwd='%s'", username, passwd);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    user.set(getUserByResSet(res));
                }
            });
        });
        if (user.get() == null)
            throw new LoginException("用户名或密码不正确！");
        return user.get();
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                query.setSql("SELECT * FROM user");
                query.ExecuteQuery(res -> {
                    while (res.next()) {
                        userList.add(getUserByResSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public User getUserByUID(String uid) {
        AtomicReference<User> user = new AtomicReference<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = String.format("SELECT * FROM user WHERE UID = '%s'", uid);
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    if (res.next()) {
                        user.set(getUserByResSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user.get();
    }

    public User getUserByName(String name) {
        AtomicReference<User> user = new AtomicReference<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = String.format("SELECT * FROM user WHERE name='%s'", name);
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    if (res.next()) {
                        user.set(getUserByResSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user.get();
    }

    private User getUserByResSet(ResultSet res) throws SQLException {
        User user = new User();
        user.setUid(res.getString("UID"));
        user.setUsername(res.getString("name"));
        user.setRegTime(res.getTimestamp("reg_time").getTime());
        user.setMajorClass(res.getString("major_class"));
        user.setTel(res.getString("tel"));
        return user;
    }

    public void RegisterUser(User user, String passwd) throws RegisterException {
        String uid;
        if (getUserByName(user.getUsername()) != null) {
            throw new RegisterException("该用户名已存在！");
        }
        do {
            uid = StringUtil.getUUID(8);
        } while (getUserByUID(uid) != null);
        user.setUid(uid);
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = String.format("INSERT INTO user(UID,name,passwd,major_class,tel,reg_time)" +
                                " VALUES('%s','%s','%s','%s','%s',%s)",
                        user.getUid(),
                        user.getUsername(),
                        passwd,
                        user.getMajorClass(),
                        user.getTel(),
                        "now()"
                );
                query.setSql(sql);
                query.ExecuteNonQuery();
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUserCounts() {
        AtomicInteger counts = new AtomicInteger();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = "SELECT COUNT(*) FROM user";
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    if (res.next())
                        counts.set(res.getInt(1));
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return counts.get();
    }
}
