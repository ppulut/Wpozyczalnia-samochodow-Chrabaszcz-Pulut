package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddFee extends JFrame {
    private JPanel rPanel;
    private JTextField textFieldUserId;
    private JTextField textFieldKwota;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    private JTextField textFieldData;
    public String id;
    public String userId;
    public String kwota;
    public String data;


    private void getValueNewAdmin(){
        userId = textFieldUserId.getText();
        kwota = textFieldKwota.getText();
        data = textFieldData.getText();
    }


    public AddFee(Client.ClientWorker clientsocket, String rUserId, String rKwota, String rData) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());


        textFieldUserId.setBackground(new Color(31, 36, 42));
        textFieldUserId.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldUserId.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldUserId.setForeground(new Color(255, 117, 0));


        textFieldKwota.setBackground(new Color(31, 36, 42));
        textFieldKwota.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldKwota.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldKwota.setForeground(new Color(255, 117, 0));


        textFieldData.setBackground(new Color(31, 36, 42));
        textFieldData.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldData.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldData.setForeground(new Color(255, 117, 0));



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
                if(clientsocket.sendDataAddFee(userId,kwota,data)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Dodano nową karę!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety dodanie kary się nie powiodło");
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
        setContentPane(rPanel);
        setVisible(true);




    }


}

