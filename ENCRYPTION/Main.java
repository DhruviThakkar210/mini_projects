import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        // Generate a secret key for AES encryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Create a Cipher object for AES encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Convert plaintext to bytes and encrypt
        byte[] plaintext = "dhruvi".getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintext);

        // Print the ciphertext as a Base64-encoded string
        String ciphertextString = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println(ciphertextString);
    }
}
