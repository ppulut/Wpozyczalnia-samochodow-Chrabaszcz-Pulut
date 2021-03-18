import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
public class zalogowanyUzytkownik {

    Statement stmt = null;
    logowanieBaza logo = new logowanieBaza();

    public void aktualnieZalogowany(JTextField username)
    {
        try {
            stmt = logo.connect().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate("USE wypozyczalnia");


            String st = ("SELECT imie,nazwisko FROM klienci,uzytkownicy WHERE klienci.klient_id = uzytkownicy.klient_id and'"+username+"'= uzytkownicy.login");
            ResultSet rs = stmt.executeQuery(st);

            rs.last();

            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");

           // Uzytkownik_interface uzytkownik_interface = new Uzytkownik_interface(imie,nazwisko);


        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}


 */