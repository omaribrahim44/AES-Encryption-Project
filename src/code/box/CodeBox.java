package code.box;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
public class CodeBox {
    public static void main(String[] args) throws Exception{
   String plainText = "CYBER SECURITY";
        byte[] plainBytes = plainText.getBytes();

        // Generate a secret key
        byte[] keyBytes = new byte[24];
        Key key = new SecretKeySpec(keyBytes, "AES");

        // Encrypt one block
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(plainBytes);
        String cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        System.out.println("Cipher text: " + cipherText);

        // Decrypt the cipher text
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(cipherBytes);
        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted text: " + decryptedText);

        // Measure the time it takes to encrypt one block
        long startTime = System.nanoTime();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipher.doFinal(plainBytes);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time to encrypt one block: " + TimeUnit.NANOSECONDS.toMillis(elapsedTime) + " ms");

        // Measure the time it takes to encrypt two blocks
        startTime = System.nanoTime();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipher.doFinal(plainBytes);
        cipher.doFinal(plainBytes);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Time to encrypt two blocks: " + TimeUnit.NANOSECONDS.toMillis(elapsedTime) + " ms");
    }
    }  
