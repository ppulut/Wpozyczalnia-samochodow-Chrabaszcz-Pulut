package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.Server;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDataInformation {

    PrintStream ps;
    ConnectDataBase conn;
    ResultSet rs;
    ResultSet rs2;
    private Server server;
    private ArrayList<String> userLogin;

    public ClientDataInformation(PrintStream ps, ConnectDataBase conn, ResultSet rs, Server server){
        this.conn=conn;
        this.ps = ps;
        this.rs = rs;
        this.server = server;
    }

    public void checkUniqueLogin(String[] tokens) throws SQLException {

        if (tokens.length == 3) {

            String type = tokens[1];
            String login = tokens[2];

            String st = null;
            String st2 = null;

            Statement stmt = conn.connect().createStatement( rs.TYPE_SCROLL_SENSITIVE,
                    rs.CONCUR_UPDATABLE);

            stmt.executeUpdate("USE wypozyczalnia");

            if (type.equalsIgnoreCase("user")) {
                st = "select login from uzytkownicy WHERE login = '" + login + "'";
                st2 = "select login from admin WHERE login = '" + login + "'";
            }


            rs = stmt.executeQuery(st);

            if (rs.next()) {
                ps.println("blad");
            } else {
                rs.beforeFirst();
                rs = stmt.executeQuery(st2);
                if(rs.next()){
                    ps.println("blad");
                }else{
                    ps.println("ok");
                }
            }

            //Zamkniecie polaczen z bazą
            if (!conn.closeDatabase()) {
                ps.println("blad");
            }

        } else {
            ps.println("blad");
        }

    }

    //Metoda Sprawdzajca czy wpisany login podczas logowania jest już zalogowany
    public void handleActualLogin(String[] tokens) {
        if (tokens.length == 2) {
            userLogin = server.getUserLogin();
            String login = tokens[1];
            boolean check = userLogin.contains(login);

            if (check) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        } else {
            ps.println("blad");
        }
    }

    //Metoda wyswietlajaca liczbe dostepny i niedostepnych samochodow
    public void handleNumberCars(String[] tokens) throws SQLException {

        String check = tokens[1];

        String sql = null;
        int rows = 0;

        if (check.equalsIgnoreCase("dst")) {
            sql = "select * from spis_samochodow where dostepny = 'T'";
        }
        if (check.equalsIgnoreCase("ndst")) {
            sql = "select * from spis_samochodow where dostepny = 'N'";
        }


        Statement stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("USE wypozyczalnia");

        rs = stmt.executeQuery(sql);

        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }

        if (!conn.closeDatabase()) {
            ps.println("blad");
        }
        if (!conn.checkDataBaseComponents(rs, stmt)) {
            rs.close();
            stmt.close();
        }

        ps.println(rows);
    }


    public void getActualogin(){
        long size = server.getUserLoginSize();
        ps.println(size);
    }

    //Metoda obslugujaca odswiezanie okna przez klienta
    public void handleRefreshData(String[] tokens) throws SQLException {
        if (tokens.length == 2) {
            String userId = tokens[1];

            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            String sql = "select sum(kary.kwota) as kwota,saldo from kary,uzytkownicy where  kary.klient_id = '" + userId + "' and uzytkownicy.id = kary.Klient_id and uzytkownicy.id = '" + userId + "'";

            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            if (rs.getString("kwota") == null) {
                ps.println("0" + " " + rs.getString("saldo"));
            } else {
                ps.println(rs.getString("kwota") + " " + rs.getString("saldo"));
            }

            if (!conn.closeDatabase()) {
                ps.println("blad");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                rs.close();
                stmt.close();
            }
        } else {
            ps.println("blad");
        }

    }


}
