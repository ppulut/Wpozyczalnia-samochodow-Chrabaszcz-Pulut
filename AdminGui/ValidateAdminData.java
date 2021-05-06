package AdminGui;

import Client.ClientWorker;
import SharedUserAdminClasses.DateInformation;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ValidateAdminData {

    DateInformation dateInformation;

    public ValidateAdminData(ClientWorker clientSocket){
        DateInformation dateInformation = new DateInformation(clientSocket);
        setDateInformation(dateInformation);
    }

    private void setDateInformation(DateInformation dateInformation) {
        this.dateInformation = dateInformation;
    }
    private DateInformation getDateInformation() {
        return dateInformation;
    }


    public boolean validateDate(String value) throws ParseException {


        Date actualDateD;
        Date inputDateD;
        String actualDateS;

        try {
            inputDateD = getDateInformation().getPatternDate().parse(value);
        } catch (ParseException pe) {
            return false;
        }

        //Wyciecie lat z wprowadzonej daty
        int yearsFromData = Integer.parseInt(value.substring(0, 4));
        //Wyciecie Miesicy z wprowadzonej daty
        int monthsFromdata = Integer.parseInt(value.substring(5, 7));
        //Wyciecie Dni z wprowadzonej daty
        int daysFromData = Integer.parseInt(value.substring(8, 10));

        //Pobieranie akutalnej daty w odpowiednim formacie w String
        actualDateS = getDateInformation().getPatternDate().format(getDateInformation().getActuallDate());

        //Ustawianie daty ze String
        actualDateD = getDateInformation().getPatternDate().parse(actualDateS);

        //Różnica Dni między datami
        long diff = inputDateD.getTime() - actualDateD.getTime();
        long roznica2 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1);


        if (yearsFromData != 2021 || monthsFromdata > 12 || monthsFromdata <= 0 || roznica2 > 1) {
            return false;
        }

        if (monthsFromdata == 1 || monthsFromdata == 3 || monthsFromdata == 5 || monthsFromdata == 7 || monthsFromdata == 8 || monthsFromdata == 10 || monthsFromdata == 12) {
            if (daysFromData > 31) {
                return false;
            }

        } else if (monthsFromdata == 2) {
            if (daysFromData > 28) {
                return false;
            }
        } else {
            if (daysFromData > 30) {
                return false;
            }
        }
        return true;
    }

    public boolean validateNotNull(String value){

        if(value.equalsIgnoreCase("") || value.length()>5){
            return false;
        }
        return  true;
    }

    public boolean validateNewCarData(String carName, String carModel, String carPrice, String carAviability) {

        String onlyNumbersPattern   = "[0-9]+";

        if (carName.equalsIgnoreCase("") || carModel.equalsIgnoreCase("") || carPrice.equalsIgnoreCase("") ||
                carAviability.equalsIgnoreCase("") || carName.length() > 100 || carModel.length() > 100 || carPrice.length() > 5
                || carAviability.length() > 1 || !Pattern.matches(onlyNumbersPattern,carPrice)){
            return false;
        } else if (carAviability.equals("T") || carAviability.equals("N")) {
            return true;
        }
        return false;
    }

    public boolean validateEditCarData(String idCar, String carName, String carModel, String carPrice, String carAviability) {

        String onlyNumbersPattern   = "[0-9]+";

        if (idCar.equalsIgnoreCase("") ||carName.equalsIgnoreCase("") || carModel.equalsIgnoreCase("") || carPrice.equalsIgnoreCase("") ||
                carAviability.equalsIgnoreCase("") || idCar.length() > 5 || carName.length() > 100 || carModel.length() > 100 || carPrice.length() > 5
                || carAviability.length() > 1 || !Pattern.matches(onlyNumbersPattern,idCar) || !Pattern.matches(onlyNumbersPattern,carPrice)) {
            return false;
        } else if (carAviability.equals("T") || carAviability.equals("N")) {
            return true;
        }
        return false;
    }


    public boolean validateNewDebtData(String debtUserId, String price, String date) {

        String onlyNumbersPattern   = "[0-9]+";

        if (debtUserId.equalsIgnoreCase("") ||price.equalsIgnoreCase("") || date.equalsIgnoreCase("") || debtUserId.length() > 5 || price.length() > 8 ||
                !Pattern.matches(onlyNumbersPattern,debtUserId) || !Pattern.matches(onlyNumbersPattern,price)) {
            return false;
        }
        return true;

    }

}


