package UserGui.validateuser;

public class ValidateUserInterfaceClass {

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


