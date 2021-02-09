package web;

import bean.user.UserSex;
import utils.LoggerUtil;
import utils.MailUtil;
import utils.jdbcutils.connection.DBConnector;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * ��ʼ������
 */
public class InitListener implements Servlet {
    /**
     * ��ʼ�����
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        LoggerUtil.Log("��ʼ��...");
        long startTime = System.currentTimeMillis();
        DBConnector.get().Init();
        MailUtil.getInstance().Init();
        long endTime = System.currentTimeMillis();
        LoggerUtil.Logf("��ʼ����ϣ���ʱ��%d ms", endTime - startTime);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
