import java.util.Scanner;

public class Vigenere {
    public static final String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        while(true) {
            System.out.println("\n\n<<<===========================Vigenere Cipher===========================>>>");
            System.out.println("Enter the message to be encrypted / decrypted : ");
            String user = obj.nextLine();
            user = user.toUpperCase();
            System.out.println("Enter the key for encryption / decryption : ");
            String key = obj.nextLine();
            key = key.toUpperCase();

            //Checking if all characters of the strings are valid
            validity(user);
            validity(key);
            System.out.println("Valid Characters entered.");
            //Modifying key length according to Vigenere Rules
            key = keyLength(key, user);
            System.out.println("<==============================Output====================================>");
            System.out.println("Text entered is : \"" + user + "\" and length is : " + user.length());
            System.out.println("Key is          : \"" + key + "\" and length is : " + key.length());
            System.out.println("<==============================Function==================================>\n1)Encryption(Enter 1)\t2)Decryption(Enter 2)\t3)Exit(Any other key)");
            int choice = obj.nextInt();obj.nextLine();
            switch (choice) {
                case 1 -> {
                    String encrypt = encryption(key, user);
                    System.out.println("Encrypted form of the Message is : " + encrypt);
                    System.out.println("<============================Message Secured============================>");
                }
                case 2 -> {
                    String decrypt = decryption(key, user);
                    System.out.println("Decrypted form of the Message is : " + decrypt);
                    System.out.println("<============================Message Secured============================>");
                }
                default -> {
                    System.out.println("Program exiting .....");
                    obj.close();
                    return;
                }
            }

        }
    }

    public static void validity(String check) {
        char ch;
        int index;

        for (int i = 0; i < check.length(); i++) {
            ch = check.charAt(i);
            index = valid.indexOf(ch);
            if (index == -1) {
                System.out.println("\n" + ch + " is an Invalid character.\nPlease try again with correct input.");
                System.exit(0);
            }
        }
    }

    public static String keyLength(String key, String user) {
        int keyL = key.length();
        int userL = user.length();

        if (keyL < userL) {
            for (int i = 0; i < userL - keyL; i++) {

                if (i > keyL) {
                    key = key + key.charAt(i % keyL);
                } else {
                    key = key + key.charAt(i);
                }
            }
            return key;
        }

        else if (keyL > userL) {
            key = key.substring(0, userL);
            return key;
        }

        else {
            return key;
        }

    }
    public static String encryption(String key, String user){
        int index1,index2,totalIndex;
        String finalStr = "";
        char ch;
        for (int i = 0; i < key.length(); i++) {
            char ch1 = key.charAt(i);
            char ch2 = user.charAt(i);
            index1 = valid.indexOf(ch1);
            index2 = valid.indexOf(ch2);
            totalIndex = index1 + index2;
            if (totalIndex >= valid.length()) {
                totalIndex = totalIndex  % valid.length();
                ch = valid.charAt(totalIndex);
            }
            else {
                ch = valid.charAt(totalIndex);
            }
            finalStr = finalStr + ch;
        }
        return finalStr;

    }
    public static String decryption(String key, String user){
        int index1,index2,totalIndex;
        String finalStr = "";
        char ch;
        for (int i = 0; i < key.length(); i++) {
            char ch1 = key.charAt(i);
            char ch2 = user.charAt(i);
            index1 = valid.indexOf(ch1);
            index2 = valid.indexOf(ch2);
            totalIndex = index2 - index1;    //Key is subtracted from user input(not vice versa)
            if (totalIndex < 0) {
                totalIndex = totalIndex + valid.length();
                ch = valid.charAt(totalIndex);
            }
            else {
                ch = valid.charAt(totalIndex);
            }
            finalStr = finalStr + ch;
        }
        return finalStr;

    }


}
