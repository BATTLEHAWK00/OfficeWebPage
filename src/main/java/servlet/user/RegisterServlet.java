package servlet.user;

import bean.Response;
import bean.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.exceptions.RegisterException;
import service.impl.UsersServiceImpl;
import utils.LoggerUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        Response res = new Response();
        try {
            User user = new User();
            user.setUsername(jsonObject.get("username").getAsString().trim());
            user.setTel(jsonObject.get("tel").getAsString().trim());
            user.setMajorClass(jsonObject.get("majorclass").getAsString().trim());
            new UsersServiceImpl().doUserRegister(user, jsonObject.get("passwd").getAsString());
            res.SetMessage("OK");
            LoggerUtil.Logf("ÓÃ»§×¢²á£º%s(uid:%s)", user.getUsername(), user.getUid());
        } catch (RegisterException e) {
            res.SetMessage(e.getMessage());
            resp.setStatus(400);
        } catch (Exception e) {
            resp.setStatus(500);
            return;
        }
        resp.getWriter().write(res.toJson());
    }
}
