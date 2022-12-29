package com.restdemo.restapidemo.Utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

import com.restdemo.restapidemo.error.NoSuchAlgorithmException;

@Service
public class PasswordEncryption {

    public byte[] getSHA(String input) throws NoSuchAlgorithmException, java.security.NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public String toHexString(byte[] newPassword) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, newPassword);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
