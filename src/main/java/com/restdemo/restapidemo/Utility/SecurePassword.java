package com.restdemo.restapidemo.Utility;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.restdemo.restapidemo.error.NoSuchAlgorithmException;

@Service
public class SecurePassword {

    // byte[] salt = getSalt();

    public String getSecurePassword(String password, byte[] saltValue)
            throws NoSuchAlgorithmException, java.security.NoSuchAlgorithmException {

        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(saltValue);
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    public byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}
