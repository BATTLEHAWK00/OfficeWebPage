package servlet;

import utils.LoggerUtil;
import utils.MailUtil;
import utils.jdbcutils.connection.DBConnector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LoggerUtil.Log("初始化...");
        DBConnector.get().Init();
        MailUtil.getInstance().Init();
        LoggerUtil.Log("初始化完毕！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
