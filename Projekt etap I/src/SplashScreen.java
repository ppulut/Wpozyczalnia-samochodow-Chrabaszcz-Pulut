

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class SplashScreen extends JWindow
{

    static private JProgressBar progressBar = new JProgressBar();
    static private int count = 1, timerPause = 20, progBar = 100;
    static private Timer progressBarTimer;



    public SplashScreen() {


        //Wymiary okeinka
        setSize(1895,863);
        setLayout(new BorderLayout());

        //Zmienia kursor na Wait kursor
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Ustawienie obrazka na tlo
        //JLabel background=new JLabel(new ImageIcon(""));


        //Wyswietla okienko na srodku ekranu
        setLocationRelativeTo(null);

        //Tworzenie kontenera
        Container container = getContentPane();

        //Ustawienia progressbara
        progressBar.setMaximum(progBar);
        container.add(progressBar, BorderLayout.SOUTH);


        progressBarTimer = new Timer(timerPause, actionProgressBar);
        progressBarTimer.start();


        //add(background);
        setVisible(true);



    }

    //Action listener jest potrzebny do Timera i ustawiania wartosci
    ActionListener actionProgressBar = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {


            //Zapewnia ze na progressbar beda wyswietlaly sie %
            progressBar.setStringPainted(true);
            //Ustawianie wyswietalnej wartosci na ProgressBar
            progressBar.setValue(count);
            //Wielkosci progress baru
            progressBar.setBounds(0, 770, 1895, 100);
            //Zmiena kolor ladowania progressbar
            progressBar.setForeground(Color.gray);

            //Jezeli progressbar bedzie mial wartosc 100% to okienko jest zamykane
            if (progBar == count) {
                //Zamyka okno
                dispose();
                //Wywolanie okienka wyboru uzytkownika
                OknoWyboruUzytkownika OknoWyboruUzytkownika = new OknoWyboruUzytkownika();

            }
            //Zwiekszanie wartosci do progressBar

            count++;
        }
    };

    public static void main(String args[]){
        SplashScreen OknoLadowania = new SplashScreen();
    }

}
