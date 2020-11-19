package controller;

import com.google.gson.Gson;
import dao.db.Sad;
import dao.response.Data;
import dao.response.Response;
import utils.database.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@WebServlet("/hello")
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        List<Map<String,String>> maplist = new ArrayList<>();
        DBConnector.getConnection((conn)->{
            Statement statement = conn.createStatement();
            String sql = "SELECT name,balance FROM users";
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                Map<String,String> map = new HashMap<>();
                map.put("name",res.getString("name"));
                map.put("balance",res.getString("balance"));
                maplist.add(map);
            }
        });
        resp.getWriter().write(new Gson().toJson(maplist));
    }
}
