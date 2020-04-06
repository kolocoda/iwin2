package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.SubAppliance;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/22/2016.
 */
public class SubApplianceSetup {

    public static ArrayList<SubAppliance> getAllSubAppliances() {
        ArrayList<SubAppliance> apps = new ArrayList<>();

        SubAppliance a = new SubAppliance();
        a.setTitle("Air purifiers");
        a.setShortDesc("");
        a.setCode(AppCode.APPLIANCES);
        a.setFullDesc("ENERGY STAR certified room air purifiers are 40% more energy-efficient than standard models, saving consumers about 230 kWh/year and $25 annually on utility bills. These savings could add up to $200 over its lifetime!");
        a.setImageCode(R.drawable.appliance_pic);

        SubAppliance a1 = new SubAppliance();
        a1.setTitle("Cloth washers");
        a1.setShortDesc("");
        a1.setCode(AppCode.APPLIANCES);
        a1.setFullDesc("ENERGY STAR can help families cut their related energy and water costs.\n" +
                "ENERGY STAR certified clothes washers use about 20% less energy and 35% less water than regular washers.");
        a1.setImageCode(R.drawable.appliance_pic);

        SubAppliance a2 = new SubAppliance();
        a2.setTitle("Refrigerators");
        a2.setShortDesc("");
        a2.setCode(AppCode.APPLIANCES);
        a2.setFullDesc("ENERGY STAR certified refrigerators are about 9-10 percent more energy efficient than models that meet the federal minimum energy efficiency standard.");
        a2.setImageCode(R.drawable.appliance_pic);

        SubAppliance a3 = new SubAppliance();
        a3.setTitle("Freezers");
        a3.setShortDesc("");
        a3.setCode(AppCode.APPLIANCES);
        a3.setFullDesc("\n" +
                "\n" +
                "    Improvements in insulation and compressors mean today's freezers consume much less energy than older models.\n" +
                "    Select a freezer that's earned the ENERGY STAR for maximum energy savings and the latest features.\n" +
                "\n" +
                "    Cut your utility bills.\n" +
                "    Freezers that have earned the ENERGY STAR are at least 10 percent more energy efficient than the minimum federal standard.\n" +
                "\n" +
                "    The older the freezer, the higher your bills.\n" +
                "    An estimated 35 million freezers are currently in use in the United States. Over 16 million of these freezers are more than 10 years old, costing consumers $990 million per year on their energy bills.\n" +
                "\n" +
                "    Protect the environment.\n" +
                "    STAR certified freezers use less energy and help us reduce our impact on the environment.\n");
        a3.setImageCode(R.drawable.appliance_pic);

        SubAppliance a4 = new SubAppliance();
        a4.setTitle("Audio/Video");
        a4.setShortDesc("");
        a4.setCode(AppCode.ELECTRONICS);
        a4.setFullDesc("ENERGY STAR certified audio/video equipment is up to 60% more efficient than conventional models.\n" +
                "Ray players that meet ENERGY STAR qualifications are, on average, 45% more efficient than conventional models.");
        a4.setImageCode(R.drawable.electronics);

        SubAppliance a5 = new SubAppliance();
        a5.setTitle("Set up boxes");
        a5.setShortDesc("");
        a5.setCode(AppCode.ELECTRONICS);
        a5.setFullDesc("A \"set-top box\" is a cable, satellite, Internet Protocol or other device whose primary function is to receive television signals from a specific source and deliver them to a consumer display and/or recording device, such as a television or DVR.\n" +
                "ENERGY STAR qualified set-top boxes are on average 45 percent more efficient than conventional models.");
        a5.setImageCode(R.drawable.electronics);

        SubAppliance a6 = new SubAppliance();
        a6.setTitle("Televisions");
        a6.setShortDesc("");
        a6.setCode(AppCode.ELECTRONICS);
        a6.setFullDesc("ENERGY STAR certified televisions are on average, over 25 percent more energy efficient than conventional models, saving energy in all usage modes: sleep, idle, and on. The label can be found on everything from standard TVs to large screen TVs with the latest features like 3D and internet connectivity.\n" +
                "Models that earn the ENERGY STAR incorporate LEDs, the latest in screen backlighting technology.");
        a6.setImageCode(R.drawable.electronics);

        SubAppliance a7 = new SubAppliance();
        a7.setTitle("Telephones");
        a7.setShortDesc("");
        a7.setCode(AppCode.ELECTRONICS);
        a7.setFullDesc("ENERGY STAR certified cordless phones, answering machines, and combination units perform much more efficiently and use about half the energy of standard units by incorporating improved energy performance features such as switch-mode power supplies and \"smart\" chargers.");
        a7.setImageCode(R.drawable.electronics);

        SubAppliance a8 = new SubAppliance();
        a8.setTitle("Air conditioning");
        a8.setShortDesc("");
        a8.setCode(AppCode.APPLIANCES);
        a8.setFullDesc("\n" +
                "\n" +
                "    Use about 15% less energy than conventional models, which could mean a savings of about $90 over the lifetime of the unit, on average.\n" +
                "\n" +
                "    Often include timers for better temperature control, allowing you to use the minimum amount of energy you need to cool your room.\n");
        a8.setImageCode(R.drawable.appliance_pic);

        SubAppliance a9 = new SubAppliance();
        a9.setTitle("Boilers");
        a9.setShortDesc("");
        a9.setCode(AppCode.APPLIANCES);
        a9.setFullDesc("\n" +
                "\n" +
                "    ENERGY STAR qualified boilers have annual fuel utilization efficiency (AFUE) ratings of 85% or greater.\n" +
                "    AFUE is the measure of heating equipment efficiency.\n" +
                "    They achieve greater efficiency with features, including:\n" +
                "\n" +
                "    electronic ignition, which eliminates the need to have the pilot light burning all the time\n" +
                "\n" +
                "    new combustion technologies that extract more heat from the same amount of fuel\n" +
                "\n" +
                "    sealed combustion that uses outside air to fuel the burner, reducing drafts and improving safety.\n");
        a9.setImageCode(R.drawable.appliance_pic);


        return apps;
    }
}
