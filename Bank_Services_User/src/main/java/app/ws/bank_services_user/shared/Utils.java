package app.ws.bank_services_user.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();

    private final String ALPHANUMERIQUE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz";

    public String generatedStringId(int length){
        StringBuilder returnedValue = new StringBuilder();

        for (int i = 0; i < length; i++){
            returnedValue.append(ALPHANUMERIQUE.charAt(RANDOM.nextInt(ALPHANUMERIQUE.length())));
        }

        return new String(returnedValue);
    }
}
