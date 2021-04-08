package AdminGui;

import SharedGui.CreateJTable;
import Client.ClientWorker;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminInterface {
    ClientWorker clientSocket;

    public AdminInterface(ClientWorker clientSocket) throws IOException, ClassNotFoundException {
        this.clientSocket = clientSocket;
        CreateJTable create = new CreateJTable(clientSocket);

        String name =clientSocket.getName();
        String surrname = clientSocket.getSurrname();
        String id_klient = clientSocket.getId();

        //Card layout admin
        adminButton.setBackground(new Color(255, 117, 0));
        adminButton.setSize(60, 30);
        adminButton.setBorder(null);

        //Card layout spis samochodow
        listaSamochodówButton.setBackground(new Color(255, 117, 0));
        listaSamochodówButton.setSize(60, 30);
        listaSamochodówButton.setBorder(null);

        //Card layout uzytkownicy
        uzytkownicyButton.setBackground(new Color(255, 117, 0));
        uzytkownicyButton.setSize(60, 30);
        uzytkownicyButton.setBorder(null);

        //Card layout wypożyczenia
        wypozyczeniaButton.setBackground(new Color(255, 117, 0));
        wypozyczeniaButton.setSize(60, 30);
        wypozyczeniaButton.setBorder(null);

        //Card layout kary
        karyButton.setBackground(new Color(255,117,0));
        karyButton.setSize(60,30);
        karyButton.setBorder(null);

        //Dodaj admina
        addAdminButton.setBackground(new Color(255, 117, 0));
        addAdminButton.setSize(80, 30);
        addAdminButton.setBorder(null);

        //Usuń admina
        deleteAdminButton.setBackground(new Color(255, 117, 0));
        deleteAdminButton.setSize(80, 30);
        deleteAdminButton.setBorder(null);

        //Edytuj admina
        editAdminButton.setBackground(new Color(255, 117, 0));
        editAdminButton.setSize(80, 30);
        editAdminButton.setBorder(null);

        //Dodaj samochód
        addCarButton.setBackground(new Color(255, 117, 0));
        addCarButton.setSize(80, 30);
        addCarButton.setBorder(null);

        //Usuń samochód
        deleteCarButton.setBackground(new Color(255, 117, 0));
        deleteCarButton.setSize(80, 30);
        deleteCarButton.setBorder(null);

        //Edytuj samochód
        editCarButton.setBackground(new Color(255, 117, 0));
        editCarButton.setSize(80, 30);
        editCarButton.setBorder(null);

        //Dodaj użytkownika
        addUserButton.setBackground(new Color(255, 117, 0));
        addUserButton.setSize(80, 50);
        addUserButton.setBorder(null);

        //Usuń użytkownika
        deleteUserButton.setBackground(new Color(255, 117, 0));
        deleteUserButton.setSize(80, 30);
        deleteUserButton.setBorder(null);

        //Edytuj użytkownika
        editUserButton.setBackground(new Color(255, 117, 0));
        editUserButton.setSize(80, 30);
        editUserButton.setBorder(null);

        System.out.println(clientSocket.getSocket());
        adminButton.addActionListener(e -> {
            setMainPanel(adminPanel);
            labelHello.setText("Zalogowany jako: "+name+" "+surrname);
            tableAdm.add(create.createTable("admin"), BorderLayout.CENTER);

        });
        listaSamochodówButton.addActionListener(e -> {
            setMainPanel(autaPanel);
            tableCars.add(create.createTable("spis"), BorderLayout.CENTER);

        });

        uzytkownicyButton.addActionListener(e -> {
            setMainPanel(usersPanel);
            tablePanel.add(create.createTable("user"), BorderLayout.CENTER);
            listNames.setText("Liczba zarejestrowanych użytkowników: "+create.getTableRowCount(create.getTable()));


        });
        wypozyczeniaButton.addActionListener(e -> {
            setMainPanel(wypozyczeniaPanel);
            tableWypozycz.add(create.createTable("user"), BorderLayout.CENTER);
            rentCars.setText("Liczba wypozyczonych samochodów: "+create.getTableRowCount(create.getTable()));

        });
        karyButton.addActionListener(e -> {
            setMainPanel(karyPanel);
        });

        JFrame mainframe = new JFrame("Administracja zarząd");
        mainframe.setContentPane(panel1);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(1000, 850);
        mainframe.setUndecorated(true);
        mainframe.setLocationRelativeTo(null);
        mainframe.setResizable(false);
        mainframe.setVisible(true);

        setMainPanel(welcomePanel);


    }

    public void setMainPanel(JPanel jPanel) {
        mainPanel.removeAll();
        mainPanel.add(jPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private JPanel panel1;
    private JButton listaSamochodówButton;
    private JButton uzytkownicyButton;
    private JButton adminButton;
    private JLabel labelHello;
    private JPanel adminPanel;
    private JPanel autaPanel;
    private JPanel usersPanel;
    private JLabel labelLista;
    private JLabel listNames;
    private JPanel mainPanel;
    private JPanel tableCars;
    private JPanel tableAdm;
    private JPanel tablePanel;
    private JPanel welcomePanel;
    private JButton addCarButton;
    private JButton deleteCarButton;
    private JButton editCarButton;
    private JButton addUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    private JButton addAdminButton;
    private JButton editAdminButton;
    private JButton deleteAdminButton;
    private JButton wypozyczeniaButton;
    private JButton karyButton;
    private JPanel wypozyczeniaPanel;
    private JPanel karyPanel;
    private JPanel tableWypozycz;
    private JLabel rentCars;
    private JLabel lNumberOfUseres;

}


