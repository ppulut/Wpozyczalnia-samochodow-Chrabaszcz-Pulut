package Server;

import Server.ServerClasses.*;
import SharedServerClientClasses.TableObject;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;


public class ServerWorker extends Thread {
    //Reprezentuje port Servera
    private final Server server;
    //  Disconnect disconnect;
    ServerSocket serverSocket;
    //zmienne do komunikacji z Clientem
    private final PrintStream ps;
    private final BufferedReader buffReader;
    //Stworzenie obeiktu do poleczenia z baza danych
    private Socket clientSocket;
    //Inicjacja ResultSet
    //Obiekt na klase handleLogTables
    HandleLogTables handleLogTables;
    //Obiekt na klase insertsClientData
    InsertsClientData insertsClientData;
    //Obiekt na klase deteleClientData
    DeteleClientData deteleClientData;
    //Obiekt na klase manipulateClientData
    ManipulateClientData manipulateClientData;
    //Obiekt na klase clientDataInformation
    ClientDataInformation clientDataInformation;
    //Obiekt na klase logoffClass
    LogoffClass logoffClass;
    //setter klasy LogoffClass
    public void setLogoffClass(LogoffClass logoffClass) { this.logoffClass = logoffClass; }
    //getter Klasy GetLogoffClass
    public LogoffClass getLogoffClass() { return logoffClass; }
    //setter klasy ClientDataIfnormtion
    public void setClientDataInformation(ClientDataInformation clientDataInformation) { this.clientDataInformation = clientDataInformation; }
    //getter Klasy ClientDataInformation
    public ClientDataInformation getClientDataInformation() { return clientDataInformation; }
    //setter klasy Manipulate
    public void setManipulateClientData(ManipulateClientData manipulateClientData) { this.manipulateClientData = manipulateClientData; }
    //getter Klasy Manipulate ClientData
    public ManipulateClientData getManipulateClientData() { return manipulateClientData; }
    //setter klasy DeleteClientData
    public void setDeteleClientData(DeteleClientData deteleClientData) { this.deteleClientData = deteleClientData; }
    //getter Klasy DeleteClientData
    public DeteleClientData getDeteleClientData() {
        return deteleClientData;
    }
    //setter klasy HandleLogTables
    public void setHandleLogTables(HandleLogTables handleLogTables) {
        this.handleLogTables = handleLogTables;
    }
    //getter Klasy HandleLogTables
    public HandleLogTables getHandleLogTables() {
        return handleLogTables;
    }
    //setter klasy Insertclientdata
    public void setInsertsClientData(InsertsClientData insertsClientData) { this.insertsClientData = insertsClientData; }
    //getter Klasy IntertsClientdata
    public InsertsClientData getInsertsClientData() {
        return insertsClientData;
    }
    //Konsutrktor ustawiajacy porty servera oraz zmienne do komunikacji z Clientem

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ServerWorker(Server server, Socket clientSocket, ServerSocket serverSocket) throws IOException {
        //Incjuje zmienne sluzace do komunikacji z klientem
        this.buffReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.server = server;
        //Reprezentuje socket clienta
        this.serverSocket = serverSocket;
        this.ps = new PrintStream(clientSocket.getOutputStream());
        setClientSocket(clientSocket);
        //Incjuje obiekty na klasy
        setHandleLogTables(new HandleLogTables());
        setInsertsClientData(new InsertsClientData());
        setDeteleClientData(new DeteleClientData(ps,server));
        setManipulateClientData(new ManipulateClientData());
        setClientDataInformation(new ClientDataInformation(server));
        setLogoffClass(new LogoffClass(server,serverSocket,clientSocket,getDeteleClientData()));
    }

    @Override
    public void run() {
        //Obsluga żądań od klienta
        try {
            handleClientSocket();
        } catch (IOException | InterruptedException | SQLException | ClassNotFoundException | ParseException e) {}
    }

