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


public class OffiecTipsFragment extends Fragment {

    private TextView tips;

    public OffiecTipsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_offiec_tips, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view){
        tips = (TextView) view.findViewById(R.id.tipsText);
        FontUtil.setDefaultTypeFace(getActivity(), tips);
        tips.setText(Html.fromHtml(officeTips));
    }

    private String officeTips = "<p>Below are a list of energy saving tips for the office</p>\n" +
            "            <ol data-role=\"listview\">\n" +
            "            \t<p>1. Select energy efficient office equipment by paying attention to the energy demand labels.</p>\n" +
            "            \t<p>2. Consider utilising dwarf walls to demarcate offices to enable efficient utilisation of cooling resources.</p>\n" +
            "            \t<p>3. Set computers to turn to idle mode when not in use.</p>\n" +
            "            \t<p>4. Consider buying laptops instead of desk tops as lap tops consume less power.</p>\n" +
            "            \t<p>5. Disconnect all appliances at the end of every work day.</p>\n" +
            "            \t<p>6. Most of the tips for home energy saving above are also applicable.</p>\n" +
            "            \t<p> </p>\n" +
            "            </ol>";
}
