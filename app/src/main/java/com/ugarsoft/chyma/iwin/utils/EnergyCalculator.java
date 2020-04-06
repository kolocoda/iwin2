package com.ugarsoft.chyma.iwin.utils;

import android.util.Log;

import com.ugarsoft.chyma.iwin.models.AppUtilization;
import com.ugarsoft.chyma.iwin.models.Bill;
import com.ugarsoft.chyma.iwin.models.Month;
import com.ugarsoft.chyma.iwin.models.Tariff;

import java.text.DecimalFormat;

/**
 * Created by Chyma on 5/12/2016.
 */
public class EnergyCalculator {

    public static String calculateTokenUnits(String amount, String tariff) {
        Double amt = Double.parseDouble(amount);
        Double tar = Double.parseDouble(tariff);

        Double units = (amt - 0.05 * amt) / tar;

        return Double.toString(units);
    }

    public static String calculateBillperMonth(Bill bill) {
        Double monthBill = Double.parseDouble(totalKwh(bill)) * bill.getCompanyTariff();
        return String.valueOf(twoDecPlaces(monthBill));
    }

    public static String unitsDuration(Bill bill, String amount) {
        Double d = (Double.parseDouble(calculateBillperMonth(bill).replace(",","")) / 730);
        Double d2 = (Double.parseDouble(amount) / d);
        Double days = d2 / 24;

        return String.valueOf(twoDecPlaces(days));
    }

    public static String twoDecPlaces(Double d) {
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        return String.valueOf(df.format(d));
    }

    public static Double getTotalHours(Bill bill) {
        return bill.getHoursPerDay() * 30.0;
    }

