package dao;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrdersDao extends BaseDao {
    /**
     * ��ȡָ���û��Ĺ���
     *
     * @param uid �û�UID
     * @return
     * @throws SQLException
     */
    List<Order> getOrdersByUID(String uid) throws SQLException;

    /**
     * ��ȡָ��OID�Ĺ���
     *
     * @param oid ����OID
     * @return
     * @throws SQLException
     */
    Order getOrderByOID(String oid) throws SQLException;

    /**
     * �ύ����
     *
     * @param order
     * @throws SQLException
     */
    void postOrder(Order order) throws SQLException;

    String getOrderType(int index) throws SQLException;

    int getOrderType(String name) throws SQLException;

    void setOrderState(String oid, int state) throws SQLException;

    void deleteOrder(String oid) throws SQLException;

    Map<Integer,String> getOrderTypeMap() throws SQLException;
}
