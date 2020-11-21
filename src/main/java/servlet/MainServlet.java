package servlet;

import utils.jdbcutils.connection.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet()
public class MainServlet extends HttpServlet {
    @Override
    public void init() {
        DBConnector.get().Init();
    }
}
