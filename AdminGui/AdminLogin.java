package AdminGui;

import Client.ClientWorker;
import SharedServerClientClasses.ValidateRegisterData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminLogin {

    public AdminLogin(ClientWorker clientSocket) {
        ValidateRegisterData validate = new ValidateRegisterData();
        //Logowanie
        JFrame f= new JFrame("Wypozyczalnia samochodowa");
        //loginText
        loginText = new JLabel("Login");
        loginText.setBounds(220, 170 , 50, 100);
        loginText.setForeground(new Color(255, 117, 0));
        loginText.setFont(new Font("Verdana", Font.PLAIN, 14));
        //loginText haslo
        passwordText = new JLabel("Haslo");
        passwordText.setBounds(220, 270 , 50, 100);
        passwordText.setForeground(new Color(255, 117, 0));
        passwordText.setFont(new Font("Verdana", Font.PLAIN, 14));

        //Przycisk login
        loginButton = new JButton("Zaloguj");
        loginButton.setBounds(80,440,320,50);
        loginButton.setBackground(new Color(255, 117, 0));
        loginButton.setBorder(null);

        //Separator srodek
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBounds(500,0,4,850);
        separator.setBackground(new Color(255,117,0));
        separator.setForeground(new Color(255,117,0));
        separator.setBorder(null);

        //Logowanie Login
        adminLogin = new JTextField();
        adminLogin.setBounds(80, 240 , 320, 50);
        adminLogin.setBackground(new Color(31, 36, 42));
        adminLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        adminLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        adminLogin.setForeground(new Color(255, 117, 0));

        //logowanie haslo
        adminPassword = new JPasswordField();
        adminPassword.setBounds(80, 340 , 320, 50);
        adminPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        adminPassword.setForeground(new Color(255, 117, 0));
        adminPassword.setBackground(new Color(31, 36, 42));
        adminPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));

        //Rejestracja
        //Przcysik rejestracja
        registerButton = new JButton("Rejestruj");
        registerButton.setBounds(590,600,330,50);
        registerButton.setBackground(new Color(255, 117, 0));
        registerButton.setBorder(null);

        //PassField potwierdz_haslo
        confirmAdmPassword = new JPasswordField();
        confirmAdmPassword.setBounds(590, 420 , 330, 50);
        confirmAdmPassword.setBackground(new Color(31, 36, 42));
        confirmAdmPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        confirmAdmPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        confirmAdmPassword.setForeground(new Color(255, 117, 0));

        //PassField haslo
        registerAdmPassword = new JPasswordField();
        registerAdmPassword.setBounds(590, 320 , 330, 50);
        registerAdmPassword.setBackground(new Color(31, 36, 42));
        registerAdmPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerAdmPassword.setForeground(new Color(255, 117, 0));

        //Nazwisko
        registerAdmSurname = new JTextField();
        registerAdmSurname.setBounds(760, 120 , 160, 50);
        registerAdmSurname.setBackground(new Color(31, 36, 42));
        registerAdmSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmSurname.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerAdmSurname.setForeground(new Color(255, 117, 0));

        //Imie
        registerAdmName= new JTextField();
        registerAdmName.setBounds(590, 120 , 160, 50);
        registerAdmName.setBackground(new Color(31, 36, 42));
        registerAdmName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerAdmName.setForeground(new Color(255, 117, 0));

        //textfield login
        registerAdmLogin = new JTextField();
        registerAdmLogin.setBounds(590, 220 , 330, 50);
        registerAdmLogin.setBackground(new Color(31, 36, 42));
        registerAdmLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerAdmLogin.setForeground(new Color(255, 117, 0));

        //Jlabel Imie
        nameText = new JLabel("Imie");
        nameText.setBounds(660, 50 , 50, 100);
        nameText.setForeground(new Color(255, 117, 0));
        nameText.setFont(new Font("Verdana", Font.PLAIN, 14));

        //Jlabel Nazwisko
        surnameText = new JLabel("Nazwisko");
        surnameText.setBounds(800, 50 , 160, 100);
        surnameText.setForeground(new Color(255, 117, 0));
        surnameText.setFont(new Font("Verdana", Font.PLAIN, 14));

        //Jlabel login rejestracja
        registrationLoginText = new JLabel("Login");
        registrationLoginText.setBounds(730, 150 , 50, 100);
        registrationLoginText.setForeground(new Color(255, 117, 0));
        registrationLoginText.setFont(new Font("Verdana", Font.PLAIN, 14));

        //Jlabel haslo rejestracja
        registrationPasswordText = new JLabel("Haslo");
        registrationPasswordText.setBounds(730, 250 , 50, 100);
        registrationPasswordText.setForeground(new Color(255, 117, 0));
        registrationPasswordText.setFont(new Font("Verdana", Font.PLAIN, 14));

        //Jlabel potwierdz haslo
        registrationConfirmPasswordText = new JLabel("Potwierdź Haslo");
        registrationConfirmPasswordText.setBounds(700, 350 , 300, 100);
        registrationConfirmPasswordText.setForeground(new Color(255, 117, 0));
        registrationConfirmPasswordText.setFont(new Font("Verdana", Font.PLAIN, 14));

        accessCode = new JLabel("Wprowadz kod dostepu");
        accessCode.setBounds(680, 450 , 300, 100);
        accessCode.setForeground(new Color(255, 117, 0));
        accessCode.setFont(new Font("Verdana", Font.PLAIN, 14));

        accessCodeField = new JTextField();
        accessCodeField.setBounds(590, 520 , 330, 50);
        accessCodeField.setBackground(new Color(31, 36, 42));
        accessCodeField.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        accessCodeField.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        accessCodeField.setForeground(new Color(255, 117, 0));

        logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/logoUserLogin.png")));
        logo.setBorder(null);
        logo.setRequestFocusEnabled(false);
        logo.setBounds(75,60,500,88);


        f.getContentPane().setBackground(new Color(21,25,28));
        f.setSize(1000,750);
        //Przycisk zamykajacy
        closeBtn = new JButton();
        closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/cancel.png")));
        closeBtn.setBorder(null);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setRequestFocusEnabled(false);
        closeBtn.setBounds(950,5,30,30);
        //Minimaluzujacy
        minimalizeBtn = new JButton();
        minimalizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/minus.png")));
        minimalizeBtn.setBorder(null);
        minimalizeBtn.setBorderPainted(false);
        minimalizeBtn.setContentAreaFilled(false);
        minimalizeBtn.setRequestFocusEnabled(false);
        minimalizeBtn.setBounds(920,5,30,30);

        closeBtn.addActionListener(e -> {
            try {
                clientSocket.logoff(null,"def");
                f.dispose();
                JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalni");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        minimalizeBtn.addActionListener(ae -> f.setState(Frame.ICONIFIED));

        f.setLayout(null);

        //dodawanie login
        f.add(loginText);
        f.add(passwordText);
        f.add(adminLogin);
        f.add(adminPassword);
        f.add(separator);
        f.add(loginButton);
        f.add(logo);

        //dodawnie rejestracja
        f.add(registerAdmLogin);
        f.add(registerButton);
        f.add(confirmAdmPassword);
        f.add(registerAdmPassword);
        f.add(registrationLoginText);
        f.add(registrationPasswordText);
        f.add(registrationConfirmPasswordText);
        f.add(registerAdmSurname);
        f.add(registerAdmName);
        f.add(nameText);
        f.add(surnameText);
        f.add(accessCode);
        f.add(accessCodeField);
        f.add(closeBtn);
        f.add(minimalizeBtn);

        f.setUndecorated(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);

        //Dzialanie przycisku login
        loginButton.addActionListener(ae -> {
            String sAdminLogin = adminLogin.getText();
            String sAdminPassword = String.valueOf(adminPassword.getPassword());

            if (sAdminLogin.equals("") || sAdminPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            } else {
                try {
                    if(clientSocket.checkActualLogin(sAdminLogin)) {
                        JOptionPane.showMessageDialog(null, "Admin zalogowany!");
                    }else {
                        if (clientSocket.login("admin", sAdminLogin, sAdminPassword)) {
                            JOptionPane.showMessageDialog(null, "Witamy w naszej wypożyczalnii!");
                            f.dispose();
                            new AdminInterface(clientSocket,sAdminLogin);

                        } else {
                            JOptionPane.showMessageDialog(null, "blad poczas logowania");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        registerButton.addActionListener(ae -> {

            //Imie
            String adminCreateName = registerAdmName.getText();
            //haslo
            String admCreatePassword = String.valueOf(registerAdmPassword.getPassword());
            //potwierdz haslo
            String admConfirmPassword = String.valueOf(confirmAdmPassword.getPassword());
            //Login
            String adminLogin = registerAdmLogin.getText();
            //Nazwisko
            String adminSurname = registerAdmSurname.getText();
            //kod dostepu
            String adminAccesCode = accessCodeField.getText();

            if(admCreatePassword.equals(admConfirmPassword)) {
                if(validate.validateClientData(admCreatePassword, adminLogin, adminCreateName, adminSurname) ||(!adminAccesCode.equalsIgnoreCase("1234"))){
                    JOptionPane.showMessageDialog(null, "Wpisano niepoprawne dane");
                }else {

                        try {
                            if (!clientSocket.checkUniqueLogin("user",adminLogin)) {
                                JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                            }else {
                                if (clientSocket.registerPerson("admin", validate.changeClientData(adminCreateName), validate.changeClientData(adminSurname), adminLogin, admCreatePassword)) {
                                    JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano uzytkownika");
                                    setTxtFld();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas rejestracji");
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Hasla musza byc takie same");
            }
        });

    }

    private void setTxtFld(){

        registerAdmName.setText(null);
        registerAdmPassword.setText(null);
        confirmAdmPassword.setText(null);
        registerAdmLogin.setText(null);
        registerAdmSurname.setText(null);
        accessCodeField.setText(null);

    }

    //Logowanie
    JButton loginButton;
    JSeparator separator;
    JLabel loginText;
    JLabel passwordText;
    JTextField adminLogin;
    JPasswordField adminPassword;
    //Rejestracja
    JButton registerButton;
    JTextField registerAdmLogin;
    JTextField registerAdmName;
    JTextField registerAdmSurname;
    JPasswordField registerAdmPassword;
    JPasswordField confirmAdmPassword;
    JLabel registrationLoginText;
    JLabel registrationPasswordText;
    JLabel registrationConfirmPasswordText;
    JLabel surnameText;
    JLabel nameText;
    JLabel accessCode;
    JTextField accessCodeField;
    JLabel logo;
    JButton closeBtn;
    JButton minimalizeBtn;

}





