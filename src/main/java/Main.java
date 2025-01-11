import java.security.KeyException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int oneMinute = 32;
    private static int tenMinute = 305;
    private static int oneHour = 1811;

    public static void main(String[] args) throws KeyException {
        bruteforce(oneMinute);
        bruteforce(tenMinute);
        bruteforce(oneHour);
    }

    public static void bruteforce(int count) throws KeyException {
        String startPart = "Secret";
        String message = startPart + " message, key = %s";
        SimpleCipher cipher = new SimpleCipher();
        ArrayList<String> encodedMessages = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String key = String.format("k%s", (char) i);
            encodedMessages.add(cipher.encode(String.format(message, key), key));
        }

        long startTime = System.currentTimeMillis();

        for (String encodedMessage : encodedMessages) {
            cipher.brut(encodedMessage, startPart);
        }

        System.out.printf("Elapsed time: %d sec%n", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime));
    }
}