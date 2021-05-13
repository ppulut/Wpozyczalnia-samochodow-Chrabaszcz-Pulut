package Server.ServerClasses;

import Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogoffClass {

    private final Server server;
    private final ServerSocket serverSocket;
    private final Socket clientSocket;
    private final DeteleClientData deteleClientData;

    public LogoffClass(Server server, ServerSocket serverSocket, Socket clientSocket, DeteleClientData deteleClientData){

        this.server = server;
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.deteleClientData = deteleClientData;
    }

    public void handleCloseServer() throws IOException {
        server.setWork(false);
        server.removeAllUserLogin();
        serverSocket.close();
    }

    public boolean handleLogoffClient(String[] tokens) throws IOException {
        String def = tokens[2];
        if (def.equalsIgnoreCase("undef")) {
            deteleClientData.removeActualLogin(tokens);
            return false;
        } else {
            deteleClientData.removeActualLogin(tokens);
            clientSocket.close();
            return true;
        }

    }

}
