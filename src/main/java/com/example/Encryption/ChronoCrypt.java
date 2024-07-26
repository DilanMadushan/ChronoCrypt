package com.example.Encryption;

import java.util.ArrayList;

public class ChronoCrypt {

    private static final int KEY = 10;

    public String Encryption(String text) {
        char[] data = text.toCharArray();
        ArrayList<Character> encryptedData = new ArrayList<>();

        for (char ch : data) {
            ch += KEY;
            encryptedData.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : encryptedData) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public String Decryption(String text) {
        char[] data = text.toCharArray();
        ArrayList<Character> decryptedData = new ArrayList<>();

        for (char ch : data) {
            ch -= KEY;
            decryptedData.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : decryptedData) {
            sb.append(ch);
        }

        return sb.toString();
    }

}
