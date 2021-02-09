package web.controllers;

import bean.Response;
import bean.order.Order;
import bean.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.impl.OrdersDaoImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.OrdersService;
import service.impl.OrdersServiceImpl;
import utils.LoggerUtil;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/order")
public class OrdersController {
    OrdersService ordersService = new OrdersServiceImpl();

    @RequestMapping("/getlist")
    public ResponseEntity<String> getList(@RequestParam("uid") String uid) throws SQLException {
        Response res = new Response();
        if (uid != null) {
            res.SetData(ordersService.doGetOrdersByUID(uid));
            res.SetMessage("OK");
            return new ResponseEntity<>(res.toJsonStr(), HttpStatus.ACCEPTED);
        } else {
            res.SetMessage("找不到用户或会话失效！");
            return new ResponseEntity<>(res.toJsonStr(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/post")
    public String postOrder(@RequestBody String param, HttpSession session) throws SQLException {
        JsonObject jsonObject = JsonParser.parseString(param).getAsJsonObject();
        Response res = new Response();
        Order order = new Order();
        User user = (User) session.getAttribute("loggedUser");
        order.setUid(user.getUid());
        order.setType(new OrdersDaoImpl().getOrderType(jsonObject.get("orderType").getAsString()));
        order.setDesc(jsonObject.get("orderDesc").getAsString());
        ordersService.doPostOrder(order);
        res.SetMessage("OK");
        LoggerUtil.Logf("工单提交：OID(%s),UID(%s)", order.getOid(), order.getUid());
        return res.toJsonStr();
    }

    @RequestMapping("/delete")
    public void deleteOrder(@RequestParam("oid") String oid) throws Exception {
        ordersService.doDeleteOrder(oid);
    }

    @RequestMapping("/setstate")
    public void setOrderState(@RequestParam("oid") String oid, @RequestParam("state") int state) throws Exception {
        ordersService.ChangeOrderState(oid, state);
    }

    @RequestMapping("/typelist")
    public String getOrderTypeList() throws SQLException {
        return new Gson().toJson(ordersService.getOrderTypes());
    }
}
