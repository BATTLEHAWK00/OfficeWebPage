package service.impl;

import bean.Order;
import dao.OrdersDao;
import dao.impl.OrdersDaoImpl;
import service.OrdersService;
import utils.StringUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @class: OrdersServiceImpl
 * @description:
 * @author: YXL
 **/
public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = new OrdersDaoImpl();

    @Override
    public void doPostOrder(Order order) {
        try {
            String oid;
            do {
                oid = StringUtil.getUUID(8);
            } while (ordersDao.getOrderByOID(oid) != null);
            order.setOid(oid);
            ordersDao.postOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> doGetOrdersByUID(String uid) {
        try {
            return ordersDao.getOrdersByUID(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


}
