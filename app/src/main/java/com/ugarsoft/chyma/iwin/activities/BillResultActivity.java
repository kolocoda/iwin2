package com.ugarsoft.chyma.iwin.activities;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.Bill;
import com.ugarsoft.chyma.iwin.utils.CompatUtil;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.EnergyCalculator;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.KeyboardUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;

public class BillResultActivity extends AppCompatActivity {

    private TextView totalkwh, monthlyBill, percentage, estimateUnits, unitDuration;
    private Button unitDurationButton;
    private EditText inputAmount;
    private View result;
    boolean shouldAnimate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_result);
        setupViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (CompatUtil.hasLolipop() && shouldAnimate) {
            toolbar.setAlpha(0f);
            toolbar.animate().translationZBy(40f).alphaBy(1f).setDuration(1500).start();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        totalkwh =(TextView) findViewById(R.id.totalKwh);
        monthlyBill= (TextView) findViewById(R.id.monthlyBill);
        percentage = (TextView) findViewById(R.id.percentContribution);
        estimateUnits = (TextView) findViewById(R.id.estimateUnits);
        unitDuration = (TextView) findViewById(R.id.unitDuration);
        inputAmount = (EditText) findViewById(R.id.inputAmount);
        unitDurationButton = (Button) findViewById(R.id.unitDurationButton);
        result = findViewById(R.id.result);

        KeyboardUtil.setupUI(result, BillResultActivity.this);

        String tot, bill, percent;

        tot = Pref.getStringValue(BillResultActivity.this, Constant.TOTAL_KWH.name(), "");
        bill = Pref.getStringValue(BillResultActivity.this, Constant.MONTHLY_BILL.name(), "");
        percent = Pref.getStringValue(BillResultActivity.this, Constant.PERCENT_CONTRIBUTION.name(), "");

        FontUtil.setDefaultTypeFace(BillResultActivity.this, totalkwh, monthlyBill, percentage, estimateUnits);

        totalkwh.setText(tot);
        monthlyBill.setText(bill);
        percentage.setText(percent);
        unitDuration.setVisibility(View.GONE);

        unitDurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputAmount.getText().toString().length() == 0) {
                    MessageUtil.showAlert(BillResultActivity.this, "Please enter a valid number.");
                } else {
                    Bill bill = Pref.getBillFromSharedPrefs(BillResultActivity.this);
                    String amount = EnergyCalculator.unitsDuration(bill, inputAmount.getText().toString());

                    MessageUtil.showAlert(BillResultActivity.this, "This NGN " + inputAmount.getText().toString() + " should last for about " + amount + " days.");

                }

            }
        });

    }

}
