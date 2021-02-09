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
 * 初始化监听
 */
public class InitListener implements Servlet {
    /**
     * 初始化入口
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        LoggerUtil.Log("初始化...");
        long startTime = System.currentTimeMillis();
        DBConnector.get().Init();
        MailUtil.getInstance().Init();
        long endTime = System.currentTimeMillis();
        LoggerUtil.Logf("初始化完毕！耗时：%d ms", endTime - startTime);
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
