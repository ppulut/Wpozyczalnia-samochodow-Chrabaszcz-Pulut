package Server;

import DataBase.CreateDatabase;
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server extends Thread {
    //Gui Servera
    static JFrame serverWindow;
    static JButton startBtn;
    //Numer portu na ktym bedzie dzialal server
    private final int serverPort;
    //Zmienna informujaca w jakim stanie jest server
    public boolean Work;
    //Metoda ustawiajaca Port servera
    public Server(int serverPort) {
        this.serverPort = serverPort;
    }
    //Metoda ustawiajaca aktualny stan pracy servera
    public void setWork(boolean Work) {
        this.Work = Work;
    }
    //Metoda pozwiajaca wyłuskac w jakim stanie pracy jest server
    public boolean getWork() {
        return Work;
    }
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wylaczono server");
            System.exit(1);
        }
    }
    public static void main(String[] arg){
        //Gui servera
        serverWindow = new JFrame("Server");
        startBtn = new JButton("Uruchom");
        serverWindow.add(startBtn);
        serverWindow.setSize(200, 200);
        serverWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        serverWindow.pack();

        serverWindow.setVisible(true);
        //Port na którym działa serwer
        int port = 2138;
        //

        //Tworzenie servera
        Server server = new Server(port);
        server.setWork(false);

            startBtn.addActionListener(ae -> {
                if(!server.getWork()) {
                    server.setWork(true);
                    try {
                        new CreateDatabase();
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Server nie moze połączyć się z bazą danych");
                    }
                    server.start();
                    serverWindow.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Serwer jest juz uruchomiony");
                }

            });
        }
    }


