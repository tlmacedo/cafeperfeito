package br.com.tlmacedo.cafeperfeito.service;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class ServiceCryptografia {
    public static final Integer DEFAULT_ITERATIONS = 120000;
    public static final String ALGORITHM = "pbkdf2_sha256";

    public static String encrypt(String senhaSimples) {
        return encode(senhaSimples);
    }

    public static boolean senhaValida(String senhaSimples, String senhaBD) {
        return checkPassword(senhaSimples, senhaBD);
    }

    public static String getEncodedHash(String password, String salt, int iterations) {
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could NOT retrieve PBKDF2WithHmacSHA256 algorithm");
            System.exit(1);
        }
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Charset.forName("UTF-8")), iterations, 256);
        SecretKey secret = null;
        try {
            secret = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            System.out.println("Could NOT generate secret key");
            e.printStackTrace();
        }

        byte[] rawHash = secret.getEncoded();
        byte[] hashBase64 = Base64.getEncoder().encode(rawHash);

        return String.format("%s$%d$%s$%s", ALGORITHM, iterations, salt, new String(hashBase64));
    }

    public static String encode(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return getEncodedHash(password, new String(salt), DEFAULT_ITERATIONS);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        String[] parts = hashedPassword.split("\\$");
        if (parts.length != 4)
            return false;
        Integer iterations = Integer.parseInt(parts[1]);
        String salt = parts[2];
        String hash = getEncodedHash(password, salt, iterations);
        return hash.equals(hashedPassword);
    }


}
