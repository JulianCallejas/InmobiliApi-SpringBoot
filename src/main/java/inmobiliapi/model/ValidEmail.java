
package inmobiliapi.model;

import java.util.regex.Pattern;

public class ValidEmail {
    
        public static boolean validEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
    
}
