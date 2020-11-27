package service;

import bean.User;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;

public interface UsersService {
    /**
     * �����û���¼
     *
     * @param username �û���
     * @param passwd   ����(����)
     * @return
     * @throws LoginException
     */
    User doUserLogin(String username, String passwd) throws LoginException;

    /**
     * �����û�ע��
     *
     * @param user   �û�Bean����
     * @param passwd ����(����)
     * @throws RegisterException
     */
    void doUserRegister(User user, String passwd) throws RegisterException;
}
