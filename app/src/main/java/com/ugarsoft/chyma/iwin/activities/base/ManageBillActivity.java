package com.ugarsoft.chyma.iwin.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.EstimateBillsActivity;
import com.ugarsoft.chyma.iwin.activities.VerifyPurchaseActivity;

public class ManageBillActivity extends BaseActivity {

    private View verifyCard;
    private View estimateCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bill);
        setUpNavigationBar();
        setupViews();
    }
    private void setupViews(){
        verifyCard =  findViewById(R.id.verifyCard);
        estimateCard =  findViewById(R.id.estimateCard);

        verifyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBillActivity.this, VerifyPurchaseActivity.class);
                startActivity(intent);
            }
        });

        estimateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBillActivity.this, EstimateBillsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getMenuItemType() {
       return NAVIGATION_MENU_ITEM_MANAGE_BILL;
    }
}
