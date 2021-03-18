
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class SplashScreen extends JWindow
{

    final static private JProgressBar progressBar = new JProgressBar();
    static private int count = 1;
    static private final int timerPause = 20;
    static private final int progBar = 100;

    private SplashScreen() throws SQLException {
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

        new CreateDatabase();

        Timer progressBarTimer = new Timer(timerPause, actionProgressBar);
        progressBarTimer.start();


        //add(background);
        setVisible(true);
        }

         //Action listener jest potrzebny do Timera i ustawiania wartosci
        ActionListener actionProgressBar = evt -> {
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
            new OknoWyboruUzytkownika();
        }
        //Zwiekszanie wartosci do progressBar
        count++;
    };

    public static void main(String[] args) throws SQLException {
        new SplashScreen();
    }

}
