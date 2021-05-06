package UserGui;

import Client.ClientWorker;
import SharedUserAdminClasses.ValidateRegisterData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class UserLogin extends JFrame{
    public UserLogin(ClientWorker clientSocket) {
        //Logowanie
        new JFrame();
        ValidateRegisterData validate = new ValidateRegisterData();

        URL iconURL = getClass().getResource("/rsc/car.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

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
        //Info
        info = new JLabel(" ");
        info.setBounds(660, 5 , 500, 100);
        info.setForeground(new Color(255, 117, 0));
        info.setFont(new Font("Verdana", Font.PLAIN, 14));
        //Jlabel Imie
        nameText = new JLabel("Imie");
        nameText.setBounds(660, 50 , 50, 100);
        nameText.setForeground(new Color(255, 117, 0));
        nameText.setFont(new Font("Verdana", Font.PLAIN, 14));

        nameText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info.setText("Imie nie może zawierać cyfr");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                info.setText(" ");
            }
        });
        //Jlabel Nazwisko
        surnameText = new JLabel("Nazwisko");
        surnameText.setBounds(800, 50 , 160, 100);
        surnameText.setForeground(new Color(255, 117, 0));
        surnameText.setFont(new Font("Verdana", Font.PLAIN, 14));

        surnameText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info.setText("Nazwisko nie może zawierać cyfr");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                info.setText(" ");
            }
        });
        //Jlabel login rejestracja
        registrationLoginText = new JLabel("Login");
        registrationLoginText.setBounds(730, 150 , 50, 100);
        registrationLoginText.setForeground(new Color(255, 117, 0));
        registrationLoginText.setFont(new Font("Verdana", Font.PLAIN, 14));
        registrationLoginText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info.setText("Login musi zawierać od 4 do 20 znaków i być unikalny");
                info.setBounds(570, 5 , 500, 100);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                info.setText(" ");
                info.setBounds(660, 5 , 500, 100);
            }
        });
        //Jlabel haslo rejestracja
        registrationPasswordText = new JLabel("Haslo");
        registrationPasswordText.setBounds(730, 250 , 50, 100);
        registrationPasswordText.setForeground(new Color(255, 117, 0));
        registrationPasswordText.setFont(new Font("Verdana", Font.PLAIN, 14));
        registrationPasswordText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info.setText("Haslo musi zawierać duża Literę oraz cyfrę");
                info.setBounds(600, 5 , 500, 100);

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                info.setText(" ");
                info.setBounds(660, 5 , 500, 100);

            }
        });


        //Jlabel potwierdz haslo
        registrationConfirmPasswordText = new JLabel("Potwierdź Haslo");
        registrationConfirmPasswordText.setBounds(700, 350 , 300, 100);
        registrationConfirmPasswordText.setForeground(new Color(255, 117, 0));
        registrationConfirmPasswordText.setFont(new Font("Verdana", Font.PLAIN, 14));

        registrationConfirmPasswordText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info.setText("Powtórzone hasło musi być takie samo");
                info.setBounds(600, 5 , 500, 100);

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                info.setText(" ");
                info.setBounds(660, 5 , 500, 100);

            }
        });
        //Obsluga Frame
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

        logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsc/logoUserLogin.png")));
        logo.setBorder(null);
        logo.setRequestFocusEnabled(false);
        logo.setBounds(75,60,500,88);



        getContentPane().setBackground(new Color(21,25,28));
        setSize(1000,700);

        setLayout(null);

        //dodawanie login
        add(loginText);
        add(passwordText);
        add(userLogin);
        add(userPassword);
        add(separator);
        add(loginButton);
        add(logo);

        //dodawnie rejestracja
        add(registerUserLogin);
        add(registerButton);
        add(confirmUserPassword);
        add(registerUserPassword);
        add(registrationLoginText);
        add(registrationPasswordText);
        add(registrationConfirmPasswordText);
        add(registerUserSurname);
        add(registerUserName);
        add(nameText);
        add(surnameText);
        add(closeBtn);
        add(minimalizeBtn);
        add(info);

        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        //Dzialanie przycisku login
        loginButton.addActionListener(ae -> {

            String sUserName = userLogin.getText();
            String sUserPassword = String.valueOf(userPassword.getPassword());

            if (sUserName.equals("") || sUserPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            } else {
                try {
                    if(clientSocket.checkActualLogin(sUserName)) {
                       JOptionPane.showMessageDialog(null, "uzytkownik zalogowany!");
                   }else {
                        if (clientSocket.login("user", sUserName, sUserPassword)) {
                            JOptionPane.showMessageDialog(null, "Witamy w naszej wypożyczalnii!");
                            dispose();
                            try {
                                new UserInterface(clientSocket,sUserName);
                            } catch (ClassNotFoundException | IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Niestety podane dane są nieprawidłowe");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            });

        //Przycisk wysyla informacje z TextFieldow do servera
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
                    if(validate.validateClientData(userCreatePassword, userLogin, userCreateName, userSurrname)){
                        JOptionPane.showMessageDialog(null, "Niepoprawne haslo lub login");
                    }else {
                        try {
                            if (!clientSocket.checkUniqueLogin("user",userLogin)) {
                                JOptionPane.showMessageDialog(null, "Login jest juz zajety, wybierz inny");
                                 }else {
                                if (clientSocket.registerPerson("user", validate.changeClientData(userCreateName), validate.changeClientData(userSurrname), userLogin, userCreatePassword)) {
                                    JOptionPane.showMessageDialog(null, "Pomyslnie zarejestowano uzytkownika");
                                    this.setTxtFld();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Niestety wystapil blad podczas rejestracji");
                                }
                            }
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                    }else{

                    JOptionPane.showMessageDialog(null, "Hasla musza byc takie same");
                }
        });

        //Zamyka aplikacje
        closeBtn.addActionListener(e -> {
            try {
                clientSocket.logoff(null,"def");
                dispose();
                JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalni");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        minimalizeBtn.addActionListener(ae -> setState(Frame.ICONIFIED));
    }

    private void setTxtFld(){

        registerUserPassword.setText(null);
        confirmUserPassword.setText(null);
        registerUserLogin.setText(null);
        registerUserName.setText(null);
        registerUserSurname.setText(null);
    }
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
    JLabel logo;
    JLabel info;
    //Obsluga frame
    JButton closeBtn;
    JButton minimalizeBtn;

}



