package service;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
	/**
	 * 提交用户工单
	 *
	 * @param order
	 */
	void doPostOrder(Order order) throws SQLException;

    /**
     * 获取指定用户的工单
     *
     * @param uid 用户UID
     * @return
     */
    List<Order> doGetOrdersByUID(String uid);
}
