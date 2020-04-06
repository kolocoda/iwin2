package com.ugarsoft.chyma.iwin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.utils.FontUtil;


public class HomeTipsFragment extends Fragment {

    private TextView tips;
    public HomeTipsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_tips, container, false);
        setupViews(view);
        return  view;
    }

    private void setupViews(View view) {
        tips = (TextView) view.findViewById(R.id.tipsText);
        FontUtil.setDefaultTypeFace(getActivity(), tips);
        tips.setText(Html.fromHtml(homeTips));
    }

    private  String homeTips = "<p>The following are a list of energy saving tips for the home:</p>\n" +
            "    \t\t<p></p>\n" +
            "            <ol data-role=\"listview\">\n" +
            "            \t<p>1. Replace all incandescent energy bulbs with the more energy efficient ones (eg. CFLs and LEDs).</p>\n" +
            "            \t<p>2. When buying domestic electrical appliances make sure to inspect the energy demand labels to enable identification of the more energy efficient ones especially for fridges and freezers.</p>\n" +
            "            \t<p>3. When in use, pay attention to the thermostat setting of your freezers and fridges to avoid excessive freezing with attendant ice formation that will eventually require defrosting hence resulting in energy wastage.</p>\n" +
            "            \t<p>4. Make sure that the fridge and freezer doors are always closed and airtight.</p>\n" +
            "            \t<p>5. Make sure that all light bulbs within the house are switched off once no one is in the room. Note that even a non-functional fluorescent bulb consumes power when left on.</p>\n" +
            "            \t<p>6. Disconnect all appliances that are not in use.</p>\n" +
            "            \t<p>7. Make sure that all security lighting is switched off once there is daylight. There are some security lighting that incorporate accessories that operate on this principle and people are encourage to consider this option to avoid forgetfulness.</p>\n" +
            "            \t<p>8. Ensure that all non-essential appliances are switch off when there is no one at home.</p>\n" +
            "            \t<p>9. Lower the thermostat setting of your water heater and air dry clothes instead of using the washing machine when it is practical.</p>\n" +
            "            \t<p>10. Make sure windows and vents are closed when using the air conditioner.</p>\n" +
            "            \t<p>11. Make sure that curtains are drawn when the air conditioner is on.</p>\n" +
            "            \t<p>12. Consider coating glass windows with heat reflective material to prevent sun radiation from heating up the room.</p>\n" +
            "            \t<p>13. During construction, prioritise savings on air conditioning load over lighting. It costs more to cool than to light-up an area.</p>\n" +
            "            \t<p>14. Set the temperature on your air conditioner to comfortable temperature like 22 degrees to avoid excessive cooling. You waste energy when cover yourself with blanket when sleeping with the air conditioner on.</p>\n" +
            "            \t<p>15. Consider using fan to spread cooled air to save on energy by way of need for additional cooling units or reduced thermostatic setting.</p>\n" +
            "            \t<p>16. During construction, consider utilising specialised heat reflective  material for roofing as it makes the inside of the house cooler and hence reduces the need for prolonged cooling or extensive cooling.</p>\n" +
            "            \t<p>17. When renting an apartmen,t avoid topmost floors as they tend to be hotter due to heating effect of the roof hence requiring more cooling load.</p>\n" +
            "            \t<p>18. Consider planting trees on the side of the house where the sun hits directly as it will help provide shed and could be a source of cool breeze.</p>\n" +
            "            \t<p> </p>\n" +
            "        \t</ol>\t\n" +
            "        </div>";
}
