package UserGui;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ReservationUser extends JFrame{

    public ReservationUser(Client.ClientWorker clientsocket, String name, String surrname,
                           String sIlosc, String sMark, String sModel, String firstDate, String secondDate, String sFinalCost, String id_klient, String carId){

        nameTf.setBackground(new Color(31, 36, 42));
        nameTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        nameTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        nameTf.setForeground(new Color(255, 117, 0));

        nameTf.setText(name);

        surrNameTf.setBackground(new Color(31, 36, 42));
        surrNameTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        surrNameTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        surrNameTf.setForeground(new Color(255, 117, 0));

        surrNameTf.setText(surrname);

        firstDateTf.setBackground(new Color(31, 36, 42));
        firstDateTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        firstDateTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        firstDateTf.setForeground(new Color(255, 117, 0));

        firstDateTf.setText(firstDate);


        seconDateTf.setBackground(new Color(31, 36, 42));
        seconDateTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        seconDateTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        seconDateTf.setForeground(new Color(255, 117, 0));

        seconDateTf.setText(secondDate);


        markaTf.setBackground(new Color(31, 36, 42));
        markaTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        markaTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        markaTf.setForeground(new Color(255, 117, 0));

        markaTf.setText(sMark);

        modelTf.setBackground(new Color(31, 36, 42));
        modelTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        modelTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        modelTf.setForeground(new Color(255, 117, 0));

        modelTf.setText(sModel);

        numberOfDatTf.setBackground(new Color(31, 36, 42));
        numberOfDatTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        numberOfDatTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        numberOfDatTf.setForeground(new Color(255, 117, 0));

        numberOfDatTf.setText(sIlosc);

        finalCostTf.setBackground(new Color(31, 36, 42));
        finalCostTf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        finalCostTf.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        finalCostTf.setForeground(new Color(255, 117, 0));

        finalCostTf.setText(sFinalCost);

        reservationBtn.setBackground(new Color(255, 117, 0));
        reservationBtn.setBorder(null);

        backBtn.setBackground(new Color(255, 117, 0));
        backBtn.setBorder(null);

        backBtn.addActionListener(e -> {

        dispose();
            try {
                new UserInterface(clientsocket);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });


        reservationBtn.addActionListener(e -> {
                dispose();
            try {
                if(clientsocket.sendDataReservation(id_klient,carId,firstDate,secondDate,sFinalCost)){
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano twoją rezerwacje!");
                }else{
                    JOptionPane.showMessageDialog(null, "Niestety dodanie rezerwacji się nie powiodło");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                new UserInterface(clientsocket);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }

        });
        new JFrame("Okno Rezerwacji");
        setLocationRelativeTo(null);
        setUndecorated(true);
        setSize(600,500);
        setContentPane(mainPanel);
        setVisible(true);

    }

    private JPanel mainPanel;
    private JTextField nameTf;
    private JTextField surrNameTf;
    private JTextField firstDateTf;
    private JTextField seconDateTf;
    private JTextField markaTf;
    private JTextField modelTf;
    private JTextField numberOfDatTf;
    private JTextField finalCostTf;
    private JButton reservationBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel topPanel;



}
