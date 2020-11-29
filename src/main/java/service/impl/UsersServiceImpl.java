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
    public String doUserLogin(String username, String passwd) throws LoginException {
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, username);
        try {
            String uid = usersDao.getUserByPasswd(username, passwd_md5);
            if (uid == null)
                throw new LoginException("�û��������벻��ȷ��");
            usersDao.setLoginTime(uid);
            return uid;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("�ڲ�����");
        }
    }

    @Override
    public void doUserRegister(User user, String passwd) throws RegisterException {
        String uid;
        String passwd_md5 = SecurityUtil.getSaltMD5(passwd, user.getUsername());
        try {
            if (usersDao.getUserByName(user.getUsername()) != null) {
                throw new RegisterException("���û����Ѵ��ڣ�");
            }
            do {
                uid = StringUtil.getUUID(8);
            } while (usersDao.getUserByUID(uid) != null);
            user.setIdentity(UserIdentity.Student);
            user.setUid(uid);
            usersDao.RegisterUser(user, passwd_md5);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RegisterException("�ڲ�����");
        }
    }

    @Override
    public User getUserByUID(String uid) throws SQLException {
        return usersDao.getUserByUID(uid);
    }
}
