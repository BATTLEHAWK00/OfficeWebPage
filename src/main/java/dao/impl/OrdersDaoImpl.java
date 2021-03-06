package dao.impl;

import bean.order.Order;
import dao.OrdersDao;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class OrdersDaoImpl implements OrdersDao {

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
	public void postOrder(Order order) throws SQLException {

		DBConnector.get().getConnection(conn -> {
			var query = new SQLOperation(conn);
			String sql = String.format("INSERT INTO orders(OID,UID,type,desc_text,photos,submit_time,state)" +
							" VALUES('%s','%s',%d,'%s','%s',%s,%d)",
					order.getOid(),
					order.getUid(),
					order.getType(),
					order.getDesc(),
					"",
					"now()",
					1
			);
			query.setSql(sql);
			query.ExecuteNonQuery();
		});
	}

	@Override
	public String getOrderType(int index) throws SQLException {
		AtomicReference<String> name = new AtomicReference<>();
		DBConnector.get().getConnection(conn -> {
			var query = new SQLOperation(conn);
			query.setSql(String.format("SELECT type_name FROM order_type WHERE type_index=%d", index));
			query.ExecuteQuery(res -> {
				if (res.next())
					name.set(res.getString("type_name"));
			});
		});
		return name.get();
	}

	@Override
	public int getOrderType(String name) throws SQLException {
		AtomicInteger index = new AtomicInteger();
		DBConnector.get().getConnection(conn -> {
			var query = new SQLOperation(conn);
			query.setSql(String.format("SELECT type_index FROM order_type WHERE type_name='%s'", name));
			query.ExecuteQuery(res -> {
				if (res.next())
					index.set(res.getInt("type_index"));
			});
		});
		return index.get();
	}

	@Override
	public void setOrderState(String oid,int state) throws SQLException {
		DBConnector.get().getConnection(conn -> {
			var op = new SQLOperation(conn);
			String sql = String.format("UPDATE orders SET state=%d WHERE OID='%s'",state,oid);
			op.setSql(sql);
			op.ExecuteUpdate();
		});
	}

	@Override
	public void deleteOrder(String oid) throws SQLException {
		DBConnector.get().getConnection(conn -> {
			var op = new SQLOperation(conn);
			String sql = String.format("DELETE FROM orders where OID='%s'",oid);
			op.setSql(sql);
			op.ExecuteNonQuery();
		});
	}

	@Override
	public Map<Integer, String> getOrderTypeMap() throws SQLException {
		Map<Integer,String> types = new HashMap<>();
		DBConnector.get().getConnection(conn -> {
			var op = new SQLOperation(conn);
			String sql = String.format("SELECT * FROM order_type");
			op.setSql(sql);
			op.ExecuteQuery(res -> {
				while (res.next())
					types.put(res.getInt("type_index"),res.getString("type_name"));
			});
		});
		return types;
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
		order.setType(res.getInt("type"));
		return order;
	}
}
