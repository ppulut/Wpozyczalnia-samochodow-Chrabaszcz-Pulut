package AdminGui;

import Client.ClientWorker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DeleteUser extends JFrame{
    private JPanel uPanel;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldLogin;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    public String imie;
    public String nazwisko;
    public String login;

    boolean check;
    ClientWorker clientSocket;

    private void getValueNewAdmin() {
        imie = textFieldName.getText();
        nazwisko = textFieldSurname.getText();
        login = textFieldLogin.getText();
    }


    public DeleteUser(ClientWorker clientsocket) {
        this.clientSocket = clientsocket;
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

                this.check = clientSocket.checkActualLogin(imie);

                if (check) {
                    JOptionPane.showMessageDialog(null, "Uzytkownik zalogowany");
                } else {

                    if (clientsocket.sendDataDeleteUser(imie, nazwisko, login)) {
                        JOptionPane.showMessageDialog(null, "Gratulacje! Usunięto użytkownika!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Niestety usunięcie użytkownika się nie powiodło");
                    }
                }
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
                try {
                    new AdminInterface(clientsocket);
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
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







