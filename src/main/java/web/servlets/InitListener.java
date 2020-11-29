package web.servlets;

import bean.user.UserSex;
import utils.LoggerUtil;
import utils.MailUtil;
import utils.jdbcutils.connection.DBConnector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 初始化监听
 */
@WebListener
public class InitListener implements ServletContextListener {
	/**
	 * 初始化入口
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println(UserSex.男.name());
		LoggerUtil.Log("初始化...");
		DBConnector.get().Init();
		MailUtil.getInstance().Init();
		LoggerUtil.Log("初始化完毕！");

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}
