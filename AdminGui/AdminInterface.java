package AdminGui;

import SharedGui.CreateJTable;
import Client.ClientWorker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminInterface extends JFrame{
    ClientWorker clientSocket;

    public AdminInterface(ClientWorker clientSocket) throws IOException, ClassNotFoundException {
        this.clientSocket = clientSocket;
        CreateJTable create = new CreateJTable(clientSocket);

        String name =clientSocket.getName();
        String surrname = clientSocket.getSurrname();
        String id_klient = clientSocket.getId();

        //Card layout admin
        adminButton.setBackground(new Color(255, 117, 0));
        adminButton.setSize(60, 25);
        adminButton.setBorder(null);

        //Card layout spis samochodow
        listaSamochodówButton.setBackground(new Color(255, 117, 0));
        listaSamochodówButton.setSize(60, 25);
        listaSamochodówButton.setBorder(null);

        //Card layout uzytkownicy
        uzytkownicyButton.setBackground(new Color(255, 117, 0));
        uzytkownicyButton.setSize(60, 25);
        uzytkownicyButton.setBorder(null);

        //Card layout wypożyczenia
        wypozyczeniaButton.setBackground(new Color(255, 117, 0));
        wypozyczeniaButton.setSize(60, 25);
        wypozyczeniaButton.setBorder(null);

        //Card layout kary
        karyButton.setBackground(new Color(255,117,0));
        karyButton.setSize(60,25);
        karyButton.setBorder(null);

        //Dodaj admina
        addAdminButton.setBackground(new Color(255, 117, 0));
        addAdminButton.setSize(60, 30);
        addAdminButton.setBorder(null);

        //Usuń admina
        deleteAdminButton.setBackground(new Color(255, 117, 0));
        deleteAdminButton.setSize(60, 30);
        deleteAdminButton.setBorder(null);

        //Edytuj admina
        editAdminButton.setBackground(new Color(255, 117, 0));
        editAdminButton.setSize(60, 30);
        editAdminButton.setBorder(null);

        //Dodaj samochód
        addCarButton.setBackground(new Color(255, 117, 0));
        addCarButton.setSize(60, 30);
        addCarButton.setBorder(null);

        //Usuń samochód
        deleteCarButton.setBackground(new Color(255, 117, 0));
        deleteCarButton.setSize(60, 30);
        deleteCarButton.setBorder(null);

        //Edytuj samochód
        editCarButton.setBackground(new Color(255, 117, 0));
        editCarButton.setSize(60, 30);
        editCarButton.setBorder(null);

        //Dodaj użytkownika
        addUserButton.setBackground(new Color(255, 117, 0));
        addUserButton.setSize(60, 50);
        addUserButton.setBorder(null);

        //Usuń użytkownika
        deleteUserButton.setBackground(new Color(255, 117, 0));
        deleteUserButton.setSize(60, 30);
        deleteUserButton.setBorder(null);

        //Edytuj użytkownika
        editUserButton.setBackground(new Color(255, 117, 0));
        editUserButton.setSize(60, 30);
        editUserButton.setBorder(null);

        //Usuń wypozyczenie
        deleteRentButton.setBackground(new Color(255, 117, 0));
        deleteRentButton.setSize(60, 30);
        deleteRentButton.setBorder(null);

        //dodaj karę
        addFeeButton.setBackground(new Color(255, 117, 0));
        addFeeButton.setSize(60, 30);
        addFeeButton.setBorder(null);

        //usuń karę
        deleteFeeButton.setBackground(new Color(255, 117, 0));
        deleteFeeButton.setSize(60, 30);
        deleteFeeButton.setBorder(null);

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
            tableWypozycz.add(create.createTable("rentsAdmin"), BorderLayout.CENTER);
            rentCars.setText("Liczba wypozyczonych samochodów: "+create.getTableRowCount(create.getTable()));

        });
        karyButton.addActionListener(e -> {
            setMainPanel(karyPanel);
            tableKary.add(create.createTable("kary"), BorderLayout.CENTER);
            feeForUsers.setText("Lista kar dla użytkowników: "+create.getTableRowCount(create.getTable()));
        });


        addAdminButton.addActionListener(ae -> {
            new AddAdmin(clientSocket, aName, aSurname, aLogin, aPassword);
            dispose();

        });

        addCarButton.addActionListener(ae -> {

            new AddNewCar(clientSocket, cMarka, cModel,cCena, dostepnosc);
            dispose();

        });


        addUserButton.addActionListener(ae -> {

            new AddNewUser(clientSocket, uName, uSurname, uLogin, uPassword, uSaldo);
            dispose();

        });

        deleteAdminButton.addActionListener(ae -> {

            new DeleteAdmin(clientSocket, aName, aSurname, aLogin, aPassword);
            dispose();

        });

        deleteCarButton.addActionListener(ae -> {

            new DeleteCar(clientSocket, cMarka, cModel,cCena);
            dispose();

        });

        deleteUserButton.addActionListener(ae -> {
                new DeleteUser(clientSocket);
                dispose();

        });

        deleteRentButton.addActionListener(ae -> {

            new DeleteRent(clientSocket, wID);
            dispose();

        });

        addFeeButton.addActionListener(ae -> {

            new AddFee(clientSocket, rUserId, rKwota, rData);
            dispose();

        });

        deleteFeeButton.addActionListener(ae -> {

            new DeleteFee(clientSocket, rUserId, rKwota, rData);
            dispose();

        });

        editAdminButton.addActionListener(ae -> {

            new EditAdmin(clientSocket, aId, aName, aSurname, aLogin, aPassword);
            dispose();

        });

        editCarButton.addActionListener(ae -> {

            new EditCar(clientSocket, cId, cMarka, cModel, cCena, dostepnosc);
            dispose();

        });

        editUserButton.addActionListener(ae -> {

            new EditUser(clientSocket, uId, uName, uSurname, uLogin, uPassword, uSaldo);
            dispose();

        });



        new JFrame("Administracja zarząd");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

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
    private JButton deleteRentButton;
    private JPanel tableKary;
    private JButton addFeeButton;
    private JLabel feeForUsers;
    private JButton deleteFeeButton;
    private JButton closeBtn;
    private JButton minimalizeBtn;
    private JButton logoutBTN;
    private String aName;
    private String aSurname;
    private String aLogin;
    private String aPassword;
    private String cMarka;
    private String cModel;
    private String cCena;
    private String cDataWyp;
    private String cDataZwr;
    private String dostepnosc;
    private String uName;
    private String uSurname;
    private String uLogin;
    private String uPassword;
    private String uSaldo;
    private String wID;
    private String rUserId;
    private String rKwota;
    private String rData;
    private String aId;
    private String rId;
    private String uId;
    private String cId;

}


