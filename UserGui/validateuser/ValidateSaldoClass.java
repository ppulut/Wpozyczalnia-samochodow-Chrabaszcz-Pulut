package UserGui.validateuser;
import Client.ClientWorker;

public class ValidateSaldoClass {
    ClientWorker clientSocket;

    public ValidateSaldoClass(ClientWorker clientSocket) {
        this.clientSocket = clientSocket;
    }

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

    public boolean validePrice(String sPrice) {
        try {
            Integer.parseInt(sPrice);

            int moreThan = Integer.parseInt(sPrice);

            if(moreThan>50000) {
                return false;
            }
            return true;
        }

        catch (NumberFormatException e) {
            return false;
        }
    }

}
