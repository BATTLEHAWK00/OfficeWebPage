package service.impl;

import bean.User;
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
    public User doUserLogin(String username, String passwd) throws LoginException {
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, username);
        try {
            User user = usersDao.getUserByPasswd(username, passwd_md5);
            if (user == null)
                throw new LoginException("用户名或密码不正确！");
            usersDao.setLoginTime(user.getUid());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("内部错误！");
        }
    }

    @Override
    public void doUserRegister(User user, String passwd) throws RegisterException {
        String uid;
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, user.getUsername());
        try {
            if (usersDao.getUserByName(user.getUsername()) != null) {
                throw new RegisterException("该用户名已存在！");
            }
            do {
                uid = StringUtil.getUUID(8);
            } while (usersDao.getUserByUID(uid) != null);
            user.setUid(uid);
            usersDao.RegisterUser(user, passwd_md5);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RegisterException("内部错误！");
        }
    }
}
