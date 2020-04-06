package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.Appliance;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/12/2016.
 */
public class ApplianceSetup {

    public static ArrayList<Appliance> getAllAppliances(){
        ArrayList<Appliance> apps = new ArrayList<>();

        Appliance a = new Appliance();
        a.setTitle("Appliances");
        a.setShortDesc("Freezers, Refrigerators, etc.");
        a.setCode(AppCode.APPLIANCES);
        a.setFullDesc("");
        a.setImageCode(R.drawable.appliance_pic);
        a.setHasContent(true);

        Appliance a1 = new Appliance();
        a1.setTitle("Electronics");
        a1.setShortDesc("Telephones, Televisions, etc.");
        a1.setCode(AppCode.ELECTRONICS);
        a1.setFullDesc("");
        a1.setImageCode(R.drawable.electronics);
        a1.setHasContent(true);

        Appliance a2 = new Appliance();
        a2.setTitle("Heating and Cooling");
        a2.setShortDesc("Air Conditioning, Boilers, etc.");
        a2.setCode(AppCode.HVAC);
        a2.setFullDesc("");
        a2.setImageCode(R.drawable.airconditioning);
        a2.setHasContent(true);

        Appliance a3 = new Appliance();
        a3.setTitle("Office equipment");
        a3.setShortDesc("Printers, Computers, etc.");
        a3.setCode(AppCode.OFFICE);
        a3.setFullDesc("");
        a3.setImageCode(R.drawable.photocopiers);
        a3.setHasContent(false);

        Appliance a4 = new Appliance();
        a4.setTitle("Water heater");
        a4.setShortDesc("House, office etc.");
        a4.setCode(AppCode.WATER_HEATER);
        a4.setFullDesc("");
        a4.setImageCode(R.drawable.electricwaterheater);
        a4.setHasContent(false);

        Appliance a5 = new Appliance();
        a5.setTitle("Lights and Fans");
        a5.setShortDesc("Energy savers, Security lights, etc.");
        a5.setCode(AppCode.LIGHT_FANS);
        a5.setFullDesc("");
        a5.setImageCode(R.drawable.ceiling_fan);
        a5.setHasContent(false);

        apps.add(a);
        apps.add(a1);
        apps.add(a2);
        apps.add(a3);
        apps.add(a4);
        apps.add(a5);

        return apps;
    }
}
