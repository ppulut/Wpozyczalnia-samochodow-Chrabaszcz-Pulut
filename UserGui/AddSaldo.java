package UserGui;

import Client.ClientWorker;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddSaldo extends JFrame{
    ClientWorker clientsocket;

 public AddSaldo(ClientWorker clientsocket,String name, String surrname,String klientId,String saldo,String sUsername){
        this.clientsocket = clientsocket;
        //Gui
         JLabel loginAs = new JLabel(name + " " + surrname);
         loginAs.setBounds(150,0,300,50);
         loginAs.setForeground(new Color(255,117,0));
         loginAs.setFont(new Font("Cooper Black", Font.PLAIN, 16));

         JLabel methodPriceLb = new JLabel("Wybierz Metode Płatnosci");
         methodPriceLb.setBounds(10,50,300,50);
         methodPriceLb.setForeground(new Color(255,117,0));
         methodPriceLb.setFont(new Font("Verdana", Font.PLAIN, 14));

         JLabel priceLb = new JLabel("Ilość wpłaty");
         priceLb.setBounds(10,150,300,50);
         priceLb.setForeground(new Color(255,117,0));
         priceLb.setFont(new Font("Verdana", Font.PLAIN, 14));

        JTextField price = new JTextField();
        price.setBounds(10, 200 , 300, 40);
        price.setBackground(new Color(31, 36, 42));
        price.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        price.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        price.setForeground(new Color(255, 117, 0));

        JButton addMoney = new JButton("Wpłać");
        addMoney.setBounds(10,350,300,40);
        addMoney.setBackground(new Color(255, 117, 0));
        addMoney.setBorder(null);

        JButton backBtn = new JButton("Powrót");
        backBtn.setBounds(10,400,300,40);
        backBtn.setBackground(new Color(255, 117, 0));
        backBtn.setBorder(null);

        String[] sPriceMetod ={"Visa","MasterCard","Santander"};

        JComboBox<String> jCB = new JComboBox<>(sPriceMetod);
        jCB.setBounds(10,100,300,40);
        jCB.setForeground(new Color(255,117,0));
        jCB.setBackground(new Color(31,36,42));
        jCB.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));

        JTextField cardNumber = new JTextField();
        cardNumber.setBounds(10, 300 , 300, 40);
        cardNumber.setBackground(new Color(31, 36, 42));
        cardNumber.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 117, 0)));
        cardNumber.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
        cardNumber.setForeground(new Color(255, 117, 0));

        JLabel numberCardLb = new JLabel("Podaj numer karty");
        numberCardLb.setBounds(10,250,300,50);
        numberCardLb.setForeground(new Color(255,117,0));
        numberCardLb.setFont(new Font("Verdana", Font.PLAIN, 14));

        //
        new JFrame("Wpłata");
        setSize(350,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(21,25,28));

        setLayout(null);
        setResizable(false);

        add(addMoney);
        add(jCB);
        add(methodPriceLb);
        add(loginAs);
        add(price);
        add(priceLb);
        add(backBtn);
        add(cardNumber);
        add(numberCardLb);

         setVisible(true);

        //Koniec Gui

        backBtn.addActionListener(ae -> {

            dispose();
            try {
                new UserInterface(clientsocket,sUsername);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        });

        addMoney.addActionListener(ae -> {

            String sNumberCard = cardNumber.getText();
            String sPrice = price.getText();



            if (sNumberCard.equals("") || sPrice.equals("")) {
                JOptionPane.showMessageDialog(null, "Uzupełnij pola!");
            }else {
                if (validePrice(sPrice)) {

                    double saldoInt = Double.parseDouble(saldo);
                    double priceInt = Double.parseDouble(sPrice);

                    if (validateNumberCard(sNumberCard)) {
                        if(saldoInt + priceInt>9999){
                            JOptionPane.showMessageDialog(null, "Za duza liczba gotowki na koncie!");
                        }else {
                            try {
                                if (clientsocket.addMoney(klientId, sPrice)) {
                                    JOptionPane.showMessageDialog(null, "Dodano Saldo");
                                    dispose();
                                    new UserInterface(clientsocket,sUsername);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Wystapil blad podczas dokonywania wplaty");
                                }
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Niepoprawny numer karty");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "wprowadz poprawna ilosc pieniedzy");

                }
            }
     });
    }

    private boolean validateNumberCard(String sNumberCard) {

     if(sNumberCard.length()!= 16){
            return false;
     }
      return true;
    }

    private boolean validePrice(String sPrice) {
        try {
            Integer.parseInt(sPrice);

            int moreThan = Integer.parseInt(sPrice);

            if(moreThan>9999) {
                return false;
            }
            return true;
            }

             catch (NumberFormatException e) {
            return false;
            }

    }
}