    public static String calculatePercentage(Bill bill) {
        AppUtilization app;
        Double kwh1, kwh2, kwh3, kwh4, kwh5, kwh6, kwh7, kwh8,
                kwh9, kwh11, kwh10, kwh12, kwh13, kwh14, kwh15, kwh16,
                kwh17, kwh18, kwh19, kwh20, kwh21, kwh22, kwh23, kwh24,
                kwh25, kwh26, kwh27, kwh28, kwh29, kwh30, kwh31, kwh32, kwh33;

        if (bill.getNoOfOccupants() > 1) {
            app = calculateMoreThanOneOccupant(bill);
        } else if (bill.getNoOfOccupants() == 1) {
            app = calculateOneOccupant(bill);
        } else {
            app = new AppUtilization();
        }

        Double hours = getTotalHours(bill);
        //calculating the total kilowatts
        if (hours > 486) {
            kwh1 = ((hours * bill.getIncandescentLights() * 60 * app.getIncandescentUtilization()) / 1000);
        } else if (hours < 486) {
            kwh1 = (((hours * 0.001369) * hours * bill.getIncandescentLights() * 60 * app.getIncandescentUtilization()) / 1000);
        } else {
            kwh1 = 0.0;
        }
        //app2
        if (hours > 486) {
            kwh2 = (hours * bill.getEnergyLights()* 10 * app.getEnergyUtilization()) / 1000;
        } else {
            kwh2 = ((hours * 0.001369) * hours * bill.getEnergyLights()* 10 * app.getEnergyUtilization()) / 1000;
        }

        //app3
        if (hours > 486) {
            kwh3 = (hours * bill.getSecurityLights()* 200 * app.getSecurityUtilization()) / 1000;
        } else {
            kwh3 = ((hours * 0.001369) * hours * bill.getSecurityLights()* 200 * app.getSecurityUtilization()) / 1000;
        }

        //app4
        if (hours > 486) {
            kwh4 = (hours * bill.getFluorescentLights()* 40 * app.getFluorescentUtilization()) / 1000;
        } else {
            kwh4 = ((hours * 0.001369) * hours * bill.getFluorescentLights()* 40 * app.getFluorescentUtilization()) / 1000;
        }

        //app5
        if (hours > 486) {
            kwh5 = (hours * bill.getFans() * 50 * app.getFanUtilization()) / 1000;
        } else {
            kwh5 = ((hours * 0.001369) * hours * bill.getFans() * 50 * app.getFanUtilization()) / 1000;
        }

        //app6
        if (hours > 486) {
            kwh6 = (hours * bill.getHumidifier() * 60 * app.getHumidifierUtilization()) / 1000;
        } else {
            kwh6 = ((hours * 0.001369) * hours * bill.getHumidifier() * 60 * app.getHumidifierUtilization()) / 1000;
        }

        //app7
        kwh7 = (hours * bill.getFridge()* 200 * app.getRefrigeratorUtilization()) / 1000;


        //app8
        kwh8 = (hours * bill.getFreezer() * 250 * app.getFreezerUtilization()) / 1000;

        //app9
        if (hours > 486) {
            kwh9 = (hours * bill.getAcOneHp() * 800 * app.getAcOneUtilization()) / 1000;
        } else {
            kwh9 = ((hours * 0.001369) * hours * bill.getAcOneHp() * 800 * app.getAcOneUtilization()) / 1000;
        }

        //app10
        if (hours > 486) {
            kwh10 = (hours * bill.getAcOneHalfHp() * 1100 * app.getAcHalfUtilization()) / 1000;
        } else {
            kwh10 = ((hours * 0.001369) * hours * bill.getAcOneHalfHp() * 1100 * app.getAcHalfUtilization()) / 1000;
        }

        //app11
        if (hours > 486) {
            kwh11 = (hours * bill.getAcTwoHp() * 1500  * app.getAcTwoUtilization()) / 1000;
        } else {
            kwh11 = ((hours * 0.001369) * hours * bill.getAcTwoHp() * 1500 * app.getAcTwoUtilization()) / 1000;
        }

        //I got tired of refactoring copy an paste here so lets do it the easy way

        int app12 = bill.getIron() * 1200;
        int app13 = bill.getToaster() * 1000;
        int app14 = bill.getMicrowave() * 1200;
        int app15 = bill.getWaterHeater() * 1200;
        int app16 = bill.getElectricStove() * 1000;
        int app17 = bill.getWashingMachine() * 500;
        int app18 = bill.getPumpMotor() * 740;
        int app19 = bill.getBlender() * 200;
        int app20 = bill.getHomeTheatre() * 40;
        int app21 = bill.getPlasmaTv() * 200;
        int app22 = bill.getLcdTv() * 80;
        int app23 = bill.getLedTv() * 60;
        int app24 = bill.getPrinter() * 50;
        int app25 = bill.getHairDryer()* 1000;
        int app26 = bill.getVacuumCleaner() * 600;
        int app27 = bill.getGameConsole() * 100;
        int app28 = bill.getPhoneCharger() * 5;
        int app29 = bill.getCoffeeMaker() * 80;
        int app30 = bill.getWaterDispenser() * 150;
        int app31 = bill.getFishAquarium() * 20;
        int app32 = bill.getElectricKettle() * 1200;
        int app33 = bill.getDeepFrier() * 1200;

        Double aquariumUtilization = app.getAquariumUtilization();
        Double dispenserUtilization = app.getDispenserUtilization();
        Double homeTheatreUtilization = app.getHomeTheatreUtilization();
        Double plasmaUtilization = app.getPlasmaUtilization();
        Double ledUtilization = app.getLedUtilization();
        Double lcdUtilization = app.getLcdUtilization();
        Double gameUtilization = app.getGameUtilization();
        Double chargerUtilization = app.getChargerUtilization();

        Double toasterUtilization = app.getToasterUtilization();
        Double cleanerUtilization = app.getCleanerUtilization();
        Double deepfrierUtilization = app.getDeepfrierUtilization();
        Double ironUtilization = app.getIronUtilization();
        Double microwaveUtilization = app.getMicrowaveUtilization();
        Double heaterUtilization = app.getHeaterUtilization();
        Double pumpUtilization = app.getPumpUtilization();
        Double kettleUtilization = app.getKettleUtilization();
        Double coffeemakerUtilization = app.getCoffeemakerUtilization();
        Double stoveUtilization = app.getStoveUtilization();
        Double blenderUtilization = app.getBlenderUtilization();
        Double printUtilization = app.getPrintUtilization();
        Double dryerUtilization = app.getDryerUtilization();


        //app12
        if (hours > 486) {
            kwh12 = (hours * app12 * ironUtilization) / 1000;
        } else {
            kwh12 = ((hours * 0.001369) * hours * app12 * ironUtilization) / 1000;
        }

        //app13
        if (hours > 486) {
            kwh13 = (hours * app13 * toasterUtilization) / 1000;
        } else {
            kwh13 = ((hours * 0.001369) * hours * app13 * toasterUtilization) / 1000;
        }

        //app14
        if (hours > 486) {
            kwh14 = (hours * app14 * microwaveUtilization) / 1000;
        } else {
            kwh14 = ((hours * 0.001369) * hours * app14 * microwaveUtilization) / 1000;
        }

        //app15
        if (hours > 486) {
            kwh15 = (hours * app15 * heaterUtilization) / 1000;
        } else {
            kwh15 = ((hours * 0.001369) * hours * app15 * heaterUtilization) / 1000;
        }

        //app16
        if (hours > 486) {
            kwh16 = (hours * app16 * stoveUtilization) / 1000;
        } else {
            kwh16 = ((hours * 0.001369) * hours * app16 * stoveUtilization) / 1000;
        }

        //app17
        if (hours > 486) {
            kwh17 = (hours * app17 * microwaveUtilization) / 1000;
        } else {
            kwh17 = ((hours * 0.001369) * hours * app17 * microwaveUtilization) / 1000;
        }

        //app18
        if (hours > 486) {
            kwh18 = (hours * app18 * pumpUtilization) / 1000;
        } else {
            kwh18 = ((hours * 0.001369) * hours * app18 * pumpUtilization) / 1000;
        }

        //app19
        if (hours > 486) {
            kwh19 = (hours * app19 * blenderUtilization) / 1000;
        } else {
            kwh19 = ((hours * 0.001369) * hours * app19 * blenderUtilization) / 1000;
        }

        //app20
        if (hours > 486) {
            kwh20 = (hours * app20 * homeTheatreUtilization) / 1000;
        } else {
            kwh20 = ((hours * 0.001369) * hours * app20 * homeTheatreUtilization) / 1000;
        }


        //app21
        if (hours > 486) {
            kwh21 = (hours * app21 * plasmaUtilization) / 1000;
        } else {
            kwh21 = ((hours * 0.001369) * hours * app21 * plasmaUtilization) / 1000;
        }

        //app22
        if (hours > 486) {
            kwh22 = (hours * app22 * lcdUtilization) / 1000;
        } else {
            kwh22 = ((hours * 0.001369) * hours * app22 * lcdUtilization) / 1000;
        }

        //app23
        if (hours > 486) {
            kwh23 = (hours * app23 * ledUtilization) / 1000;
        } else {
            kwh23 = ((hours * 0.001369) * hours * app23 * ledUtilization) / 1000;
        }

        //app24
        if (hours > 486) {
            kwh24 = (hours * app24 * printUtilization) / 1000;
        } else {
            kwh24 = ((hours * 0.001369) * hours * app24 * printUtilization) / 1000;
        }

        //app25
        if (hours > 486) {
            kwh25 = (hours * app25 * dryerUtilization) / 1000;
        } else {
            kwh25 = ((hours * 0.001369) * hours * app25 * dryerUtilization) / 1000;
        }

        //app26
        if (hours > 486) {
            kwh26 = (hours * app26 * cleanerUtilization) / 1000;
        } else {
            kwh26 = ((hours * 0.001369) * hours * app26 * cleanerUtilization) / 1000;
        }

        //app27
        if (hours > 486) {
            kwh27 = (hours * app27 * gameUtilization) / 1000;
        } else {
            kwh27 = ((hours * 0.001369) * hours * app27 * gameUtilization) / 1000;
        }

        //app28
        if (hours > 486) {
            kwh28 = (hours * app28 * chargerUtilization) / 1000;
        } else {
            kwh28 = ((hours * 0.001369) * hours * app28 * chargerUtilization) / 1000;
        }

        //app29
        if (hours > 486) {
            kwh29 = (hours * app29 * coffeemakerUtilization) / 1000;
        } else {
            kwh29 = ((hours * 0.001369) * hours * app29 * coffeemakerUtilization) / 1000;
        }

        //app30
        //if (hours>486) {
        kwh30 = (hours * app30 * dispenserUtilization) / 1000;
            /*else{
                kwh30 = ((hours * 0.001369) * hours * app30 * dispenserUtilization)/1000; }*/

        //app31
        if (hours > 486) {
            kwh31 = (hours * app31 * aquariumUtilization) / 1000;
        } else {
            kwh31 = ((hours * 0.001369) * hours * app31 * aquariumUtilization) / 1000;
        }

        //app32
        if (hours > 486) {
            kwh32 = (hours * app32 * kettleUtilization) / 1000;
        } else {
            kwh32 = ((hours * 0.001369) * hours * app32 * kettleUtilization) / 1000;
        }

        //app33
        if (hours > 486) {
            kwh33 = (hours * app33 * deepfrierUtilization) / 1000;
        } else {
            kwh33 = ((hours * 0.001369) * hours * app33 * deepfrierUtilization) / 1000;
        }

        Double kwhTotal = (kwh1 + kwh2 + kwh3 + kwh4 + kwh5 + kwh6 + kwh7
                + kwh8 + kwh9 + kwh11 + kwh10 + kwh12 + kwh13 + kwh14 + kwh15
                + kwh16 + kwh17 + kwh18 + kwh19 + kwh20 + kwh21 + kwh22 + kwh23
                + kwh24 + kwh25 + kwh26 + kwh27 + kwh28 + kwh29 + kwh30 + kwh31 + kwh32 + kwh33);

        Log.i("Hours","total: " + hours);
        Log.i("Total KWH", "kwh1: " + kwh1);
        Log.i("Total KWH", "kwh2: " + kwh2);
        Log.i("Total KWH", "kwh3: " + kwh3);
        Log.i("Total KWH", "kwh4: " + kwh4);
        Log.i("Total KWH", "kwh5: " + kwh5);
        Log.i("Total KWH", "kwh6: " + kwh6);
        Log.i("Total KWH", "kwh7: " + kwh7);
        Log.i("Total KWH", "kwh8: " + kwh8);
        Log.i("Total KWH", "kwh9: " + kwh9);
        Log.i("Total KWH", "kwh10: " + kwh10);


        StringBuilder sb = new StringBuilder();
        if (kwh1 > 0) {
            Double per1 = ((kwh1 / kwhTotal) * 100);
            sb.append("Incadescent Light contributes " + twoDecPlaces(per1) + "%");
        }
        if (kwh2 > 0) {
            Double per2 = ((kwh2 / kwhTotal) * 100);
            String per2out = per2.toString();
            sb.append("\n Energy Light contributes " + twoDecPlaces(per2) + "%");
        }

        if (kwh3 > 0) {
            Double per3 = ((kwh3 / kwhTotal) * 100);
            String per3out = per3.toString();
            sb.append("\n Security Light contributes " + twoDecPlaces(per3) + "%");
        }
        if (kwh4 > 0) {
            Double per4 = ((kwh4 / kwhTotal) * 100);
            String per4out = per4.toString();
            sb.append("\n Fluorescent  contributes " + twoDecPlaces(per4) + "%");
        }

        if (kwh5 > 0) {
            Double per5 = ((kwh5 / kwhTotal) * 100);
            String per5out = per5.toString();
            sb.append("\n Fan contributes " + twoDecPlaces(per5) + "%");
        }

        if (kwh6 > 0) {
            Double per6 = ((kwh6 / kwhTotal) * 100);
            String per6out = per6.toString();
            sb.append("\n Humidifier contributes " + twoDecPlaces(per6) + "%");
        }

        if (kwh7 > 0) {
            Double per7 = ((kwh7 / kwhTotal) * 100);
            String per7out = per7.toString();
            sb.append("\n Refrigerator contributes " + twoDecPlaces(per7) + "%");
        }

        if (kwh8 > 0) {
            Double per8 = ((kwh8 / kwhTotal) * 100);
            String per8out = per8.toString();
            sb.append("\n Freezer contributes " + twoDecPlaces(per8) + "%");
        }

        if (kwh9 > 0) {
            Double per9 = ((kwh9 / kwhTotal) * 100);
            String per9out = per9.toString();
            sb.append("\n 1 hp AC contributes " + twoDecPlaces(per9) + "%");
        }

        if (kwh10 > 0) {
            Double per10 = ((kwh10 / kwhTotal) * 100);
            String per10out = per10.toString();
            sb.append("\n 1.5 hp AC contributes " + twoDecPlaces(per10) + "%");
        }

        if (kwh11 > 0) {
            Double per11 = ((kwh11 / kwhTotal) * 100);
            String per11out = per11.toString();
            sb.append("\n 2 hp AC contributes " + twoDecPlaces(per11) + "%");
        }

        if (kwh12 > 0) {
            Double per12 = ((kwh12 / kwhTotal) * 100);
            String per12out = per12.toString();
            sb.append("\n Electric iron contributes " + twoDecPlaces(per12) + "%");
        }


        if (kwh13 > 0) {
            Double per13 = ((kwh13 / kwhTotal) * 100);
            String per13out = per13.toString();
            sb.append("\n Toaster contributes " + twoDecPlaces(per13) + "%");
        }

        if (kwh14 > 0) {
            Double per14 = ((kwh14 / kwhTotal) * 100);
            String per14out = per14.toString();
            sb.append("\n Microwave oven contributes " + twoDecPlaces(per14) + "%");
        }

        if (kwh15 > 0) {
            Double per15 = ((kwh15 / kwhTotal) * 100);
            String per15out = per15.toString();
            sb.append("\n Water heater contributes " + twoDecPlaces(per15) + "%");
        }


        if (kwh16 > 0) {
            Double per16 = ((kwh16 / kwhTotal) * 100);
            String per16out = per16.toString();
            sb.append("\n Electric stove contributes " + twoDecPlaces(per16) + "%");
        }

        if (kwh17 > 0) {
            Double per17 = ((kwh17 / kwhTotal) * 100);
            String per17out = per17.toString();
            sb.append("\n Washing Machine contributes " + twoDecPlaces(per17) + "%");
        }


        if (kwh18 > 0) {
            Double per18 = ((kwh18 / kwhTotal) * 100);
            String per18out = per18.toString();
            sb.append("\n Pump motor contributes " + twoDecPlaces(per18) + "%");
        }


        if (kwh19 > 0) {
            Double per19 = ((kwh19 / kwhTotal) * 100);
            String per19out = per19.toString(3);
            sb.append("\n Blender contributes " + twoDecPlaces(per19) + "%");
        }


        if (kwh20 > 0) {
            Double per20 = ((kwh20 / kwhTotal) * 100);
            String per20out = per20.toString();
            sb.append("\n Home theatre contributes " + twoDecPlaces(per20) + "%");
        }


        if (kwh21 > 0) {
            Double per21 = ((kwh21 / kwhTotal) * 100);
            String per21out = per21.toString();
            sb.append("\n Plasma TV contributes " + twoDecPlaces(per21) + "%");
        }


        if (kwh22 > 0) {
            Double per22 = ((kwh22 / kwhTotal) * 100);
            String per22out = per22.toString();
            sb.append("\n LCD TV contributes " + twoDecPlaces(per22) + "%");
        }


        if (kwh23 > 0) {
            Double per23 = ((kwh23 / kwhTotal) * 100);
            String per23out = per23.toString();
            sb.append("\n LED TV contributes " + twoDecPlaces(per23) + "%");
        }


        if (kwh24 > 0) {
            Double per24 = ((kwh24 / kwhTotal) * 100);
            String per24out = per24.toString();
            sb.append("\n Printer contributes " + twoDecPlaces(per24) + "%");
        }


        if (kwh25 > 0) {
            Double per25 = ((kwh25 / kwhTotal) * 100);
            String per25out = per25.toString();
            sb.append("\n Hair dryer contributes " + twoDecPlaces(per25) + "%");
        }


        if (kwh26 > 0) {
            Double per26 = ((kwh26 / kwhTotal) * 100);
            String per26out = per26.toString();
            sb.append("\n Vacuum cleaner contributes " + twoDecPlaces(per26) + "%");
        }


        if (kwh27 > 0) {
            Double per27 = ((kwh27 / kwhTotal) * 100);
            String per27out = per27.toString();
            sb.append("\n Game console contributes " + twoDecPlaces(per27) + "%");
        }

        if (kwh28 > 0) {
            Double per28 = ((kwh28 / kwhTotal) * 100);
            String per28out = per28.toString();
            sb.append("\n Phone charger contributes " + twoDecPlaces(per28) + "%");
        }


        if (kwh29 > 0) {
            Double per29 = ((kwh29 / kwhTotal) * 100);
            String per29out = per29.toString();
            sb.append("\n Coffee maker contributes " + twoDecPlaces(per29) + "%");
        }


        if (kwh30 > 0) {
            Double per30 = ((kwh30 / kwhTotal) * 100);
            String per30out = per30.toString();
            sb.append("\n Water dispenser contributes " + twoDecPlaces(per30) + "%");
        }


        if (kwh31 > 0) {
            Double per31 = ((kwh31 / kwhTotal) * 100);
            String per31out = per31.toString();
            sb.append("\n Aquarium contributes " + twoDecPlaces(per31) + "%");
        }

        if (kwh32 > 0) {
            Double per32 = ((kwh32 / kwhTotal) * 100);
            String per32out = per32.toString();
            sb.append("\n Electric Kettle contributes " + twoDecPlaces(per32) + "%");
        }

        if (kwh33 > 0) {
            Double per33 = ((kwh33 / kwhTotal) * 100);
            String per33out = per33.toString();
            sb.append("\n Deep frier contributes " + twoDecPlaces(per33) + "%");
        }
        return sb.toString();
    }

