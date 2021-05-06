package UserGui.calculateFunctions;

import Client.ClientWorker;
import SharedUserAdminClasses.DateInformation;
import java.text.ParseException;
import java.util.Calendar;

public class CalculateMethods {

   int finalPrice;
   String newDate;
   DateInformation dateInformation;

    private void setDateInformation(DateInformation dateInformation) {
        this.dateInformation = dateInformation;
    }
    private DateInformation getDateInformation() {
        return dateInformation;
    }

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

    public CalculateMethods(ClientWorker clientSocket){
        DateInformation dateInformation = new DateInformation(clientSocket);
        setDateInformation(dateInformation);
    }

    public void calculateDataMethod(String priceArg, String iloscArg, String firstDate){

        int price = Integer.parseInt(priceArg);
        int ilosc = Integer.parseInt(iloscArg);

        setFinalPrice(this.finalPrice = price * ilosc);
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(getDateInformation().getPatternDate().parse(firstDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, ilosc);
        this.newDate = getDateInformation().getPatternDate().format(c.getTime());
        setNewDate(this.newDate);
    }
}
