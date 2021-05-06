package Server;

import DataBase.ConnectDataBase;
import Server.ServerClasses.*;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ServerWorker extends Thread {
    //Reprezentuje socket clienta
    private Socket clientSocket;
    //Reprezentuje port Servera
    private Server server;
    //  Disconnect disconnect;
    ServerSocket serverSocket;
    //zmienne do komunikacji z Clientem
    private PrintStream ps;
    private BufferedReader buffReader;
    //Stworzenie obeiktu do poleczenia z baza danych
    private static final ConnectDataBase conn = new ConnectDataBase();
    //Inicjacja ResultSet
    private static ResultSet rs = null;
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
    public ServerWorker(Server server, Socket clientSocket, ServerSocket serverSocket) throws IOException {
        //Incjuje zmienne sluzace do komunikacji z klientem
        this.buffReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.server = server;
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        this.ps = new PrintStream(clientSocket.getOutputStream());
        //Incjuje obiekty na klasy
        setHandleLogTables(new HandleLogTables(ps,buffReader,rs,server,clientSocket));
        setInsertsClientData(new InsertsClientData(ps,conn,rs));
        setDeteleClientData(new DeteleClientData(ps,conn,rs,server));
        setManipulateClientData(new ManipulateClientData(ps,conn,rs));
        setClientDataInformation(new ClientDataInformation(ps,conn,rs,server));
        setLogoffClass(new LogoffClass(server,serverSocket,clientSocket,getDeteleClientData()));
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

    //Metoda Obslugujaca policemen od klienta
    private void handleClientSocket() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
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
                    getClientDataInformation().handleNumberCars(tokens);
                    //Metoda obslugujaca logowanie do wypozyczlanii
                } else if ("login".equalsIgnoreCase(cmd)) {
                    getHandleLogTables().handleLogin(tokens);
                    //Metoda tworzy żądaną tabele przez klienta
                } else if ("give_table".equalsIgnoreCase(cmd)) {
                    getHandleLogTables().createJTable(tokens);
                    //Metoda dodaje rezerwacje klienta
                } else if ("insert_reservation".equalsIgnoreCase(cmd)) {
                    getInsertsClientData().insertReservation(tokens);
                    //Metoda odpowiadajaca za rejestracje Klienta
                } else if ("insert_register".equalsIgnoreCase(cmd)) {
                    getInsertsClientData().handleRegister(tokens);
                    //Metoda odswieza dane uzytkownika
                } else if ("refresh_data".equalsIgnoreCase(cmd)) {
                    getClientDataInformation().handleRefreshData(tokens);
                    //Metoda dodaje pieniandze do konta
                } else if ("insert_saldo".equalsIgnoreCase(cmd)) {
                    getInsertsClientData().handleModifySaldo(tokens);
                    //Metoda odpowiadajaca za splacenie kary uzytkownika
                } else if ("pay_debt".equalsIgnoreCase(cmd)) {
                    getInsertsClientData().handlePayDebt(tokens);
                    //Metoda sprawdzajaca czy wpisany login jest aktualnie zalogowany
                } else if ("actual_login".equalsIgnoreCase(cmd)) {
                    getClientDataInformation().handleActualLogin(tokens);
                    //Metoda dodajaca dane po stronie Admina
                } else if ("insert_New_Data".equalsIgnoreCase(cmd)) {
                    getInsertsClientData().insertNewData(tokens);
                    //Metoda usuwajaca dane po stronie Admina
                } else if ("delete_Admin".equalsIgnoreCase(cmd)) {
                    getDeteleClientData().deleteAdmin(tokens);
                    //Metoda edytujaca dane po stronie Admina
                } else if ("edit_Admin".equalsIgnoreCase(cmd)) {
                    getManipulateClientData().editAdmin(tokens);
                    //Metoda zwraca liczbe aktualnie zalogowanych uzytkownikow
                } else if ("get_actual_login".equalsIgnoreCase(cmd)) {
                    getClientDataInformation().getActualogin();
                    //Metoda sprawdzajaca czy wpisany login podczas rejestracji juz istnieje w bazie
                } else if ("check_unique_login".equalsIgnoreCase(cmd)) {
                    getClientDataInformation().checkUniqueLogin(tokens);
                } else {
                    String msg = "Nieobslugiwane polecenie przez Server " + cmd;
                    ps.println(msg);
                }
            }
        }
    }

}

