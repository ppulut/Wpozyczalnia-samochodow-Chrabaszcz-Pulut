package Server;

import DataBase.CreateDatabase;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 Klasa zarządzająca instancją servera. Przechowuje informacje w jakim stanie znajduje się server(Włączony/wyłączony).
 Klasa także przechowuje informacje o aktualnie zalogowanych uzytkownikach.
 Poprzez metody tej funkcji możemy się dowiedzieć czy podany login jest aktualnie zalogowany do bazy a także usunąć login z aktualnie zalogowanych.
 Klasa posiada także, metode która odpowiada za uruchomienie okienka poprzez które możemy rozpocząć prace servera.
*/

public class Server extends Thread {
    static private JFrame serverWindow;
    //Numer portu na ktym bedzie dzialal server
    private final int serverPort;
    //Zmienna informujaca w jakim stanie jest server
    private boolean Work;
    //Metoda ustawiajaca Port servera

    /*
     * Konstruktor Klasy Server
     * @param serverPort ustawia port Servera
     */
    public Server(int serverPort) {
        this.serverPort = serverPort;
    }


    //  Metoda ustawiajaca aktualny stan pracy servera

    public void setWork(boolean Work) {
        this.Work = Work;
    }

    //Metoda pozwalająca wyłuskac w jakim stanie pracy jest server

    public boolean getWork() {
        return Work;
    }
    //przechowuje zalogowanych uzytkownikow
    private final ArrayList<String> userLogin = new ArrayList<>();

    /*
     * Zwraca Liste z aktualnie zalogowanymi użytkownikami
     * @return lista z zalogowanymi loginami
     */
    public ArrayList<String> getUserLogin() {
        return userLogin;
    }

    /*
     * Dodaje login do aktualnie w bazie
     */
    public void setUserLogin(String login) {
        this.userLogin.add(login);
    }

    /*
     *
     * Usuwa login z aktulanie zalogowanych
     */
    public void removeUserLogin (String login) {
        this.userLogin.remove(login);
    }

    /*
     *
     * @return Zwraca rozmiar listy z aktualnie zalogowanymi uzytkownikami
     */
    public long getUserLoginSize(){
        return userLogin.size();
    }

    /*
     *
     * @return usuwa wszystkie loginy z listy
     */
    public void removeAllUserLogin(){
        if (!this.userLogin.isEmpty()) {
            this.userLogin.clear();
        }
    }

    /*
     Metoda, która jest odpowiedzialna za uruchomienie nowego wątku dla Clienta.
     */
    @Override
    public void run() {
        try {
            //stworzenie socketu dla kazdego wątku
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while(Work) {

                        System.out.println("Czekam na uzytkownika");
                        //Akceptowanie polaczenia od uzytkownika
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Zaakceptowano polaczenie od uzytkownika: " + clientSocket);
                        //Stworzenie obiektu odwolujacego sie do ServerWorkera
                        ServerWorker worker = new ServerWorker(this, clientSocket, serverSocket);
                        worker.start();

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Wylaczono server");
            System.exit(1);
        }
    }
    public static void main(String[] arg){
        //Gui servera
        serverWindow = new JFrame("Server");
        JButton startBtn = new JButton("Uruchom");
        serverWindow.add(startBtn);
        serverWindow.setSize(200, 200);
        serverWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        serverWindow.pack();

        serverWindow.setVisible(true);
        //Port na którym działa serwer
        int port = 2137;
        //

        //Tworzenie servera
        Server server = new Server(port);
        server.setWork(false);

            startBtn.addActionListener(ae -> {
                if(!server.getWork()) {
                    server.setWork(true);
                    try {
                        new CreateDatabase();
                    } catch (SQLException throwable) {
                        JOptionPane.showMessageDialog(null, "Stworzono baze danych");
                    }
                    server.start();
                    serverWindow.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Serwer jest juz uruchomiony");
                }

            });
        }
    }


