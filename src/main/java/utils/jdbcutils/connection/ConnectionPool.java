package utils.jdbcutils.connection;

import utils.LoggerUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@FunctionalInterface
interface GetConnection {
	Connection getConn() throws SQLException;
}

/**
 * @author 15407
 */
public class ConnectionPool {
	private final Queue<Connection> connPool = new ConcurrentLinkedQueue<>();
	private final int MAX_SIZE;
	private final int INIT_SIZE;
	private final GetConnection getConnection;

	public ConnectionPool(int initsize, int maxsize, GetConnection getConnection) {
		MAX_SIZE = maxsize;
		INIT_SIZE = initsize;
		this.getConnection = getConnection;
		initConn();
	}

	private void initConn() {
		try {
			while (connPool.size() < INIT_SIZE) {
				connPool.add(getConnection.getConn());
				//休眠线程，避免初始化时消耗过多资源
				Thread.sleep(50);
			}
		} catch (Exception e) {
			System.err.println("创建数据库连接时发生错误：");
			System.err.println(e.getMessage());
		}
	}

	public void push(Connection conn) {
		try {
			if (connPool.size() >= MAX_SIZE) {
				LoggerUtil.LogErr("数据库连接池已满，考虑增加连接池容量");
				conn.close();
				return;
			}
			connPool.add(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection pop() throws SQLException {
		try {
			while (!connPool.element().isValid(3) || connPool.element().isClosed())
				connPool.remove();
			return connPool.remove();
		} catch (NoSuchElementException e) {
			new Thread(() -> initConn()).start();
			return getConnection.getConn();
		}
	}

	public int size() {
		return connPool.size();
	}

	public int maxSize() {
		return MAX_SIZE;
	}
}