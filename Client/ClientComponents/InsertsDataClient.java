package Client.ClientComponents;

import java.io.*;

public class InsertsDataClient{

    public boolean sendDataReservation(PrintStream ps, BufferedReader bufferedIn, String userId, String carId, String firstDate, String secondDate, String sFinalCost) throws IOException {

        String cmd = "insert_reservation"+" "+userId+" "+carId+" "+firstDate+" "+secondDate+" "+sFinalCost;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean addMoney(PrintStream ps, BufferedReader bufferedIn, String klientId, String sPrice) throws IOException {

        String cmd = "insert_saldo"+" "+klientId + " " + sPrice;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean payDebt(PrintStream ps, BufferedReader bufferedIn, String userId, String debt) throws IOException {

        String cmd = "pay_debt" + " " + userId + " " + debt;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean registerPerson(PrintStream ps, InputStream serverIn, String type, String userCreateName, String userSurrname, String userLogin, String userCreatePassword) throws IOException {
        String cmd = null;

        if(type.equals("user")){
            cmd = "insert_register"+" "+type+" "+userCreateName+" "+userSurrname+" "+userLogin+" "+userCreatePassword;
        }else if(type.equals("admin")){
            cmd = "insert_register"+" "+type+" "+userCreateName+" "+userSurrname+" "+userLogin+" "+userCreatePassword;
        }

        ps.println(cmd);
        BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean addAdminSide(PrintStream ps, BufferedReader bufferedIn, String type, String value1, String value2, String value3, String value4) throws IOException {

        String cmd = "insert_New_Data"+" "+type+" "+value1+" "+value2+" "+value3+" "+value4;
        ps.println(cmd);
        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean sendDataEditAdmin(PrintStream ps, BufferedReader bufferedIn, String type, String aId, String aName, String aSurname, String aLogin, String aPassword) throws IOException {

        String cmd = "edit_Admin"+ " " +type+ " "+aId+" "+aName+" "+aSurname+" "+aLogin+" "+aPassword;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteAdminSide(PrintStream ps, BufferedReader bufferedIn, String type, String aId) throws IOException {

        String cmd = "delete_Admin"+" "+type+" "+aId;
        ps.println(cmd);

        String response = bufferedIn.readLine();

        if ("ok".equalsIgnoreCase(response)) {
            return true;
        }else{
            return false;
        }
    }

    public String getNumberOfCars(PrintStream ps, BufferedReader bufferedIn, String type) throws IOException {
        String cmd = "Number_Of_Cars" + " " + type;
        ps.println(cmd);
        return bufferedIn.readLine();
    }
}
