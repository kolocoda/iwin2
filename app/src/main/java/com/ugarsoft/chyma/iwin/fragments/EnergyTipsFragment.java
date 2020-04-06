package com.ugarsoft.chyma.iwin.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.ApplianceViewActivity;
import com.ugarsoft.chyma.iwin.models.Appliance;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.j256.ormlite.dao.Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EnergyTipsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Appliance> appList = new ArrayList<>();
    private AppAdapter appAdapter;
    private DBService dbService;

    public EnergyTipsFragment() {
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
        View view =  inflater.inflate(R.layout.activity_energy_appliance, container, false);
        setupViews(view);
        loadApps();
        return view;
    }

    private void setupViews(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        appAdapter = new AppAdapter(getActivity(), appList);
        recyclerView.setAdapter(appAdapter);
    }

    void loadApps() {
        dbService = DBService.getInstance(getActivity());
        appList.clear();
        if (appList.isEmpty()) {
            try {
                Dao<Appliance, Long> dao = dbService.getDao(Appliance.class);
                List<Appliance> list = dao.queryForAll();
                for (Appliance con : list){
                    appList.add(con);
                    Log.i("TAG", con.getTitle());
                }
                appAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class AppAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        Context context;
        List<Appliance> appliances;
        DBService dbService;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

        public AppAdapter(Context context, List<Appliance> appliances) {
            this.context = context;
            this.appliances = appliances;
            dbService = DBService.getInstance(context);
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_appliance, null);
            return new TaskViewHolder(context, view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            Appliance app = appliances.get(position);
            int image = app.getImageCode();
            if (image != 0 ) {
                holder.appLogo.setImageResource(image);
            } else {
                holder.appLogo.setImageResource(R.drawable.ic_menu_news);
            }

            holder.appTitleTextView.setText(app.getTitle());
            holder.appDescTextView.setText(app.getShortDesc());

            if(app.isHasContent()){
                holder.appCard.setTag(app);
                holder.appCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Appliance appliance = (Appliance) view.getTag();
                        Pref.saveCurrentAppliance(getActivity(), appliance);
                        Intent intent = new Intent(getActivity(), ApplianceViewActivity.class);
                        startActivity(intent);

                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return appliances.size();
        }

    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView appTitleTextView;
        private TextView appDescTextView;
        private CardView appCard;

        private ImageView appLogo;

        public TaskViewHolder(Context context, View itemView) {
            super(itemView);
            appTitleTextView = (TextView) itemView.findViewById(R.id.appTitleTextView);
            appDescTextView = (TextView) itemView.findViewById(R.id.appDescTextView);
            appCard = (CardView) itemView.findViewById(R.id.appCard);

            FontUtil.setDefaultTypeFace(context, appDescTextView);
            FontUtil.setDefaultTypeFace(context, appTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);
            appLogo = (ImageView) itemView.findViewById(R.id.appLogo);

        }
    }
}
