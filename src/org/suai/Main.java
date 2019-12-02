package org.suai;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Twofish tf = new Twofish();
        System.out.print("Enter the key (minimal length is 16): ");
        Scanner scan = new Scanner(System.in);
        String k = scan.nextLine();
        String k2 = k.substring(0, k.length()/8*8 > 32 ? 32 : k.length()/8*8);
        try {
            byte[] key = k2.getBytes();
            if (key.length < 16) throw new Exception("The key is too small");
            tf.FileEncrypt("C:\\Users\\Tuhlomon\\Desktop\\crypto\\crypto2_input.jpg", "C:\\Users\\Tuhlomon\\Desktop\\crypto\\crypto2_encrypted.jpg", key);
            System.out.println("File was encrypted!");
            tf.FileDecrypt("C:\\Users\\Tuhlomon\\Desktop\\crypto\\crypto2_encrypted.jpg", "C:\\Users\\Tuhlomon\\Desktop\\crypto\\crypto2_decrypted.jpg", key);
            System.out.println("File was decrypted!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
