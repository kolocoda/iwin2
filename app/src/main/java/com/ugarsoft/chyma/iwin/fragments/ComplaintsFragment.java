package com.ugarsoft.chyma.iwin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.utils.FontUtil;


public class ComplaintsFragment extends Fragment {

    private TextView complaintText;

    public ComplaintsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);
        setupView(view);
        return  view;
    }

    private void setupView(View view){
        complaintText = (TextView) view.findViewById(R.id.complaints);
        FontUtil.setDefaultTypeFace(getActivity(), complaintText);
    }
}
