package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.Server;
import Server.ValidateServerData.ValidateAdminData;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeteleClientData extends ReturnedMessage {

    PrintStream ps;
    ResultSet rs;
    private Server server;
    ValidateAdminData validateAdminData = new ValidateAdminData();
    private final ConnectDataBase conn = new ConnectDataBase();

    public DeteleClientData(PrintStream ps, Server server){
        this.ps = ps;
        this.server = server;
    }

    public DeteleClientData(){}

    //Metoda usuwająca  admina
    public String deleteAdmin(String[] tokens) throws SQLException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if(tokens.length == 3) {
            String type = tokens[1];
            String value1 = tokens[2];
            String deleteAdmin = null;
            String deleteAdmin2 = null;
            String idSamochodu = null;

            //Stworzenie polaczenia  z baza
            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            //Zmienna przechowujaca żadanie okreslonego widoku
            if (type.equalsIgnoreCase("admin")) {
                if (!validateAdminData.checkAdminExist(value1)) {
                    return super.errorStr;
                } else {
                    deleteAdmin = ("DELETE FROM admin WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("car")) {
                if (!validateAdminData.checkCarExist(value1)) {
                    return super.errorStr;
                } else {
                    deleteAdmin = ("DELETE FROM spis_samochodow WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("user")) {
                if (!validateAdminData.checkUserExist(value1)) {
                    return super.errorStr;
                } else {
                    deleteAdmin2 = ("DELETE FROM kary WHERE Klient_id='" + value1 + "'");
                    deleteAdmin = ("DELETE FROM uzytkownicy WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("rent")) {

                if (!validateAdminData.checkRentExist(value1)) {
                    return super.errorStr;
                } else {
                    deleteAdmin = ("DELETE FROM wypozyczenia_samochodow_przez_klientow WHERE id='" + value1 + "'");

                    String update = "select * from wypozyczenia_samochodow_przez_klientow WHERE id='" + value1 + "'";
                    rs = stmt.executeQuery(update);

                    if (rs.next()) {
                        idSamochodu = rs.getString("id_samochodu");
                    }

                    if (idSamochodu != null) {
                        String updateCar = "Update spis_samochodow SET dostepny = 'T' WHERE id = '" + idSamochodu + "'";
                        stmt.executeUpdate(updateCar);
                    }
                }

            } else if (type.equalsIgnoreCase("fee")) {
                if (!validateAdminData.checkDebtExist(value1)) {
                    return super.errorStr;
                } else {
                    deleteAdmin = ("DELETE FROM kary WHERE id='" + value1 + "'");
                }
            }

            if (deleteAdmin2 != null) {
                stmt.executeUpdate(deleteAdmin2);
            }
            //Żadanie okreslonego widoku z bazy
            int rows = stmt.executeUpdate(deleteAdmin);


            if (!super.checkClose(conn)) {
                return super.errorStr;
            }
            //Obsluga bledow do zadanego widoku z bazy
            if (rows > 0) {
                return super.correctStr;
            } else {
                return super.errorStr;
            }
        }else{
            return super.errorStr;
        }
    }

    public void removeActualLogin(String[] tokens) {
        if (tokens.length == 3) {
            String login = tokens[1];
            server.removeUserLogin(login);
            ps.println(super.correctStr);
        } else {
            ps.println(super.errorStr);
        }

    }
}
