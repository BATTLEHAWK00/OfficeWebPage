package servlet;

import com.google.gson.Gson;
import service.impl.PostServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //JsonArray json = new JsonArray();
        resp.getWriter().write(new Gson().toJson(new PostServiceImpl().getPublishedPosts()));

    }
}
