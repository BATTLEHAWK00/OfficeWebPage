package service.impl;

import bean.order.Order;
import dao.OrdersDao;
import dao.impl.OrdersDaoImpl;
import service.OrdersService;
import utils.StringUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @class: OrdersServiceImpl
 * @description:
 * @author: YXL
 **/
public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = new OrdersDaoImpl();

    @Override
    public void doPostOrder(Order order) throws SQLException {
        String oid;
        do {
            oid = StringUtil.getUUID(8);
        } while (ordersDao.getOrderByOID(oid) != null);
        order.setOid(oid);
        ordersDao.postOrder(order);
    }

    @Override
    public List<Order> doGetOrdersByUID(String uid) throws SQLException {
        return ordersDao.getOrdersByUID(uid);
    }

    @Override
    public void doDeleteOrder(String oid) throws Exception {
        if (getOrderByOID(oid) == null)
            throw new Exception("未找到该工单!");
        ordersDao.deleteOrder(oid);
    }

    @Override
    public Order getOrderByOID(String oid) throws SQLException {
        return ordersDao.getOrderByOID(oid);
    }

    @Override
    public void ChangeOrderState(String oid, int state) throws Exception {
        if (getOrderByOID(oid) == null)
            throw new Exception("未找到该工单!");
        ordersDao.setOrderState(oid, state);
    }

    @Override
    public void ReplyOrder(String oid, String msg) {

    }

    @Override
    public Map<Integer, String> getOrderTypes() throws SQLException {
        return ordersDao.getOrderTypeMap();
    }
}
