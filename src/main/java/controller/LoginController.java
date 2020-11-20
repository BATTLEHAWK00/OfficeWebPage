package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.response.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String passwd = jsonObject.get("passwd").getAsString();
        JsonObject data = new JsonObject();
        data.addProperty("Hello", "Hello");
        var res = new Response("OK", data);
        resp.getWriter().write(res.toJson());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }
}
