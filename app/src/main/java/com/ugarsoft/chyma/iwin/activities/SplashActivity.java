package com.ugarsoft.chyma.iwin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.base.HomeActivity;
import com.ugarsoft.chyma.iwin.models.AllMonth;
import com.ugarsoft.chyma.iwin.models.Appliance;
import com.ugarsoft.chyma.iwin.models.Contact;
import com.ugarsoft.chyma.iwin.models.Disco;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.models.MessagePriority;
import com.ugarsoft.chyma.iwin.models.PaymentUrl;
import com.ugarsoft.chyma.iwin.setup.ApplianceSetup;
import com.ugarsoft.chyma.iwin.setup.ContactSetup;
import com.ugarsoft.chyma.iwin.setup.DiscoSetup;
import com.ugarsoft.chyma.iwin.setup.EPaySetup;
import com.ugarsoft.chyma.iwin.setup.MonthSetup;
import com.ugarsoft.chyma.iwin.setup.PaymentUrlSetup;
import com.ugarsoft.chyma.iwin.setup.PrioritySetup;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.SetupUtil;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View view = findViewById(R.id.container);

        final Handler handler =  new Handler();
        final Runnable runner = new Runnable() {
            @Override
            public void run() {
                Boolean dbIsLoaded = Pref.getBooleanValue(SplashActivity.this, Constant.LOAD_DB.name(), false);
                if(!dbIsLoaded) {
                    readSetupFiles();
                }
                start();
            }
        };
        handler.postDelayed(runner, 2000);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runner);

                start();
            }
        });


    }

    private void readSetupFiles() {
        loadContacts();
    }

    private void loadContacts() {
        SetupUtil setup = new SetupUtil();
        setup.insertIntoDB(Contact.class, ContactSetup.getAllContacts(), SplashActivity.this);
        setup.insertIntoDB(Appliance.class, ApplianceSetup.getAllAppliances(), SplashActivity.this);
        setup.insertIntoDB(EPay.class, EPaySetup.getAllEPayAreas(), SplashActivity.this);
        setup.insertIntoDB(Disco.class, DiscoSetup.getAllDiscos(),SplashActivity.this);
        setup.insertIntoDB(AllMonth.class, MonthSetup.getAllMonths(),SplashActivity.this);
        setup.insertIntoDB(PaymentUrl.class, PaymentUrlSetup.getAllEPayUrls(),SplashActivity.this);
        setup.insertIntoDB(MessagePriority.class, PrioritySetup.getAllMessagePriority(),SplashActivity.this);

        Pref.saveToSharedPreference(SplashActivity.this, Constant.LOAD_DB.name(), true);
    }

    public void start(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
