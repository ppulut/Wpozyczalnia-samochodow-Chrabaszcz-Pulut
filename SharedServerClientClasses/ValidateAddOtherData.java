package SharedServerClientClasses;

import java.util.regex.Pattern;

public class ValidateAddOtherData {

    public boolean validateNewCarData(String carName, String carModel, String carPrice, String carAviability) {

        String onlyNumbersPattern = "[0-9]+";

        if (carName.equalsIgnoreCase("") || carModel.equalsIgnoreCase("") || carPrice.equalsIgnoreCase("") ||
                carAviability.equalsIgnoreCase("") || carName.length() > 100 || carModel.length() > 100 || carPrice.length() > 5
                || carAviability.length() > 1 || !Pattern.matches(onlyNumbersPattern, carPrice)) {
            return false;
        } else if (carAviability.equals("T") || carAviability.equals("N")) {
            return true;
        }
        return false;
    }

    public boolean validateNewDebtData(String debtUserId, String price) {
        String onlyNumbersPattern = "[0-9]+";

        if (debtUserId.equalsIgnoreCase("") || price.equalsIgnoreCase("") || debtUserId.length() > 5 || price.length() > 8 ||
                !Pattern.matches(onlyNumbersPattern, debtUserId) || !Pattern.matches(onlyNumbersPattern, price)) {
            return false;
        }
        return true;
    }

    public boolean validePrice(String sPrice) {
        try {
            Double.parseDouble(sPrice);

            double checkValue = Double.parseDouble(sPrice);
            //Nie mozna posiadac wiecej pieniedzy na koncie niz 50000
            if (checkValue > 50000) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
