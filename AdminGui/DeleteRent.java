package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DeleteRent extends JFrame{
    private JPanel wPanel;
    private JTextField textFieldId;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    public String id;


    private void getValueNewAdmin(){
        id = textFieldId.getText();
    }


    public DeleteRent(Client.ClientWorker clientsocket, String wId) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());

        textFieldId.setBackground(new Color(31, 36, 42));
        textFieldId.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldId.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldId.setForeground(new Color(255, 117, 0));


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
                if(clientsocket.sendDataDeleteRent(id)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Usunięto wypozyczenie!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety usunięcie wypozyczenia się nie powiodło");
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
        setContentPane(wPanel);
        setVisible(true);




    }


}

