package Server.ValidateServerData;

import DataBase.ConnectDataBase;
import Server.ServerClasses.ReturnedMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValidateAdminData extends ReturnedMessage {
    ConnectDataBase conn = new ConnectDataBase();
    ResultSet rs;

    public boolean checkUserExist(String id) throws SQLException {

        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select id from uzytkownicy WHERE id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            if(!super.checkClose(conn)){
                return false;
            }
            return true;
        }else{
            if(!super.checkClose(conn)){
                return false;
            }
            return false;
        }
    }

    public boolean checkAdminExist(String id) throws SQLException {

        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select id from admin WHERE id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            if(!super.checkClose(conn)){
                return false;
            }
            return true;
        }else{
            if(!super.checkClose(conn)){
                return false;
            }
            return false;
        }
    }

    public boolean checkCarExist(String id) throws SQLException {

        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select id from spis_samochodow WHERE id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            if(!super.checkClose(conn)){
                return false;
            }
            return true;
        }else{
            if(!super.checkClose(conn)){
                return false;
            }
            return false;
        }
    }

    public boolean checkDebtExist(String id) throws SQLException {

        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select id from kary WHERE id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            if(!super.checkClose(conn)){
                return false;
            }
            return true;
        }else{
            if(!super.checkClose(conn)){
                return false;
            }
            return false;
        }
    }

    public boolean checkIdSize(String value){
        //Zakres integer w bazie danych
        if(value.length()>10000000)
        {
            return false;
        }else{
            return true;
        }
    }

    public boolean validateLogin(String login){

        if (login.length() <= 3 || login.length()>20) {
            return false;
        }else{
                return true;
        }
    }

    public boolean checkRentExist(String id) throws SQLException {

        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select id from wypozyczenia_samochodow_przez_klientow WHERE id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            if(!super.checkClose(conn)){
                return false;
            }
            return true;
        }else{
            if(!super.checkClose(conn)){
                return false;
            }
            return false;
        }
    }
}
