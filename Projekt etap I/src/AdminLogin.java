
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminLogin {


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


    public AdminLogin() {
//Logowanie

        JFrame f= new JFrame("Wypozyczalnia samochodowa");
        //loginText
        loginText = new JLabel("Login");
        loginText.setBounds(220, 170 , 50, 100);
        loginText.setForeground(new Color(255, 117, 0));
        loginText.setFont(new Font("Verdana", 0, 14));
        //loginText haslo
        passwordText = new JLabel("Haslo");
        passwordText.setBounds(220, 270 , 50, 100);
        passwordText.setForeground(new Color(255, 117, 0));
        passwordText.setFont(new Font("Verdana", 0, 14));

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
        adminLogin.setFont(new java.awt.Font("Verdana", 0, 14));
        adminLogin.setForeground(new Color(255, 117, 0));

        //logowanie haslo
        adminPassword = new JPasswordField();
        adminPassword.setBounds(80, 340 , 320, 50);
        adminPassword.setFont(new java.awt.Font("Verdana", 0, 14));
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
        confirmAdmPassword.setFont(new java.awt.Font("Verdana", 0, 14));
        confirmAdmPassword.setForeground(new Color(255, 117, 0));

        //PassField haslo
        registerAdmPassword = new JPasswordField();
        registerAdmPassword.setBounds(590, 320 , 330, 50);
        registerAdmPassword.setBackground(new Color(31, 36, 42));
        registerAdmPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmPassword.setFont(new java.awt.Font("Verdana", 0, 14));
        registerAdmPassword.setForeground(new Color(255, 117, 0));


        //Nazwisko
        registerAdmSurname = new JTextField();
        registerAdmSurname.setBounds(760, 120 , 160, 50);
        registerAdmSurname.setBackground(new Color(31, 36, 42));
        registerAdmSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmSurname.setFont(new java.awt.Font("Verdana", 0, 14));
        registerAdmSurname.setForeground(new Color(255, 117, 0));

        //Imie
        registerAdmName= new JTextField();
        registerAdmName.setBounds(590, 120 , 160, 50);
        registerAdmName.setBackground(new Color(31, 36, 42));
        registerAdmName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmName.setFont(new java.awt.Font("Verdana", 0, 14));
        registerAdmName.setForeground(new Color(255, 117, 0));

        //textfield login
        registerAdmLogin = new JTextField();
        registerAdmLogin.setBounds(590, 220 , 330, 50);
        registerAdmLogin.setBackground(new Color(31, 36, 42));
        registerAdmLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        registerAdmLogin.setFont(new java.awt.Font("Verdana", 0, 14));
        registerAdmLogin.setForeground(new Color(255, 117, 0));

        //Jlabel Imie
        nameText = new JLabel("Imie");
        nameText.setBounds(660, 50 , 50, 100);
        nameText.setForeground(new Color(255, 117, 0));
        nameText.setFont(new Font("Verdana", 0, 14));

        //Jlabel Nazwisko
        surnameText = new JLabel("Nazwisko");
        surnameText.setBounds(800, 50 , 160, 100);
        surnameText.setForeground(new Color(255, 117, 0));
        surnameText.setFont(new Font("Verdana", 0, 14));

        //Jlabel login rejestracja
        registrationLoginText = new JLabel("Login");
        registrationLoginText.setBounds(730, 150 , 50, 100);
        registrationLoginText.setForeground(new Color(255, 117, 0));
        registrationLoginText.setFont(new Font("Verdana", 0, 14));

        //Jlabel haslo rejestracja

        registrationPasswordText = new JLabel("Haslo");
        registrationPasswordText.setBounds(730, 250 , 50, 100);
        registrationPasswordText.setForeground(new Color(255, 117, 0));
        registrationPasswordText.setFont(new Font("Verdana", 0, 14));


        //Jlabel potwierdz haslo
        registrationConfirmPasswordText = new JLabel("Potwierdź Haslo");
        registrationConfirmPasswordText.setBounds(700, 350 , 300, 100);
        registrationConfirmPasswordText.setForeground(new Color(255, 117, 0));
        registrationConfirmPasswordText.setFont(new Font("Verdana", 0, 14));


        accessCode = new JLabel("Wprowadz kod dostepu");
        accessCode.setBounds(680, 450 , 300, 100);
        accessCode.setForeground(new Color(255, 117, 0));
        accessCode.setFont(new Font("Verdana", 0, 14));


        accessCodeField = new JTextField();
        accessCodeField.setBounds(590, 520 , 330, 50);
        accessCodeField.setBackground(new Color(31, 36, 42));
        accessCodeField.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        accessCodeField.setFont(new java.awt.Font("Verdana", 0, 14));
        accessCodeField.setForeground(new Color(255, 117, 0));




        f.getContentPane().setBackground(new Color(21,25,28));
        f.setSize(1000,850);

        f.setLayout(null);

        //dodawanie login
        f.add(loginText);
        f.add(passwordText);
        f.add(adminLogin);
        f.add(adminPassword);
        f.add(separator);
        f.add(loginButton);

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

        f.setUndecorated(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);


        //Dzialanie przycisku login
        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

            }
        });


        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }

        });

    }

}





