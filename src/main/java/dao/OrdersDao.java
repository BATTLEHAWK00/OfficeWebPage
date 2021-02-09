package dao;

import bean.order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrdersDao extends BaseDao {
    /**
     * 获取指定用户的工单
     *
     * @param uid 用户UID
     * @return
     * @throws SQLException
     */
    List<Order> getOrdersByUID(String uid) throws SQLException;

    /**
     * 获取指定OID的工单
     *
     * @param oid 工单OID
     * @return
     * @throws SQLException
     */
    Order getOrderByOID(String oid) throws SQLException;

    /**
     * 提交工单
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
