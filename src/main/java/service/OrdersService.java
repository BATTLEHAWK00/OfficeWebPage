package service;

import bean.Order;

import java.util.List;

public interface OrdersService {
    /**
     * 提交用户工单
     *
     * @param order
     */
    void doPostOrder(Order order);

    /**
     * 获取指定用户的工单
     *
     * @param uid 用户UID
     * @return
     */
    List<Order> doGetOrdersByUID(String uid);
}
