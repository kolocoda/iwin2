package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.models.AllMonth;
import com.ugarsoft.chyma.iwin.models.Month;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/14/2016.
 */
public class MonthSetup {

    public static ArrayList<AllMonth> getAllMonths(){
        ArrayList<AllMonth> allDiscos = new ArrayList<>();

        AllMonth month = new AllMonth();
        month.setMonthName("January");
        month.setMonthCode(Month.JAN);

        AllMonth month1 = new AllMonth();
        month1.setMonthName("February");
        month1.setMonthCode(Month.FEB);

        AllMonth month2 = new AllMonth();
        month2.setMonthName("March");
        month2.setMonthCode(Month.MAR);

        AllMonth month3 = new AllMonth();
        month3.setMonthName("April");
        month3.setMonthCode(Month.APR);

        AllMonth month4 = new AllMonth();
        month4.setMonthName("May");
        month4.setMonthCode(Month.MAY);

        AllMonth month5 = new AllMonth();
        month5.setMonthName("June");
        month5.setMonthCode(Month.JUN);

        AllMonth month6 = new AllMonth();
        month6.setMonthName("July");
        month6.setMonthCode(Month.JUL);

        AllMonth month7 = new AllMonth();
        month7.setMonthName("August");
        month7.setMonthCode(Month.AUG);

        AllMonth month8 = new AllMonth();
        month8.setMonthName("September");
        month8.setMonthCode(Month.SEP);

        AllMonth month9 = new AllMonth();
        month9.setMonthName("October");
        month9.setMonthCode(Month.OCT);

        AllMonth month10 = new AllMonth();
        month10.setMonthName("November");
        month10.setMonthCode(Month.NOV);

        AllMonth month11 = new AllMonth();
        month11.setMonthName("December");
        month11.setMonthCode(Month.DEC);



        allDiscos.add(month);
        allDiscos.add(month1);
        allDiscos.add(month2);
        allDiscos.add(month3);
        allDiscos.add(month4);
        allDiscos.add(month5);
        allDiscos.add(month6);
        allDiscos.add(month7);
        allDiscos.add(month8);
        allDiscos.add(month9);
        allDiscos.add(month10);
        allDiscos.add(month11);

        return allDiscos;
    }
}
