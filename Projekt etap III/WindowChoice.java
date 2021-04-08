package SharedGui;

import AdminGui.AdminLogin;
import Client.ClientWorker;
import UserGui.UserLogin;

import javax.swing.*;
import java.awt.*;

public class WindowChoice {
    public WindowChoice(ClientWorker clientsocket) {
        //Stworzenie przycisku
        userButton = new JButton("Użytkownik");
        adminButton = new JButton("Administrator");
        closeButton = new JButton();
        minimizeButton = new JButton();
        userButton.setBackground(new Color(255, 117, 0));
        userButton.setBorder(null);
        adminButton.setBackground(new Color(255, 117, 0));
        adminButton.setBorder(null);

        //zamknij
       // closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));

        closeButton.setBorder(null);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setRequestFocusEnabled(false);
        closeButton.setBounds(450,5,30,30);

        //minimalizuj
       // minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));

        minimizeButton.setBorder(null);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setRequestFocusEnabled(false);
        minimizeButton.setBounds(420,5,40,30);

        tekst = new JLabel("Wybierz z jakiego poziomu chcesz sie zalogować");
        tekst.setBounds(55,40,500,100);
        tekst.setFont(new Font("Italic",Font.BOLD ,17));
        tekst.setForeground(new Color(255,117,0));

        userButton.setBounds(95, 200, 300, 60);
        adminButton.setBounds(95, 300, 300, 60);
        okno = new JFrame("Wypożyczalnia Samochodowa");
        //Bez tego przycisk jest na calym ekranie
        okno.setLayout(null);
        okno.setUndecorated(true);
        //Wymiary okienka
        okno.setSize(500, 500);
        //Zapewnie widocznosci okienka
        okno.setVisible(true);
        //Wyswietla okienko na srodku ekranu
        okno.setLocationRelativeTo(null);
        //Tytul JFrame
        okno.setTitle("Wypożyczalnia samochodowa");
        //Nie mozna zmieniac rozmiaru okna
        okno.setResizable(false);
        okno.setDefaultCloseOperation(okno.EXIT_ON_CLOSE);
        okno.getContentPane().setBackground(new Color(21,25,28));

        okno.add(userButton);
        okno.add(closeButton);
        okno.add(minimizeButton);
        okno.add(adminButton);
        okno.add(tekst);

        //Obsluga przycisku minimalizuj
        minimizeButton.addActionListener(ae -> okno.setState(Frame.ICONIFIED));

        //Obslga przycisku "cancel"
        closeButton.addActionListener(ae -> okno.dispose());

        //Obsluga przycisku "Uzytkownik"
        userButton.addActionListener(ae -> {
             new UserLogin(clientsocket);
             okno.dispose();
        });

        //Obsluga przycisku "Administrator"
        adminButton.addActionListener(ae -> {
            new AdminLogin(clientsocket);
            okno.dispose();
        });
    }
    JFrame okno;
    JButton userButton;
    JButton adminButton;
    JButton closeButton;
    JButton minimizeButton;
    JLabel tekst;
}