package Server.ServerClasses;

import DataBase.ConnectDataBase;

import java.sql.SQLException;

public abstract class ReturnedMessage {
    protected String correctStr = "ok";
    protected String errorStr = "error";

    protected Boolean checkClose(ConnectDataBase conn) throws SQLException {
        if (!conn.closeDatabase()) {
            return false;
        }
        return true;
    }
}
