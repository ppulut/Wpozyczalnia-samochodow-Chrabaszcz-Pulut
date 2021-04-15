package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddNewUser extends JFrame{
    private JPanel uPanel;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldLogin;
    private JTextField textFieldPass;
    private JTextField textFieldSaldo;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    public String noweImie;
    public String noweNazwisko;
    public String nowyLogin;
    public String noweHaslo;
    public String noweSaldo;

    private void getValueNewAdmin() {
        noweImie = textFieldName.getText();
        noweNazwisko = textFieldSurname.getText();
        nowyLogin = textFieldLogin.getText();
        noweHaslo = textFieldPass.getText();
        noweSaldo = textFieldSaldo.getText();
    }


    public AddNewUser(Client.ClientWorker clientsocket, String uName, String uSurname, String uLogin, String uHaslo, String uSaldo) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());

        textFieldName.setBackground(new Color(31, 36, 42));
        textFieldName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldName.setForeground(new Color(255, 117, 0));



        textFieldSurname.setBackground(new Color(31, 36, 42));
        textFieldSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldSurname.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldSurname.setForeground(new Color(255, 117, 0));



        textFieldLogin.setBackground(new Color(31, 36, 42));
        textFieldLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldLogin.setForeground(new Color(255, 117, 0));



        textFieldPass.setBackground(new Color(31, 36, 42));
        textFieldPass.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldPass.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldPass.setForeground(new Color(255, 117, 0));

        textFieldSaldo.setBackground(new Color(31, 36, 42));
        textFieldSaldo.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldSaldo.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldSaldo.setForeground(new Color(255, 117, 0));




        cofnijButton.setBackground(new Color(255, 117, 0));
        cofnijButton.setBorder(null);

        cofnijButton.addActionListener(e -> {

            dispose();
            try {
                new AdminInterface(clientsocket);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });


        zatwierdźButton.setBackground(new Color(255, 117, 0));
        zatwierdźButton.setBorder(null);

        zatwierdźButton.addActionListener(e -> {
            getValueNewAdmin();
            try {
                if(clientsocket.sendDataAddUser(noweImie,noweNazwisko,nowyLogin,noweHaslo,noweSaldo)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Dodano nowego użytkownika!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety dodanie użytkownika się nie powiodło");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                new AdminInterface(clientsocket);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            dispose();
        });

        new JFrame("Okno Rezerwacji");
        setLocationRelativeTo(null);
        setUndecorated(true);
        setSize(600,500);
        setContentPane(uPanel);
        setVisible(true);




    }


}







