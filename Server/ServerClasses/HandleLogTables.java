package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.Server;
import SharedServerClientClasses.TableData;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class HandleLogTables {

    String id;
    private PrintStream ps;
    private BufferedReader buffReader;
    Statement stmt = null;
    private ResultSet rs;
    private Server server;
    private Socket clientSocket;
    private static final ConnectDataBase conn = new ConnectDataBase();
    //Metody ustawiajace i zwracajace id zalogowanego uzytkownika
    private void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return id;
    }


    public HandleLogTables(PrintStream ps, BufferedReader buffReader, ResultSet rs, Server server, Socket clientSocket){
        this.ps = ps;
        this.buffReader = buffReader;
        this.rs = rs;
        this.server = server;
        this.clientSocket = clientSocket;
    }

    //Metoda obslugujaca logowanie się uzytkownika do bazy danych
    public void handleLogin(String[] tokens) throws SQLException {

        if (tokens.length == 4) {

            String st = null;
            String login = tokens[1];
            String password = tokens[2];
            String type = tokens[3];

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");
            if (type.equals("user")) {
                st = ("SELECT * FROM Uzytkownicy WHERE BINARY login='" + login + "' AND BINARY haslo='" + password + "'");
            } else if (type.equals("admin")) {
                st = ("SELECT * FROM admin WHERE BINARY login='" + login + "' AND BINARY haslo='" + password + "'");
            }

            rs = stmt.executeQuery(st);

            if (rs.next()) {
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String id = rs.getString("id");
                setId(id);
                String msg = "ok" + " " + imie + " " + nazwisko + " " + getsId();
                ps.println(msg);
                server.setUserLogin(login);
                if (!conn.closeDatabase()) {
                    ps.println("blad");
                }
                if (!conn.checkDataBaseComponents(rs, stmt)) {
                    rs.close();
                    stmt.close();
                }
                return;

            }
            if (!rs.next()) {
                String msg = "error login\n";
                ps.println(msg);
                System.err.println("Blad logowania " + login);

                if (!conn.closeDatabase()) {
                    ps.println("blad");
                }
                if (!conn.checkDataBaseComponents(rs, stmt)) {
                    rs.close();
                    stmt.close();
                }

            }
        } else {
            ps.println("blad");
        }
    }

    //Metoda tworzaca tabele na podstawie okreslonego przez tokeny widoku
    public void createJTable(String[] tokens) throws SQLException, IOException {

        if (tokens.length == 2) {
            String status = tokens[1];
            String sql = null;

            ObjectOutput objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (status.equalsIgnoreCase("spis")) {
                sql = "select * from spis_samochodow";
            }

            if (status.equalsIgnoreCase("user")) {
                sql = "select * from uzytkownicy";
            }

            if (status.equalsIgnoreCase("admin")) {
                sql = "select * from admin";
            }

            if (status.equalsIgnoreCase("rents")) {
                sql = "select marka,model,data_wypozyczenia,data_oddania from wypozyczenia_samochodow_przez_klientow,spis_samochodow,uzytkownicy where wypozyczenia_samochodow_przez_klientow.id_samochodu=spis_samochodow.id and uzytkownicy.id=wypozyczenia_samochodow_przez_klientow.id_uzytkownika and wypozyczenia_samochodow_przez_klientow.id_uzytkownika='" + id + "'";
                System.out.println(id);
            }

            if (status.equalsIgnoreCase("rentsAdmin")) {
                sql = "select * from wypozyczenia_samochodow_przez_klientow";
            }

            if (status.equalsIgnoreCase("kary")) {
                sql = "select * from kary";
            }


            ResultSet rs = stmt.executeQuery(sql);
            //Zwracanie Metadanych Mysql
            ResultSetMetaData rsmt = rs.getMetaData();
            //Zlicza ile kolumn znajduje sie w tabeli
            int columnCount = rsmt.getColumnCount();
            Vector<String> column = new Vector<>(columnCount);

            for (int i = 1; i <= columnCount; i++) {
                column.add(rsmt.getColumnName(i));
            }

            Vector<Vector<String>> data = new Vector<>();
            Vector<String> rows;

            while (rs.next()) {
                rows = new Vector<>(columnCount);

                for (int i = 1; i <= columnCount; i++) {
                    rows.add(rs.getString(i));
                }
                data.add(rows);
            }

            objectOutput.writeObject(new TableData(data, column));

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
