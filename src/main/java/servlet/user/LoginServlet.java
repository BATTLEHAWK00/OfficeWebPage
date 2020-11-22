package servlet.user;

import bean.Response;
import bean.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.UsersDao;
import dao.exceptions.LoginException;
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
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String passwd = jsonObject.get("passwd").getAsString();
        JsonObject data = new JsonObject();
        Response res;
        User user = null;
        res = new Response();
        try {
            user = new UsersDao().getUser(username, passwd);
            if (req.getSession().getAttribute("uid") == null)
                req.getSession().setAttribute("uid", user.getUid());
            res.SetMessage("OK");
            res.SetData(user);
            LoggerUtil.Log("ÓÃ»§µÇÂ¼£º" + user.getUsername());
        } catch (LoginException e) {
            res.SetMessage(e.getMessage());
            resp.setStatus(400);
        } catch (Exception e) {
            resp.setStatus(500);
            return;
        }
        resp.getWriter().write(res.toJson());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
