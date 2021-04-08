package SharedServerClientClasses;
import java.util.ArrayList;

public class ActLoginUser {

    ArrayList<String> users = new ArrayList<>();

    public boolean addUsers(String login) {
        System.out.println("Otrzymalem " +login);
        if(users.add(login)){
            return true;
        }
        return false;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

   public boolean removeUsers(String login){
        if(users.remove(login)){
            return true;
       }
       return false;
   }

   public boolean ifUsersExist(String login){
        if(users.contains(login)){
            return true;
        }
        return false;
    }
}
