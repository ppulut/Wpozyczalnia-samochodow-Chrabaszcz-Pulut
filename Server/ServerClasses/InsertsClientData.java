package Server.ServerClasses;

import DataBase.ConnectDataBase;


import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertsClientData {
    PrintStream ps;
    ConnectDataBase conn;
    ResultSet rs;

    public InsertsClientData( PrintStream ps, ConnectDataBase conn, ResultSet rs){
       this.conn=conn;
       this.ps = ps;
       this.rs = rs;
    }


    //Metoda dodająca Rezerwacje
    public void insertReservation(String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if (tokens.length == 6) {
            String userId = tokens[1];
            String carId = tokens[2];
            String firstDate = tokens[3];
            String secondDate = tokens[4];
            String sFinalCost = tokens[5];

            //Stworzenie polaczenia  z baza
            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            //Dodaje nowa rezerwacje do bazy danych
            String insertReservation = "Insert into wypozyczenia_samochodow_przez_klientow(id_uzytkownika,id_samochodu,data_wypozyczenia,data_oddania)"
                    + "VALUES('" + userId + "','" + carId + "','" + firstDate + "','" + secondDate + "')";

            String updateCar = "Update spis_samochodow SET dostepny = 'N' WHERE id = '" + carId + "'";
            String modifySaldo = "Update uzytkownicy set saldo = saldo - '" + sFinalCost + "' where id = '" + userId + "'";

            //Dodanie rezerwacji do bazy
            int rows = stmt.executeUpdate(insertReservation);
            int rowsUpdate = stmt.executeUpdate(updateCar);
            int rowsModifySaldo = stmt.executeUpdate(modifySaldo);

            //Zamkniecie polaczen z bazą
            if (!conn.closeDatabase()) {
                ps.println("blad");
            }

            //Obsluga bledow do zadanego widoku z bazy
            if (rows > 0 && rowsUpdate > 0 && rowsModifySaldo > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        } else {
            ps.println("blad");
        }
    }

    public void handleRegister(String[] tokens) throws SQLException {
        if (tokens.length == 6) {
            String type = tokens[1];
            String name = tokens[2];
            String surrname = tokens[3];
            String login = tokens[4];
            String password = tokens[5];
            String saldo = "0";

            String insertRegiser = null;

            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (type.equals("user")) {
                insertRegiser = "INSERT INTO  uzytkownicy(imie,nazwisko,login,haslo,Saldo) " +
                        "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "','" + saldo + "')";

            } else if (type.equals("admin")) {

                insertRegiser = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                        "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "')";
            }

            int rows = stmt.executeUpdate(insertRegiser);


            //Zamkniecie polaczen z bazą
            if (!conn.closeDatabase()) {
                ps.println("blad");
            }

            if (rows > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }

        } else {
            ps.println("blad");
        }
    }

    public void handlePayDebt(String[] tokens) throws SQLException {
        if (tokens.length == 3) {

            String userId = tokens[1];
            String debt = tokens[2];

           Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            String sql = "delete from kary where Klient_id = '" + userId + "'";
            String minusSaldo = "UPDATE uzytkownicy SET saldo = saldo - '" + debt + "' where id = '" + userId + "'";

            int rows = stmt.executeUpdate(sql);
            int rowsMinusSaldo = stmt.executeUpdate(minusSaldo);

            if (!conn.closeDatabase()) {
                ps.println("blad");
            }

            if (rows > 0 && rowsMinusSaldo > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        } else {
            ps.println("blad");
        }
    }

    //Metoda obslugujaca dodawanie i usuwanie salda
    public void handleModifySaldo(String[] tokens) throws SQLException {
        if (tokens.length == 3) {

            String idKleint = tokens[1];
            String sPrice = tokens[2];

           Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            System.out.println("Saldo: " + sPrice);
            System.out.println("Klient: " + idKleint);

            String addSaldo = "UPDATE uzytkownicy SET saldo = saldo + '" + sPrice + "' where id = '" + idKleint + "'";

            int rows = stmt.executeUpdate(addSaldo);

            if (!conn.closeDatabase()) {
                ps.println("blad");
            }

            if (rows > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        } else {
            ps.println("blad");
        }

    }

    //Metoda dodająca nowy pojazd
    public void insertNewData(String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");
        boolean checkValue;
        int rows = 0;


        String insert_NewCar = null;
        String type = tokens[1];

        if (type.equalsIgnoreCase("car")) {
            String value1 = tokens[2];
            String value2 = tokens[3];
            String value3 = tokens[4];
            String value4 = tokens[5];
            //Zmienna przechowujaca żadanie okreslonego widoku
            insert_NewCar = "Insert into spis_samochodow(Marka,Model,Cena,Dostepny)"
                    + "VALUES('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "')";
        } else if (type.equalsIgnoreCase("fee")) {
            System.out.println("Otrzymalem "+ type);
            String value1 = tokens[2];
            String value2 = tokens[3];
            String value3 = tokens[4];
            System.out.println("Otrzymalem "+ value1);
            System.out.println("Otrzymalem "+ value2);
            System.out.println("Otrzymalem "+ value3);


            String check = "select * from uzytkownicy where id = '" + value1 + "'";
            rs = stmt.executeQuery(check);
            checkValue = rs.next();
            if(checkValue){
                insert_NewCar = "Insert into kary(Klient_id,Kwota,Data)"
                        + "VALUES('" + value1 + "','" + value2 + "','" + value3 + "')";
            }


        }

        if(insert_NewCar!=null) {
            //Żadanie okreslonego widoku z bazy
            rows = stmt.executeUpdate(insert_NewCar);
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
