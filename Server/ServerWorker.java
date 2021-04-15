package Server;

import DataBase.ConnectDataBase;
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
    private String id;

    private ArrayList<String> userLogin;


    //Metody ustawiajace i zwracajace id zalogowanego uzytkownika
    private void setId(String id) {
        this.id = id;
    }
    public String getsId() {
        return id;
    }

    //Konsutrktor ustawiajacy porty servera oraz zmienne do komunikacji z Clientem
    public ServerWorker(Server server, Socket clientSocket, ServerSocket serverSocket) throws IOException {
        this.buffReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.server = server;
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        this.ps = new PrintStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        //Obsluga żądań od klienta
        try {
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
                    String def = tokens[2];
                    if(def.equalsIgnoreCase("undef")) {
                        removeActualLogin(tokens);
                    }else{
                        removeActualLogin(tokens);
                        clientSocket.close();
                        break;
                    }
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
                } else if ("actual_login".equalsIgnoreCase(cmd)) {
                    userLogin = server.getUserLogin();
                    handleActualLogin(tokens,userLogin);
                } else if ("insert_New_Admin".equalsIgnoreCase(cmd)){
                    insertNewAdmin(ps,tokens);
                } else if ("insert_New_Car".equalsIgnoreCase(cmd)){
                    insertNewCar(ps,tokens);
                }else if ("insert_New_User".equalsIgnoreCase(cmd)){
                    insertNewUser(ps,tokens);
                } else if ("delete_Admin".equalsIgnoreCase(cmd)){
                    deleteAdmin(ps,tokens);
                } else if ("delete_Car".equalsIgnoreCase(cmd)){
                    deleteCar(ps,tokens);
                }else if ("delete_User".equalsIgnoreCase(cmd)){
                    deleteUser(ps,tokens);
                }else if ("delete_Rent".equalsIgnoreCase(cmd)){
                    deleteRent(ps,tokens);
                }else if ("insert_NewFee".equalsIgnoreCase(cmd)){
                    insertNewFee(ps,tokens);
                }else if ("delete_Fee".equalsIgnoreCase(cmd)){
                    deleteFee(ps,tokens);
                }else if ("edit_Admin".equalsIgnoreCase(cmd)){
                    editAdmin(ps,tokens);
                }else if ("edit_User".equalsIgnoreCase(cmd)){
                    editUser(ps,tokens);
                }else if ("edit_Car".equalsIgnoreCase(cmd)){
                    editCar(ps,tokens);
                }else {
                    String msg = "Nieobslugiwane polecenie przez Server " + cmd;
                    ps.println(msg);
                }
            }
        }
    }

    private void removeActualLogin(String[] tokens) {

        String login = tokens[1];
        server.removeUserLogin(login);
        System.out.println("Otrzymalem "+login);

        ps.println("ok");
    }

    private void handleActualLogin(String[] tokens, ArrayList<String> userLogin) {

        this.userLogin = userLogin;
        String login = tokens[1];
        System.out.println(userLogin);
        boolean check = userLogin.contains(login);

        if(check){
            ps.println("ok");
        }else{
            ps.println("blad");
        }

    }


    private void handlePayDebt(String[] tokens) throws SQLException {
        if(tokens.length == 3) {

            String userId = tokens[1];
            String debt = tokens[2];

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            String sql = "delete from kary where Klient_id = '" + userId + "'";
            String minusSaldo = "UPDATE uzytkownicy SET saldo = saldo - '" + debt + "' where id = '" + userId + "'";

            int rows = stmt.executeUpdate(sql);
            int rowsMinusSaldo = stmt.executeUpdate(minusSaldo);

            if (!conn.closeDatabase()) {
                System.err.println("Blad podczas rozłączania z bazą danych");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                rs.close();
                stmt.close();
            }

            if (rows > 0 && rowsMinusSaldo > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        }else{
            System.err.println("Przekazano bledne dane");
        }
    }

    private void handleRefreshData(String[] tokens) throws SQLException {
        if(tokens.length==2) {
            String userId = tokens[1];

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            String sql = "select sum(kary.kwota) as kwota,saldo from kary,uzytkownicy where  kary.klient_id = '" + userId + "' and uzytkownicy.id = kary.Klient_id and uzytkownicy.id = '" + userId + "'";

            rs = stmt.executeQuery(sql);
            rs.next();

            if (rs.getString("kwota") == null) {
                ps.println("0" + " " + rs.getString("saldo"));
            } else {
                ps.println(rs.getString("kwota") + " " + rs.getString("saldo"));
            }

            if (!conn.closeDatabase()) {
                System.err.println("Blad podczas rozłączania z bazą danych");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                rs.close();
                stmt.close();
            }
        }else{
            System.err.println("Przekazano bledne dane");
        }

    }

    private void handleModifySaldo(String[] tokens) throws SQLException {
        if(tokens.length == 3) {

            String idKleint = tokens[1];
            String sPrice = tokens[2];

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            System.out.println("Saldo: " + sPrice);
            System.out.println("Klient: " + idKleint);

            String addSaldo = "UPDATE uzytkownicy SET saldo = saldo + '" + sPrice + "' where id = '" + idKleint + "'";

            int rows = stmt.executeUpdate(addSaldo);

            if (!conn.closeDatabase()) {
                System.err.println("Blad podczas rozłączania z bazą danych");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                System.err.println("Blad podczas rozłączania komponentow bazt danych");
            }

            if (rows > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        }else{
            System.err.println("Przekazano bledne dane");
        }

    }
    private void handleRegister(String[] tokens) throws SQLException {
        if(tokens.length == 6) {
            String type = tokens[1];
            String name = tokens[2];
            String surrname = tokens[3];
            String login = tokens[4];
            String password = tokens[5];
            String saldo = "0";

            String insertRegiser = null;

            stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (type.equals("user")) {

                insertRegiser = "INSERT INTO  uzytkownicy(imie,nazwisko,login,haslo,Saldo) " +
                        "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "','" + saldo + "')";

            } else if (type.equals("admin")) {

                insertRegiser = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                        "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "')";
            }

            int rows = stmt.executeUpdate(insertRegiser);


            if (!conn.closeDatabase()) {
                System.err.println("Blad podczas rozłączania z bazą danych");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                System.err.println("Blad podczas rozłączania komponentow bazt danych");
            }

            if (rows > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        }else{
            System.err.println("Przekazano bledne dane");
        }
    }


    //Metoda dodająca Rezerwacje
    private void insertReservation(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if(tokens.length == 6) {
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
                System.err.println("Blad podczas rozłączania z bazą danych");
            }
            if (!conn.checkDataBaseComponents(rs, stmt)) {
                System.err.println("Blad podczas rozłączania komponentow bazt danych");
            }

            //Obsluga bledow do zadanego widoku z bazy
            if (rows > 0 && rowsUpdate > 0 && rowsModifySaldo > 0) {
                ps.println("ok");
            } else {
                ps.println("blad");
            }
        }else{
            System.err.println("Przekazano bledne dane");
        }
    }

    //Metoda tworzaca tabele na podstawie okreslonego przez tokeny widoku
    private void createTable(String[] tokens) throws SQLException, IOException {
        if(tokens.length == 2) {
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
                sql = "select marka,model,data_wypozyczenia,data_oddania from wypozyczenia_samochodow_przez_klientow,spis_samochodow,uzytkownicy where wypozyczenia_samochodow_przez_klientow.id_samochodu=spis_samochodow.id and uzytkownicy.id=wypozyczenia_samochodow_przez_klientow.id_uzytkownika and wypozyczenia_samochodow_przez_klientow.id_uzytkownika='" + getsId() + "'";
            }

            if(status.equalsIgnoreCase("rentsAdmin")) {
                sql = "select * from wypozyczenia_samochodow_przez_klientow";
            }

            if(status.equalsIgnoreCase("kary")) {
                sql = "select * from kary";
            }


            rs = stmt.executeQuery(sql);
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
                System.err.println("Blad podczas rozłączania z bazą danych");
            }

            if (!conn.checkDataBaseComponents(rs, stmt)) {
                System.err.println("Blad podczas rozłączania komponentow bazt danych");
            }
        }else{
            System.err.println("Przekazano bledne dane");
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
                server.setUserLogin(login);
                if (!conn.closeDatabase()) {
                    System.err.println("Blad podczas rozłączania z bazą danych");
                }
                if (!conn.checkDataBaseComponents(rs, stmt)) {
                    System.err.println("Blad podczas rozłączania komponentow bazt danych");
                }
                return;

            }
            if (!rs.next()) {
                String msg = "error login\n";
                ps.println(msg);
                System.err.println("Blad logowania " + login);

                if (!conn.closeDatabase()) {
                    System.err.println("Blad podczas rozłączania z bazą danych");
                }
                if (!conn.checkDataBaseComponents(rs, stmt)) {
                    System.err.println("Blad podczas rozłączania komponentow bazt danych");
                }

            }
        }else{
            System.err.println("Przekazano bledne dane");
        }
    }

    //Metoda dodająca nowego admina
    private void insertNewAdmin(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String aName = tokens[1];
        String aSurname = tokens[2];
        String aLogin = tokens[3];
        String aPassword = tokens[4];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String insert_NewAdmin = "Insert into admin(Imie,Nazwisko,Login,Haslo)"
                + "VALUES('"  + aName + "','" + aSurname + "','" + aLogin + "','" + aPassword  + "')";

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(insert_NewAdmin);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda usuwająca  admina
    private void deleteAdmin(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String aName = tokens[1];
        String aSurname = tokens[2];
        String aLogin = tokens[3];
        String aPassword = tokens[4];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String delete_Admin = ("DELETE FROM admin WHERE Imie='" + aName + "' AND Nazwisko='" +aSurname + "' AND Login='" +aLogin + "' AND haslo='" +aPassword + "'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_Admin);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda dodająca nowy pojazd
    private void insertNewCar(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String cMarka = tokens[1];
        String cModel = tokens[2];
        String cCena = tokens[3];
        String dostepnosc = tokens[4];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String insert_NewCar = "Insert into spis_samochodow(Marka,Model,Cena,Dostepny)"
                + "VALUES('"  + cMarka + "','" + cModel + "','" +  cCena  + "','" + dostepnosc  + "')";

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(insert_NewCar);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    private void deleteCar(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String cMarka = tokens[1];
        String cModel = tokens[2];
        String cCena = tokens[3];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String delete_Car = ("DELETE FROM spis_samochodow WHERE Marka='" + cMarka + "' AND Model='" +cModel + "' AND Cena='" +cCena + "'");


        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_Car);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }


    //Metoda dodająca nowego uzytkownika
    private void insertNewUser(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String uName = tokens[1];
        String uSurname = tokens[2];
        String uLogin = tokens[3];
        String uPassword = tokens[4];
        String uSaldo = tokens[5];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String insert_NewUser = "Insert into uzytkownicy(Imie,Nazwisko,Login,Haslo,Saldo)"
                + "VALUES('"  + uName + "','" + uSurname + "','" + uLogin + "','" + uPassword  + "','" +uSaldo + "')";

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(insert_NewUser);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }


    //Metoda usuwająca uzytkownika
    private void deleteUser(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String uName = tokens[1];
        String uSurname = tokens[2];
        String uLogin = tokens[3];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String delete_User = ("DELETE FROM uzytkownicy WHERE Imie='" + uName + "' AND Nazwisko='" +uSurname + "' AND Login='" +uLogin + "'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_User);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }


    //Metoda usuwająca wypozyczenie
    private void deleteRent(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String wId = tokens[1];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String delete_Rent = ("DELETE FROM wypozyczenia_samochodow_przez_klientow WHERE id='" + wId + "'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_Rent);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda dodająca nową karę dla uzytkownika
    private void insertNewFee(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String rUserId = tokens[1];
        String rKwota = tokens[2];
        String rData = tokens[3];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String insert_NewFee = "Insert into kary(Klient_id,Kwota,Data)"
                + "VALUES('"  + rUserId + "','" + rKwota + "','" + rData  + "')";

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(insert_NewFee);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda usuwająca karę dla uzytkownika
    private void deleteFee(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String rUserId = tokens[1];
        String rKwota = tokens[2];
        String rData = tokens[3];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String delete_Fee = ("DELETE FROM kary WHERE Klient_id='" + rUserId + "' AND Kwota='" +rKwota + "' AND Data='" +rData + "'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(delete_Fee);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda edytująca dane admina
    private void editAdmin(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String aId = tokens[1];
        String aName = tokens[2];
        String aSurname = tokens[3];
        String aLogin = tokens[4];
        String aPassword = tokens[5];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String edit_Admin = ("UPDATE admin SET Imie='" + aName + "' ,Nazwisko='" +aSurname + "',Login='" +aLogin + "',Haslo='" +aPassword+"' WHERE id='"+aId+"'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(edit_Admin);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda edytująca dane o pojeździe
    private void editCar(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String cId = tokens[1];
        String cMarka = tokens[2];
        String cModel = tokens[3];
        String cCena = tokens[4];
        String dostepnosc = tokens[5];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String edit_Car = ("UPDATE spis_samochodow SET Marka='" + cMarka + "' ,Model='" +cModel + "',Cena='" +cCena + "',Dostepny='" +dostepnosc+"' WHERE id='"+cId+"'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(edit_Car);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }

    //Metoda edytująca dane o kliencie/użytkowniku
    private void editUser(PrintStream ps, String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        String uId = tokens[1];
        String uName = tokens[2];
        String uSurname = tokens[3];
        String uLogin = tokens[4];
        String uPassword = tokens[5];
        String uSaldo = tokens[6];

        //Stworzenie polaczenia  z baza
        Statement stmt = conn.connect().createStatement();
        stmt.executeUpdate("USE wypozyczalnia");

        //Zmienna przechowujaca żadanie okreslonego widoku
        String edit_User = ("UPDATE uzytkownicy SET Imie='" + uName + "' ,Nazwisko='" +uSurname + "',Login='" +uLogin + "',Haslo='" +uPassword+"',Saldo='"+uSaldo+"' WHERE id='"+uId+"'");

        //Żadanie okreslonego widoku z bazy
        int rows = stmt.executeUpdate(edit_User);

        stmt.close();
        //Zamkniecie polaczen z bazą
        conn.closeDatabase();
        //Obsluga bledow do zadanego widoku z bazy
        if(rows>0) {
            ps.println("ok");
        }else{
            ps.println("blad");
        }
    }



}



