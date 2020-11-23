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
        LoggerUtil.Log("��ʼ��...");
        DBConnector.get().Init();
        MailUtil.getInstance().Init();
        LoggerUtil.Log("��ʼ����ϣ�");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
