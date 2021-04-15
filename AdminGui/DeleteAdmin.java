package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DeleteAdmin extends JFrame{
    private JPanel aPanel;
    private JTextField textDelName;
    private JTextField textDelSurname;
    private JTextField textDelLogin;
    private JTextField textDelPass;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    public String imie;
    public String nazwisko;
    public String login;
    public String haslo;


    private void getValueNewAdmin(){
        imie = textDelName.getText();
        nazwisko = textDelSurname.getText();
        login = textDelLogin.getText();
        haslo = textDelPass.getText();

    }


    public DeleteAdmin(Client.ClientWorker clientsocket, String aName, String aSurname, String aLogin, String aPassword) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());

        textDelName.setBackground(new Color(31, 36, 42));
        textDelName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textDelName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textDelName.setForeground(new Color(255, 117, 0));

        // textAddName.setText(aName);

        textDelSurname.setBackground(new Color(31, 36, 42));
        textDelSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textDelSurname.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textDelSurname.setForeground(new Color(255, 117, 0));

        // textAddSurname.setText(aSurname);

        textDelLogin.setBackground(new Color(31, 36, 42));
        textDelLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textDelLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textDelLogin.setForeground(new Color(255, 117, 0));

        //textAddLogin.setText(aLogin);

        textDelPass.setBackground(new Color(31, 36, 42));
        textDelPass.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textDelPass.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textDelPass.setForeground(new Color(255, 117, 0));

        //  textAddPass.setText(aPassword);


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
                if(clientsocket.sendDataDeleteAdmin(imie,nazwisko,login,haslo)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Usunięto  admina!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety usunięcie admina się nie powiodło");
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
        setContentPane(aPanel);
        setVisible(true);




    }


}