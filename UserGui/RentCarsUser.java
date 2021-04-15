package UserGui;

import Client.ClientWorker;
import SharedGui.CreateJTable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RentCarsUser extends JFrame{
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel topPanel;
    private JLabel loginAsLb;
    private JButton backBtn;
    private JPanel leftPanel;

    ClientWorker clientSocket;

    public RentCarsUser(ClientWorker clientSocket, String name, String surrname,String sUserName) {
        //Przekazanie Aktywnego połaczenia
        this.clientSocket = clientSocket;

        //Gui
        loginAsLb.setText("Wypożyczenia dla: " + name + " " + surrname);

        backBtn.setBackground(new Color(255, 117, 0));
        backBtn.setBorder(null);
        //Koniec Gui

        //Tworzenie tabeli
        CreateJTable create = new CreateJTable(clientSocket);
        tablePanel.add(create.createTable("rents"), BorderLayout.CENTER);

        new JFrame("Podsumowanie Wypożyczeń");
        setSize(500,500);
        setContentPane(mainPanel);


        setUndecorated(true);
        setResizable(false);
        setVisible(true);

        backBtn.addActionListener(e -> {

            dispose();
            try {
                new UserInterface(clientSocket,sUserName);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }

        });
    }

}
