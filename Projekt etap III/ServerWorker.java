package Server;

//Package
import DataBase.ConnectDataBase;

import SharedServerClientClasses.ActLoginUser;
import SharedServerClientClasses.TableData;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class ServerWorker extends Thread {
    //Reprezentuje socket clienta
    private final Socket clientSocket;
    //Reprezentuje port Servera
    private final Server server;
    ServerSocket serverSocket;
    //zmienne do komunikacji z Clientem
    private final PrintStream ps;
    private final BufferedReader buffReader;
    //inicjajca statmentu
    Statement stmt = null;
    //Stworzenie obeiktu do poleczenia z baza danych
    private static final ConnectDataBase conn = new ConnectDataBase();
    //Inicjacja ResultSet
    private static ResultSet rs = null;
    String id;
    //Aktualnie zalogowani uzytkownicy
    ArrayList<String> users = new ArrayList<>();

    //Konsutrktor ustawiajacy porty servera oraz zmienne do komunikacji z Clientem
    public ServerWorker(Server server, Socket clientSocket, ServerSocket serverSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        this.buffReader = new BufferedReader(new InputStreamReader(inputStream));
        this.server = server;
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        this.ps = new PrintStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            //Obsluga żądań od klienta
            handleClientSocket();
        } catch (IOException | InterruptedException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Metoda Obslugujaca polecenia od klienta
    private void handleClientSocket() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        String line;
        while ((line = buffReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("quit".equals(cmd)) {
                    System.out.println("Rozlaczono klienta o sokecie: " + clientSocket);
                    clientSocket.close();
                    break;
                } else if ("closeServer".equalsIgnoreCase(cmd)) {
                    server.setWork(false);
                    serverSocket.close();
                } else if ("login".equalsIgnoreCase(cmd)) {
                    handleLogin(tokens);
                } else if ("give_table".equalsIgnoreCase(cmd)) {
                    createTable(tokens);
                } else if ("insert_reservation".equalsIgnoreCase(cmd)) {
                    insertReservation(ps, tokens);
                } else if ("insert_register".equalsIgnoreCase(cmd)) {
                    handleRegister(tokens);
                }else if("refresh_data".equalsIgnoreCase(cmd)){
                    handleRefreshData(tokens);
                }else if("insert_saldo".equalsIgnoreCase(cmd)) {
                    handleModifySaldo(tokens);
                } else if ("pay_debt".equalsIgnoreCase(cmd)) {
                    handlePayDebt(tokens);
                }else {
                    String msg = "Nieobslugiwane polecenie przez Server " + cmd;
                    ps.println(msg);
                }
            }
        }
    }

    private void setId(String id) {
        this.id = id;
    }


    public String getsId() {
       return id;
    }

    private void handlePayDebt(String[] tokens) throws SQLException {
        String userId = tokens[1];
        String debt = tokens[2];

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "delete from kary where Klient_id = '" + userId + "'";
        String minusSaldo = "UPDATE uzytkownicy SET saldo = saldo - '" + debt + "' where id = '" + userId + "'";

        int rows = stmt.executeUpdate(sql);
        int rowsMinusSaldo = stmt.executeUpdate(minusSaldo);

        stmt.close();

        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }

        if(rows>0 && rowsMinusSaldo > 0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }



    }

    private void handleRefreshData(String[] tokens) throws SQLException {

        String userId = tokens[1];

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        String sql = "select sum(kary.kwota) as kwota,saldo from kary,uzytkownicy where  kary.klient_id = '" + userId + "' and uzytkownicy.id = kary.Klient_id and uzytkownicy.id = '" + userId + "'";



        rs = stmt.executeQuery(sql);
            rs.next();

            if(rs.getString("kwota")==null){
                ps.println("0"+" "+rs.getString("saldo"));
            }else{
                    ps.println(rs.getString("kwota")+" "+rs.getString("saldo"));
                }

        rs.close();
        stmt.close();
        
        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }


    }

    private void handleModifySaldo(String[] tokens) throws SQLException {
        String idKleint = tokens[1];
        String sPrice = tokens[2];

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        System.out.println("Saldo: " + sPrice);
        System.out.println("Klient: " + idKleint);

        String addSaldo = "UPDATE uzytkownicy SET saldo = saldo + '" + sPrice + "' where id = '" + idKleint + "'";

        int rows = stmt.executeUpdate(addSaldo);

        stmt.close();

        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }

        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }

    }
    private void handleRegister(String[] tokens) throws SQLException {
        String type = tokens[1];
        String name = tokens[2];
        String surrname = tokens[3];
        String login = tokens[4];
        String password = tokens[5];
        String saldo = "0";

        String insertRegiser = null;

        stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        if(type.equals("user")){

            insertRegiser = "INSERT INTO  uzytkownicy(imie,nazwisko,login,haslo,Saldo) " +
                    "VALUES('" + name + "','" + surrname + "','" + login + "','" + password +"','" + saldo +"')";

        }else if(type.equals("admin")){

            insertRegiser = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                    "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "')";
        }

        int rows = stmt.executeUpdate(insertRegiser);

        stmt.close();
        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }

        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }


    //Metoda dodająca Rezerwacje
    private void insertReservation(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
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
                + "VALUES('" + userId + "','" + carId + "','" + firstDate + "','" + secondDate +"')";

        String updateCar = "Update spis_samochodow SET dostepny = 'N' WHERE id = '" + carId + "'";

        String modifySaldo = "Update uzytkownicy set saldo = saldo - '" + sFinalCost + "' where id = '" + userId + "'";

        //Dodanie rezerwacji do bazy
        int rows = stmt.executeUpdate(insertReservation);
        int rowsUpdate = stmt.executeUpdate(updateCar);
        int rowsModifySaldo = stmt.executeUpdate(modifySaldo);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0&&rowsUpdate>0&&rowsModifySaldo>0) {
          ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda tworzaca tabele na podstawie okreslonego przez tokeny widoku
    private void createTable(String[] tokens) throws SQLException, IOException {
        String status = tokens[1];
        String sql = null;

        ObjectOutput objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        if(status.equalsIgnoreCase("spis")) {
            sql = "select * from spis_samochodow";
        }

        if(status.equalsIgnoreCase("user")) {
            sql = "select * from uzytkownicy";
        }

        if(status.equalsIgnoreCase("admin")) {
            sql = "select * from admin";
        }

        if(status.equalsIgnoreCase("rents")) {
            sql = "select marka,model,data_wypozyczenia,data_oddania from wypozyczenia_samochodow_przez_klientow,spis_samochodow,uzytkownicy where wypozyczenia_samochodow_przez_klientow.id_samochodu=spis_samochodow.id and uzytkownicy.id=wypozyczenia_samochodow_przez_klientow.id_uzytkownika and wypozyczenia_samochodow_przez_klientow.id_uzytkownika='" + getsId() + "'";
        }


        rs = stmt.executeQuery(sql);
        //Zwracanie Metadanych Mysql
        ResultSetMetaData rsmt = rs.getMetaData();
        //Zlicza ile kolumny znajduje sie w tabeli
        int columnCount = rsmt.getColumnCount();
        Vector column = new Vector(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            column.add(rsmt.getColumnName(i));
        }

        Vector data = new Vector();
        Vector rows;

        while (rs.next()) {
            rows = new Vector(columnCount);

            for (int i = 1; i <= columnCount; i++) {
                rows.add(rs.getString(i));
            }
            data.add(rows);
        }

        objectOutput.writeObject(new TableData(data, column));
        rs.close();
        stmt.close();
        if(!conn.closeDatabase()){
            System.err.println("Blad podczas rozłączania z bazą danych");
        }

    }

    //Metoda obslugujaca logowanie się uzytkownika do bazy danych
    private void handleLogin(String[] tokens) throws SQLException{
        String st = null;
        if (tokens.length == 4) {
            String login = tokens[1];
            String password = tokens[2];
            String type = tokens[3];

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");
            if(type.equals("user")) {
                st = ("SELECT * FROM Uzytkownicy WHERE login='" + login + "' AND haslo='" + password + "'");
            }else if(type.equals("admin")){
                st = ("SELECT * FROM admin WHERE login='" + login + "' AND haslo='" + password + "'");
            }

            rs = stmt.executeQuery(st);
            if (rs.next()) {
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String id = rs.getString("id");
                setId(id);
                String msg = "ok"+" "+imie+" "+nazwisko+" "+getsId();
                ps.println(msg);


                stmt.close();
                if(!conn.closeDatabase()){
                    System.err.println("Blad podczas rozłączania z bazą danych");
                }
                rs.close();
                return;

            }
            if (!rs.next()) {
                String msg = "error login\n";
                ps.println(msg);
                System.err.println("Blad logowania " + login);

                stmt.close();
                if(!conn.closeDatabase()){
                    System.err.println("Blad podczas rozłączania z bazą danych");
                }

               rs.close();

            }
        }

    }

}



