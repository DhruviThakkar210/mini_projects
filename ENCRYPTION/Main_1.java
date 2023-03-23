import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main_1 {
    public static void main(String[] args) throws Exception {
        // Generate a secret key for AES encryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // Create a Cipher object for AES encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Read in the plaintext file and encrypt it
        File plaintextFile = new File("input.txt");
        FileInputStream inputStream = new FileInputStream(plaintextFile);
        byte[] plaintextBytes = new byte[(int) plaintextFile.length()];
        inputStream.read(plaintextBytes);
        inputStream.close();

        byte[] ciphertext = cipher.doFinal(plaintextBytes);

        // Print the ciphertext as a Base64-encoded string
        String ciphertextString = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println(ciphertextString);

        // Save the encrypted ciphertext to a file
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        outputStream.write(ciphertext);
        outputStream.close();
    }
}
