package service;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
    List<Order> doGetOrdersByUID(String uid) throws SQLException;
    void doDeleteOrder(String oid) throws Exception;
    Order getOrderByOID(String oid) throws SQLException;
    void ChangeOrderState(String oid,int state) throws Exception;
    void ReplyOrder(String oid,String msg);
    Map<Integer,String> getOrderTypes() throws SQLException;
}
