package SharedUserAdminClasses;
import java.util.regex.Pattern;

public class ValidateRegisterData {

    public boolean validateClientData(String password, String login, String name, String surrname){
        String numbersPattern   = ".*[0-9].*";
        String lettersPattern = ".*[A-Z].*";
        String onlyLettersPattern = "[a-zA-Z]+";

        if (password.length() <= 3 || login.length() <= 3 || login.length()>20 || password.length()>20 || !Pattern.matches(onlyLettersPattern,name) || !Pattern.matches(onlyLettersPattern,surrname)|| name.length()>49 || surrname.length()>49) {
            return true;
        }else{
            if (password.matches(lettersPattern) && password.matches(numbersPattern)){
                return false;
            }else{
                return true;
            }
        }
    }

    public String changeClientData(String value){
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }
}
