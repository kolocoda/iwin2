package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.models.Disco;
import com.ugarsoft.chyma.iwin.models.Tariff;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/14/2016
 */
public class DiscoSetup {

    public static ArrayList<Disco> getAllDiscos(){
        ArrayList<Disco> allDiscos = new ArrayList<>();

        Disco d = new Disco();
        d.setDisplayName("Abuja Electricity DisCo");
        d.setCode("aedc");
        d.setTariff(Tariff.aedc);
        d.setePayImageCode(Tariff.aedc);

        Disco d1 = new Disco();
        d1.setDisplayName("Benin Electricity DisCo (R2-S)");
        d1.setCode("bedcS");
        d1.setTariff(Tariff.bedcS);
        d1.setePayImageCode(Tariff.bedcS);

        Disco d2 = new Disco();
        d2.setDisplayName("Benin Electricity DisCo (R2-T)");
        d2.setCode("bedcT");
        d2.setTariff(Tariff.bedcT);
        d2.setePayImageCode(Tariff.bedcS);

        Disco d3 = new Disco();
        d3.setDisplayName("Eko Electricity DisCo (R2-S)");
        d3.setCode("ekedcS");
        d3.setTariff(Tariff.ekedcS);
        d3.setePayImageCode(Tariff.ekedcS);

        Disco d4 = new Disco();
        d4.setDisplayName("Eko Electricity DisCo (R2-T)");
        d4.setCode("ekedcT");
        d4.setTariff(Tariff.ekedcT);
        d4.setePayImageCode(Tariff.ekedcS);

        Disco d5 = new Disco();
        d5.setDisplayName("Enugu Electricity DisCo");
        d5.setCode("eedc");
        d5.setTariff(Tariff.eedc);
        d5.setePayImageCode(Tariff.eedc);

        Disco d6 = new Disco();
        d6.setDisplayName("Ibadan Electricity DisCo");
        d6.setCode("ibedc");
        d6.setTariff(Tariff.ibedc);
        d6.setePayImageCode(Tariff.ibedc);

        Disco d7 = new Disco();
        d7.setDisplayName("Ikeja Electricity DisCo (R2-SP)");
        d7.setCode("ikedcS");
        d7.setTariff(Tariff.ikedcS);
        d7.setePayImageCode(Tariff.ikedcS);

        Disco d8 = new Disco();
        d8.setDisplayName("Ikeja Electricity DisCo (R2-TP)");
        d8.setCode("ikedcT");
        d8.setTariff(Tariff.ikedcT);
        d8.setePayImageCode(Tariff.ikedcS);

        Disco d9 = new Disco();
        d9.setDisplayName("Jos Electricity DisCo");
        d9.setCode("jedc");
        d9.setTariff(Tariff.jedc);
        d9.setePayImageCode(Tariff.jedc);

        Disco d10 = new Disco();
        d10.setDisplayName("Kaduna Electricity DisCo (R2-SP)");
        d10.setCode("kaedcS");
        d10.setTariff(Tariff.kaedcS);
        d10.setePayImageCode(Tariff.kaedcS);

        Disco d11 = new Disco();
        d11.setDisplayName("Kaduna Electricity DisCo (R2-TP)");
        d11.setCode("kaedcT");
        d11.setTariff(Tariff.kaedcT);
        d11.setePayImageCode(Tariff.kaedcS);

        Disco d12 = new Disco();
        d12.setDisplayName("Kano Electricity DisCo (R2-A)");
        d12.setCode("kedcA");
        d12.setTariff(Tariff.kedcA);
        d12.setePayImageCode(Tariff.kedcA);

        Disco d13 = new Disco();
        d13.setDisplayName("Kano Electricity DisCo (R2-B)");
        d13.setCode("kedcB");
        d13.setTariff(Tariff.kedcB);
        d13.setePayImageCode(Tariff.kedcA);

        Disco d14 = new Disco();
        d14.setDisplayName("Port Harcourt DisCo");
        d14.setCode("phedc");
        d14.setTariff(Tariff.phedc);
        d14.setePayImageCode(Tariff.phedc);

        Disco d15 = new Disco();
        d15.setDisplayName("Yola Electricity DisCo (R2-S)");
        d15.setCode("yedcS");
        d15.setTariff(Tariff.yedcS);
        d15.setePayImageCode(Tariff.yedcS);

        Disco d16 = new Disco();
        d16.setDisplayName("Yola Electricity DisCo (R2-T)");
        d16.setCode("yedcT");
        d16.setTariff(Tariff.yedcT);
        d16.setePayImageCode(Tariff.yedcS);


        allDiscos.add(d);
        allDiscos.add(d1);
        allDiscos.add(d2);
        allDiscos.add(d3);
        allDiscos.add(d4);
        allDiscos.add(d5);
        allDiscos.add(d6);
        allDiscos.add(d7);
        allDiscos.add(d8);
        allDiscos.add(d9);
        allDiscos.add(d10);
        allDiscos.add(d11);
        allDiscos.add(d12);
        allDiscos.add(d13);
        allDiscos.add(d14);
        allDiscos.add(d15);
        allDiscos.add(d16);

        return allDiscos;
    }
}
