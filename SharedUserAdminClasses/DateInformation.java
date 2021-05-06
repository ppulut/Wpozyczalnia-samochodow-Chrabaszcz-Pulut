package SharedUserAdminClasses;

import Client.ClientWorker;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInformation {
    ClientWorker clientSocket;

    public DateInformation(ClientWorker clientSocket){
        this.clientSocket=clientSocket;
    }

    public Date getActuallDate(){
        Date data;
        data = new Date();
        return data;
    }

    public SimpleDateFormat getPatternDate(){
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf;
    }

}
