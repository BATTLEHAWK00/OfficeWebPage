package service;

import bean.user.User;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;

import java.sql.SQLException;

public interface UsersService {
	/**
	 * �����û���¼
	 *
	 * @param username �û���
	 * @param passwd   ����(����)
	 * @return
	 * @throws LoginException
	 */
	String doUserLogin(String username, String passwd) throws LoginException;

	/**
	 * �����û�ע��
	 *
	 * @param user   �û�Bean����
	 * @param passwd ����(����)
	 * @throws RegisterException
	 */
	void doUserRegister(User user, String passwd) throws RegisterException;

	/**
	 * ����UID��ȡ�û�
	 *
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	User getUserByUID(String uid) throws SQLException;

	String getUserDispName(String uid) throws SQLException;
}
