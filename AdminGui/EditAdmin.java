package AdminGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditAdmin extends JFrame{

    private void getValueNewAdmin(){
        aktualneId = textEditId.getText();
        noweImie = textAddName.getText();
        noweNazwisko = textAddSurname.getText();
        nowyLogin = textAddLogin.getText();
        noweHaslo = textAddPass.getText();

    }

    private JPanel aPanel;
    private JTextField textAddName;
    private JTextField textAddSurname;
    private JTextField textAddLogin;
    private JTextField textAddPass;
    private JButton zatwierdźButton;
    private JButton cofnijButton;
    private JTextField textEditId;

    public String noweImie;
    public String noweNazwisko;
    public String nowyLogin;
    public String noweHaslo;
    public String aktualneId;


    public EditAdmin(Client.ClientWorker clientsocket, String aId, String aName, String aSurname, String aLogin, String aPassword) {
        System.out.println("Socket w Reservation " + clientsocket.getSocket());

        textAddName.setBackground(new Color(31, 36, 42));
        textAddName.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textAddName.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textAddName.setForeground(new Color(255, 117, 0));


        textAddSurname.setBackground(new Color(31, 36, 42));
        textAddSurname.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textAddSurname.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textAddSurname.setForeground(new Color(255, 117, 0));


        textAddLogin.setBackground(new Color(31, 36, 42));
        textAddLogin.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textAddLogin.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textAddLogin.setForeground(new Color(255, 117, 0));


        textAddPass.setBackground(new Color(31, 36, 42));
        textAddPass.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textAddPass.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textAddPass.setForeground(new Color(255, 117, 0));

        textEditId.setBackground(new Color(31, 36, 42));
        textEditId.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        textEditId.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        textEditId.setForeground(new Color(255, 117, 0));


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
                if(clientsocket.sendDataEditAdmin(aktualneId,noweImie,noweNazwisko,nowyLogin,noweHaslo)){
                    JOptionPane.showMessageDialog(null, "Gratulacje! Edycja admina przebiegła pomyślnie!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety edycja danych admina się nie powiodło");
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
