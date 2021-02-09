package service.impl;

import bean.user.User;
import bean.user.UserIdentity;
import dao.UsersDao;
import dao.impl.UsersDaoImpl;
import service.UsersService;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;
import utils.SecurityUtil;
import utils.StringUtil;

import java.sql.SQLException;

/**
 * @class: UsersServiceImpl
 * @description:
 * @author: YXL
 **/
public class UsersServiceImpl implements UsersService {
    UsersDao usersDao = new UsersDaoImpl();

    @Override
    public String doUserLogin(String username, String passwd) throws LoginException, SQLException {
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, username);
        String uid = usersDao.getUserByPasswd(username, passwd_md5);
        if (uid == null)
            throw new LoginException("用户名或密码不正确！");
        usersDao.setLoginTime(uid);
        return uid;
    }

    @Override
    public void doUserRegister(User user, String passwd) throws RegisterException, SQLException {
        String uid;
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, user.getUsername());
        if (usersDao.getUserByName(user.getUsername()) != null) {
            throw new RegisterException("该用户名已存在！");
        }
        do {
            uid = StringUtil.getUUID(8);
        } while (usersDao.getUserByUID(uid) != null);
        user.setIdentity(UserIdentity.Student);
        user.setUid(uid);
        usersDao.RegisterUser(user, passwd_md5);
    }

    @Override
    public User getUserByUID(String uid) throws SQLException {
        return usersDao.getUserByUID(uid);
    }

    @Override
    public String getUserDispName(String uid) throws SQLException {
        return usersDao.getUserDispName(uid);
    }
}
