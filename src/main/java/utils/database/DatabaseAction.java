package utils.database;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface DatabaseAction {
    void action(Connection conn) throws SQLException, InterruptedException;
}
