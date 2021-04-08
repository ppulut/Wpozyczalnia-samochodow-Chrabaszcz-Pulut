package UserGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserLogin {
    //Komunikacja z serverem

    //Logowanie
    JButton loginButton;
    JSeparator separator;
    JLabel loginText;
    JLabel passwordText;
    JTextField userLogin;
    JPasswordField userPassword;
    //Rejestracja
    JButton registerButton;
    JTextField registerUserLogin;
    JTextField registerUserName;
    JTextField registerUserSurname;
    JPasswordField registerUserPassword;
    JPasswordField confirmUserPassword;
    JLabel registrationLoginText;
    JLabel registrationPasswordText;
    JLabel registrationConfirmPasswordText;
    JLabel surnameText;
    JLabel nameText;

    public UserLogin(Client.ClientWorker clientsocket) {
        //Laczenie z serverem
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
        separator.setBounds(500,0,4,700);
        separator.setBackground(new Color(255,117,0));
        separator.setForeground(new Color(255,117,0));
        separator.setBorder(null);
        //Logowanie Login
        userLogin = new JTextField();
        userLogin.setBounds(80, 240 , 320, 50);
        userLogin.setBackground(new Color(31, 36, 42));
        userLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        userLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        userLogin.setForeground(new Color(255, 117, 0));
        //logowanie haslo
        userPassword = new JPasswordField();
        userPassword.setBounds(80, 340 , 320, 50);
        userPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        userPassword.setForeground(new Color(255, 117, 0));
        userPassword.setBackground(new Color(31, 36, 42));
        userPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        //Rejestracja
        //Przcysik rejestracja
        registerButton = new JButton("Rejestruj");
        registerButton.setBounds(590,520,330,50);
        registerButton.setBackground(new Color(255, 117, 0));
        registerButton.setBorder(null);
        //PassField potwierdz_haslo
        confirmUserPassword = new JPasswordField();
        confirmUserPassword.setBounds(590, 420 , 330, 50);
        confirmUserPassword.setBackground(new Color(31, 36, 42));
        confirmUserPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        confirmUserPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        confirmUserPassword.setForeground(new Color(255, 117, 0));
        //PassField haslo
        registerUserPassword = new JPasswordField();
        registerUserPassword.setBounds(590, 320 , 330, 50);
        registerUserPassword.setBackground(new Color(31, 36, 42));
        registerUserPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerUserPassword.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerUserPassword.setForeground(new Color(255, 117, 0));
        //Nazwisko
        registerUserSurname = new JTextField();
        registerUserSurname.setBounds(760, 120 , 160, 50);
        registerUserSurname.setBackground(new Color(31, 36, 42));
        registerUserSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerUserSurname.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerUserSurname.setForeground(new Color(255, 117, 0));
        //Imie
        registerUserName= new JTextField();
        registerUserName.setBounds(590, 120 , 160, 50);
        registerUserName.setBackground(new Color(31, 36, 42));
        registerUserName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerUserName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerUserName.setForeground(new Color(255, 117, 0));
        //textfield login
        registerUserLogin = new JTextField();
        registerUserLogin.setBounds(590, 220 , 330, 50);
        registerUserLogin.setBackground(new Color(31, 36, 42));
        registerUserLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerUserLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        registerUserLogin.setForeground(new Color(255, 117, 0));
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

        f.getContentPane().setBackground(new Color(21,25,28));
        f.setSize(1000,700);

        f.setLayout(null);

        //dodawanie login
        f.add(loginText);
        f.add(passwordText);
        f.add(userLogin);
        f.add(userPassword);
        f.add(separator);
        f.add(loginButton);

        //dodawnie rejestracja
        f.add(registerUserLogin);
        f.add(registerButton);
        f.add(confirmUserPassword);
        f.add(registerUserPassword);
        f.add(registrationLoginText);
        f.add(registrationPasswordText);
        f.add(registrationConfirmPasswordText);
        f.add(registerUserSurname);
        f.add(registerUserName);
        f.add(nameText);
        f.add(surnameText);

        f.setUndecorated(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);


        //Dzialanie przycisku login
        loginButton.addActionListener(ae -> {

            String sUserName = userLogin.getText();
            String sUserPassword = userPassword.getText();
            if (sUserName.equals("") || sUserPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            } else {
                try {
                    if (clientsocket.login("user",sUserName, sUserPassword)) {
                        JOptionPane.showMessageDialog(null, "Witamy w naszej wypożyczalnii!");
                        f.dispose();
                        try {
                            new UserInterface(clientsocket);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "blad poczas logowania");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            });

        registerButton.addActionListener(ae -> {
                //Imie
                String userCreateName = registerUserName.getText();
                //haslo
                String userCreatePassword = String.valueOf(registerUserPassword.getPassword());
                //potwierdz haslo
                String userConfirmPassword =  String.valueOf(confirmUserPassword.getPassword());
                //Login
                String userLogin = registerUserLogin.getText();
                //Nazwisko
                String userSurrname = registerUserSurname.getText();

                if(userCreatePassword.equals(userConfirmPassword)) {
                    if(!validateRegisterData(userCreatePassword,userLogin)){
                        JOptionPane.showMessageDialog(null, "Niepoprawne haslo lub login");
                    }else{
                        try {
                            if(clientsocket.registerPerson("user",userCreateName,userSurrname,userLogin,userCreatePassword)){
                                JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano uzytkownika");
                                registerUserName.setText(null);
                                registerUserPassword.setText(null);
                                confirmUserPassword.setText(null);
                                registerUserLogin.setText(null);
                                registerUserName.setText(null);
                                registerUserSurname.setText(null);
                            }else{
                                JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas rejestracji");

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

    public boolean validateRegisterData(String password, String login){

        String numbersPattern   = ".*[0-9].*";
        String lettersPattern = ".*[A-Z].*";

            if (password.length() <= 3 || login.length() <= 3 || login.length()>20 || password.length()>20) {
                    return false;
            }else{
                if (password.matches(lettersPattern) && password.matches(numbersPattern)){
                    return true;
                }else{
                    return false;
                }
            }
    }
}



