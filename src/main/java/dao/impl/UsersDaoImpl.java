package dao.impl;

import bean.User;
import dao.UsersDao;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class UsersDaoImpl implements UsersDao {
    @Override
    public void setLoginTime(String uid) throws SQLException {

        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("UPDATE users SET login_time=now() WHERE UID='%s'", uid);
            query.setSql(sql);
            query.ExecuteUpdate();
        });

    }

    @Override
    public User getUserByUID(String uid) throws SQLException {
        AtomicReference<User> user = new AtomicReference<>();

        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM users WHERE UID = '%s'", uid);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    user.set(getByResultSet(res));
                }
            });
        });

        return user.get();
    }

    @Override
    public User getUserByPasswd(String username, String passwd_md5) throws SQLException {
        AtomicReference<User> user = new AtomicReference<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM users WHERE name='%s' and passwd='%s'", username, passwd_md5);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    user.set(getByResultSet(res));
                }
            });
        });
        return user.get();
    }

    public User getUserByName(String name) throws SQLException {
        AtomicReference<User> user = new AtomicReference<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM users WHERE name='%s'", name);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    user.set(getByResultSet(res));
                }
            });
        });
        return user.get();
    }


    @Override
    public void RegisterUser(User user, String passwd_md5) throws SQLException {
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("INSERT INTO users(UID,name,passwd,major_class,tel,reg_time)" +
                            " VALUES('%s','%s','%s','%s','%s',%s)",
                    user.getUid(),
                    user.getUsername(),
                    passwd_md5,
                    user.getMajorClass(),
                    user.getTel(),
                    "now()"
            );
            query.setSql(sql);
            query.ExecuteNonQuery();
        });
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            query.setSql("SELECT * FROM user");
            query.ExecuteQuery(res -> {
                while (res.next()) {
                    userList.add(getByResultSet(res));
                }
            });
        });
        return userList;
    }

    @Override
    public int getUserCounts() throws SQLException {
        AtomicInteger counts = new AtomicInteger();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = "SELECT COUNT(*) FROM users";
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next())
                    counts.set(res.getInt(1));
            });
        });

        return counts.get();
    }


    @Override
    public User getByResultSet(ResultSet res) throws SQLException {
        User user = new User();
        user.setUid(res.getString("UID"));
        user.setUsername(res.getString("name"));
        user.setRegTime(res.getTimestamp("reg_time").getTime());
        user.setMajorClass(res.getString("major_class"));
        user.setTel(res.getString("tel"));
        return user;
    }
}
