package AdminGui;

import Client.ClientWorker;

import javax.swing.*;
import java.util.regex.PatternSyntaxException;

public class SearchBtnHandle {
    ClientWorker clientSocket;
    public SearchBtnHandle(ClientWorker clientSocket, String text){
        this.clientSocket = clientSocket;
        handleBtn(text);
    }

    private void handleBtn(String text){
        if(text.length() == 0) {
            clientSocket.getSorter().setRowFilter(null);
        } else {
            try {
                clientSocket.getSorter().setRowFilter(RowFilter.regexFilter(text));

            } catch(PatternSyntaxException pse) {
                pse.printStackTrace();
            }
        }
    }

}
