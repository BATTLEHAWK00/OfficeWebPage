package web.servlets.user;

import bean.Response;
import bean.user.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.UsersService;
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
	UsersService usersService = new UsersServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        Response res = new Response();
        try {
            User user = new User();
            user.setUsername(jsonObject.get("username").getAsString().trim());
            user.setTel(jsonObject.get("tel").getAsString().trim());
	        usersService.doUserRegister(user, jsonObject.get("passwd").getAsString());
	        res.SetMessage("OK");
            LoggerUtil.Logf("ÓÃ»§×¢²á£º%s(uid:%s)", user.getUsername(), user.getUid());
        } catch (RegisterException e) {
            res.SetMessage(e.getMessage());
            resp.setStatus(400);
        } catch (Exception e) {
	        e.printStackTrace();
	        resp.setStatus(500);
            return;
        }
        resp.getWriter().write(res.toJson());
    }
}
