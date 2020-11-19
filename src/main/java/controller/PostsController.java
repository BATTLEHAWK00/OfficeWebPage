package controller;

import com.google.gson.Gson;
import utils.database.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/posts")
public class PostsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        List<Map<String,String>> maplist = new ArrayList<>();
        DBConnector.getConnection((conn)->{
            Statement statement = conn.createStatement();
            String sql = "SELECT title,content,date FROM posts";
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                Map<String,String> map = new HashMap<>();
                map.put("postName",res.getString("title"));
                map.put("postContent",res.getString("content"));
                map.put("postDate",String.valueOf(res.getTimestamp("date").getTime()));
                maplist.add(map);
            }
        });
        resp.getWriter().write(new Gson().toJson(maplist));
    }
}
