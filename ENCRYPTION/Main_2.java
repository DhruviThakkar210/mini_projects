import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main_2 {
    public static void main(String[] args) throws Exception {
        // Generate a secret key for AES encryption
        SecretKey secretKey = generateSecretKey();

        // Encrypt plaintext using AES with CBC mode and PKCS5 padding
        byte[] ciphertext = encrypt("dhruvi", secretKey);

        // Print the ciphertext as a Base64-encoded string
        String ciphertextString = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println("Ciphertext: " + ciphertextString);

        // Decrypt ciphertext using the same secret key and cipher
        String decryptedText = decrypt(ciphertext, secretKey);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static byte[] encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = cipher.getIV(); // get initialization vector from cipher object
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintextBytes);
        return ciphertext;
    }

    public static String decrypt(byte[] ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = cipher.getIV(); // get initialization vector from cipher object
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);

        byte[] plaintextBytes = cipher.doFinal(ciphertext);
        String plaintext = new String(plaintextBytes, StandardCharsets.UTF_8);
        return plaintext;
    }
}
