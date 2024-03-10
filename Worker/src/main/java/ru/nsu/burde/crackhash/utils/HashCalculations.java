package ru.nsu.burde.crackhash.utils;

import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;

public class HashCalculations {
    public static boolean hashMatches(String word, String hashed){
        try {
            byte[] bytesOfWord = word.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesOfHashedWord = md.digest(bytesOfWord);
            byte[] bytesOfHash = HexFormat.of().parseHex(hashed);
            return Arrays.equals(bytesOfHashedWord, bytesOfHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
