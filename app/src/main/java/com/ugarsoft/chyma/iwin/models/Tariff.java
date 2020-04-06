package com.ugarsoft.chyma.iwin.models;

/**
 * Created by Chyma on 5/12/2016.
 */
public class Tariff {

    public static Double None = 0.0;
    public static Double aedc = 24.3;
    public static Double bedcS= 24.08;
    public static Double bedcT = 24.45;
    public static Double ekedcS = 24.00;
    public static Double ekedcT = 25.79;
    public static Double eedc = 27.13;
    public static Double ibedc = 23.09;
    public static  Double ikedcS = 21.3;
    public static Double ikedcT = 21.8;
    public static Double jedc = 26.93;
    public static Double phedc = 24.91;
    public static  Double kaedcS = 26.37;
    public static  Double kaedcT = 28.05;
    public static  Double kedcA = 20.26;
    public static Double kedcB = 26.41;
    public static   Double yedcS = 23.25;
    public static  Double yedcT = 24.75;

    public static Double[] getColdDiscos(){
        Double[] stations = new Double[8];
        stations[1] = ekedcS;
        stations[2] = ekedcT;
        stations[3] = ikedcS;
        stations[4] = ikedcT;
        stations[5] = bedcS;
        stations[6] = bedcT;
        stations[7] = phedc;
        stations[0] = eedc;

        return  stations;

    }

    public static Double[] getHotDiscos(){
        Double[] stations = new Double[9];
        stations[0] = aedc;
        stations[2] = yedcS;
        stations[3] = yedcT;
        stations[4] = kedcA;
        stations[5] = kedcB;
        stations[6] = kaedcS;
        stations[7] = kaedcT;
        stations[0] = ibedc;
        stations[0] = jedc;

        return  stations;

    }
}
