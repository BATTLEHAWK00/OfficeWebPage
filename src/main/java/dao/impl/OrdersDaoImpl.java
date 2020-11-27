package dao.impl;

import bean.Order;
import dao.OrdersDao;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public void postOrder(Order order) throws SQLException {
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("INSERT INTO orders(OID,UID,type,desc_text,photos,submit_time,state)" +
                            " VALUES('%s','%s','%s','%s','%s',%s,'%s')",
                    order.getOid(),
                    order.getUid(),
                    order.getType(),
                    order.getDesc(),
                    "",
                    "now()",
                    "“—Ã·Ωª"
            );
            query.setSql(sql);
            query.ExecuteNonQuery();
        });
    }

    @Override
    public List<Order> getOrdersByUID(String uid) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM orders WHERE UID ='%s' ORDER BY submit_time DESC", uid);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                while (res.next()) {
                    orderList.add(getByResultSet(res));
                }
            });
        });
        return orderList;
    }

    @Override
    public Order getOrderByOID(String oid) throws SQLException {
        AtomicReference<Order> order = new AtomicReference<>();

        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM orders WHERE OID='%s'", oid);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    order.set(getByResultSet(res));
                }
            });
        });

        return order.get();
    }


    @Override
    public Order getByResultSet(ResultSet res) throws SQLException {
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
        order.setSubmitTime(res.getTimestamp("submit_time").getTime());
        order.setState(res.getString("state"));
        order.setType(res.getString("type"));
        return order;
    }
}
