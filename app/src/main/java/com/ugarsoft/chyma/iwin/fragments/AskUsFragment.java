package com.ugarsoft.chyma.iwin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;


public class AskUsFragment extends Fragment {

    private TextView askText;

    public AskUsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ask_us, container, false);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        askText = (TextView) view.findViewById(R.id.askText);
        FontUtil.setDefaultTypeFace(getActivity(), askText);
        if (Pref.getUser(getActivity()) == null){
            askText.setText(notLoggedon);
        }
        else askText.setText(loggedOn);
    }

    private String notLoggedon ="In the event that you were unable to find answers to your questions in our FAQ section, please register with us, create a support ticket and fill out the questions form for us to answer your question.";
    private String loggedOn = "In the event that you were unable to find answers to your questions in our FAQ section, please create a support ticket and fill out the questions form for us to answer your question.";
}
