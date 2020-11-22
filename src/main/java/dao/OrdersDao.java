package dao;

import bean.Order;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    public List<Order> getOrdersOfUid(String uid) {
        List<Order> orderList = new ArrayList<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = String.format("SELECT * FROM orders WHERE UID ='%s'", uid);
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    while (res.next()) {
                        orderList.add(getOrderByResSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    private Order getOrderByResSet(ResultSet res) throws SQLException {
        Order order = new Order();
        order.setOid(res.getString("OID"));
        order.setUid(res.getString("UID"));
        order.setDesc(res.getString("desc_text"));
        order.setImgSrcs(res.getString("photos"));
        order.setResp(res.getString("resp_text"));
        Timestamp respTime = res.getTimestamp("resp_time");
        if (respTime != null)
            order.setRespTime(respTime.getTime());
        else
            order.setRespTime(0);
        order.setState(res.getString("state"));
        order.setType(res.getString("type"));
        return order;
    }
}
