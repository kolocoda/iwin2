package com.ugarsoft.chyma.iwin.activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.Disco;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.utils.DBQuery;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.EnergyCalculator;
import com.ugarsoft.chyma.iwin.utils.KeyboardUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.apache.commons.lang.WordUtils;

import java.sql.SQLException;
import java.util.List;

public class VerifyPurchaseActivity extends AppCompatActivity {
    private View resultPanel;
    private Spinner spinner;
    private EditText amount;
    private TextView totalUnits;
    private CardView verifyCard;
    private DBService dbService;
    private TextView discoName;
    private ImageView discoLogo;
    private  View verifyPurchase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_purchase);
        setupViews();
        addItemsToSpinner();

    }

    private void setupViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(10f);

        dbService = DBService.getInstance(this);
        resultPanel = findViewById(R.id.displayUnits);
        spinner = (Spinner) findViewById(R.id.spinner1);
        amount = (EditText) findViewById(R.id.inputAmount);
        verifyCard = (CardView) findViewById(R.id.verifyButton);
        totalUnits = (TextView) findViewById(R.id.totalUnits);
        discoName = (TextView) findViewById(R.id.discoName);
        discoLogo = (ImageView) findViewById(R.id.discoLogo);
        verifyPurchase = findViewById(R.id.purchase);

        KeyboardUtil.setupUI(verifyPurchase, VerifyPurchaseActivity.this);

        resultPanel.setVisibility(View.GONE);

        verifyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value  = String.valueOf(spinner.getSelectedItem());
                calculateUnits(value);
            }
        });
    }

    private void addItemsToSpinner(){
        List<String> list = DBQuery.getDiscoList(VerifyPurchaseActivity.this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }



    private void calculateUnits(String disco){
        if (disco.isEmpty()){
            MessageUtil.showAlert(VerifyPurchaseActivity.this, "Please select a Distribution company.");
        } else if (amount.getText().toString().length() == 0) {
            MessageUtil.showAlert(VerifyPurchaseActivity.this, "Please enter a valid number.");
        } else {
            Disco dis = getSelectedDisco(disco);
            Double tariff = dis.getTariff();
            String amt = amount.getText().toString();

            String units = EnergyCalculator.calculateTokenUnits(amt, Double.toString(tariff));

            totalUnits.setText(units);
            discoName.setText(WordUtils.capitalize(disco));
            discoLogo.setImageResource(getContactImageCodeFromEPay(dis));
            resultPanel.setVisibility(View.VISIBLE);
        }
    }

    private Disco getSelectedDisco(String discoName) {
        Disco disco = new Disco();
        try {
            Dao<Disco, Long> dao = dbService.getDao(Disco.class);
            QueryBuilder<Disco, Long> qb = dao.queryBuilder();
            qb.orderBy("displayName", true);
            qb.where().eq("displayName", discoName);
            Log.i("setup", qb.query().get(0).getDisplayName());
            disco = qb.query().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return disco;
    }

    private  int getContactImageCodeFromEPay(Disco disco){
        int i = 0;
        try {
            Dao<EPay, Long> dao = dbService.getDao(EPay.class);
            QueryBuilder<EPay, Long> qb = dao.queryBuilder();
            qb.where().eq("code", disco.getePayImageCode());
            i = qb.query().get(0).getImageCode();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  i;
    }
}
