package servlet.user;

import bean.Response;
import bean.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.UsersDao;
import utils.stdio.LoggerUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String passwd = jsonObject.get("passwd").getAsString();
        JsonObject data = new JsonObject();
        Response res;
        User user = new UsersDao().getUser(username, passwd);
        if (user != null) {
            if (req.getSession().getAttribute("uid") == null)
                req.getSession().setAttribute("uid", user.getUid());
            res = new Response("OK", data);
        } else {
            res = new Response("验证失败！");
        }
        LoggerUtil.Log("用户登录：" + user.getUsername());
        resp.getWriter().write(res.toJson());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
