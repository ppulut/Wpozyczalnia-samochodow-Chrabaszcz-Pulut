package UserGui.calculateFunctions;

import SharedUserAdminClasses.DateInformation;

import java.text.ParseException;
import java.util.Calendar;

public class CalculateMethods {

   int finalPrice;
   String newDate;

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNewDate() {
        return newDate;
    }

    public void calculateDataMethod(String priceArg, String iloscArg, String firstDate){

        int price = Integer.parseInt(priceArg);
        int ilosc = Integer.parseInt(iloscArg);

        DateInformation dateInformation = new DateInformation();


        setFinalPrice(this.finalPrice = price * ilosc);
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(dateInformation.getPatternDate().parse(firstDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, ilosc);
        this.newDate = dateInformation.getPatternDate().format(c.getTime());
        setNewDate(this.newDate);
    }
}
