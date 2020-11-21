package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLQuery;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        JsonArray json = new JsonArray();
        try {
            DBConnector.get().getConnection((conn) -> {
                SQLQuery sqlQuery = new SQLQuery(conn);
                sqlQuery.setSql("SELECT title,content,date FROM posts");
                sqlQuery.Execute(res -> {
                    while (res.next()) {
                        Map<String, String> map = new HashMap<>();
                        var ele = new JsonObject();
                        ele.addProperty("postName", res.getString("title"));
                        ele.addProperty("postContent", res.getString("content"));
                        ele.addProperty("postDate", String.valueOf(res.getTimestamp("date").getTime()));
                        json.add(ele);
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.getWriter().write(json.toString());
    }
}
