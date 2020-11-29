package dao;

import bean.user.User;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao extends BaseDao {
    List<User> getAllUsers() throws SQLException;

    int getUserCounts() throws SQLException;

    void RegisterUser(User user, String passwd) throws RegisterException, SQLException;

    User getUserByUID(String uid) throws SQLException;

	String getUserByPasswd(String username, String passwd) throws SQLException, LoginException;

    void setLoginTime(String uid) throws SQLException;

    User getUserByName(String name) throws SQLException;
}
