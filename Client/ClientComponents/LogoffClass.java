package Client.ClientComponents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class LogoffClass{

    public boolean logoutUser(String login, String def, PrintStream ps, BufferedReader bufferedIn) throws IOException {

        String cmd = "quit" + " " + login + " " + def;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        } else {
            return false;
        }
    }

    public void closeServer(PrintStream ps){
        ps.println("close_server");
    }

}
