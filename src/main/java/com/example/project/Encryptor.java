package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {

    public static int determineColumns(int messageLen, int rows) {
        if (messageLen == 0) {
            return 1; 
        }
        int columns = messageLen / rows;
        if (messageLen % rows != 0) {
            columns++;
        }
        return columns;
    }

    public static String[][] generateEncryptArray(String message, int rows) {
        int columns = determineColumns(message.length(), rows);

        String[][] arr = new String[rows][columns];
        
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (index < message.length()) {
                    arr[r][c] = message.substring(index, index + 1);
                    index++;
                } else {
                    arr[r][c] = "=";  
                }
            }
        }
        return arr;
    }
    
    

    public static String encryptMessage(String message, int rows) {
        String[][] arr = generateEncryptArray(message, rows);
        int columns = arr[0].length;
        String encryptedMessage = "";
        for (int c = columns - 1; c >= 0; c--) {
            for (int r = 0; r < rows; r++) {
                encryptedMessage += arr[r][c];
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int messageLen = encryptedMessage.length();
        int columns = determineColumns(messageLen, rows);
        String[][] arr = new String[rows][columns];
        int index = 0;
        for (int c = columns - 1; c >= 0; c--) {
            for (int r = 0; r < rows; r++) {
                if (index < messageLen) {
                    arr[r][c] = encryptedMessage.substring(index, index + 1);
                    index++;
                } else {
                    arr[r][c] = "=";
                }
            }
        }
        String decryptedMessage = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!arr[r][c].equals("=")) {
                    decryptedMessage += arr[r][c];
                }
            }
        }
        return decryptedMessage;
    }
}
