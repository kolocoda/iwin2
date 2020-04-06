package com.ugarsoft.chyma.iwin.models;



/**
 * Created by Chyma on 5/12/2016.
 */
public class Month {

    public static int JAN = 1;
    public static int FEB = 2;
    public static int MAR = 3;
    public static int APR = 4;
    public static int MAY = 5;
    public static int JUN = 6;
    public static int JUL = 7;
    public static int AUG = 8;
    public static int SEP = 9;
    public static int OCT = 10;
    public static int NOV = 11;
    public static int DEC = 12;

    public static int[] getColdMonths1(){
        int[] months = new int[9];
        months[0] = MAR;
        months[1] = APR;
        months[2] = MAY;
        months[3] = JUN;
        months[4] = JUL;
        months[5] = AUG;
        months[6] = SEP;
        months[7] = OCT;
        months[8] = DEC;

        return months;
    }
    public static int[] getColdMonths2() {
        int[] months = new int[8];
        months[0] = JAN;
        months[1] = MAY;
        months[2] = NOV;
        months[3] = DEC;
        months[4] = JUN;
        months[5] = JUL;
        months[6] = AUG;
        months[7] = SEP;

        return months;
    }

    public static int[] getHotMonths2() {
        int[] months = new int[4];
        months[0] = FEB;
        months[1] = MAR;
        months[2] = APR;
        months[3] = OCT;

        return months;
    }

    public static int[] getHotMonths1() {
        int[] months = new int[3];
        months[0] = NOV;
        months[1] = JAN;
        months[2] = FEB;

        return months;
    }

}
