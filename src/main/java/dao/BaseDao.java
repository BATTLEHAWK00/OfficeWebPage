package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

interface BaseDao {
    /**
     * Bean����ת��
     *
     * @param res �������ResultSet
     * @return ����Bean����
     * @throws SQLException
     */
    Object getByResultSet(ResultSet res) throws SQLException;
}