    public static String totalKwh(Bill bill) {
        AppUtilization app;
        Double kwh1, kwh2, kwh3, kwh4, kwh5, kwh6, kwh7, kwh8,
                kwh9, kwh11, kwh10, kwh12, kwh13, kwh14, kwh15, kwh16,
                kwh17, kwh18, kwh19, kwh20, kwh21, kwh22, kwh23, kwh24,
                kwh25, kwh26, kwh27, kwh28, kwh29, kwh30, kwh31, kwh32, kwh33;

        if (bill.getNoOfOccupants() > 1) {
            app = calculateMoreThanOneOccupant(bill);
        } else if (bill.getNoOfOccupants() == 1) {
            app = calculateOneOccupant(bill);
        } else {
            app = new AppUtilization();
        }

        Double hours = getTotalHours(bill);
        //calculating the total kilowatts
        if (hours > 486) {
            kwh1 = ((hours * bill.getIncandescentLights() * 60 * app.getIncandescentUtilization()) / 1000);
        } else if (hours < 486) {
            kwh1 = (((hours * 0.001369) * hours * bill.getIncandescentLights() * 60 * app.getIncandescentUtilization()) / 1000);
        } else {
            kwh1 = 0.0;
        }
        //app2
        if (hours > 486) {
            kwh2 = (hours * bill.getEnergyLights()* 10 * app.getEnergyUtilization()) / 1000;
        } else {
            kwh2 = ((hours * 0.001369) * hours * bill.getEnergyLights()* 10 * app.getEnergyUtilization()) / 1000;
        }

        //app3
        if (hours > 486) {
            kwh3 = (hours * bill.getSecurityLights()* 200 * app.getSecurityUtilization()) / 1000;
        } else {
            kwh3 = ((hours * 0.001369) * hours * bill.getSecurityLights()* 200 * app.getSecurityUtilization()) / 1000;
        }

        //app4
        if (hours > 486) {
            kwh4 = (hours * bill.getFluorescentLights()* 40 * app.getFluorescentUtilization()) / 1000;
        } else {
            kwh4 = ((hours * 0.001369) * hours * bill.getFluorescentLights()* 40 * app.getFluorescentUtilization()) / 1000;
        }

        //app5
        if (hours > 486) {
            kwh5 = (hours * bill.getFans() * 50 * app.getFanUtilization()) / 1000;
        } else {
            kwh5 = ((hours * 0.001369) * hours * bill.getFans() * 50 * app.getFanUtilization()) / 1000;
        }

        //app6
        if (hours > 486) {
            kwh6 = (hours * bill.getHumidifier() * 60 * app.getHumidifierUtilization()) / 1000;
        } else {
            kwh6 = ((hours * 0.001369) * hours * bill.getHumidifier() * 60 * app.getHumidifierUtilization()) / 1000;
        }

        //app7
        kwh7 = (hours * bill.getFridge()* 200 * app.getRefrigeratorUtilization()) / 1000;


        //app8
        kwh8 = (hours * bill.getFreezer() * 250 * app.getFreezerUtilization()) / 1000;

        //app9
        if (hours > 486) {
            kwh9 = (hours * bill.getAcOneHp() * 800 * app.getAcOneUtilization()) / 1000;
        } else {
            kwh9 = ((hours * 0.001369) * hours * bill.getAcOneHp() * 800 * app.getAcOneUtilization()) / 1000;
        }

        //app10
        if (hours > 486) {
            kwh10 = (hours * bill.getAcOneHalfHp() * 1100 * app.getAcHalfUtilization()) / 1000;
        } else {
            kwh10 = ((hours * 0.001369) * hours * bill.getAcOneHalfHp() * 1100 * app.getAcHalfUtilization()) / 1000;
        }

        //app11
        if (hours > 486) {
            kwh11 = (hours * bill.getAcTwoHp() * 1500  * app.getAcTwoUtilization()) / 1000;
        } else {
            kwh11 = ((hours * 0.001369) * hours * bill.getAcTwoHp() * 1500 * app.getAcTwoUtilization()) / 1000;
        }

        //I got tired of refactoring copy an paste here so lets do it the easy way

        int app12 = bill.getIron() * 1200;
        int app13 = bill.getToaster() * 1000;
        int app14 = bill.getMicrowave() * 1200;
        int app15 = bill.getWaterHeater() * 1200;
        int app16 = bill.getElectricStove() * 1000;
        int app17 = bill.getWashingMachine() * 500;
        int app18 = bill.getPumpMotor() * 740;
        int app19 = bill.getBlender() * 200;
        int app20 = bill.getHomeTheatre() * 40;
        int app21 = bill.getPlasmaTv() * 200;
        int app22 = bill.getLcdTv() * 80;
        int app23 = bill.getLedTv() * 60;
        int app24 = bill.getPrinter() * 50;
        int app25 = bill.getHairDryer()* 1000;
        int app26 = bill.getVacuumCleaner() * 600;
        int app27 = bill.getGameConsole() * 100;
        int app28 = bill.getPhoneCharger() * 5;
        int app29 = bill.getCoffeeMaker() * 80;
        int app30 = bill.getWaterDispenser() * 150;
        int app31 = bill.getFishAquarium() * 20;
        int app32 = bill.getElectricKettle() * 1200;
        int app33 = bill.getDeepFrier() * 1200;

        Double aquariumUtilization = app.getAquariumUtilization();
        Double dispenserUtilization = app.getDispenserUtilization();
        Double homeTheatreUtilization = app.getHomeTheatreUtilization();
        Double plasmaUtilization = app.getPlasmaUtilization();
        Double ledUtilization = app.getLedUtilization();
        Double lcdUtilization = app.getLcdUtilization();
        Double gameUtilization = app.getGameUtilization();
        Double chargerUtilization = app.getChargerUtilization();

        Double toasterUtilization = app.getToasterUtilization();
        Double cleanerUtilization = app.getCleanerUtilization();
        Double deepfrierUtilization = app.getDeepfrierUtilization();
        Double ironUtilization = app.getIronUtilization();
        Double microwaveUtilization = app.getMicrowaveUtilization();
        Double heaterUtilization = app.getHeaterUtilization();
        Double pumpUtilization = app.getPumpUtilization();
        Double kettleUtilization = app.getKettleUtilization();
        Double coffeemakerUtilization = app.getCoffeemakerUtilization();
        Double stoveUtilization = app.getStoveUtilization();
        Double blenderUtilization = app.getBlenderUtilization();
        Double printUtilization = app.getPrintUtilization();
        Double dryerUtilization = app.getDryerUtilization();


        //app12
        if (hours > 486) {
            kwh12 = (hours * app12 * ironUtilization) / 1000;
        } else {
            kwh12 = ((hours * 0.001369) * hours * app12 * ironUtilization) / 1000;
        }

        //app13
        if (hours > 486) {
            kwh13 = (hours * app13 * toasterUtilization) / 1000;
        } else {
            kwh13 = ((hours * 0.001369) * hours * app13 * toasterUtilization) / 1000;
        }

        //app14
        if (hours > 486) {
            kwh14 = (hours * app14 * microwaveUtilization) / 1000;
        } else {
            kwh14 = ((hours * 0.001369) * hours * app14 * microwaveUtilization) / 1000;
        }

        //app15
        if (hours > 486) {
            kwh15 = (hours * app15 * heaterUtilization) / 1000;
        } else {
            kwh15 = ((hours * 0.001369) * hours * app15 * heaterUtilization) / 1000;
        }

        //app16
        if (hours > 486) {
            kwh16 = (hours * app16 * stoveUtilization) / 1000;
        } else {
            kwh16 = ((hours * 0.001369) * hours * app16 * stoveUtilization) / 1000;
        }

        //app17
        if (hours > 486) {
            kwh17 = (hours * app17 * microwaveUtilization) / 1000;
        } else {
            kwh17 = ((hours * 0.001369) * hours * app17 * microwaveUtilization) / 1000;
        }

        //app18
        if (hours > 486) {
            kwh18 = (hours * app18 * pumpUtilization) / 1000;
        } else {
            kwh18 = ((hours * 0.001369) * hours * app18 * pumpUtilization) / 1000;
        }

        //app19
        if (hours > 486) {
            kwh19 = (hours * app19 * blenderUtilization) / 1000;
        } else {
            kwh19 = ((hours * 0.001369) * hours * app19 * blenderUtilization) / 1000;
        }

        //app20
        if (hours > 486) {
            kwh20 = (hours * app20 * homeTheatreUtilization) / 1000;
        } else {
            kwh20 = ((hours * 0.001369) * hours * app20 * homeTheatreUtilization) / 1000;
        }


        //app21
        if (hours > 486) {
            kwh21 = (hours * app21 * plasmaUtilization) / 1000;
        } else {
            kwh21 = ((hours * 0.001369) * hours * app21 * plasmaUtilization) / 1000;
        }

        //app22
        if (hours > 486) {
            kwh22 = (hours * app22 * lcdUtilization) / 1000;
        } else {
            kwh22 = ((hours * 0.001369) * hours * app22 * lcdUtilization) / 1000;
        }

        //app23
        if (hours > 486) {
            kwh23 = (hours * app23 * ledUtilization) / 1000;
        } else {
            kwh23 = ((hours * 0.001369) * hours * app23 * ledUtilization) / 1000;
        }

        //app24
        if (hours > 486) {
            kwh24 = (hours * app24 * printUtilization) / 1000;
        } else {
            kwh24 = ((hours * 0.001369) * hours * app24 * printUtilization) / 1000;
        }

        //app25
        if (hours > 486) {
            kwh25 = (hours * app25 * dryerUtilization) / 1000;
        } else {
            kwh25 = ((hours * 0.001369) * hours * app25 * dryerUtilization) / 1000;
        }

        //app26
        if (hours > 486) {
            kwh26 = (hours * app26 * cleanerUtilization) / 1000;
        } else {
            kwh26 = ((hours * 0.001369) * hours * app26 * cleanerUtilization) / 1000;
        }

        //app27
        if (hours > 486) {
            kwh27 = (hours * app27 * gameUtilization) / 1000;
        } else {
            kwh27 = ((hours * 0.001369) * hours * app27 * gameUtilization) / 1000;
        }

        //app28
        if (hours > 486) {
            kwh28 = (hours * app28 * chargerUtilization) / 1000;
        } else {
            kwh28 = ((hours * 0.001369) * hours * app28 * chargerUtilization) / 1000;
        }

        //app29
        if (hours > 486) {
            kwh29 = (hours * app29 * coffeemakerUtilization) / 1000;
        } else {
            kwh29 = ((hours * 0.001369) * hours * app29 * coffeemakerUtilization) / 1000;
        }

        //app30
        //if (hours>486) {
        kwh30 = (hours * app30 * dispenserUtilization) / 1000;
			/*else{
				kwh30 = ((hours * 0.001369) * hours * app30 * dispenserUtilization)/1000; }*/

        //app31
        if (hours > 486) {
            kwh31 = (hours * app31 * aquariumUtilization) / 1000;
        } else {
            kwh31 = ((hours * 0.001369) * hours * app31 * aquariumUtilization) / 1000;
        }

        //app32
        if (hours > 486) {
            kwh32 = (hours * app32 * kettleUtilization) / 1000;
        } else {
            kwh32 = ((hours * 0.001369) * hours * app32 * kettleUtilization) / 1000;
        }

        //app33
        if (hours > 486) {
            kwh33 = (hours * app33 * deepfrierUtilization) / 1000;
        } else {
            kwh33 = ((hours * 0.001369) * hours * app33 * deepfrierUtilization) / 1000;
        }

        Double kwhTotal = (kwh1 + kwh2 + kwh3 + kwh4 + kwh5 + kwh6 + kwh7
                + kwh8 + kwh9 + kwh11 + kwh10 + kwh12 + kwh13 + kwh14 + kwh15
                + kwh16 + kwh17 + kwh18 + kwh19 + kwh20 + kwh21 + kwh22 + kwh23
                + kwh24 + kwh25 + kwh26 + kwh27 + kwh28 + kwh29 + kwh30 + kwh31 + kwh32 + kwh33);

        return Double.toString(kwhTotal);
    }

