package service;

import bean.User;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;

public interface UsersService {
    /**
     * 处理用户登录
     *
     * @param username 用户名
     * @param passwd   密码(明文)
     * @return
     * @throws LoginException
     */
    User doUserLogin(String username, String passwd) throws LoginException;

    /**
     * 处理用户注册
     *
     * @param user   用户Bean对象
     * @param passwd 密码(明文)
     * @throws RegisterException
     */
    void doUserRegister(User user, String passwd) throws RegisterException;
}
