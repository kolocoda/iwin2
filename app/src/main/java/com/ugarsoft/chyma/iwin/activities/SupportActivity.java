package com.ugarsoft.chyma.iwin.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.base.AccessActivity;
import com.ugarsoft.chyma.iwin.fragments.AskUsFragment;
import com.ugarsoft.chyma.iwin.fragments.ComplaintsFragment;
import com.ugarsoft.chyma.iwin.fragments.FaqFragment;
import com.ugarsoft.chyma.iwin.utils.Pref;

import java.util.ArrayList;
import java.util.List;


public class SupportActivity extends AppCompatActivity {


    private  List<Fragment> fragments = new ArrayList<>();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragments.add(new FaqFragment());
        fragments.add(new ComplaintsFragment());
        fragments.add(new AskUsFragment());
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pref.getUser(SupportActivity.this) == null) {
                    Snackbar.make(view, "Login to send Messages", Snackbar.LENGTH_LONG)
                            .setAction("LOGIN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(SupportActivity.this, AccessActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                }else{
                    Intent intent = new Intent(SupportActivity.this, SendMailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupIcons(){
        int[] icons = {R.drawable.ic_icon_faq, R.drawable.ic_icon_complaint, R.drawable.ic_icon_support};

        for (int i = 0 ; i < fragments.size() ; i++){
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> myFragments;
        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.myFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return myFragments.get(position);
        }

        @Override
        public int getCount() {
           return myFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "FAQ";
                case 1:
                    return "COMPLAINTS";
                case 2:
                    return "ASK US";
            }
            return null;
        }
    }
}
