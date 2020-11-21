package servlet;

import bean.User;
import dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = new User();
        user.setUsername(String.valueOf(Math.random()));
        user.setMajorClass(String.valueOf(Math.random()));
        user.setTel(String.valueOf(Math.random()));
        user.setUid(String.valueOf(Math.random()).substring(0, 8));
        new UsersDao().RegisterUser(user, String.valueOf(Math.random()));
    }
}
