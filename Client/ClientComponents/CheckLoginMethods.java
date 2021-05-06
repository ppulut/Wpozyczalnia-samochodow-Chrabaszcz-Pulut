package Client.ClientComponents;
import java.io.*;

public class CheckLoginMethods {

    public boolean checkUniqueLoginMethod(String type, String login, PrintStream ps, InputStream serverIn) throws IOException {

        String cmd = "check_unique_login" + " " + type + " " + login;
        ps.println(cmd);

        BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
        String response = bufferedIn.readLine();

        if(response.equalsIgnoreCase("ok")){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkActualLogin(PrintStream ps,InputStream serverIn, String login) throws IOException {
        String cmd = "actual_login" + " " + login;
        ps.println(cmd);

        BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
        String response = bufferedIn. readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public String getNumberOfLogin(PrintStream ps, BufferedReader bufferedIn) throws IOException {
        String cmd = "get_actual_login";
        ps.println(cmd);

        return bufferedIn.readLine();
    }

}
