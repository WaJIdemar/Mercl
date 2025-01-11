import java.security.KeyException;

public class SimpleCipher {

    private void validateKey(String key) throws KeyException {
        if (key.length() != 2) {
            throw new KeyException("The key must be 2 characters long");
        }
    }

    public String decode(String message, String key) throws KeyException {
        validateKey(key);
        StringBuilder result = new StringBuilder();
        int indexKey = 0;
        for (char symbol : message.toCharArray()) {
            result.append((char) ((symbol - key.charAt(indexKey)) % Character.MAX_VALUE));
            indexKey = (indexKey + 1) % key.length();
        }
        return result.toString();
    }

    public String encode(String message, String key) throws KeyException {
        validateKey(key);
        StringBuilder result = new StringBuilder();
        int indexKey = 0;
        for (char symbol : message.toCharArray()) {
            result.append((char) ((symbol + key.charAt(indexKey)) % Character.MAX_VALUE));
            indexKey = (indexKey + 1) % key.length();
        }
        return result.toString();
    }

    public String brut(String encodedMessage, String startPart) throws KeyException {
        for (int startChar1 = 0; startChar1 < Character.MAX_VALUE; startChar1++) {
            for (int startChar2 = 0; startChar2 < Character.MAX_VALUE; startChar2++) {
                //for (int startChar3 = 0; startChar3 < Character.MAX_VALUE; startChar3++) {
                    String supposedKey = String.valueOf(new char[]{(char) startChar1, (char) startChar2, /*(char) startChar3*/});
                    String decoded = decode(encodedMessage, supposedKey);
                    if (decoded.startsWith(startPart)) {
                        return decoded;
                    }
                //}
            }
        }
        throw new KeyException("Key not found");
    }
}