    //Metoda Obslugujaca policemen od klienta
    private void handleClientSocket() throws IOException, InterruptedException, SQLException, ClassNotFoundException, ParseException {
        String line;
        while ((line = buffReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("quit".equals(cmd)) {
                    //Metoda odpowiedzialna za wylogowanie klienta
                    if(getLogoffClass().handleLogoffClient(tokens))
                   break;
                    //Metoda odpowiedzielana za zamkniecie servera
                } else if ("close_server".equalsIgnoreCase(cmd)) {
                    getLogoffClass().handleCloseServer();
                    //Metoda zwracajaca liczbe posiadanych samochodow przez wypozyczlanie
                } else if ("Number_Of_Cars".equalsIgnoreCase(cmd)) {
                    sendMessage(getClientDataInformation().handleNumberCars(tokens));
                    //Metoda obslugujaca logowanie do wypozyczlanii
                } else if ("login".equalsIgnoreCase(cmd)) {
                    String[] response = StringUtils.split(getHandleLogTables().handleLogin(tokens));
                    if(response.length>4){
                        server.setUserLogin(response[4]);
                    }
                    sendMessage(getHandleLogTables().handleLogin(tokens));
                    //Metoda tworzy żądaną tabele przez klienta
                } else if ("give_table".equalsIgnoreCase(cmd)) {
                    sendMessageSerialized(getHandleLogTables().table(tokens));
                    //Metoda dodaje rezerwacje klienta
                } else if ("insert_reservation".equalsIgnoreCase(cmd)) {
                    sendMessage (getInsertsClientData().insertReservation(tokens));
                    //Metoda odpowiadajaca za rejestracje Klienta
                } else if ("insert_register".equalsIgnoreCase(cmd)) {
                    sendMessage(getInsertsClientData().handleRegister(tokens));
                    //Metoda odswieza dane uzytkownika
                } else if ("refresh_data".equalsIgnoreCase(cmd)) {
                    sendMessage(getClientDataInformation().handleRefreshData(tokens));
                    //Metoda dodaje pieniandze do konta
                } else if ("insert_saldo".equalsIgnoreCase(cmd)) {
                    sendMessage(getInsertsClientData().handleModifySaldo(tokens));
                    //Metoda odpowiadajaca za splacenie kary uzytkownika
                } else if ("pay_debt".equalsIgnoreCase(cmd)) {
                    sendMessage(getInsertsClientData().handlePayDebt(tokens));
                    //Metoda sprawdzajaca czy wpisany login jest aktualnie zalogowany
                } else if ("actual_login".equalsIgnoreCase(cmd)) {
                    sendMessage(getClientDataInformation().handleActualLogin(tokens));
                    //Metoda dodajaca dane po stronie Admina
                } else if ("insert_New_Data".equalsIgnoreCase(cmd)) {
                    sendMessage(getInsertsClientData().insertNewData(tokens));
                    //Metoda usuwajaca dane po stronie Admina
                } else if ("delete_Admin".equalsIgnoreCase(cmd)) {
                    sendMessage(getDeteleClientData().deleteAdmin(tokens));
                    //Metoda edytujaca dane po stronie Admina
                } else if ("edit_Admin".equalsIgnoreCase(cmd)) {
                    sendMessage(getManipulateClientData().editAdmin(tokens));
                    //Metoda zwraca liczbe aktualnie zalogowanych uzytkownikow
                } else if ("get_actual_login".equalsIgnoreCase(cmd)) {
                    sendMessage(getClientDataInformation().getActualogin());
                    //Metoda sprawdzajaca czy wpisany login podczas rejestracji juz istnieje w bazie
                } else if ("check_unique_login".equalsIgnoreCase(cmd)) {
                    sendMessage(getClientDataInformation().checkUniqueLogin(tokens));
                } else {
                    String msg = "Nieobslugiwane polecenie przez Server " + cmd;
                    sendMessage(msg);
                }
            }
        }
    }

    private void sendMessage(String message){
        ps.println(message);
    }

    private void sendMessageSerialized(Object object) throws IOException {
        ObjectOutput objectOutput = new ObjectOutputStream(getClientSocket().getOutputStream());
        objectOutput.writeObject(new TableObject(object));
    }

}

