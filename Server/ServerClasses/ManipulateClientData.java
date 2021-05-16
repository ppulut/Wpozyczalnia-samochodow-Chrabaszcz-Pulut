package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.ValidateServerData.ValidateAdminData;
import SharedServerClientClasses.ValidateAddOtherData;
import SharedServerClientClasses.ValidateDate;
import SharedServerClientClasses.ValidateRegisterData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class ManipulateClientData extends ReturnedMessage {

    private final ConnectDataBase conn = new ConnectDataBase();
    ValidateRegisterData validateRegisterData = new ValidateRegisterData();
    ValidateAdminData validateAdminData = new ValidateAdminData();
    ValidateAddOtherData validateAddOtherData = new ValidateAddOtherData();
    ValidateDate validateDate = new ValidateDate();

    //Metoda edytująca dane admina
    public String editAdmin(String[] tokens) throws SQLException, ParseException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if (tokens.length == 7) {
            String type = tokens[1];
            String value1 = tokens[2];
            String value2 = tokens[3];
            String value3 = tokens[4];
            String value4 = tokens[5];
            String value5 = tokens[6];

            String editValueStr;
            int rows;

            //Stworzenie polaczenia  z baza
            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (type.equalsIgnoreCase("admin")) {
                if (validateRegisterData.validateClientData(value5, value4, value2, value3) || !validateAdminData.checkAdminExist(value1) || !validateAdminData.checkIdSize(value1)) {
                    return super.errorStr;
                } else {
                    editValueStr = ("UPDATE admin SET Imie='" + value2 + "' ,Nazwisko='" + value3 + "',Login='" + value4 + "',Haslo='" + value5 + "' WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("car")) {
                if (!validateAddOtherData.validateNewCarData(value2, value3, value4, value5) || !validateAdminData.checkCarExist(value1) || !validateAdminData.checkIdSize(value1)) {
                    return super.errorStr;
                } else {
                    editValueStr = ("UPDATE spis_samochodow SET Marka='" + value2 + "' ,Model='" + value3 + "',Cena='" + value4 + "',Dostepny='" + value5 + "' WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("user")) {
                if (validateRegisterData.validateClientData(value5, value4, value2, value3) || !validateAdminData.checkUserExist(value1) || !validateAdminData.checkIdSize(value1)) {
                    return super.errorStr;
                } else {
                    editValueStr = ("UPDATE uzytkownicy SET Imie='" + value2 + "' ,Nazwisko='" + value3 + "',Login='" + value4 + "',Haslo='" + value5 + "' WHERE id='" + value1 + "'");
                }
            } else if (type.equalsIgnoreCase("debt")) {

                if (!validateAdminData.checkUserExist(value2) || !validateAdminData.checkDebtExist(value1)
                        || !validateAdminData.checkIdSize(value1) || !validateAdminData.checkIdSize(value2)
                        || !validateDate.validateDate("admin", value4) || !validateAddOtherData.validateNewDebtData(value2, value3)) {
                    return super.errorStr;
                } else {
                    editValueStr = ("UPDATE kary SET Klient_id='" + value2 + "' ,Kwota='" + value3 + "',Data='" + value4 + "' WHERE id='" + value1 + "'");
                }
            } else {
                return super.errorStr;
            }

            //Żadanie okreslonego widoku z bazy
            rows = stmt.executeUpdate(editValueStr);

            //Zamkniecie polaczen z bazą
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
}
