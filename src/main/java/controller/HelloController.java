package controller;

import com.google.gson.Gson;
import utils.database.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/hello")
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        List<Map<String, String>> maplist = new ArrayList<>();
        try {
            DBConnector.getConnection((conn) -> {
                Statement statement = conn.createStatement();
                String sql = "SELECT name,balance FROM users";
                ResultSet res = statement.executeQuery(sql);
                while (res.next()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", res.getString("name"));
                    map.put("balance", res.getString("balance"));
                    maplist.add(map);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.getWriter().write(new Gson().toJson(maplist));
    }
}
