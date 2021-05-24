package DataBase;

import javax.swing.*;
import java.sql.*;

public class ConnectDataBase {
    //Zmienne do logowania do bazy MySql
    private static final String Url = "jdbc:mysql://localhost:3306/";
    private static final String User = "root";
    private static final String Password = "";

    Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(Url, User, Password);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nie mozna polaczyc się z baza danych");

        }
        return connection;
    }

    public boolean closeDatabase() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        return true;

    }
}
