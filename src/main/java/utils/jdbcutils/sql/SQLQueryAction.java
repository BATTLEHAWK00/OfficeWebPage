package utils.jdbcutils.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLQueryAction {
    void action(ResultSet res) throws SQLException;
}
