package Client;

import SharedServerClientClasses.TableData;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.*;
import java.net.Socket;

public class ClientWorker {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private BufferedReader bufferedIn;
    private PrintStream ps;
    private InputStream serverIn;
    public String name;
    public String surrname;
    public String id;
    JTable table;
    TableRowSorter<TableModel> sorter;
    String debt;
    String saldo;

    private void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    private void setDebt(String debt) {
        this.debt = debt;
    }

    public String getDebt() {
        return debt;
    }

    private void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    private void setSurrname(String surrname){
        this.surrname = surrname;
    }

    public String getSurrname(){
        return surrname;
    }

    private void setId(String id) {
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public ClientWorker(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            this.ps = new PrintStream(socket.getOutputStream());
            System.out.println("Port klienta: " + socket.getLocalPort());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Socket getSocket() {
        return socket;
    }

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
        System.out.println("Odpowiedz z serwera:" + response);

        String[] tokens = StringUtils.split(response);
        if ("ok".equalsIgnoreCase(tokens[0])) {
            setName(tokens[1]);
            setSurrname(tokens[2]);
            setId(tokens[3]);
            return true;
        }
            return false;
    }

    public void giveTable(String zmienna) throws IOException, ClassNotFoundException {
        String tablee = "give_table"+ " "+ zmienna;
        TableModel model;

        ps.println(tablee);

        ObjectInputStream b = new ObjectInputStream(socket.getInputStream());
        TableData received = (TableData) b.readObject();

        model = new DefaultTableModel(received.getVector1(), received.getVector2());
        JTable table = new JTable(model){

            public boolean isCellEditable(int row, int column){
            return false;
         }
        };

        table.isCellEditable(2,2);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        setTableSorter(sorter);
        setTable(table);
    }


    //Table sorter sortujący utowrzoną table wedlulg żadanych kluczy
    public void setTableSorter(TableRowSorter<TableModel> sorter){
        this.sorter = sorter;
    }
    public TableRowSorter<TableModel> getSorter(){
        return sorter;
    }

    //setter ustawiajacy żądaną table
    public void setTable(JTable table){
        this.table = table;
    }

    //getter pozwaljacy odniesć sie do konkretnej tabeli
    public JTable getTable(){
        return table;
    }

    //Metoda sluzaca do wylogowywania uzytkownika
    public void logoff(){
        ps.println("quit");
    }

    //metoda wylaczajaca server
    public void closeServer(){
        ps.println("closeServer");
    }

    //Metoda, sluzaca do wyslania do servera danych do rezerwacji
    public boolean sendDataReservation(String userId,String carId, String firstDate, String secondDate, String sFinalCost) throws IOException {

        String cmd = "insert_reservation"+" "+userId+" "+carId+" "+firstDate+" "+secondDate+" "+sFinalCost;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
                return true;
        }else{
            return false;
        }
    }

    public boolean registerPerson(String type, String userCreateName, String userSurrname, String userLogin, String userCreatePassword) throws IOException {
        String cmd = null;

   if(type.equals("user")){
        cmd = "insert_register"+" "+type+" "+userCreateName+" "+userSurrname+" "+userLogin+" "+userCreatePassword;
   }else if(type.equals("admin")){
        cmd = "insert_register"+" "+type+" "+userCreateName+" "+userSurrname+" "+userLogin+" "+userCreatePassword;
   }

   ps.println(cmd);

        bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
     String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }

    }

    public boolean addMoney(String klientId, String sPrice) throws IOException {

        String cmd = "insert_saldo"+" "+klientId + " " + sPrice;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public void refreshData(String klientId) throws IOException {

        String cmd = "refresh_data"+ " " + klientId;

        ps.println(cmd);

        String response = bufferedIn.readLine();
        String[] tokens = StringUtils.split(response);

        setDebt(tokens[0]);
        setSaldo(tokens[1]);


    }

    public Boolean payDebt(String userId,String debt) throws IOException {
        String cmd = "pay_debt" + " " + userId + " " + debt;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }

    }
}
