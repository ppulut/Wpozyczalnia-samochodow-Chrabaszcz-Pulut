package DataBase;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertsForTablesDB {

    private final ConnectDataBase conn = new ConnectDataBase();
    private Statement stmt;

    public void insertCarsTable() throws SQLException{

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String spisSamochodowInsert1 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Ford', 'Mustang', 800 , 'T') ";

        String spisSamochodowInsert2 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Volkswagen', 'Golf', 200 , 'T') ";

        String spisSamochodowInsert3 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Ford', 'Mondeo', 100 , 'T') ";

        String spisSamochodowInsert4 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Mazda', '3', 150 , 'T') ";

        String spisSamochodowInsert5 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Seat', 'Leon', 200 , 'T') ";

        String spisSamochodowInsert6 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Volvo', 'C30', 350 , 'T') ";

        String spisSamochodowInsert7 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Toyota', 'Avensis', 380 , 'T') ";

        String spisSamochodowInsert8 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Toyota', 'Yaris', 130 , 'T') ";

        String spisSamochodowInsert9 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Audi', 'R8', 1200 , 'T') ";

        String spisSamochodowInsert10 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Renault', 'Megane', 150 , 'T') ";

        String spisSamochodowInsert11 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Volkswagen', 'Caddy', 420 , 'T') ";

        String spisSamochodowInsert12 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Peugeot', '206', 270 , 'T') ";

        String spisSamochodowInsert13 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Citroen', 'Partner', 430 , 'T') ";

        String spisSamochodowInsert14 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Fiat', 'Punto', 200 , 'T') ";

        String spisSamochodowInsert15 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Skoda', 'Octavia', 120 , 'T') ";

        String spisSamochodowInsert16 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Skoda', 'Fabia', 220 , 'T') ";

        String spisSamochodowInsert17 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Seat', 'Ibiza', 160 , 'T') ";

        String spisSamochodowInsert18 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Ford', 'Escort', 200 , 'T') ";

        String spisSamochodowInsert19 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Honda', 'Civic', 270 , 'T') ";

        String spisSamochodowInsert20 = "INSERT INTO Spis_samochodow(Marka, Model, Cena,  Dostepny) " +
                "VALUES ('Volvo', 'S60', 300 , 'T') ";


        stmt.executeUpdate(spisSamochodowInsert1);
        stmt.executeUpdate(spisSamochodowInsert2);
        stmt.executeUpdate(spisSamochodowInsert3);
        stmt.executeUpdate(spisSamochodowInsert4);
        stmt.executeUpdate(spisSamochodowInsert5);
        stmt.executeUpdate(spisSamochodowInsert6);
        stmt.executeUpdate(spisSamochodowInsert7);
        stmt.executeUpdate(spisSamochodowInsert8);
        stmt.executeUpdate(spisSamochodowInsert9);
        stmt.executeUpdate(spisSamochodowInsert10);
        stmt.executeUpdate(spisSamochodowInsert11);
        stmt.executeUpdate(spisSamochodowInsert12);
        stmt.executeUpdate(spisSamochodowInsert13);
        stmt.executeUpdate(spisSamochodowInsert14);
        stmt.executeUpdate(spisSamochodowInsert15);
        stmt.executeUpdate(spisSamochodowInsert16);
        stmt.executeUpdate(spisSamochodowInsert17);
        stmt.executeUpdate(spisSamochodowInsert18);
        stmt.executeUpdate(spisSamochodowInsert19);
        stmt.executeUpdate(spisSamochodowInsert20);

        if(!conn.closeDatabase()){
            stmt.close();
        }
    }
    public void InsertsForUsers() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String Uzytkownik_table_insert = "INSERT INTO  Uzytkownicy(Imie,Nazwisko,Login,Haslo,Saldo) " +
                "VALUES ('Konrad', 'Chrabaszcz','UserTest','UserTest1',1236.6)";

        stmt.executeUpdate(Uzytkownik_table_insert);

        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }
    }

    public void InsertsForAdmins() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String Admin_table_insert = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                "VALUES ('Pawel','Pulut','AdminTest','AdminTest1')";

        stmt.executeUpdate(Admin_table_insert);

        if(!conn.closeDatabase()){
            stmt.close();
        }

    }

    public void insertCarsRent() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");
        String wypozyczoneAuta = "Insert into wypozyczenia_samochodow_przez_klientow(id_uzytkownika,id_samochodu,data_wypozyczenia,data_oddania) " +
                "Values (1,1,null,null)";

        stmt.executeUpdate(wypozyczoneAuta);

        if(!conn.closeDatabase()){
            stmt.close();
        }
    }

    public void insertKaryTable() throws SQLException {

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");
        String usersKary = "insert into kary(Klient_id,Kwota,Data) " +
                "Values (1,300.5,26-06-2019)";
        stmt.executeUpdate(usersKary);

        if(!conn.closeDatabase()){
            stmt.close();
        }
    }
}
