package SharedServerClientClasses;

import SharedUserAdminClasses.DateInformation;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class ValidateDate {

    DateInformation dateInformation = new DateInformation();

    public Boolean validateDate(String type,String firstDate) throws ParseException {
        Date actualDateD;
        Date inputDateD;
        String actualDateS;

        if(firstDate.equalsIgnoreCase("")){
            return false;
        }
        //Sprawdzanie czy w polu są tylko liczby
        try {
            inputDateD = dateInformation.getPatternDate().parse(firstDate);
        } catch (ParseException pe) {
            return false;
        }
        //Pobieranie akutalnej daty w odpowiednim formacie w String
        actualDateS = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        //Ustawianie daty ze String
        actualDateD = dateInformation.getPatternDate().parse(actualDateS);

        //Wyciecie lat z wprowadzonej daty
        int yearsFromData = Integer.parseInt(firstDate.substring(0, 4));
        //Wyciecie Miesicy z wprowadzonej daty
        int monthsFromdata = Integer.parseInt(firstDate.substring(5, 7));
        //Wyciecie Dni z wprowadzonej daty
        int daysFromData = Integer.parseInt(firstDate.substring(8, 10));

        //Różnica Dni między datami
        long diff = inputDateD.getTime() - actualDateD.getTime();
        long roznica2 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1);

        if(type.equalsIgnoreCase("user")) {

            if (yearsFromData != 2021 || monthsFromdata > 12 || monthsFromdata <= 0 || inputDateD.compareTo(actualDateD) < 0 || roznica2 > 30) {
                return false;
            }
        }else if(type.equalsIgnoreCase("admin")){
            if (yearsFromData != 2021 || monthsFromdata > 12 || monthsFromdata <= 0 || roznica2 > 1) {
                return false;
            }
        }else{
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
}


