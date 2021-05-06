package UserGui.validateuser;

import Client.ClientWorker;
import SharedUserAdminClasses.DateInformation;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ValidateUserInterfaceClass {

    DateInformation dateInformation;

    public ValidateUserInterfaceClass(ClientWorker clientSocket){
        DateInformation dateInformation = new DateInformation(clientSocket);
        setDateInformation(dateInformation);
    }

    private void setDateInformation(DateInformation dateInformation) {
        this.dateInformation = dateInformation;
    }
    private DateInformation getDateInformation() {
        return dateInformation;
    }

    public Boolean validateMoney(String saldo, String lastCost){

        double saldoDouble = Double.parseDouble(saldo);
        double finalCostDouble = Double.parseDouble(lastCost);

        if(saldoDouble<finalCostDouble) {
            return false;
        }
        return true;
    }

    public Boolean validateDate(String firstDate) throws ParseException {

        Date actualDateD;
        Date inputDateD;
        String actualDateS;
        //Sprawdzanie czy w polu są tylko liczby
        try {
            inputDateD = getDateInformation().getPatternDate().parse(firstDate);
        } catch (ParseException pe) {
            return false;
        }

        //Pobieranie akutalnej daty w odpowiednim formacie w String
        actualDateS = getDateInformation().getPatternDate().format(getDateInformation().getActuallDate());

        //Ustawianie daty ze String
        actualDateD = getDateInformation().getPatternDate().parse(actualDateS);

        //Wyciecie lat z wprowadzonej daty
        int yearsFromData = Integer.parseInt(firstDate.substring(0, 4));
        //Wyciecie Miesicy z wprowadzonej daty
        int monthsFromdata = Integer.parseInt(firstDate.substring(5, 7));
        //Wyciecie Dni z wprowadzonej daty
        int daysFromData = Integer.parseInt(firstDate.substring(8, 10));

        //Różnica Dni między datami
        long diff = inputDateD.getTime() - actualDateD.getTime();
        long roznica2 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1);


        if (yearsFromData != 2021 || monthsFromdata > 12 || monthsFromdata <= 0 || inputDateD.compareTo(actualDateD) < 0 || roznica2 > 30) {
            return false;
        }

        if(monthsFromdata == 1 || monthsFromdata == 3 || monthsFromdata== 5 || monthsFromdata== 7 || monthsFromdata== 8 || monthsFromdata == 10 || monthsFromdata == 12)
        {
            if(daysFromData>31){
                return false;
            }

        }else if(monthsFromdata == 2){
            if(daysFromData>28){
                return false;
            }
        }else{
            if(daysFromData>30){
                return false;
            }
        }
        return true;
    }

    public Boolean validNumberDay(String checkValue){

        try {
            Integer.parseInt(checkValue);
            int validateNumbDay = Integer.parseInt(checkValue);
            if(validateNumbDay>30 || validateNumbDay == 0) {
                return false;
            }
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

}


