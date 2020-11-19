package controller;

import com.google.gson.Gson;
import dao.response.Response;
import dao.response.datatypes.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var postvar = req.getParameterMap();
        var res = new Response();
        res.SetMessage(postvar.get("username")[0]);
        resp.getWriter().write(res.toJson());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }
}
