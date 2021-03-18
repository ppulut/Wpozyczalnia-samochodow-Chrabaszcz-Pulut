import java.sql.SQLException;
import java.sql.Statement;

public class InsertsForTablesDB {

    logowanieBaza conn = new logowanieBaza();
    Statement stmt;

    public void InsertsForSpisSamochodow() throws SQLException{

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String SpisSamochodowInsert1 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Ford', 'Mustang', 2500, NULL, NULL, 'T') ";

        String SpisSamochodowInsert2 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Volkswagen', 'Golf', 600, NULL, NULL, 'T') ";

        String SpisSamochodowInsert3 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Ford', 'Mondeo', 700, NULL, NULL, 'T') ";

        String SpisSamochodowInsert4 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Mazda', '3', 650, NULL, NULL, 'T') ";

        String SpisSamochodowInsert5 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Seat', 'Leon', 300, NULL, NULL, 'T') ";

        String SpisSamochodowInsert6 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Volvo', 'C30', 550, NULL, NULL, 'T') ";

        String SpisSamochodowInsert7 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Toyota', 'Avensis', 680, NULL, NULL, 'T') ";

        String SpisSamochodowInsert8 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Toyota', 'Yaris', 330, NULL, NULL, 'T') ";

        String SpisSamochodowInsert9 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Audi', 'R8', 2900, NULL, NULL, 'T') ";

        String SpisSamochodowInsert10 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Renault', 'Megane', 150, NULL, NULL, 'T') ";

        String SpisSamochodowInsert11 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Volkswagen', 'Caddy', 620, NULL, NULL, 'T') ";

        String SpisSamochodowInsert12 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Peugeot', '206', 270, NULL, NULL, 'T') ";

        String SpisSamochodowInsert13 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Citroen', 'Partner', 430, NULL, NULL, 'T') ";

        String SpisSamochodowInsert14 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Fiat', 'Punto', 300, NULL, NULL, 'T') ";

        String SpisSamochodowInsert15 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Skoda', 'Octavia', 420, NULL, NULL, 'T') ";

        String SpisSamochodowInsert16 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Skoda', 'Fabia', 320, NULL, NULL, 'T') ";

        String SpisSamochodowInsert17 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Seat', 'Ibiza', 260, NULL, NULL, 'T') ";

        String SpisSamochodowInsert18 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Ford', 'Escort', 200, NULL, NULL, 'T') ";

        String SpisSamochodowInsert19 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Honda', 'Civic', 470, NULL, NULL, 'T') ";

        String SpisSamochodowInsert20 = "INSERT INTO Spis_samochodow(Marka, Model, Cena, Data_wypozyczenia, Data_zwrotu, Dostepny) " +
                "VALUES ('Volvo', 'S60', 700, NULL, NULL, 'T') ";


        stmt.executeUpdate(SpisSamochodowInsert1);
        stmt.executeUpdate(SpisSamochodowInsert2);
        stmt.executeUpdate(SpisSamochodowInsert3);
        stmt.executeUpdate(SpisSamochodowInsert4);
        stmt.executeUpdate(SpisSamochodowInsert5);
        stmt.executeUpdate(SpisSamochodowInsert6);
        stmt.executeUpdate(SpisSamochodowInsert7);
        stmt.executeUpdate(SpisSamochodowInsert8);
        stmt.executeUpdate(SpisSamochodowInsert9);
        stmt.executeUpdate(SpisSamochodowInsert10);
        stmt.executeUpdate(SpisSamochodowInsert11);
        stmt.executeUpdate(SpisSamochodowInsert12);
        stmt.executeUpdate(SpisSamochodowInsert13);
        stmt.executeUpdate(SpisSamochodowInsert14);
        stmt.executeUpdate(SpisSamochodowInsert15);
        stmt.executeUpdate(SpisSamochodowInsert16);
        stmt.executeUpdate(SpisSamochodowInsert17);
        stmt.executeUpdate(SpisSamochodowInsert18);
        stmt.executeUpdate(SpisSamochodowInsert19);
        stmt.executeUpdate(SpisSamochodowInsert20);

        stmt.close();
        conn.isClose();

    }




    public void InsertsForUsers() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String Uzytkownik_table_insert = "INSERT INTO  Uzytkownicy(Imie,Nazwisko,Login,Haslo) " +
                "VALUES ('user', 'user','user','user')";

        stmt.executeUpdate(Uzytkownik_table_insert);
        stmt.close();
        conn.isClose();
    }



    public void InsertsForAdmins() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String Admin_table_insert = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                "VALUES ('admin','admin','admin','admin')";

        stmt.executeUpdate(Admin_table_insert);
        stmt.close();
        conn.isClose();

    }

    public void InsertsForWypozyczeniaSamochodowPrzezKleintow() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");
        String wypozyczoneAuta = "INSERT INTO  wypozyczenia_samochodow_przez_klientow(id_uzytkownika,id_samochodu) " +
                "Values (1,1)";

        stmt.executeUpdate(wypozyczoneAuta);
        stmt.close();
        conn.isClose();

    }




}
