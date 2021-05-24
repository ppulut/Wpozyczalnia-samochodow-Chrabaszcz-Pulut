package UserGui.validateuser;

public class ValidateSaldoClass {

    public ValidateSaldoClass() {}

    public boolean validateNumberCard(String sNumberCard) {
        try {
            if (sNumberCard.length() != 16) {
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

}
