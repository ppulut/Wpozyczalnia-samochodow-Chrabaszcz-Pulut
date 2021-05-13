package Server.ServerClasses;

import DataBase.ConnectDataBase;
import SharedServerClientClasses.ValidateLogin;
import net.proteanit.sql.DbUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class HandleLogTables extends ReturnedMessage {

    String id;
    Statement stmt = null;
    private ResultSet rs;
    int columntCount;
    private static final ConnectDataBase conn = new ConnectDataBase();

    //Metody ustawiajace i zwracajace id zalogowanego uzytkownika
    private void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return id;
    }

    public void setColumntCount(int columntCount) {
        this.columntCount = columntCount;
    }
    public int getColumntCount() {
        return columntCount;
    }

    //Metoda obslugujaca logowanie się uzytkownika do bazy danych
    public String handleLogin(String[] tokens) throws SQLException {

        if (tokens.length == 4) {

            String st;
            String login = tokens[1];
            String password = tokens[2];
            String type = tokens[3];

            ValidateLogin validateLogin = new ValidateLogin();

            if (!validateLogin.validateData(login, password)) {
                return super.errorStr;
            } else {
                stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");
                if (type.equals("user")) {
                    st = ("SELECT * FROM Uzytkownicy WHERE BINARY login='" + login + "' AND BINARY haslo='" + password + "'");
                } else if (type.equals("admin")) {
                    st = ("SELECT * FROM admin WHERE BINARY login='" + login + "' AND BINARY haslo='" + password + "'");
                } else {
                    return super.errorStr;
                }
                rs = stmt.executeQuery(st);
                boolean check = rs.next();

                if (check) {
                    String imie = rs.getString("imie");
                    String nazwisko = rs.getString("nazwisko");
                    String id = rs.getString("id");
                    setId(id);
                    String msg = "ok" + " " + imie + " " + nazwisko + " " + getsId() + " " + login;
                    return msg;
                }
                else{
                    return super.errorStr;
                }
            }
        }
        return super.errorStr;
    }

    public Object table(String[] tokens) throws SQLException {

        if (tokens.length == 2) {
            String status = tokens[1];
            String sql;

            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (status.equalsIgnoreCase("spis")) {
                sql = "select * from spis_samochodow";
            } else if (status.equalsIgnoreCase("user")) {
                sql = "select * from uzytkownicy";
            } else if (status.equalsIgnoreCase("admin")) {
                sql = "select * from admin";
            } else if (status.equalsIgnoreCase("rents")) {
                sql = "select marka,model,data_wypozyczenia,data_oddania from wypozyczenia_samochodow_przez_klientow,spis_samochodow,uzytkownicy where wypozyczenia_samochodow_przez_klientow.id_samochodu=spis_samochodow.id and uzytkownicy.id=wypozyczenia_samochodow_przez_klientow.id_uzytkownika and wypozyczenia_samochodow_przez_klientow.id_uzytkownika='" + id + "'";
            } else if (status.equalsIgnoreCase("rentsAdmin")) {
                sql = "select * from wypozyczenia_samochodow_przez_klientow";
            } else if (status.equalsIgnoreCase("kary")) {
                sql = "select * from kary";
            } else {
                return null;
            }

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            ResultSet rs = stmt.executeQuery(sql);
            Object object = DbUtils.resultSetToTableModel(rs);

            return object;
        }else{
            return null;
        }
    }
}








