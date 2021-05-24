package DataBase;

import java.sql.SQLException;
import java.sql.Statement;

public class DropDatabase {

    public void dropDatabaseMethod() throws SQLException {
        ConnectDataBase conn = new ConnectDataBase();

        String sql = "DROP DATABASE Wypozyczalnia";
        Statement statement;

        statement = conn.connect().createStatement();
        statement.executeUpdate(sql);

    }

}
