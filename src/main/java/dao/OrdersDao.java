package dao;

import bean.Order;

import java.sql.SQLException;
import java.util.List;

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

}
