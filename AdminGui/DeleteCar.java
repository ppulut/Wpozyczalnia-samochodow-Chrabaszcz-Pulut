package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

    public class DeleteCar extends JFrame{
        private JPanel cPanel;
        private JTextField textFieldMarka;
        private JTextField textFieldModel;
        private JTextField textFieldCena;
        private JButton zatwierdźButton;
        private JButton cofnijButton;
        public String marka;
        public String model;
        public String cena;


        private void getValueNewAdmin(){
            marka = textFieldMarka.getText();
            model = textFieldModel.getText();
            cena = textFieldCena.getText();
        }


        public DeleteCar(Client.ClientWorker clientsocket, String cMarka, String cModel, String cCena) {
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
                    if(clientsocket.sendDataDeleteCar(marka,model,cena)){
                        JOptionPane.showMessageDialog(null, "Gratulacje! Usunięto pojazd!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Niestety usunięcie pojazdu się nie powiodło");
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

