package com.example.obfuscation.obfuscation_example;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.util.Arrays;
import java.util.Objects;

public class EncryptDecryptRSA {

    public static final String ALGORITHM = "RSA";
    public static final String PRIVATE_KEY_PATH = "C:/keys/private.key";
    public static final String PUBLIC_KEY_PATH = "C:/keys/public.key";

    public static void generateKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            KeyPair keyPair = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEY_PATH);
            privateKeyFile.getParentFile().mkdirs(); // Ensure parent directory exists
            privateKeyFile.createNewFile();

            ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(keyPair.getPrivate());
            privateKeyOS.close();

            File publicKeyFile = new File(PUBLIC_KEY_PATH);
            publicKeyFile.getParentFile().mkdirs(); // Ensure parent directory exists
            publicKeyFile.createNewFile();

            ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(keyPair.getPublic());
            publicKeyOS.close();

        } catch (Exception e) {
            System.out.println("Exception occurred during key generation".concat(e.getMessage()));
        }
    }

    public static boolean checkIfKeysExist() {
        File privateKeyFile = new File(PRIVATE_KEY_PATH);
        File publicKeyFile = new File(PUBLIC_KEY_PATH);

        return privateKeyFile.exists() && publicKeyFile.exists();
    }

    public static byte[] encrypt(String plainText, PublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            System.out.println("Exception occurred during encryption".concat(e.getMessage()));
            return null;
        }
    }

    public static String decrypt(byte[] encryptedText, PrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedText = cipher.doFinal(encryptedText);
            return new String(decryptedText);
        } catch (Exception e) {
            System.out.println("Exception occurred during encryption".concat(e.getMessage()));
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            if (!checkIfKeysExist()) {
                generateKeys();
            }

            final String originalMessage = "Example message";
            ObjectInputStream inputStream;

            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_PATH));
            PublicKey publicKey = (PublicKey) inputStream.readObject();
            byte[] encryptedMessage = encrypt(originalMessage, publicKey);

            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_PATH));
            PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            String decryptedMessage = decrypt(encryptedMessage, privateKey);

            System.out.println("Original Message: ".concat(originalMessage));
            System.out.println("Encrypted Message: ".concat(Arrays.toString(encryptedMessage)));
            System.out.println("Decrypted Message: ".concat(Objects.requireNonNull(decryptedMessage)));

        } catch (Exception e) {
            System.out.println("Exception occurred in main method".concat(e.getMessage()));
        }
    }
}
