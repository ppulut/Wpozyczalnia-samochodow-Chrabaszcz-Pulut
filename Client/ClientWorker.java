package Client;

import Client.ClientComponents.CheckLoginMethods;
import Client.ClientComponents.InsertsDataClient;
import Client.ClientComponents.LogoffClass;
import SharedServerClientClasses.TableData;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.*;
import java.net.Socket;

//Klasa sluzaca do ustawiania polaczenia z Serverem, jego socketu oraz komponentow oraz zawierajaca odniesienia do metod obslugujacych komendy wydane ze Strony klienta
//odbierajaca okreslone informacje od Servera
public class ClientWorker {

    //Zmienne wykorzysytwane w klasie
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private BufferedReader bufferedIn;
    private PrintStream ps;
    private InputStream serverIn;
    private String name;
    private String surrname;
    private String id;
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private String debt;
    private String saldo;
    private InsertsDataClient insertsDataClient;
    private CheckLoginMethods checkLoginMethods;
    private LogoffClass logoffClass;
    private TableModel model;

    //setter klasy InsertDataClient
    private void setInsertsDataClient(InsertsDataClient insertsDataClient) { this.insertsDataClient = insertsDataClient; }
    //getter klasy InsertDataClient
    private InsertsDataClient getInsertsDataClient() {
        return insertsDataClient;
    }
    //setter klasy LogoffClass
    private void setLogoffClass(LogoffClass logoffClass) {
        this.logoffClass = logoffClass;
    }
    //getter klasy InsertDataClient
    private LogoffClass getLogoffClass() {
        return logoffClass;
    }
    //setter Salda
    private void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    //getter Salda
    public String getSaldo() {
        return saldo;
    }
    //setter Kar
    private void setDebt(String debt) {
        this.debt = debt;
    }
    //getter Kar
    public String getDebt() {
        return debt;
    }
    //setter Imienia
    private void setName(String name){
        this.name = name;
    }
    //getter Imienia
    public String getName(){
        return name;
    }
    //setter Nazwiska
    private void setSurrname(String surrname){
        this.surrname = surrname;
    }
    //getter Nazwiska
    public String getSurrname(){
        return surrname;
    }
    //setter ID
    private void setId(String id) {
        this.id=id;
    }
    //getter ID
    public String getId() {
        return id;
    }
    //setter Klasy LoginClass
    private void setLoginClass(CheckLoginMethods checkLoginMethods) {
        this.checkLoginMethods = checkLoginMethods;
    }
    //getter Klasy LoginClass
    private CheckLoginMethods getLoginClass() {
        return checkLoginMethods;
    }
    //Table sorter sortujący utowrzoną table wedlulg żadanych kluczy
    private void setTableSorter(TableRowSorter<TableModel> sorter){
        this.sorter = sorter;
    }
    //getter Sortera
    public TableRowSorter<TableModel> getSorter(){
        return sorter;
    }
    //setter ustawiajacy żądaną table
    private void setTable(JTable table){
        this.table = table;
    }
    //getter pozwaljacy odniesć sie do konkretnej tabeli
    public JTable getTable(){
        return table;
    }

    public void setModel(TableModel model) {
        this.model = model;
    }

    public TableModel getModel() {
        return model;
    }



    //Konstruktor klasy ClientWorker, ktory ustawia portServera oraz jego nazwe a takze, poszczegolne odniesienia do klas
    public ClientWorker(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        setLoginClass(new CheckLoginMethods());
        setLogoffClass(new LogoffClass());
        setInsertsDataClient(new InsertsDataClient());
    }

    //Metoda ustawiajaca komonenty oraz socekt sluzacae do komunikacji z serverem
    public boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            this.ps = new PrintStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Metoda sluzaca do odswiezania informacji o uzytkowniku
    public void refreshData(String klientId) throws IOException {

        String cmd = "refresh_data"+ " " + klientId;
        ps.println(cmd);
        String response = bufferedIn.readLine();
        String[] tokens = StringUtils.split(response);
        setDebt(tokens[0]);
        setSaldo(tokens[1]);
    }

