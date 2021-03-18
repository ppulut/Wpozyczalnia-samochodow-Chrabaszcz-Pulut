import java.sql.*;

public class logowanieBaza {

    private static String Url = "jdbc:mysql://localhost:3306/";
    private static String User = "root";
    private static String Password = "";

    Connection connection = null;

    public Connection connect() {

        try {

            connection = DriverManager.getConnection(Url, User, Password);

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean isClose() throws SQLException {
        connection.close();
        return true;
    }



/*
    public boolean isCorrect(String login,String password){

        String sql = "select * from uzytkownicy where user_name='" + login + "'and password='" + password + "'";

        try {

            Statement stmt = logo.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");
            String st = ("SELECT * FROM log WHERE uz='"+userName+"' AND hasl='"+password+"'");
            ResultSet rs = stmt.executeQuery(st);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }


    }

 */







}


