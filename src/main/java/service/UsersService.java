package service;

import bean.user.User;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;

import java.sql.SQLException;

public interface UsersService {
	/**
	 * 处理用户登录
	 *
	 * @param username 用户名
	 * @param passwd   密码(明文)
	 * @return
	 * @throws LoginException
	 */
	String doUserLogin(String username, String passwd) throws LoginException;

	/**
	 * 处理用户注册
	 *
	 * @param user   用户Bean对象
	 * @param passwd 密码(明文)
	 * @throws RegisterException
	 */
	void doUserRegister(User user, String passwd) throws RegisterException;

	/**
	 * 根据UID获取用户
	 *
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	User getUserByUID(String uid) throws SQLException;

	String getUserDispName(String uid) throws SQLException;
}
