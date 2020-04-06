package com.ugarsoft.chyma.iwin.activities.base;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.SupportActivity;
import com.ugarsoft.chyma.iwin.activities.TipsActivity;

public class OtherActivity extends BaseActivity {

    private View tipsCard;
    private View supportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        setUpNavigationBar();
       setUpViews();
    }

    private void setUpViews(){
        tipsCard =  findViewById(R.id.tipsCard);
        supportCard =  findViewById(R.id.supportCard);

        supportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtherActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });
        tipsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtherActivity.this, TipsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_OTHER;
    }
}
