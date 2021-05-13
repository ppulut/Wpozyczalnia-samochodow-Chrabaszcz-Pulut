package UserGui;

import SharedServerClientClasses.ValidateDate;
import SharedUserAdminClasses.CreateJTable;
import Client.ClientWorker;
import SharedUserAdminClasses.DateInformation;
import UserGui.calculateFunctions.CalculateMethods;
import UserGui.validateuser.ValidateUserInterfaceClass;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;


public class UserInterface extends JFrame{

    private String sIlosc;
    private String sPrice;
    private String firstDate;
    private String secondDate;
    private String sMark;
    private String sModel;
    private String sFinalCost;
    private String availabilityCar;
    private String carId;
    private int matchedSearchCar;
    private String oldDate;
    ClientWorker clientSocket;

    private void getValueFromTf(){
        sPrice = pricePerDay.getText();
        sIlosc = numberOfDay.getText();
        firstDate = dateFirst.getText();
        sMark = carName.getText();
        sModel = carModel.getText();
        oldDate = dateFirst.getText();

    }

    public UserInterface(ClientWorker clientSocket, String sUserName) throws IOException, ClassNotFoundException {

        this.clientSocket = clientSocket;
        URL iconURL = getClass().getResource("/rsc/car.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        clientSocket.refreshData(clientSocket.getId());
        CreateJTable create = new CreateJTable(clientSocket);
        CalculateMethods calc = new CalculateMethods();

        ValidateUserInterfaceClass validateDataUser = new ValidateUserInterfaceClass(clientSocket);
        DateInformation dateInformation = new DateInformation();
        ValidateDate validateDate = new ValidateDate();

        String name =clientSocket.getName();
        String surrname = clientSocket.getSurrname();
        String id_klient = clientSocket.getId();
        String saldo = clientSocket.getSaldo();

        String debt = clientSocket.getDebt();

        debtUserBtn.setBorder(null);
        debtUserBtn.setBorderPainted(false);
        debtUserBtn.setContentAreaFilled(false);
        debtUserBtn.setRequestFocusEnabled(false);


        if(debt.equals("0")){
            debtUserBtn.setText("Nie posiadasz żadnych kar");
            debtUserBtn.setForeground(new Color(9,139,6));

        }else {
            debtUserBtn.setText("Twoja kara wynosi: " + debt + " zł");
            debtUserBtn.setForeground(new Color(245,1,9));

            debtUserBtn.addActionListener(e -> {
                if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz spłacić Karę?", "Ostrzeżenie",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    if(!validateDataUser.validateMoney(saldo, debt)) {
                        JOptionPane.showMessageDialog(null, "Nie masz wystarczająco pieniędzy, zeby spłacic kare");
                    }else{
                        try {
                            if(clientSocket.payDebt(id_klient,debt)){
                                JOptionPane.showMessageDialog(null, "Pomyślnie spłacono kary");
                                dispose();
                                try {
                                    new UserInterface(clientSocket,sUserName);
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Niestety nie udało się spłacić kar");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    }
            });
        }
        //Przycisk zamykajacy
        closeBtn.setBorder(null);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setRequestFocusEnabled(false);

        //Przycisk minimalizujacy okno
        minimalizeBtn.setBorder(null);
        minimalizeBtn.setBorderPainted(false);
        minimalizeBtn.setContentAreaFilled(false);
        minimalizeBtn.setRequestFocusEnabled(false);

        //Przycisk  sluzacy do wylogowania uzytkownika
        logoutBTN.setBackground(new Color(255, 117, 0));
        logoutBTN.setBorder(null);

        //Przycisk dodajacy saldo
        saldoBtn.setBorder(null);
        saldoBtn.setBorderPainted(false);
        saldoBtn.setContentAreaFilled(false);
        saldoBtn.setRequestFocusEnabled(false);

        saldoBtn.setText("Saldo: "+saldo+ " zł");



        saldoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saldoBtn.setText(" Dodaj Saldo                                  ");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                saldoBtn.setText("Saldo konta wynosi: "+saldo+ " zł");
            }
        });

        //Przycisk do wyszukiwania w tabelii
        searchBtn.setBackground(new Color(255, 117, 0));
        searchBtn.setBorder(null);

        //Przycisk odswiezajacy okno
        refreshBtn.setBackground(new Color(255, 117, 0));
        refreshBtn.setBorder(null);

        actualDateBtn.setBackground(new Color(255, 117, 0));
        actualDateBtn.setBorder(null);

        showRents.setBackground(new Color(255, 117, 0));
        showRents.setBorder(null);

        zarezerwujButton.setBackground(new Color(255, 117, 0));
        zarezerwujButton.setBorder(null);

        logginAs.setText(" " + name + " "+ surrname);

        obliczButton.setBackground(new Color(255, 117, 0));
        obliczButton.setBorder(null);

        resetujButton.setBackground(new Color(255, 117, 0));
        resetujButton.setBorder(null);

        carModel.setBackground(new Color(31, 36, 42));
        carModel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        carModel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        carModel.setForeground(new Color(255, 117, 0));

        finalCostTF.setBackground(new Color(31, 36, 42));
        finalCostTF.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        finalCostTF.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        finalCostTF.setForeground(new Color(255, 117, 0));

        searchTf.setBackground(new Color(31, 36, 42));
        searchTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        searchTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        searchTf.setForeground(new Color(255, 117, 0));

        carName.setBackground(new Color(31, 36, 42));
        carName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        carName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        carName.setForeground(new Color(255, 117, 0));

        numberOfDay.setBackground(new Color(31, 36, 42));
        numberOfDay.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        numberOfDay.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        numberOfDay.setForeground(new Color(255, 117, 0));

        pricePerDay.setBackground(new Color(31, 36, 42));
        pricePerDay.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        pricePerDay.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        pricePerDay.setForeground(new Color(255, 117, 0));

        dateFirst.setBackground(new Color(31, 36, 42));
        dateFirst.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        dateFirst.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        dateFirst.setForeground(new Color(255, 117, 0));
        dateFirst.setText(dateInformation.getPatternDate().format(dateInformation.getActuallDate()));

        dateSecond.setBackground(new Color(31, 36, 42));
        dateSecond.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        dateSecond.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        dateSecond.setForeground(new Color(255, 117, 0));

        tablePanel.setBackground(Color.black);
        mainPanel.setBackground(Color.black);

        //nazwy tabel

        tablePanel.add(create.createTable("spis"), BorderLayout.CENTER);

        //Liczba wszystkich samochodow w wypozyczalnii
        int allRowCount = create.getTableRowCount(create.getTable());

        carsLB.setText(String.valueOf(allRowCount));
        dstCarLb.setText(clientSocket.getNumberOfCars("dst"));
        ndstCarLb.setText(clientSocket.getNumberOfCars("ndst"));

        ndstCarLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                try {
                    ndstCarLb.setText("Niedostępne samochody " + clientSocket.getNumberOfCars("ndst"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    ndstCarLb.setText(clientSocket.getNumberOfCars("ndst"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        dstCarLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                try {
                    dstCarLb.setText("Dostępne samochody " + clientSocket.getNumberOfCars("dst"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    dstCarLb.setText(clientSocket.getNumberOfCars("dst"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        carsLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carsLB.setText("Wszystkie Samochody " + allRowCount);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carsLB.setText(String.valueOf(allRowCount));

            }
        });

        create.getTable().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                create.getTable().setSelectionBackground(new Color(21,25,28));
                create.getTable().setSelectionForeground(new Color(255,117,0));
                carName.setText((String) create.getTable().getValueAt(create.getTable().getSelectedRow(), 1));
                carModel.setText((String) create.getTable().getValueAt(create.getTable().getSelectedRow(), 2));
                pricePerDay.setText((String) create.getTable().getValueAt(create.getTable().getSelectedRow(), 3));
                availabilityCar = (String) create.getTable().getValueAt(create.getTable().getSelectedRow(), 4);
                carId = (String) create.getTable().getValueAt(create.getTable().getSelectedRow(), 0);
            }
        });

        new JFrame("userInterface");
        setLocationRelativeTo(null);
        setSize(1000,750);
        setContentPane(mainPanel);

        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        obliczButton.addActionListener(ae -> {
            getValueFromTf();
            //Dostepnosc samocohdu
                if (sPrice.equals("") || sIlosc.equals("") || firstDate.equals("") || firstDate.length() != 10 || sMark.equals("") || sModel.equals("")) {
                    JOptionPane.showMessageDialog(null, "Uzupełnij wszystkie wymagane pola!");
                } else {
                    if (validateDataUser.validNumberDay(sIlosc)) {
                        try {
                            if (validateDate.validateDate("user",firstDate)) {
                                calc.calculateDataMethod(sPrice,sIlosc,oldDate);
                                dateSecond.setText(calc.getNewDate());
                                finalCostTF.setText(String.valueOf(calc.getFinalPrice()));
                            } else {
                                JOptionPane.showMessageDialog(null, "Pole Data jest niepoprawne");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Niepoprawna wartość w Polu Ilość Dni");
                    }
                }


        });

        resetujButton.addActionListener(ae -> {
            carName.setText(null);
            carModel.setText(null);
            numberOfDay .setText(null);
            pricePerDay .setText(null);
            dateFirst .setText(null);
            dateSecond .setText(null);
            finalCostTF .setText(null);

        });

        logoutBTN.addActionListener(ae -> {

            try {
                if(!clientSocket.logoff(sUserName,"undef"))
                    JOptionPane.showMessageDialog(null, "Wystapil blad");

            } catch (IOException e) {
                e.printStackTrace();
            }
            dispose();
            new UserLogin(clientSocket);


        });

        //Zamyka aplikacje
        closeBtn.addActionListener(e -> {
            try {
                if(!clientSocket.logoff(sUserName,"def"))
                    JOptionPane.showMessageDialog(null, "Wystapil blad");

                dispose();
                JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalnii");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        actualDateBtn.addActionListener(ae -> dateFirst .setText(dateInformation.getPatternDate().format(dateInformation.getActuallDate())));

        refreshBtn.addActionListener(ae -> {
            try {
                dispose();
                new UserInterface(clientSocket,sUserName);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        zarezerwujButton.addActionListener(ae -> {
            getValueFromTf();
                if (sPrice.equals("") || sIlosc.equals("") || firstDate.equals("") || firstDate.length() != 10 || sMark.equals("") || sModel.equals("")) {
                    JOptionPane.showMessageDialog(null, "Uzupełnij wszystkie wymagane pola!");
                } else {
                    if (this.availabilityCar.equals("N")) {
                        JOptionPane.showMessageDialog(null, "Samochod nie jest aktualnie dostepny!");
                    } else {
                        if (validateDataUser.validNumberDay(sIlosc)) {
                            try {
                                if (validateDate.validateDate("user",firstDate)) {
                                    calc.calculateDataMethod(sPrice,sIlosc,oldDate);
                                    dateSecond.setText(calc.getNewDate());
                                    finalCostTF.setText(String.valueOf(calc.getFinalPrice()));

                                    sFinalCost = finalCostTF.getText();
                                    secondDate = dateSecond.getText();

                                    if (!validateDataUser.validateMoney(saldo, sFinalCost)) {
                                        JOptionPane.showMessageDialog(null, "Masz za mało pieniedzy żeby wypożyczyć ten samochód");
                                    } else {
                                        new ReservationUser(clientSocket, name, surrname, sIlosc, sMark, sModel, firstDate, secondDate, sFinalCost, id_klient, carId,sUserName);
                                        dispose();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Pole Data jest niepoprawne");
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Niepoprawna wartość w Polu Ilość Dni");
                        }
                    }
                }
        });


        searchBtn.addActionListener(e -> {
            String text = searchTf.getText();
            if(text.length() == 0) {
                clientSocket.getSorter().setRowFilter(null);
                matchedSortLb.setText("");
            } else {
                try {
                    clientSocket.getSorter().setRowFilter(RowFilter.regexFilter(text));
                    matchedSearchCar = create.getTableRowCount(create.getTable());
                    //Jezeli Po sortowaniu zostaje w tabelii tyle wierszy co bez niego to nie wyswietlaj nic
                    if(matchedSearchCar==allRowCount){
                        matchedSortLb.setText("");
                    }else {
                        matchedSortLb.setText("Znalezionych wyników: " + matchedSearchCar);
                    }
                    } catch(PatternSyntaxException pse) {
                    pse.printStackTrace();
                }
            }
        });

        saldoBtn.addActionListener(e -> {
            new AddSaldo(clientSocket,name,surrname,id_klient,saldo,sUserName);
            dispose();
        });

        showRents.addActionListener(e -> {
            new RentCarsUser(clientSocket,name,surrname,sUserName);
            dispose();
        });


        //minimalizuje aplikacje
        minimalizeBtn.addActionListener(ae -> setState(Frame.ICONIFIED));
    }

    private JLabel logginAs;
    private JLabel carsLB;
    private JButton zarezerwujButton;
    private JTextField carModel;
    private JTextField carName;
    private JTextField numberOfDay;
    private JTextField pricePerDay;
    private JLabel wyp;
    private JTextField dateFirst;
    private JTextField dateSecond;
    private JButton obliczButton;
    private JButton resetujButton;
    private JTextField finalCostTF;
    private JLabel finalCost;
    private JButton actualDateBtn;
    private JButton refreshBtn;
    private JTextField searchTf;
    private JButton searchBtn;
    private JPanel searchPanel;
    private JPanel mainPanel;
    private JPanel leftPanell;
    private JPanel topPanel;
    private JPanel tablePanel;
    private JButton logoutBTN;
    private JButton punishBtn;
    private JButton saldoBtn;
    private JButton debtUserBtn;
    private JButton showRents;
    private JButton closeBtn;
    private JButton minimalizeBtn;
    private JPanel orgPane;
    private JPanel topTablePane;
    private JLabel matchedSortLb;
    private JLabel ndstCarLb;
    private JLabel dstCarLb;

}