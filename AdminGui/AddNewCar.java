package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddNewCar extends JFrame{
    private JPanel cPanel;
    private JTextField textFieldMarka;
    private JTextField textFieldModel;
    private JTextField textFieldCena;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    private JTextField textFieldDostep;
    public String nowaMarka;
    public String nowyModel;
    public String nowaCena;
    public String dostpenosc;


    private void getValueNewAdmin(){
        nowaMarka = textFieldMarka.getText();
        nowyModel = textFieldModel.getText();
        nowaCena = textFieldCena.getText();
        dostpenosc = textFieldDostep.getText();

    }


    public AddNewCar(Client.ClientWorker clientsocket, String cMarka, String cModel, String cCena, String cDostepnosc) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());

        textFieldMarka.setBackground(new Color(31, 36, 42));
        textFieldMarka.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldMarka.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldMarka.setForeground(new Color(255, 117, 0));



        textFieldModel.setBackground(new Color(31, 36, 42));
        textFieldModel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldModel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldModel.setForeground(new Color(255, 117, 0));



        textFieldCena.setBackground(new Color(31, 36, 42));
        textFieldCena.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldCena.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldCena.setForeground(new Color(255, 117, 0));



        textFieldDostep.setBackground(new Color(31, 36, 42));
        textFieldDostep.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textFieldDostep.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textFieldDostep.setForeground(new Color(255, 117, 0));




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
                if(clientsocket.sendDataAddCar(nowaMarka,nowyModel,nowaCena,dostpenosc)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Dodano nowy pojazd!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety dodanie pojazdu się nie powiodło");
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
        setContentPane(cPanel);
        setVisible(true);


    }

}

