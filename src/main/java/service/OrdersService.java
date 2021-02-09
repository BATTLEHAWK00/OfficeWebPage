package service;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
    List<Order> doGetOrdersByUID(String uid) throws SQLException;
    void doDeleteOrder(String oid) throws Exception;
    Order getOrderByOID(String oid) throws SQLException;
    void ChangeOrderState(String oid,int state) throws Exception;
    void ReplyOrder(String oid,String msg);
    Map<Integer,String> getOrderTypes() throws SQLException;
}
