package SharedUserAdminClasses;

import Client.ClientWorker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

final class SplashScreen extends JFrame
{
    final static private JProgressBar progressBar = new JProgressBar();
    static private int count = 1;
    static private final int timerPause = 40;
    static private final int progBar = 100;
    private Timer progressBarTimer;
    private ClientWorker clientsocket;

    private SplashScreen(){
        URL iconURL = getClass().getResource("/rsc/car.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        JLabel background = new JLabel();
        //Logowanie do servera
        clientsocket = new ClientWorker("localhost", 2137);
        if(!clientsocket.connect()){
        System.out.println("Blad z logowaniem do servera");
        dispose();
        }

        //Wymiary okeinka
        setSize(500,300);
        background.setBounds(150,10,500,100);
        setLayout(new BorderLayout());
        setUndecorated(true);
        //Zmienia kursor na Wait kursor
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Ustawienie obrazka na tlo
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/logo.png")));
        //Wyswietla okienko na srodku ekranu
        setLocationRelativeTo(null);

        //Tworzenie kontenera
        Container container = getContentPane();

        //Ustawienia progressbara
        progressBar.setMaximum(progBar);
        container.add(progressBar, BorderLayout.SOUTH);
        progressBarTimer = new Timer(timerPause, actionProgressBar);
        progressBarTimer.start();
        add(background);
        setVisible(true);
        getContentPane().setBackground(new Color(21,25,28));

    }

        //Action listener jest potrzebny do Timera i ustawiania wartosci
        ActionListener actionProgressBar = evt -> {
        //Zapewnia ze na progressbar beda wyswietlaly sie %
        progressBar.setStringPainted(true);
        //Ustawianie wyswietalnej wartosci na ProgressBar
        progressBar.setValue(count);
        //Wielkosci progress baru
        progressBar.setBounds(0, 250, 500, 50);
        //Zmiena kolor ladowania progressbar
        progressBar.setForeground(Color.gray);
             progressBar.setForeground(new Color(255,117,0));
             progressBar.setBackground(new Color(21,25,28));
             progressBar.setBorder(null);
             progressBar.setBorderPainted(false);
        //Jezeli progressbar bedzie mial wartosc 100% to okienko jest zamykane
             if (progBar == count) {
            new WindowChoice(clientsocket);
            //Zatrzymanie Timera
            progressBarTimer.stop();
            dispose();
        }
        //Zwiekszanie wartosci do progressBar
        count++;
    };

    public static void main(String[] args) throws SQLException, IOException {
      new SplashScreen();
    }
}
