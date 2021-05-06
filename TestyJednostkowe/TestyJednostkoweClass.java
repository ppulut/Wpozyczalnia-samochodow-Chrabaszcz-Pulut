package TestyJednostkowe;

import Client.ClientWorker;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestyJednostkoweClass {


    //Testuje polaczanie Clienta z serverem
    @Test
    public void connectionToServer(){
        ClientWorker clientWorker = new ClientWorker("localhost", 2137);
        assertTrue(clientWorker.connect());
    }

    //Testuje polaczenie Clienta z serverem oraz logowanie i wylogowywanie definitywne po stronie uzytkownika
    @Test
    public void loginAndLogoffByUser() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.login("user","UserTest","UserTest1"));
        assertTrue(clientWorker2.logoff("UserTest","def"));
    }

    //Testuje polaczenie Clienta z serverem oraz logowanie i wylogowywanie definitywne po stronie Admina
    @Test
    public void loginAndLogoffByAdmin() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.login("admin","AdminTest","AdminTest1"));
        assertTrue(clientWorker2.logoff("AdminTest","def"));
    }

    //Testuje rejestracje uzytkownika
    @Test
    public void registerUser() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.registerPerson("user","TestUserName","TestUserSurrname","TestUserLogin","TestUserPassword"));
    }

    //Testuje rejestracje administratora
    @Test
    public void registerAdmin() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.registerPerson("admin","TestAdminName","TestAdminSurrname","TestAdminLogin","TestAdminPassword"));
    }

    //Testuje Dodanie wypozyczenia
    @Test
    public void insertReservation() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.sendDataReservation("1","1","2020-05-02","2020-05-25","2500"));
    }

    //Testuje dodawanie pieniedzy
    @Test
    public void addMoney() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.addMoney("1","2500"));
    }

    //Testuje usuwanie Admina
    @Test
    public void deleteAdmin() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.deleteAdminSide("admin","2"));
    }

    //Testuje usuwanie Uzytkownika
    @Test
    public void deleteUser() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.deleteAdminSide("user","2"));
    }

    //Testuje Dodawanie Kar
    @Test
    public void deleteDebt() throws IOException {
        ClientWorker clientWorker2 = new ClientWorker("localhost", 2137);
        clientWorker2.connect();
        assertTrue(clientWorker2.addAdminSide("fee","1","1000","2020-10-12",null));
    }
}
