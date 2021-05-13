package UserGui.validateuser;

import Client.ClientWorker;
import SharedUserAdminClasses.DateInformation;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ValidateUserInterfaceClass {

    DateInformation dateInformation = new DateInformation();

    public ValidateUserInterfaceClass(ClientWorker clientSocket){
        DateInformation dateInformation = new DateInformation();
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


