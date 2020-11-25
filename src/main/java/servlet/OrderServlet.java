package servlet;

import bean.Order;
import bean.Response;
import bean.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.impl.OrdersDao;
import utils.LoggerUtil;

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
        String uid = null;
        if (req.getParameter("uid") != null)
            uid = req.getParameter("uid");
        if (uid != null) {
            res.SetData(new OrdersDao().getOrdersOfUid(uid));
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
        Response res = new Response();
        try {
            Order order = new Order();
            User user = (User) req.getSession().getAttribute("loggedUser");
            if (user == null) {
                resp.setStatus(400);
                return;
            }
            order.setUid(user.getUid());
            order.setType(jsonObject.get("orderType").getAsString());
            order.setDesc(jsonObject.get("orderDesc").getAsString());
            new OrdersDao().postOrder(order, user.getUid());
            res.SetMessage("OK");
            LoggerUtil.Logf("工单提交：OID(%s),UID(%s)", order.getOid(), order.getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().write(res.toJson());
    }
}
