package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.models.Tariff;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/12/2016.
 */
public class EPaySetup {

    public static ArrayList<EPay> getAllEPayAreas(){
        ArrayList<EPay> allContacts = new ArrayList<>();

        EPay c = new EPay();
        c.setArea("Abuja Electricity Distribution");
        c.setImageCode(R.drawable.aedc);
        c.setePayIsavailable(Boolean.TRUE);
        c.setCode(Tariff.aedc);

        EPay c1 = new EPay();
        c1.setArea("Benin Electricity Distribution");
        c1.setImageCode(R.drawable.bedc);
        c1.setePayIsavailable(Boolean.TRUE);
        c1.setCode(Tariff.bedcS);

        EPay c2 = new EPay();
        c2.setArea("Enugu Electricity Distribution");
        c2.setImageCode(R.drawable.eedc);
        c2.setePayIsavailable(Boolean.TRUE);
        c2.setCode(Tariff.eedc);

        EPay c3 = new EPay();
        c3.setArea("Eko Electricity Distribution");
        c3.setImageCode(R.drawable.ekedc);
        c3.setePayIsavailable(Boolean.TRUE);
        c3.setCode(Tariff.ekedcS);

        EPay c4 = new EPay();
        c4.setArea("Ibadan Electricity Distribution");
        c4.setImageCode(R.drawable.ibedc);
        c4.setePayIsavailable(Boolean.TRUE);
        c4.setCode(Tariff.ibedc);

        EPay c5 = new EPay();
        c5.setArea("Ikeja Electricity Distribution");
        c5.setImageCode(R.drawable.ikedc);
        c5.setePayIsavailable(Boolean.TRUE);
        c5.setCode(Tariff.ikedcS);

        EPay c6 = new EPay();
        c6.setArea("Jos Electricity Distribution");
        c6.setImageCode(R.drawable.jedc);
        c6.setePayIsavailable(Boolean.FALSE);
        c6.setCode(Tariff.jedc);

        EPay c7 = new EPay();
        c7.setArea("Kaduna Electricity Distribution");
        c7.setImageCode(R.drawable.kaedc);
        c7.setePayIsavailable(Boolean.FALSE);
        c7.setCode(Tariff.kaedcS);

        EPay c8 = new EPay();
        c8.setArea("Kano Electricity Distribution");
        c8.setImageCode(R.drawable.kedc);
        c8.setePayIsavailable(Boolean.TRUE);
        c8.setCode(Tariff.kedcA);

        EPay c9 = new EPay();
        c9.setArea("Portharcourt Electricity Distribution");
        c9.setImageCode(R.drawable.phedc);
        c9.setePayIsavailable(Boolean.TRUE);
        c9.setCode(Tariff.phedc);

        EPay c10 = new EPay();
        c10.setArea("Yola Electricity Distribution");
        c10.setImageCode(R.drawable.yedc);
        c10.setePayIsavailable(Boolean.TRUE);
        c10.setCode(Tariff.yedcS);


        allContacts.add(c);
        allContacts.add(c1);
        allContacts.add(c2);
        allContacts.add(c3);
        allContacts.add(c4);
        allContacts.add(c5);
        allContacts.add(c6);
        allContacts.add(c7);
        allContacts.add(c8);
        allContacts.add(c9);
        allContacts.add(c10);

        return allContacts;
    }
}
