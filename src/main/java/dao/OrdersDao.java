package dao;

import bean.Order;

import java.sql.SQLException;
import java.util.List;

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

}
