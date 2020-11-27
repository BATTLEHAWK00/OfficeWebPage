package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

interface BaseDao {
    /**
     * Bean数据转换
     *
     * @param res 待处理的ResultSet
     * @return 返回Bean数据
     * @throws SQLException
     */
    Object getByResultSet(ResultSet res) throws SQLException;
}
