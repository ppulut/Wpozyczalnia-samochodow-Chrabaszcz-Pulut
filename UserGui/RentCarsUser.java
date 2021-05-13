package UserGui;

import Client.ClientWorker;
import SharedUserAdminClasses.CreateJTable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class RentCarsUser extends JFrame{

    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel topPanel;
    private JLabel loginAsLb;
    private JButton backBtn;
    private JPanel leftPanel;
    private JButton cancelBtn;
    private JButton minimalizeBtn;
    private JPanel cancelPanel;

    ClientWorker clientSocket;

    public RentCarsUser(ClientWorker clientSocket, String name, String surrname,String sUserName) {
        //Przekazanie Aktywnego połaczenia
        this.clientSocket = clientSocket;

        URL iconURL = getClass().getResource("/rsc/car.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        //Gui
        loginAsLb.setText("Wypożyczenia dla: " + name + " " + surrname);

        backBtn.setBackground(new Color(255, 117, 0));
        backBtn.setBorder(null);

        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setRequestFocusEnabled(false);

        cancelBtn.addActionListener(e -> {

            try {
                clientSocket.logoff(sUserName,"def");
                dispose();
                JOptionPane.showMessageDialog(null, "Dziekujemy za skorzystanie z naszej wypozyczalni");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        minimalizeBtn.setBorder(null);
        minimalizeBtn.setBorderPainted(false);
        minimalizeBtn.setContentAreaFilled(false);
        minimalizeBtn.setRequestFocusEnabled(false);
        minimalizeBtn.addActionListener(ae -> setState(Frame.ICONIFIED));
        //Tworzenie tabeli
        CreateJTable create = new CreateJTable(clientSocket);
        tablePanel.add(create.createTable("rents"), BorderLayout.CENTER);

        new JFrame("Podsumowanie Wypożyczeń");
        setSize(500,500);
        setContentPane(mainPanel);

        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

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
