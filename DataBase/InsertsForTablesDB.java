package DataBase;

import Server.ServerClasses.ReturnedMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertsForTablesDB extends ReturnedMessage {
    ConnectDataBase conn = new ConnectDataBase();
    Statement stmt;
    ResultSet rs;
    int rows;

    public boolean insertCarsTable() throws SQLException{

        stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

        String sql = "select * from spis_samochodow where dostepny = 'T'";

        rs = stmt.executeQuery(sql);


        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }

        if(rows == 20 && super.checkClose(conn)){
           return true;
        }else{
            return false;
        }
    }

    public boolean InsertsForUsers() throws SQLException {

        stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("USE wypozyczalnia");

        String Uzytkownik_table_insert = "INSERT INTO  Uzytkownicy(Imie,Nazwisko,Login,Haslo,Saldo) " +
                "VALUES ('Konrad', 'Chrabaszcz','UserTest','UserTest1',1236.6)";
        String Uzytkownik_table_insert2 = "INSERT INTO  Uzytkownicy(Imie,Nazwisko,Login,Haslo,Saldo) " +
                "VALUES ('KonradTest', 'ChrabaszczTest','UserTest1','UserTest12',1236.6)";

        stmt.executeUpdate(Uzytkownik_table_insert);
        stmt.executeUpdate(Uzytkownik_table_insert2);

        String sql = "select * from Uzytkownicy";

        rs = stmt.executeQuery(sql);


        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }

        if(rows == 2 && super.checkClose(conn)){
            return true;
        }else{
            return false;
        }

    }

    public boolean InsertsForAdmins() throws SQLException {

        stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("USE wypozyczalnia");

        String Admin_table_insert = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                "VALUES ('Pawel','Pulut','AdminTest','AdminTest1')";
        String Admin_table_insert2 = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                "VALUES ('PawelTest','PulutTest','AdminTest1','AdminTest12')";

        stmt.executeUpdate(Admin_table_insert);
        stmt.executeUpdate(Admin_table_insert2);

        String sql = "select * from Admin";

        rs = stmt.executeQuery(sql);


        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }

        if(rows == 2 && super.checkClose(conn)){
            return true;
        }else{
            return false;
        }
    }


    public boolean insertCarsRent() throws SQLException {

        stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("USE wypozyczalnia");
        String wypozyczoneAuta = "Insert into wypozyczenia_samochodow_przez_klientow(id_uzytkownika,id_samochodu,data_wypozyczenia,data_oddania) " +
                "Values (1,1,null,null)";

        stmt.executeUpdate(wypozyczoneAuta);

        String sql = "select * from wypozyczenia_samochodow_przez_klientow";

        rs = stmt.executeQuery(sql);


        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }

        if(rows == 1 && super.checkClose(conn)){
            return true;
        }else{
            return false;
        }
    }


    public boolean insertKaryTable() throws SQLException {

        stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("USE wypozyczalnia");

        String usersKary = " INSERT INTO `kary` (`id`, `Klient_id`, `Kwota`, `Data`) VALUES (NULL, '1', '222', '2021-05-09')";
        String usersKary2 = " INSERT INTO `kary` (`id`, `Klient_id`, `Kwota`, `Data`) VALUES (NULL, '1', '222', '2021-05-09')";


        stmt.executeUpdate(usersKary);
        stmt.executeUpdate(usersKary2);

        String sql = "select * from kary";

        rs = stmt.executeQuery(sql);

        if (rs.last()) {
            rows = rs.getRow();
            rs.beforeFirst();
        }
        if(rows == 2 && super.checkClose(conn)){
            return true;
        }else {
            return false;
        }
    }
}
