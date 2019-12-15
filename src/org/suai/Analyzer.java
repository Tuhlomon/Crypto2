package org.suai;

import java.io.FileInputStream;

public class Analyzer {
    public static float calcCorrelation(String messageFile, String cipherFile) {
        byte[] pt = new byte[16];
        byte[] ct = new byte[16];
        float result = 0;
        long accum = 0;
        long size = 0;
        int tmp1 = 0;
        int tmp2 = 0;
        try {
            FileInputStream fis1 = new FileInputStream(messageFile);
            FileInputStream fis2 = new FileInputStream(cipherFile);
            while (fis2.read(ct, 0, 16) != -1) {
                fis1.read(pt, 0, 16);
                for (int i = 0; i < 16; i++){
                    tmp1 = pt[i];
                    tmp2 = ct[i];
                    for (int j = 0; j < 8; j++){
                        accum += (2*(tmp1 & 1) - 1) * (2*(tmp2 & 1) - 1);
                        tmp1 = tmp1 >> 1;
                        tmp2 = tmp2 >> 2;
                    }
                }
                size += 16;
            }
        } catch (Exception e) {
            System.out.println("Calculation if correlation error!" + e.getMessage());
        }
        return (float)accum/size;
    }

    public static float calcZeroToOneRatio(String cipherFile) {
        byte[] ct = new byte[16];
        long zeros = 0;
        long ones = 0;
        int tmp;
        try {
            FileInputStream fis2 = new FileInputStream(cipherFile);
            while (fis2.read(ct, 0, 16) != -1) {
                for (int i = 0; i < 16; i++){
                    tmp = ct[i];
                    for (int j = 0; j < 8; j++){
                        if ((tmp & 1) == 1) ones++;
                        else zeros++;
                        tmp = tmp >> 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Calculation zero-to-one ratio error!\n" + e.getMessage());
        }
        return (float)zeros/ones;
    }
}
