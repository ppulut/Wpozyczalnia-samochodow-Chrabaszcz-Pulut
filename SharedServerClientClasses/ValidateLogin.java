package SharedServerClientClasses;

public class ValidateLogin {

    public boolean validateData(String login,String password){

        if(login.equals("") || password.equals("") || login.length()>21 || password.length()>21){
            return false;
        }else {
            return true;
        }
        }

}
