package SharedUserAdminClasses;

import AdminGui.AdminLogin;
import Client.ClientWorker;
import UserGui.UserLogin;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

final class WindowChoice extends JFrame {
    public WindowChoice(ClientWorker clientSocket) {
        //Inicjalizacja przyciskow
        userBtn = new JButton("Użytkownik");
        adminBtn = new JButton("Administrator");
        closeBtn = new JButton();
        minimalizeBtn = new JButton();
        userBtn.setBackground(new Color(255, 117, 0));
        userBtn.setBorder(null);
        adminBtn.setBackground(new Color(255, 117, 0));
        adminBtn.setBorder(null);
        //zamknij
        closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/cancel.png")));
        closeBtn.setBorder(null);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setRequestFocusEnabled(false);
        closeBtn.setBounds(450,5,30,30);

        //minimalizuj
        minimalizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/minus.png")));
        minimalizeBtn.setBorder(null);
        minimalizeBtn.setBorderPainted(false);
        minimalizeBtn.setContentAreaFilled(false);
        minimalizeBtn.setRequestFocusEnabled(false);
        minimalizeBtn.setBounds(420,5,30,30);



        tekst = new JLabel("Wybierz z jakiego poziomu chcesz sie zalogować");
        tekst.setBounds(55,40,500,100);
        tekst.setFont(new Font("Italic",Font.BOLD ,17));
        tekst.setForeground(new Color(255,117,0));

        userBtn.setBounds(95, 200, 300, 60);
        adminBtn.setBounds(95, 300, 300, 60);
        new JFrame("Wypożyczalnia Samochodowa");
        //Bez tego przycisk jest na calym ekranie
         setLayout(null);
         setUndecorated(true);
        //Wymiary okienka
         setSize(500, 500);
        //Zapewnie widocznosci okienka
         setVisible(true);
        //Wyswietla okienko na srodku ekranu
         setLocationRelativeTo(null);
        //Tytul JFrame
         setTitle("Wypożyczalnia samochodowa");
        //Nie mozna zmieniac rozmiaru okna
         setResizable(false);
         setDefaultCloseOperation( EXIT_ON_CLOSE);
         getContentPane().setBackground(new Color(21,25,28));

         add(userBtn);
         add(closeBtn);
         add(minimalizeBtn);
         add(adminBtn);
         add(tekst);

        //Obsluga przycisku minimalizuj
        minimalizeBtn.addActionListener(ae ->  setState(Frame.ICONIFIED));

        //Obslga przycisku "cancel"
        closeBtn.addActionListener(ae ->  dispose());

        //Obsluga przycisku "Uzytkownik"
        userBtn.addActionListener(ae -> {
             new UserLogin(clientSocket);
              dispose();
        });

        //Obsluga przycisku "Administrator"
        adminBtn.addActionListener(ae -> {
            new AdminLogin(clientSocket);
             dispose();
        });

        closeBtn.addActionListener(e -> {
            try {
                if(!clientSocket.logoff(null,"def"))
                    JOptionPane.showMessageDialog(null, "Wystapil blad");


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            dispose();
            JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalni");

        });
        minimalizeBtn.addActionListener(ae ->  setState(Frame.ICONIFIED));
    }
    JButton userBtn;
    JButton adminBtn;
    JButton closeBtn;
    JButton minimalizeBtn;
    JLabel tekst;
}