package servlet;

import bean.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.OrdersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response res = new Response();
        if (req.getSession().getAttribute("uid") != null) {
            res.SetData(new OrdersDao().getOrdersOfUid((String) req.getSession().getAttribute("uid")));
            res.SetMessage("OK");
        } else {
            res.SetMessage("找不到用户或会话失效！");
            resp.setStatus(400);
        }
        resp.getWriter().write(res.toJson());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();

    }
}
