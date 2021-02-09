package web.controllers;

import bean.Response;
import bean.user.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.UsersService;
import service.exceptions.LoginException;
import service.exceptions.RegisterException;
import service.impl.UsersServiceImpl;
import utils.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    UsersService usersService = new UsersServiceImpl();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String passwd = jsonObject.get("passwd").getAsString();
        JsonObject data = new JsonObject();
        Response res;
        User user;
        res = new Response();
        try {
            String uid = usersService.doUserLogin(username, passwd);
            user = usersService.getUserByUID(uid);
            HttpSession session = req.getSession();
            if (session.getAttribute("loggedUser") != null) {
                session.removeAttribute("loggedUser");
            }
            session.setAttribute("loggedUser", user);
            session.setMaxInactiveInterval(300);
            res.SetMessage("OK");
            res.SetData(user);
            LoggerUtil.Log("用户登录：" + user.getUsername());
        } catch (LoginException e) {
            res.SetMessage(e.getMessage());
            resp.setStatus(400);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        resp.getWriter().write(res.toJson());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void doLoginCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Response res = new Response();
        if (req.getParameter("uid") == null) {
            resp.setStatus(400);
            return;
        }
        if (session.getAttribute("loggedUser") != null) {
            User user = (User) session.getAttribute("loggedUser");
            if (req.getParameter("uid").equals(user.getUid()))
                res.SetMessage("OK");
            else
                res.SetMessage("Failed");
        } else {
            res.SetMessage("Failed");
        }
        resp.getWriter().write(res.toJson());
    }

    @RequestMapping("/register")
    @ResponseStatus
    public void doRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        Response res = new Response();
        try {
            User user = new User();
            user.setUsername(jsonObject.get("username").getAsString().trim());
            user.setTel(jsonObject.get("tel").getAsString().trim());
            usersService.doUserRegister(user, jsonObject.get("passwd").getAsString());
            res.SetMessage("OK");
            LoggerUtil.Logf("用户注册：%s(uid:%s)", user.getUsername(), user.getUid());
        } catch (RegisterException e) {
            res.SetMessage(e.getMessage());
            resp.setStatus(400);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        resp.getWriter().write(res.toJson());
    }
}