    //Metoda obslugujaca logowanie sie uzytkownika do wypozyczalnii oraz ustawiajaca informacje gdy logowanie bedzie poprawne
    public boolean login(String type,String login, String password) throws IOException {

        String cmd = null;
        if(type.equals("user")) {
            cmd = "login"+" "+login + " " + password +" "+ type;
        }else if(type.equals("admin")){
            cmd = "login"+" "+ login + " " + password + " " + type;
        }else{
            System.err.println("Bledne logowanie");
        }

        ps.println(cmd);

        bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
        String response = bufferedIn.readLine();

        String[] tokens = StringUtils.split(response);
        if ("ok".equalsIgnoreCase(tokens[0])) {
            setName(tokens[1]);
            setSurrname(tokens[2]);
            setId(tokens[3]);
            return true;
        }
        return false;
    }

    //Metoda ustawiajaca zażądaną tabele przez Klienta oraz ustawiajaca do niej sortyer
    public void giveTable(String zmienna) throws IOException, ClassNotFoundException {
        String Jtable = "give_table"+ " "+ zmienna;

        ps.println(Jtable);

        ObjectInputStream b = new ObjectInputStream(socket.getInputStream());
        TableData received = (TableData) b.readObject();

        model = new DefaultTableModel(received.getVector1(), received.getVector2());
        setModel(model);
        JTable table = new JTable(model){

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        table.isCellEditable(2,2);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        setTableSorter(sorter);
        setTable(table);
    }

    //Metoda zwracajaca liczbe samochodow w wypozyczalnii
    public String getNumberOfCars(String type) throws IOException {
      return getInsertsDataClient().getNumberOfCars(ps,bufferedIn,type);
    }

    //Metoda zwracaja socket Klienta
    public Socket getSocket() {
        return socket;
    }

    //Metoda sprawdzajaca czy podany podaczas rejestacji login jest unikalny
    public boolean checkUniqueLogin(String type,String login) throws IOException {
        return getLoginClass().checkUniqueLoginMethod(type, login, ps, serverIn);
    }
    //Metoda sluzaca do wylogowywania uzytkownika
    public boolean logoff(String login,String def) throws IOException {
        return getLogoffClass().logoutUser(login, def, ps, bufferedIn);
    }
    //metoda wylaczajaca server
    public void closeServer(){
        getLogoffClass().closeServer(ps);
    }

    //Metoda, sluzaca do wyslania do servera danych do rezerwacji
    public boolean sendDataReservation(String userId,String carId, String firstDate, String secondDate, String sFinalCost) throws IOException {
        return getInsertsDataClient().sendDataReservation(ps, bufferedIn, userId, carId, firstDate, secondDate, sFinalCost);
    }
    //Metoda sluzaca do rejestracji uzytkownika
    public boolean registerPerson(String type, String userCreateName, String userSurrname, String userLogin, String userCreatePassword) throws IOException {
        return getInsertsDataClient().registerPerson(ps, serverIn, type, userCreateName, userSurrname, userLogin, userCreatePassword);
    }
    //Metoda dodajcae pieniadze do konta
    public boolean addMoney(String klientId, String sPrice) throws IOException {
        return getInsertsDataClient().addMoney(ps, bufferedIn, klientId, sPrice);
    }
    //Metoda obslugujaca splacanie kar
    public Boolean payDebt(String userId,String debt) throws IOException {
        return getInsertsDataClient().payDebt(ps, bufferedIn, userId, debt);
    }
    //Metoda sprawdzajaca czy wpisany login podczas logowania jest juz zalogowany
    public boolean checkActualLogin(String login) throws IOException {
        return getLoginClass().checkActualLogin(ps, serverIn, login);
    }
    //Metoda, sluzaca do wyslania do servera danych do usunieciu admina
    public boolean deleteAdminSide(String type,String aId) throws IOException {
        return getInsertsDataClient().deleteAdminSide(ps, bufferedIn, type, aId);
    }
    //Metoda sluzaca do danych do tabel po stronie adminna
    public boolean addAdminSide(String type,String value1, String value2, String value3, String value4) throws IOException {
        return getInsertsDataClient().addAdminSide(ps, bufferedIn, type, value1, value2, value3, value4);
    }
    //Metoda, sluzaca do wyslania do servera danych do edycji administratora
    public boolean editAdminSide(String type, String aId, String aName, String aSurname, String aLogin, String aPassword) throws IOException {
       return getInsertsDataClient().sendDataEditAdmin(ps,bufferedIn,type,aId,aName,aSurname,aLogin,aPassword);
    }
    //Metoda zwracajaca liczbe aktualnie zalogowanych uzytkownikow
    public String getNumberOfLogin() throws IOException {
       return getLoginClass().getNumberOfLogin(ps,bufferedIn);
    }
}