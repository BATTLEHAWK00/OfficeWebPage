package servlet.user;

import bean.Response;
import bean.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.UsersDao;
import utils.stdio.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        User user = new User();
        user.setUsername(jsonObject.get("username").getAsString());
        user.setTel(jsonObject.get("tel").getAsString());
        user.setMajorClass(jsonObject.get("majorclass").getAsString());
        new UsersDao().RegisterUser(user, jsonObject.get("passwd").getAsString());
        Response res = new Response("OK");
        LoggerUtil.Log("ÓÃ»§×¢²á£º" + user.getUsername());
        resp.getWriter().write(res.toJson());
    }
}
