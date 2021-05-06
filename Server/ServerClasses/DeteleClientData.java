package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.Server;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeteleClientData {

    PrintStream ps;
    ConnectDataBase conn;
    ResultSet rs;
    private Server server;

    public DeteleClientData(PrintStream ps, ConnectDataBase conn, ResultSet rs, Server server){
        this.conn=conn;
        this.ps = ps;
        this.rs = rs;
        this.server = server;
    }


    //Metoda usuwająca  admina
    public void deleteAdmin(String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String type = tokens[1];
        String aId = tokens[2];
        String delete_Admin = null;
        String delete_Admin2 = null;
        String idSamochodu = null;

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        if (type.equalsIgnoreCase("admin")) {
            delete_Admin = ("DELETE FROM admin WHERE id='" + aId + "'");
        } else if (type.equalsIgnoreCase("car")) {
            delete_Admin = ("DELETE FROM spis_samochodow WHERE id='" + aId + "'");
        } else if (type.equalsIgnoreCase("user")) {
            delete_Admin2 = ("DELETE FROM kary WHERE Klient_id='" + aId + "'");
            delete_Admin = ("DELETE FROM uzytkownicy WHERE id='" + aId + "'");
        } else if (type.equalsIgnoreCase("rent")) {
            delete_Admin = ("DELETE FROM wypozyczenia_samochodow_przez_klientow WHERE id='" + aId + "'");

            String update = "select * from wypozyczenia_samochodow_przez_klientow WHERE id='" + aId + "'";
            rs = stmt.executeQuery(update);

            if(rs.next()){
                idSamochodu = rs.getString("id_samochodu");
            }

            if(idSamochodu!=null) {
                String updateCar = "Update spis_samochodow SET dostepny = 'T' WHERE id = '" + idSamochodu + "'";
                stmt.executeUpdate(updateCar);
            }

        } else if (type.equalsIgnoreCase("fee")) {
            delete_Admin = ("DELETE FROM kary WHERE id='" + aId + "'");
        }

        if (delete_Admin2 != null) {
            int rows2 = stmt.executeUpdate(delete_Admin2);
        }
        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_Admin);


        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if (rows > 0) {
            ps.println("ok");
        } else {
            ps.println("blad");
        }
    }

    public void removeActualLogin(String[] tokens) {
        if (tokens.length == 3) {
            String login = tokens[1];
            server.removeUserLogin(login);

            ps.println("ok");
        } else {
            ps.println("blad");
        }

    }

}
