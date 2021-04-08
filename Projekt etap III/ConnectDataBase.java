package DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
    private static final String Url = "jdbc:mysql://localhost:3306/";
    private static final String User = "root";
    private static final String Password = "";
    Connection connection = null;
    public Connection connect() {
        try {
            connection = DriverManager.getConnection(Url, User, Password);

        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Nie mozna polaczyc się z baza danych");

        }
        return connection;
    }

    public boolean closeDatabase() throws SQLException {
        connection.close();
        return true;

    }

}
