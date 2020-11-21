package servlet;

import utils.jdbcutils.connection.DBConnector;
import utils.stdio.LoggerUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet()
public class MainServlet extends HttpServlet {
    @Override
    public void init() {
        LoggerUtil.Log("Servlet初始化...");
        DBConnector.get().Init();
        LoggerUtil.Log("Servlet初始化完毕！");
    }
}
