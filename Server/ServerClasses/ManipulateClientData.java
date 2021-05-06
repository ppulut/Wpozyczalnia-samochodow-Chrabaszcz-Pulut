package Server.ServerClasses;

import DataBase.ConnectDataBase;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManipulateClientData {

    PrintStream ps;
    ConnectDataBase conn;
    ResultSet rs;

    public ManipulateClientData( PrintStream ps, ConnectDataBase conn, ResultSet rs){
        this.conn=conn;
        this.ps = ps;
        this.rs = rs;
    }

    //Metoda edytująca dane admina
    public void editAdmin(String[] tokens) throws SQLException {
         //Zmienne przechowujace informacje o wypozyczeniu
         String type = tokens[1];
        String aId = tokens[2];
        String aName = tokens[3];
        String aSurname = tokens[4];
        String aLogin = tokens[5];
        String aPassword = tokens[6];
        String edit_Admin = null;
        boolean checkValue;
        int rows = 0;

        System.out.println(type);
        System.out.println(aId);
        System.out.println(aSurname);
        System.out.println(aLogin);
        System.out.println(aPassword);



        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        if (type.equalsIgnoreCase("admin")) {
            edit_Admin = ("UPDATE admin SET Imie='" + aName + "' ,Nazwisko='" + aSurname + "',Login='" + aLogin + "',Haslo='" + aPassword + "' WHERE id='" + aId + "'");

        } else if (type.equalsIgnoreCase("car")) {
            edit_Admin = ("UPDATE spis_samochodow SET Marka='" + aName + "' ,Model='" + aSurname + "',Cena='" + aLogin + "',Dostepny='" + aPassword + "' WHERE id='" + aId + "'");

        } else if (type.equalsIgnoreCase("user")) {
            edit_Admin = ("UPDATE uzytkownicy SET Imie='" + aName + "' ,Nazwisko='" + aSurname + "',Login='" + aLogin + "',Haslo='" + aPassword + "' WHERE id='" + aId + "'");
        }
        else if (type.equalsIgnoreCase("debt")) {

            String check = "select * from uzytkownicy where id = '" + aName + "'";
            rs = stmt.executeQuery(check);
            checkValue = rs.next();
            if(checkValue) {
                edit_Admin = ("UPDATE kary SET Klient_id='" + aName + "' ,Kwota='" + aSurname + "',Data='" + aLogin + "' WHERE id='" + aId + "'");
            }

        }
        //Żadanie okreslonego widoku z bazy
        if(edit_Admin!=null) {
            System.out.println("Wykonalem!!");
            rows = stmt.executeUpdate(edit_Admin);
        }
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




}
