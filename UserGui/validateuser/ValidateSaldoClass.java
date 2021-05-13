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

}