    private static AppUtilization calculateMoreThanOneOccupant(Bill bill) {
        boolean coldPeriod1 = Boolean.FALSE;
        boolean coldDisco = Boolean.FALSE;
        boolean coldPeriod2 = Boolean.FALSE;
        boolean hotDisco = Boolean.FALSE;
        boolean hotPeriod1 = Boolean.FALSE;
        boolean hotPeriod2 = Boolean.FALSE;

        Double fanUtilization;
        Double acOneUtilization;
        Double acHalfUtilization;
        Double acTwoUtilization;

        if (bill.getMonth() == Month.MAR || bill.getMonth() == Month.APR ||
                bill.getMonth() == Month.MAY || bill.getMonth() == Month.JUN ||
                bill.getMonth() == Month.JUL || bill.getMonth() == Month.AUG ||
                bill.getMonth() == Month.SEP || bill.getMonth() == Month.OCT ||
                bill.getMonth() == Month.DEC) {
            coldPeriod1 = Boolean.TRUE;
        }


        if (bill.getMonth() == Month.NOV || bill.getMonth() == Month.DEC ||
                bill.getMonth() == Month.JAN || bill.getMonth() == Month.JUN ||
                bill.getMonth() == Month.JUL || bill.getMonth() == Month.AUG ||
                bill.getMonth() == Month.SEP || bill.getMonth() == Month.MAY) {
            coldPeriod2 = Boolean.TRUE;
        }

        if (bill.getMonth() == Month.MAR || bill.getMonth() == Month.APR ||
                bill.getMonth() == Month.FEB || bill.getMonth() == Month.OCT) {
            hotPeriod2 = Boolean.TRUE;
        }

        if (bill.getMonth() == Month.NOV || bill.getMonth() == Month.JAN
                || bill.getMonth() == Month.FEB) {
            hotPeriod1 = Boolean.TRUE;
        }

        if (bill.getCompanyTariff().equals(Tariff.ekedcS) || bill.getCompanyTariff().equals(Tariff.ekedcT) ||
                bill.getCompanyTariff().equals(Tariff.ikedcS) || bill.getCompanyTariff().equals(Tariff.ikedcT) ||
                bill.getCompanyTariff().equals(Tariff.bedcS) || bill.getCompanyTariff().equals(Tariff.bedcT) ||
                bill.getCompanyTariff().equals(Tariff.phedc) || bill.getCompanyTariff().equals(Tariff.eedc)) {
            coldDisco = Boolean.TRUE;

        }

        if (bill.getCompanyTariff().equals(Tariff.aedc) || bill.getCompanyTariff().equals(Tariff.yedcS) ||
                bill.getCompanyTariff().equals(Tariff.yedcT) || bill.getCompanyTariff().equals(Tariff.kedcA) ||
                bill.getCompanyTariff().equals(Tariff.kedcB) || bill.getCompanyTariff().equals(Tariff.kaedcS) ||
                bill.getCompanyTariff().equals(Tariff.kaedcT) || bill.getCompanyTariff().equals(Tariff.ibedc) ||
                bill.getCompanyTariff().equals(Tariff.jedc)) {
            hotDisco = Boolean.TRUE;
        }

        if (coldPeriod1 && coldDisco) {
            fanUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acOneUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acHalfUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acTwoUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
        } else if (hotPeriod1 && coldDisco) {
            fanUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acOneUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acHalfUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acTwoUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
        } else if (coldPeriod2 && hotDisco) {
            fanUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acOneUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acHalfUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acTwoUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
        } else if (hotPeriod2 && hotDisco) {
            fanUtilization = (0.05556 * bill.getNoOfOccupants() * 0.5);
            acOneUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acHalfUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
            acTwoUtilization = (0.1666 * bill.getNoOfOccupants() * 0.5);
        } else {
            fanUtilization = 0.0;
            acOneUtilization = 0.0;
            acHalfUtilization = 0.0;
            acTwoUtilization = 0.0;
        }
        Double incandescentUtilization = (0.3 * bill.getNoOfOccupants() * 0.5);
        Double energyUtilization = (0.3 * bill.getNoOfOccupants() * 0.5);
        Double securityUtilization = (0.5 * bill.getNoOfOccupants());
        Double fluorescentUtilization = (0.3 * bill.getNoOfOccupants() * 0.5);
        Double refrigeratorUtilization = 1.0;
        Double freezerUtilization = 1.0;
        Double aquariumUtilization = 1.0;
        Double dispenserUtilization = 1.0;
        Double humidifierUtilization = (0.05556 * bill.getNoOfOccupants());
        Double homeTheatreUtilization = (0.30556 * bill.getNoOfOccupants());
        Double plasmaUtilization = (0.30556 * 1);
        Double ledUtilization = (0.30556 * 1);
        Double lcdUtilization = (0.30556 * 1);
        Double gameUtilization = (0.16667 * bill.getNoOfOccupants());
        Double chargerUtilization = (0.16667 * bill.getNoOfOccupants());
        Double toasterUtilization = (0.00277 * bill.getNoOfOccupants());
        Double cleanerUtilization = (0.00277 * 1);
        Double deepfrierUtilization = (0.00277 * 1);
        Double ironUtilization = (0.0111 * bill.getNoOfOccupants());
        Double microwaveUtilization = (0.020833 * bill.getNoOfOccupants() * 0.5);
        Double heaterUtilization = (0.08333 * bill.getNoOfOccupants() * 0.5);
        Double pumpUtilization = (0.08333 * bill.getNoOfOccupants());
        Double kettleUtilization = (0.08333 * bill.getNoOfOccupants() * 0.5);
        Double coffeemakerUtilization = (0.08333 * bill.getNoOfOccupants());
        Double stoveUtilization = (0.015278 * bill.getNoOfOccupants());
        Double blenderUtilization = (0.006941 * bill.getNoOfOccupants());
        Double printUtilization = (0.25 * bill.getNoOfOccupants() * 0.5);
        Double dryerUtilization = (0.0069 * bill.getNoOfOccupants());

        AppUtilization app = new AppUtilization();
        app.setAcHalfUtilization(acHalfUtilization);
        app.setAcOneUtilization(acOneUtilization);
        app.setAcTwoUtilization(acTwoUtilization);
        app.setAquariumUtilization(aquariumUtilization);
        app.setBlenderUtilization(blenderUtilization);
        app.setChargerUtilization(chargerUtilization);
        app.setCleanerUtilization(cleanerUtilization);
        app.setCoffeemakerUtilization(coffeemakerUtilization);
        app.setDeepfrierUtilization(deepfrierUtilization);
        app.setDispenserUtilization(dispenserUtilization);
        app.setDryerUtilization(dryerUtilization);
        app.setEnergyUtilization(energyUtilization);
        app.setFanUtilization(fanUtilization);
        app.setFluorescentUtilization(fluorescentUtilization);
        app.setFreezerUtilization(freezerUtilization);
        app.setGameUtilization(gameUtilization);
        app.setHeaterUtilization(heaterUtilization);
        app.setHomeTheatreUtilization(homeTheatreUtilization);
        app.setHumidifierUtilization(humidifierUtilization);
        app.setIncandescentUtilization(incandescentUtilization);
        app.setIronUtilization(ironUtilization);
        app.setKettleUtilization(kettleUtilization);
        app.setLcdUtilization(lcdUtilization);
        app.setLedUtilization(ledUtilization);
        app.setMicrowaveUtilization(microwaveUtilization);
        app.setPlasmaUtilization(plasmaUtilization);
        app.setPrintUtilization(printUtilization);
        app.setPumpUtilization(pumpUtilization);
        app.setRefrigeratorUtilization(refrigeratorUtilization);
        app.setSecurityUtilization(securityUtilization);
        app.setStoveUtilization(stoveUtilization);
        app.setToasterUtilization(toasterUtilization);

        return app;
    }

