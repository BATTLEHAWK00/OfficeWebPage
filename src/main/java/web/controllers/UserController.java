package web.controllers;

import bean.Response;
import bean.user.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UsersService;
import service.exceptions.RegisterException;
import service.impl.UsersServiceImpl;
import utils.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    UsersService usersService = new UsersServiceImpl();

    @RequestMapping(value = "/login/check")
    public String loginCheck(@RequestParam("uid") String uid, HttpSession session) {
        if (session.getAttribute("loggedUser") == null)
            return Response.Fail.toJsonStr();
        User user = (User) session.getAttribute("loggedUser");
        if (uid.equals(user.getUid()))
            return Response.OK.toJsonStr();
        else
            return Response.Fail.toJsonStr();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestBody String param, HttpSession session) throws IOException {
        JsonObject jsonObject = JsonParser.parseString(param).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String passwd = jsonObject.get("passwd").getAsString();
        Response res = new Response();
        User user;
        try {
            String uid = usersService.doUserLogin(username, passwd);
            user = usersService.getUserByUID(uid);
            if (session.getAttribute("loggedUser") != null) {
                session.removeAttribute("loggedUser");
            }
            session.setAttribute("loggedUser", user);
            res.SetMessage("OK");
            res.SetData(user);
            LoggerUtil.Log("用户登录：" + user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toJsonStr();
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
        resp.getWriter().write(res.toJsonStr());
    }

    @RequestMapping("/logout")
    public void doLogout(HttpSession session) {
        session.removeAttribute("loggedUser");
    }
}
