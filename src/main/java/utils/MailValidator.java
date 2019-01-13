package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator {

    public boolean emailValidator(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find() && matcher.group().equals(string)) {
            return true;
        }
        else
            return false;
    }


}

