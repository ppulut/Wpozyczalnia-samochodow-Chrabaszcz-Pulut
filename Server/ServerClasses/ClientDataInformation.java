package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.Server;
import Server.ValidateServerData.ValidateAdminData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDataInformation extends ReturnedMessage {

    private final ConnectDataBase conn = new ConnectDataBase();
    private Server server;
    ValidateAdminData validateAdminData = new ValidateAdminData();
    ResultSet rs;

    public ClientDataInformation(Server server){
        this.server = server;
    }

    public ClientDataInformation(){}

    public String checkUniqueLogin(String[] tokens) throws SQLException {

        if (tokens.length == 3) {

            String type = tokens[1];
            String login = tokens[2];

            if (!validateAdminData.validateLogin(login)) {
                return super.errorStr;
            } else {

                String st;
                String st2;

                Statement stmt = conn.connect().createStatement(rs.TYPE_SCROLL_SENSITIVE,
                        rs.CONCUR_UPDATABLE);

                stmt.executeUpdate("USE wypozyczalnia");

                if (type.equalsIgnoreCase("user")) {
                    st = "select login from uzytkownicy WHERE login = '" + login + "'";
                    st2 = "select login from admin WHERE login = '" + login + "'";
                } else {
                    return super.errorStr;
                }

                rs = stmt.executeQuery(st);

                if (rs.next()) {
                    return super.errorStr;
                } else {
                    rs.beforeFirst();
                    rs = stmt.executeQuery(st2);
                    if (rs.next()) {
                        if(!super.checkClose(conn)){
                            return super.errorStr;
                        }
                        return super.errorStr;
                    } else {
                        if(!super.checkClose(conn)){
                            return super.errorStr;
                        }
                        return super.correctStr;
                    }
                }

            }
        }
        return super.errorStr;
    }

    //Metoda Sprawdzajca czy wpisany login podczas logowania jest już zalogowany
    public String handleActualLogin(String[] tokens) {
        if (tokens.length == 2) {
            ArrayList<String> userLogin = server.getUserLogin();
            String login = tokens[1];
            boolean check = userLogin.contains(login);

            if (check) {
                return super.correctStr;
            } else {
                return super.errorStr;
            }
        } else {
            return super.errorStr;
        }
    }

    //Metoda wyswietlajaca liczbe dostepny i niedostepnych samochodow
    public String handleNumberCars(String[] tokens) throws SQLException {
        if(tokens.length == 2) {
            String check = tokens[1];

            String sql ;
            int rows = 0;

            if (check.equalsIgnoreCase("dst")) {
                sql = "select * from spis_samochodow where dostepny = 'T'";
            }
           else if (check.equalsIgnoreCase("ndst")) {
                sql = "select * from spis_samochodow where dostepny = 'N'";
            }else{

                return super.errorStr;
            }


            Statement stmt = conn.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate("USE wypozyczalnia");

            rs = stmt.executeQuery(sql);

            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }
            if(!super.checkClose(conn)){
                return super.errorStr;
            }
            return super.correctStr + " " + rows;
        }else{
            return super.errorStr;
        }
    }

    public String getActualogin(){
        long size = server.getUserLoginSize();
        return super.correctStr + " " + size;
    }

    //Metoda obslugujaca odswiezanie okna przez klienta
    public String handleRefreshData(String[] tokens) throws SQLException {
        if (tokens.length == 2) {

            String userId = tokens[1];

            if(!validateAdminData.checkUserExist(userId) || !validateAdminData.checkIdSize(userId)){
                return super.errorStr;
            }else {

                Statement stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");

                String sql = "select sum(kary.kwota) as kwota,saldo from kary,uzytkownicy where  kary.klient_id = '" + userId + "' and uzytkownicy.id = kary.Klient_id and uzytkownicy.id = '" + userId + "'";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();

                if (rs.getString("kwota") == null) {
                    String saldoStr = rs.getString("saldo");
                    if(!super.checkClose(conn)){
                        return super.errorStr;
                    }
                    return super.correctStr + " " + "0" + " " + saldoStr;

                } else {
                    String saldoStr = rs.getString("saldo");
                    String kwotaStr = rs.getString("kwota");
                    if(!super.checkClose(conn)){
                        return super.errorStr;
                    }
                    return super.correctStr + " " + kwotaStr + " " + saldoStr;
                }
            }
        }
        return super.errorStr;
    }
}
