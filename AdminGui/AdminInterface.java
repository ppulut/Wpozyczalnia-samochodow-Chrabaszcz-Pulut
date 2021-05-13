package AdminGui;

import Client.ClientWorker;
import SharedServerClientClasses.ValidateAddOtherData;
import SharedServerClientClasses.ValidateDate;
import SharedUserAdminClasses.CreateJTable;
import SharedUserAdminClasses.DateInformation;
import SharedServerClientClasses.ValidateRegisterData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public class AdminInterface extends JFrame{

    private JPanel mainPanel;
    private JButton AdminBtn;
    private JButton userBtn;
    private JButton rentBtn;
    private JButton debtBtn;
    private JButton closeServerBtn;
    private JPanel adminPanel;
    private JPanel userPanel;
    private JPanel glowny;
    private JPanel tableforAdmin;
    private JPanel tableforUser;
    private JTextField userId;
    private JButton deleteUserBTN;
    private JTextField idAdminTf;
    private JTextField userNameTf;
    private JTextField userSurrnameTf;
    private JTextField userLoginTf;
    private JTextField userPasswordTf;
    private JButton addUserBtn;
    private JButton deleteAdminBtn;
    private JButton LogoffBtn;
    private JButton exitBtn;
    private JButton minimalizeBtn;
    private JTextField adminSurrnameTf;
    private JTextField adminLoginTf;
    private JTextField adminNameTf;
    private JButton editAdmin;
    private JButton addAdminBtn;
    private JButton editUser;
    private JPanel rentPanel;
    private JButton carsBtn;
    private JPanel tableForRent;
    private JButton deleteRent;
    private JTextField rentIdTf;
    private JPanel carsPanel;
    private JPanel tableForCars;
    private JButton addCarBtn;
    private JTextField carAvabilityTf;
    private JTextField carPriceTf;
    private JTextField carModelTf;
    private JTextField carMarkaTf;
    private JTextField CarIdTF;
    private JButton deleteCarBtn;
    private JButton editCarBtn;
    private JPanel debtPanel;
    private JPanel tableForDebt;
    private JButton deleteDebtBtn;
    private JTextField debtIdTf;
    private JButton refreshBtn;
    private JPasswordField adminPasswordTf;
    private JPanel welcomePanel;
    private JButton editDebtBtn;
    private JButton addDebtBtn;
    private JTextField debtPriceTf;
    private JTextField debtDateTf;
    private JTextField debtUserIdTf;
    private JLabel logginLb;
    private JLabel adminsCountLb;
    private JLabel usersCountLb;
    private JLabel rentsCarLb;
    private JLabel carsLb;
    private JLabel debtCountLb;
    private JLabel actualLoginClients;
    private JButton dearchAdmBtn;
    private JTextField searchAdmTf;
    private JButton searchUserBtn;
    private JTextField searchUserTf;
    private JTextField searchRentTf;
    private JButton searchRentBtn;
    private JTextField searchCarTf;
    private JButton searchCarBtn;
    private JButton searchDebtBtn;
    private JTextField searchDebtTf;
    JScrollPane panelAdminTable;
    JScrollPane panelUserTable;
    JScrollPane panelRentsTable;
    JScrollPane panelCarsTable;
    JScrollPane panelDebtTable;

    public AdminInterface(ClientWorker clientSocket, String sAdminLogin) throws IOException {

        URL iconURL = getClass().getResource("/rsc/car.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        new JFrame();
        setContentPane(mainPanel);
        getContentPane().setBackground(new Color(21,25,28));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        CardLayout cl = new CardLayout();

        panelAdminTable = new JScrollPane();
        panelUserTable = new JScrollPane();
        panelRentsTable = new JScrollPane();
        panelCarsTable = new JScrollPane();
        panelDebtTable = new JScrollPane();

        glowny.setLayout(cl);
        glowny.add(adminPanel, "adminPanel");
        glowny.add(userPanel, "userPanel");
        glowny.add(rentPanel, "rentPanel");
        glowny.add(carsPanel, "carsPanel");
        glowny.add(debtPanel, "debtPanel");
        glowny.add(welcomePanel,"welcomePanel");

        cl.show(glowny, "welcomePanel");



        CreateJTable create = new CreateJTable(clientSocket);
        ValidateRegisterData validate = new ValidateRegisterData();
        DateInformation dateInformation = new DateInformation();
        ValidateAddOtherData validateAddOtherData = new ValidateAddOtherData();
        ValidateDate validateDate = new ValidateDate();

        String name =clientSocket.getName();
        String surrname = clientSocket.getSurrname();

        logginLb.setText("Zalogowany jako "+ name +" "+surrname);
        actualLoginClients.setText("Aktualnie zalogowanych klientow jest: "+clientSocket.getNumberOfLogin());
        actualLoginClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                try {
                    actualLoginClients.setText("Aktualnie zalogowanych klientow jest: "+clientSocket.getNumberOfLogin());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    actualLoginClients.setText("Aktualnie zalogowanych klientow jest: "+clientSocket.getNumberOfLogin());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        AdminBtn.addActionListener(ae -> {
            cl.show(glowny, "adminPanel");
            tableforAdmin.remove(panelAdminTable);
            panelAdminTable = create.createTable("admin");
            tableforAdmin.add(panelAdminTable, BorderLayout.CENTER);
            adminsCountLb.setText("Liczba stworzony kont Administratorow: "+create.getTableRowCount(create.getTable()));
        });

        userBtn.addActionListener(ae -> {
            cl.show(glowny, "userPanel");
            tableforUser.remove(panelUserTable);
            panelUserTable = create.createTable("user");
            tableforUser.add(panelUserTable, BorderLayout.CENTER);
            usersCountLb.setText("Liczba stworzony kont Uzytkownika: "+create.getTableRowCount(create.getTable()));
            for(int i = clientSocket.getModel().getRowCount()- 1; i >= 0; --i)
            {
                if(clientSocket.getModel().getValueAt(i, 0).equals("24")) {
                    System.out.println(clientSocket.getModel().getValueAt(i,3));
                }
            }

        });

        rentBtn.addActionListener(ae -> {
            cl.show(glowny, "rentPanel");
            tableForRent.remove(panelRentsTable);
            panelRentsTable = create.createTable("rentsAdmin");
            tableForRent.add(panelRentsTable, BorderLayout.CENTER);
            rentsCarLb.setText("Liczba aktualnie wypozyczyonych samochodow: "+create.getTableRowCount(create.getTable()));

        });

        carsBtn.addActionListener(ae -> {
            cl.show(glowny, "carsPanel");
            tableForCars.remove(panelCarsTable);
            panelCarsTable = create.createTable("spis");
            tableForCars.add(panelCarsTable, BorderLayout.CENTER);
            carsLb.setText("Liczba Samochod w wypozyczalni: "+create.getTableRowCount(create.getTable()));

        });


        debtBtn.addActionListener(ae -> {
            cl.show(glowny, "debtPanel");
            tableForDebt.remove(panelDebtTable);
            panelDebtTable = create.createTable("kary");
            tableForDebt.add(panelDebtTable, BorderLayout.CENTER);
            debtCountLb.setText("Ogolna liczba kar uzytkownikow: "+create.getTableRowCount(create.getTable()));
            debtDateTf.setText(dateInformation.getPatternDate().format(dateInformation.getActuallDate()));
        });

        deleteUserBTN.addActionListener(ae -> {
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz Usunac Użytkownika?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String id = userId.getText();
                String checkLogin = null;

                if(id.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Uzupelnij pole id!");
                }else {

                    for (int i = clientSocket.getModel().getRowCount() - 1; i >= 0; --i) {
                        if (clientSocket.getModel().getValueAt(i, 0).equals(id)) {
                            checkLogin = (String) clientSocket.getModel().getValueAt(i, 3);
                        }
                    }

                    try {
                        if (clientSocket.checkActualLogin(checkLogin)) {
                            JOptionPane.showMessageDialog(null, "Uzytkownik zalogowany!");
                        } else {
                            if (clientSocket.deleteAdminSide("user", id)) {
                                JOptionPane.showMessageDialog(null, " Pomyslnie usunieto uzytkownika");
                                tableforUser.remove(panelUserTable);
                                tableforUser.repaint();
                                tableforUser.revalidate();
                                panelUserTable = create.createTable("user");
                                tableforUser.add(panelUserTable, BorderLayout.CENTER);
                                adminsCountLb.setText("Liczba stworzony kont Uzytkownika: " + create.getTableRowCount(create.getTable()));


                            } else {
                                JOptionPane.showMessageDialog(null, "Niestety usunięcie użytkownika się nie powiodło");
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                }
        });



        addUserBtn.addActionListener(ae -> {
            String userName = userNameTf.getText();
            String userSurrname = userSurrnameTf.getText();
            String userLogin = userLoginTf.getText();
            String userPassword = userPasswordTf.getText();

            if (userPassword.equals("") || userLogin.equals("") || userName.equals("") || userSurrname.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            }else{
                if(validate.validateClientData(userPassword, userLogin, userName, userSurrname)){
                    JOptionPane.showMessageDialog(null, "Niepoprawne haslo lub login");
                }else {
                    try {
                        if (!clientSocket.checkUniqueLogin("user",userLogin)) {
                            JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                        }else {
                            if (clientSocket.registerPerson("user", validate.changeClientData(userName), validate.changeClientData(userSurrname), userLogin, userPassword)) {
                                JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano uzytkownika");
                                tableforUser.remove(panelUserTable);
                                tableforUser.repaint();
                                tableforUser.revalidate();
                                panelUserTable = create.createTable("user");
                                tableforUser.add(panelUserTable, BorderLayout.CENTER);

                            } else {
                                JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas rejestracji");
                            }
                        }
                    } catch(IOException e1){
                        e1.printStackTrace();
                    }
                }
            }
        });



        editUser.addActionListener(ae -> {

            String id = userId.getText();
            String userName = userNameTf.getText();
            String userSurrname = userSurrnameTf.getText();
            String userLogin = userLoginTf.getText();
            String userPassword = userPasswordTf.getText();

            String checkLogin = null;

            for (int i = clientSocket.getModel().getRowCount() - 1; i >= 0; --i) {
                if (clientSocket.getModel().getValueAt(i, 0).equals(id)) {
                    checkLogin = (String) clientSocket.getModel().getValueAt(i, 3);
                }
            }

            if (userPassword.equals("") || userLogin.equals("") || userName.equals("") || userSurrname.equals("") || id.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            }else{
                if(validate.validateClientData(userPassword, userLogin, userName, userSurrname)){
                    JOptionPane.showMessageDialog(null, "Niepoprawne haslo lub login");
                }else {
                    try {
                        if (clientSocket.checkActualLogin(checkLogin)) {
                            JOptionPane.showMessageDialog(null, "Uzytkownik zalogowany!");
                        }else {
                            if (!clientSocket.checkUniqueLogin("user", userLogin)) {
                                JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                            } else {
                                if (clientSocket.editAdminSide("user", id, userName, userSurrname, userLogin, userPassword)) {
                                    JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano uzytkownika");
                                    tableforUser.remove(panelUserTable);
                                    tableforUser.repaint();
                                    tableforUser.revalidate();
                                    panelUserTable = create.createTable("user");
                                    tableforUser.add(panelUserTable, BorderLayout.CENTER);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas rejestracji");
                                }
                            }
                        }
                    } catch(IOException e1){
                        e1.printStackTrace();
                    }
                }
            }
        });





        addAdminBtn.addActionListener(ae -> {
            String adminName = adminNameTf.getText();
            String adminSurrname = adminSurrnameTf.getText();
            String adminLogin = adminLoginTf.getText();
            String adminPassword = adminPasswordTf.getText();
            if (adminPassword.equals("") || adminLogin.equals("") || adminName.equals("") || adminSurrname.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            }else{
                if (validate.validateClientData(adminPassword, adminLogin, adminName, adminSurrname)) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
                } else {
                    try {
                        if (!clientSocket.checkUniqueLogin("user", adminLogin)) {
                            JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                        } else {
                            if (clientSocket.registerPerson("admin", validate.changeClientData(adminName), validate.changeClientData(adminSurrname), adminLogin, adminPassword)) {
                                JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano Administratora");
                                tableforAdmin.remove(panelAdminTable);
                                tableforAdmin.repaint();
                                tableforAdmin.revalidate();
                                panelAdminTable = create.createTable("admin");
                                tableforAdmin.add(panelAdminTable, BorderLayout.CENTER);
                                adminsCountLb.setText("Liczba stworzony kont Administratorow: "+create.getTableRowCount(create.getTable()));

                            } else {
                                JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas dodawania Administratora");
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        editAdmin.addActionListener(ae -> {
            String idAdmin = idAdminTf.getText();
            String adminName = adminNameTf.getText();
            String adminSurrname = adminSurrnameTf.getText();
            String adminLogin = adminLoginTf.getText();
            String adminPassword = adminPasswordTf.getText();
            String checkLogin = null;

            for (int i = clientSocket.getModel().getRowCount() - 1; i >= 0; --i) {
                if (clientSocket.getModel().getValueAt(i, 0).equals(idAdmin)) {
                    checkLogin = (String) clientSocket.getModel().getValueAt(i, 3);
                }
            }
            if (adminPassword.equals("") || adminLogin.equals("") || adminName.equals("") || adminSurrname.equals("") || idAdmin.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            }else{
                if (validate.validateClientData(adminPassword, adminLogin, adminName, adminSurrname)) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
                } else {
                    try {
                        if (clientSocket.checkActualLogin(checkLogin)) {
                            JOptionPane.showMessageDialog(null, "Uzytkownik zalogowany!");
                        } else {
                            if (!clientSocket.checkUniqueLogin("user", adminLogin)) {
                                JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                            } else {
                                if (clientSocket.editAdminSide("admin", idAdmin, adminName, adminSurrname, adminLogin, adminPassword)) {
                                    JOptionPane.showMessageDialog(null, "Gratulacje! Edytowano Admina");
                                    tableforAdmin.remove(panelAdminTable);
                                    tableforAdmin.repaint();
                                    tableforAdmin.revalidate();
                                    panelAdminTable = create.createTable("admin");
                                    tableforAdmin.add(panelAdminTable, BorderLayout.CENTER);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Niestety edycja admina sie nie powiodła");
                                }
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        deleteAdminBtn.addActionListener(ae -> {
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz Usunac Admina?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String idAdmin = idAdminTf.getText();
                String checkLogin = null;
                if(idAdmin.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Uzuplenij pole id!");

                }else {
                    try {
                        for (int i = clientSocket.getModel().getRowCount() - 1; i >= 0; --i) {
                            if (clientSocket.getModel().getValueAt(i, 0).equals(idAdmin)) {
                                checkLogin = (String) clientSocket.getModel().getValueAt(i, 3);
                            }
                        }

                        if (clientSocket.checkActualLogin(checkLogin)) {
                            JOptionPane.showMessageDialog(null, "Admin zalogowany!");
                        } else {
                            if (clientSocket.deleteAdminSide("admin", idAdmin)) {
                                JOptionPane.showMessageDialog(null, "Pomyslnie usunieto Admina");
                                tableforAdmin.remove(panelAdminTable);
                                tableforAdmin.repaint();
                                tableforAdmin.revalidate();
                                panelAdminTable = create.createTable("admin");
                                tableforAdmin.add(panelAdminTable, BorderLayout.CENTER);
                                adminsCountLb.setText("Liczba stworzony kont Administratorow: " + create.getTableRowCount(create.getTable()));

                            } else {
                                JOptionPane.showMessageDialog(null, "Dodanie nowego Admina niestety się nie udało");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


        deleteRent.addActionListener(ae ->{
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz usunąć Wypozyczenie?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String idRent = rentIdTf.getText();
                if (idRent.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
                } else {
                    try {
                        if (clientSocket.deleteAdminSide("rent", idRent)) {
                            JOptionPane.showMessageDialog(null, "Dobrze");
                            tableForRent.remove(panelRentsTable);
                            tableForRent.repaint();
                            tableForRent.revalidate();
                            panelRentsTable = create.createTable("rentsAdmin");
                            tableForRent.add(panelRentsTable, BorderLayout.CENTER);
                            rentsCarLb.setText("Liczba aktualnie wypozyczyonych samochodow: " + create.getTableRowCount(create.getTable()));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        deleteCarBtn.addActionListener(ae ->{
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz usunąć samochód?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String idCar = CarIdTF.getText();
                if(idCar.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Uzupelnij pole id!");
                }else {
                    try {
                        if (clientSocket.deleteAdminSide("car", idCar)) {
                            JOptionPane.showMessageDialog(null, "Usunieto samochod");
                            tableForCars.remove(panelCarsTable);
                            tableForCars.repaint();
                            tableForCars.revalidate();
                            panelCarsTable = create.createTable("spis");
                            tableForCars.add(panelCarsTable, BorderLayout.CENTER);
                            carsLb.setText("Liczba Samochod w wypozyczalnii: " + create.getTableRowCount(create.getTable()));

                        } else {
                            JOptionPane.showMessageDialog(null, "Niestety nie udalo sie usunac samochodu");

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        addCarBtn.addActionListener(ae ->{
            String carName = carMarkaTf.getText();
            String carModel = carModelTf.getText();
            String carPrice = carPriceTf.getText();
            String carAviability = carAvabilityTf.getText();
            if (!validateAddOtherData.validateNewCarData(carName, carModel, carPrice, carAviability)) {
                JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
            } else {
                try {
                    if (clientSocket.addAdminSide("car", carName, carModel, carPrice, carAviability)) {
                        JOptionPane.showMessageDialog(null, "Dobrze");
                        tableForCars.remove(panelCarsTable);
                        tableForCars.repaint();
                        tableForCars.revalidate();
                        panelCarsTable = create.createTable("spis");
                        tableForCars.add(panelCarsTable, BorderLayout.CENTER);
                        carsLb.setText("Liczba Samochod w wypozyczalnii: " + create.getTableRowCount(create.getTable()));

                    } else {
                        JOptionPane.showMessageDialog(null, "Niestety nie udalo sie dodac nowego samochodu");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        editCarBtn.addActionListener(ae ->{
            String idCar = CarIdTF.getText();
            String carName = carMarkaTf.getText();
            String carModel = carModelTf.getText();
            String carPrice = carPriceTf.getText();
            String carAviability = carAvabilityTf.getText();
            if (!validateAddOtherData.validateNewCarData(carName, carModel, carPrice, carAviability) || idCar.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
            } else {
                try {
                    if (clientSocket.editAdminSide("car", idCar, carName, carModel, carPrice, carAviability)) {
                        JOptionPane.showMessageDialog(null, "Dobrze");
                        tableForCars.remove(panelCarsTable);
                        tableForCars.repaint();
                        tableForCars.revalidate();
                        panelCarsTable = create.createTable("spis");
                        tableForCars.add(panelCarsTable, BorderLayout.CENTER);
                    } else {
                        JOptionPane.showMessageDialog(null, "Niestety nie udalo sie edytowac samochodu");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        deleteDebtBtn.addActionListener(ae ->{
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz usunąć Karę?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String idDebt = debtIdTf.getText();
                if (idDebt.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
                } else {
                    try {
                        if (clientSocket.deleteAdminSide("fee", idDebt)) {
                            JOptionPane.showMessageDialog(null, "Dobrze");
                            tableForDebt.remove(panelDebtTable);
                            tableForDebt.repaint();
                            tableForDebt.revalidate();
                            panelDebtTable = create.createTable("kary");
                            tableForDebt.add(panelDebtTable, BorderLayout.CENTER);
                            debtCountLb.setText("Ogolna liczba kar uzytkownikow: " + create.getTableRowCount(create.getTable()));

                        } else {
                            JOptionPane.showMessageDialog(null, "Niestety nie udalo sie usunac Kary");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        //Zamyka aplikacje
        exitBtn.addActionListener(e -> {
            try {
                if(!clientSocket.logoff(sAdminLogin,"def"))
                    JOptionPane.showMessageDialog(null, "Wystapil blad");
                dispose();
                JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalnii");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        LogoffBtn.addActionListener(e -> {
            try {
                if(!clientSocket.logoff(sAdminLogin,"undef")) {
                    JOptionPane.showMessageDialog(null, "Wystapil blad");
                }else {
                    JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalnii");
                    dispose();
                    new AdminLogin(clientSocket);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });



        refreshBtn.addActionListener(e -> {
            dispose();
            try {
                new AdminInterface(clientSocket,sAdminLogin);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //minimalizuje aplikacje
        minimalizeBtn.addActionListener(ae -> setState(Frame.ICONIFIED));


        closeServerBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz wyłączyc Server?", "Ostrzeżenie",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                clientSocket.closeServer();
                dispose();
            }
        });


        addDebtBtn.addActionListener(e -> {


            String debtUserId = debtUserIdTf.getText();
            String price = debtPriceTf.getText();
            String date = debtDateTf.getText();

            try {
                if(!validateDate.validateDate("admin",date) || !validateAddOtherData.validateNewDebtData(debtUserId,price)) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne Dane");

                }else {
                    try {
                        if (clientSocket.addAdminSide("fee", debtUserId, price, date, null)) {
                            JOptionPane.showMessageDialog(null, "Gratulacje! Dodano nową karę!");
                            tableForDebt.remove(panelDebtTable);
                            tableForDebt.repaint();
                            tableForDebt.revalidate();
                            panelDebtTable = create.createTable("kary");
                            tableForDebt.add(panelDebtTable, BorderLayout.CENTER);
                            debtCountLb.setText("Ogolna liczba kar uzytkownikow: " + create.getTableRowCount(create.getTable()));

                        } else {
                            JOptionPane.showMessageDialog(null, "Niestety dodanie kary się nie powiodło");
                        }

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });


        editDebtBtn.addActionListener(e -> {
            String idDebt = debtIdTf.getText();
            String debtUserId = debtUserIdTf.getText();
            String price = debtPriceTf.getText();
            String date = debtDateTf.getText();

            try {
                if(!validateDate.validateDate("admin",date) || !validateAddOtherData.validateNewDebtData(debtUserId,price) || idDebt.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne Dane");

                }else {
                    try {
                        if (clientSocket.editAdminSide("debt", idDebt, debtUserId, price, date, null)) {
                            JOptionPane.showMessageDialog(null, "Gratulacje! Edytowano karę!");
                            tableForDebt.remove(panelDebtTable);
                            tableForDebt.repaint();
                            tableForDebt.revalidate();
                            panelDebtTable = create.createTable("kary");
                            tableForDebt.add(panelDebtTable, BorderLayout.CENTER);
                        } else {
                            JOptionPane.showMessageDialog(null, "Niestety edycja kary się nie powiodło");
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });

        searchUserBtn.addActionListener(e -> {
            String getSearchUser = searchUserTf.getText();
            new SearchBtnHandle(clientSocket,getSearchUser);
        });

        searchCarBtn.addActionListener(e -> {
            String getSearchCar = searchCarTf.getText();
            new SearchBtnHandle(clientSocket,getSearchCar);
        });

        searchRentBtn.addActionListener(e -> {
            String getSearchRent = searchRentTf.getText();
            new SearchBtnHandle(clientSocket,getSearchRent);
        });

        dearchAdmBtn.addActionListener(e -> {
            String getSearchAdmin = searchAdmTf.getText();
            new SearchBtnHandle(clientSocket,getSearchAdmin);
        });

        searchDebtBtn.addActionListener(e -> {
            String getSearchDebt = searchDebtTf.getText();
            new SearchBtnHandle(clientSocket,getSearchDebt);
        });




        //GUI//
        //Edytuj użytkownika

        setBtnDesgin(deleteDebtBtn);
        setBtnDesgin(refreshBtn);
        setBtnDesgin(LogoffBtn);
        setBtnDesgin(closeServerBtn);
        setBtnDesgin(debtBtn);
        setBtnDesgin(carsBtn);
        setBtnDesgin(rentBtn);
        setBtnDesgin(userBtn);
        setBtnDesgin(addAdminBtn);
        setBtnDesgin(addCarBtn);
        setBtnDesgin(deleteUserBTN);
        setBtnDesgin(editUser);
        setBtnDesgin(AdminBtn);
        setBtnDesgin(deleteAdminBtn);
        setBtnDesgin(deleteCarBtn);
        setBtnDesgin(editCarBtn);
        setBtnDesgin(searchCarBtn);
        setBtnDesgin(searchDebtBtn);
        setBtnDesgin(searchRentBtn);
        setBtnDesgin(searchUserBtn);
        setBtnDesgin(addUserBtn);
        setBtnDesgin(editAdmin);
        setBtnDesgin(deleteRent);
        setBtnDesgin(addDebtBtn);
        setBtnDesgin(editDebtBtn);

        //Przycisk zamykajacy
        exitBtn.setBorder(null);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setRequestFocusEnabled(false);

        //Przycisk minimalizujacy okno
        minimalizeBtn.setBorder(null);
        minimalizeBtn.setBorderPainted(false);
        minimalizeBtn.setContentAreaFilled(false);
        minimalizeBtn.setRequestFocusEnabled(false);

        setTfDesign(searchUserTf);
        setTfDesign(searchRentTf);
        setTfDesign(searchDebtTf);
        setTfDesign(searchCarTf);
        setTfDesign(searchAdmTf);
        setTfDesign(debtIdTf);
        setTfDesign(adminSurrnameTf);
        setTfDesign(adminPasswordTf);
        setTfDesign(adminLoginTf);
        setTfDesign(idAdminTf);
        setTfDesign(CarIdTF);
        setTfDesign(adminNameTf);
        setTfDesign(userId);
        setTfDesign(userNameTf);
        setTfDesign(userSurrnameTf);
        setTfDesign(userLoginTf);
        setTfDesign(userSurrnameTf);
        setTfDesign(userPasswordTf);
        setTfDesign(carPriceTf);
        setTfDesign(carModelTf);
        setTfDesign(carMarkaTf);
        setTfDesign(carAvabilityTf);
        setTfDesign(rentIdTf);
        setTfDesign(debtUserIdTf);
        setTfDesign(debtPriceTf);
        setTfDesign(debtDateTf);
    }

    private JButton setBtnDesgin(JButton Jbutton){
        Jbutton.setBackground(new Color(255, 117, 0));
        Jbutton.setSize(60, 30);
        Jbutton.setBorder(null);
        return Jbutton;
    }

    private JTextField setTfDesign(JTextField textField){
        textField.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textField.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        return textField;
    }
}