    private static AppUtilization calculateOneOccupant(Bill bill) {
        boolean coldPeriod1 = Boolean.FALSE;
        boolean coldDisco = Boolean.FALSE;
        boolean coldPeriod2 = Boolean.FALSE;
        boolean hotDisco = Boolean.FALSE;
        boolean hotPeriod1 = Boolean.FALSE;
        boolean hotPeriod2 = Boolean.FALSE;

        Double fanUtilization;
        Double acOneUtilization;
        Double acHalfUtilization;
        Double acTwoUtilization;


        if (bill.getMonth() == Month.MAR || bill.getMonth() == Month.APR ||
                bill.getMonth() == Month.MAY || bill.getMonth() == Month.JUN ||
                bill.getMonth() == Month.JUL || bill.getMonth() == Month.AUG ||
                bill.getMonth() == Month.SEP || bill.getMonth() == Month.OCT || bill.getMonth() == Month.DEC) {
            coldPeriod1 = Boolean.TRUE;
        }


        if (bill.getMonth() == Month.NOV || bill.getMonth() == Month.DEC ||
                bill.getMonth() == Month.JAN || bill.getMonth() == Month.JUN ||
                bill.getMonth() == Month.JUL || bill.getMonth() == Month.AUG ||
                bill.getMonth() == Month.SEP || bill.getMonth() == Month.MAY) {
            coldPeriod2 = Boolean.TRUE;
        }

        if (bill.getMonth() == Month.MAR || bill.getMonth() == Month.APR ||
                bill.getMonth() == Month.FEB || bill.getMonth() == Month.OCT) {
            hotPeriod1 = Boolean.TRUE;
        }

        if (bill.getMonth() == Month.NOV || bill.getMonth() == Month.JAN
                || bill.getMonth() == Month.FEB) {
            hotPeriod2 = Boolean.TRUE;
        }

        if (bill.getCompanyTariff().equals(Tariff.ekedcS) || bill.getCompanyTariff().equals(Tariff.ekedcT) ||
                bill.getCompanyTariff().equals(Tariff.ikedcS) || bill.getCompanyTariff().equals(Tariff.ikedcT) ||
                bill.getCompanyTariff().equals(Tariff.bedcS) || bill.getCompanyTariff().equals(Tariff.bedcT) ||
                bill.getCompanyTariff().equals(Tariff.phedc) || bill.getCompanyTariff().equals(Tariff.eedc)) {
            coldDisco = Boolean.TRUE;

        }

        if (bill.getCompanyTariff().equals(Tariff.aedc) || bill.getCompanyTariff().equals(Tariff.yedcS) ||
                bill.getCompanyTariff().equals(Tariff.yedcT) || bill.getCompanyTariff().equals(Tariff.kedcA) ||
                bill.getCompanyTariff().equals(Tariff.kedcB) || bill.getCompanyTariff().equals(Tariff.kaedcS) ||
                bill.getCompanyTariff().equals(Tariff.kaedcT) || bill.getCompanyTariff().equals(Tariff.ibedc) ||
                bill.getCompanyTariff().equals(Tariff.jedc)) {
            hotDisco = Boolean.TRUE;
        }

        if (coldPeriod1 && coldDisco) {
            fanUtilization = (0.1666 * bill.getNoOfOccupants());
            acOneUtilization = (0.05556 * bill.getNoOfOccupants());
            acHalfUtilization = (0.05556 * bill.getNoOfOccupants());
            acTwoUtilization = (0.05556 * bill.getNoOfOccupants());
        } else if (hotPeriod1 && coldDisco) {
            fanUtilization = (0.05556 * bill.getNoOfOccupants());
            acOneUtilization = (0.1666 * bill.getNoOfOccupants());
            acHalfUtilization = (0.1666 * bill.getNoOfOccupants());
            acTwoUtilization = (0.1666 * bill.getNoOfOccupants());
        } else if (coldPeriod2 && hotDisco) {
            fanUtilization = (0.1666 * bill.getNoOfOccupants());
            acOneUtilization = (0.05556 * bill.getNoOfOccupants());
            acHalfUtilization = (0.05556 * bill.getNoOfOccupants());
            acTwoUtilization = (0.05556 * bill.getNoOfOccupants());
        } else if (hotPeriod2 && hotDisco) {
            fanUtilization = (0.05556 * bill.getNoOfOccupants());
            acOneUtilization = (0.1666 * bill.getNoOfOccupants());
            acHalfUtilization = (0.1666 * bill.getNoOfOccupants());
            acTwoUtilization = (0.1666 * bill.getNoOfOccupants());
        } else {
            fanUtilization = 0.0;
            acOneUtilization = 0.0;
            acHalfUtilization = 0.0;
            acTwoUtilization = 0.0;
        }
        Double incandescentUtilization = (0.3 * bill.getNoOfOccupants());
        Double energyUtilization = (0.3 * bill.getNoOfOccupants());
        Double securityUtilization = (0.5 * bill.getNoOfOccupants());
        Double fluorescentUtilization = (0.3 * bill.getNoOfOccupants());
        Double refrigeratorUtilization = 1.0;
        Double freezerUtilization = 1.0;
        Double aquariumUtilization = 1.0;
        Double dispenserUtilization = 1.0;
        Double humidifierUtilization = (0.05556 * bill.getNoOfOccupants());
        Double homeTheatreUtilization = (0.30556 * bill.getNoOfOccupants());
        Double plasmaUtilization = (0.30556 * 1);
        Double ledUtilization = (0.30556 * 1);
        Double lcdUtilization = (0.30556 * 1);
        Double gameUtilization = (0.16667 * bill.getNoOfOccupants());
        Double chargerUtilization = (0.16667 * bill.getNoOfOccupants());
        Double toasterUtilization = (0.00277 * bill.getNoOfOccupants());
        Double cleanerUtilization = (0.00277 * 1);
        Double deepfrierUtilization = (0.00277 * 1);
        Double ironUtilization = (0.0111 * bill.getNoOfOccupants());
        Double microwaveUtilization = (0.020833 * bill.getNoOfOccupants());
        Double heaterUtilization = (0.08333 * bill.getNoOfOccupants());
        Double pumpUtilization = (0.08333 * bill.getNoOfOccupants());
        Double kettleUtilization = (0.08333 * bill.getNoOfOccupants());
        Double coffeemakerUtilization = (0.08333 * bill.getNoOfOccupants());
        Double stoveUtilization = (0.015278 * bill.getNoOfOccupants());
        Double blenderUtilization = (0.006941 * bill.getNoOfOccupants());
        Double printUtilization = (0.25 * bill.getNoOfOccupants());
        Double dryerUtilization = (0.0069 * bill.getNoOfOccupants());

        AppUtilization app = new AppUtilization();
        app.setAcHalfUtilization(acHalfUtilization);
        app.setAcOneUtilization(acOneUtilization);
        app.setAcTwoUtilization(acTwoUtilization);
        app.setAquariumUtilization(aquariumUtilization);
        app.setBlenderUtilization(blenderUtilization);
        app.setChargerUtilization(chargerUtilization);
        app.setCleanerUtilization(cleanerUtilization);
        app.setCoffeemakerUtilization(coffeemakerUtilization);
        app.setDeepfrierUtilization(deepfrierUtilization);
        app.setDispenserUtilization(dispenserUtilization);
        app.setDryerUtilization(dryerUtilization);
        app.setEnergyUtilization(energyUtilization);
        app.setFanUtilization(fanUtilization);
        app.setFluorescentUtilization(fluorescentUtilization);
        app.setFreezerUtilization(freezerUtilization);
        app.setGameUtilization(gameUtilization);
        app.setHeaterUtilization(heaterUtilization);
        app.setHomeTheatreUtilization(homeTheatreUtilization);
        app.setHumidifierUtilization(humidifierUtilization);
        app.setIncandescentUtilization(incandescentUtilization);
        app.setIronUtilization(ironUtilization);
        app.setKettleUtilization(kettleUtilization);
        app.setLcdUtilization(lcdUtilization);
        app.setLedUtilization(ledUtilization);
        app.setMicrowaveUtilization(microwaveUtilization);
        app.setPlasmaUtilization(plasmaUtilization);
        app.setPrintUtilization(printUtilization);
        app.setPumpUtilization(pumpUtilization);
        app.setRefrigeratorUtilization(refrigeratorUtilization);
        app.setSecurityUtilization(securityUtilization);
        app.setStoveUtilization(stoveUtilization);
        app.setToasterUtilization(toasterUtilization);

        Log.i("HOT_DISC0", String.valueOf(hotDisco));
        Log.i("Cold_DISC0", String.valueOf(coldDisco));
        Log.i("HOT_P1", String.valueOf(hotPeriod1));
        Log.i("HOT_P2", String.valueOf(hotPeriod2));
        Log.i("COLD_P1", String.valueOf(coldPeriod1));
        Log.i("COLD_P2", String.valueOf(coldPeriod2));

        return app;
    }
}
