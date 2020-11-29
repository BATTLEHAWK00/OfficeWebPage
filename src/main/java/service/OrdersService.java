package service;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
	/**
	 * �ύ�û�����
	 *
	 * @param order
	 */
	void doPostOrder(Order order) throws SQLException;

    /**
     * ��ȡָ���û��Ĺ���
     *
     * @param uid �û�UID
     * @return
     */
    List<Order> doGetOrdersByUID(String uid);
}
