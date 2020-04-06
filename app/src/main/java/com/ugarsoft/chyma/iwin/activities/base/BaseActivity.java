package com.ugarsoft.chyma.iwin.activities.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.utils.CompatUtil;
import com.ugarsoft.chyma.iwin.utils.DBQuery;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.j256.ormlite.dao.Dao;
import com.pkmmte.view.CircularImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


public abstract class BaseActivity extends AppCompatActivity {

    private static final int NAVIGATION_ITEM_TYPE_ACCOUNT = 0;
    private static final int NAVIGATION_ITEM_TYPE_SEPARATOR = 1;
    private static final int NAVIGATION_ITEM_TYPE_MENU_ITEM = 2;
    private static final int NAVIGATION_INTENT_DELAY = 200;

    public static final int NAVIGATION_MENU_ITEM_HOME = 0;
    public static final int NAVIGATION_MENU_ITEM_POWER_STATS = 1;
    public static final int NAVIGATION_MENU_ITEM_OPERATOR_ANNOUNCEMENT = 2;
    public static final int NAVIGATION_MENU_ITEM_MANAGE_BILL = 3;
    public static final int NAVIGATION_MENU_ITEM_EPAYMENT_OPTIONS = 4;
    public static final int NAVIGATION_MENU_ITEM_OTHER = 5;
    public static final int NAVIGATION_MENU_ITEM_CONTACT = 6;
    public static final int NAVIGATION_MENU_ITEM_LOGIN = 7;
    private RecyclerView _recyclerView;
    private DBService dbService;

    private NavigationBarAdapter navigationBarAdapter;

    private DrawerLayout drawerLayout;

    private List<NavigationItem> navigationItems = new ArrayList<>();

    private boolean shouldAnimate = true;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public abstract int getMenuItemType();

    public int getActionBarColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void setUpNavigationBar() {
        setupActionBar();
        _recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNav);
        navigationBarAdapter = new NavigationBarAdapter(this, navigationItems);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _recyclerView.setItemAnimator(new SlideInLeftAnimator());
        _recyclerView.setAdapter(navigationBarAdapter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        reloadMenuItems();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (CompatUtil.hasLolipop() && shouldAnimate) {
            toolbar.setAlpha(0f);
            toolbar.animate().translationZBy(40f).alphaBy(1f).setDuration(1500).start();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getActionBarColor());
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    void reloadMenuItems() {
        navigationItems.clear();
        navigationBarAdapter.notifyDataSetChanged();
        AppUser appUser = Pref.getUser(this);

        if (appUser != null) {
            navigationItems.add(new NavigationItemAccount("", appUser.getName() + " " + appUser.getUsername(), appUser.getEmail(), ""));
        }
        else{
            navigationItems.add(new NavigationItemAccount("", "Energy Centre", "info@iwin.org.ng", ""));
        }
        navigationItems.add(new NavigationItemSeperator());
        boolean isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_HOME;
        int count = 0;
        int newscount = DBQuery.countUnreadNews(BaseActivity.this);
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Power Sector News", newscount, R.drawable.ic_menu_news, R.drawable.ic_menu_news, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "News is Clicked");
                finish();
            }
        }));
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_POWER_STATS;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Power Generation Report", count, R.drawable.ic_menu_chart, R.drawable.ic_menu_chart, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, PowerStatsActivity.class);
                Log.i(TAG, "Stats is Clicked");
                runDelayedIntent(intent);
            }
        }));
        int announceCount = DBQuery.countUnreadAnnouncement(BaseActivity.this);
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_OPERATOR_ANNOUNCEMENT;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Operator Announcements", announceCount, R.drawable.ic_icon_announce, R.drawable.ic_icon_announce, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, AnnouncementActivity.class);
                Log.i(TAG, "Announcement is Clicked");
                runDelayedIntent(intent);
            }
        }));
        navigationItems.add(new NavigationItemSeperator());
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_MANAGE_BILL;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Manage Bills", count, R.drawable.ic_icon_money, R.drawable.ic_icon_money, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, ManageBillActivity.class);
                runDelayedIntent(intent);
            }
        }));
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_EPAYMENT_OPTIONS;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "e-Payment Options", count, R.drawable.ic_icon_epay, R.drawable.ic_icon_epay, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, EPaymentActivity.class);
                runDelayedIntent(intent);
            }
        }));
        navigationItems.add(new NavigationItemSeperator());
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_OTHER;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Support and Tips", count, R.drawable.ic_icon_support, R.drawable.ic_icon_support, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, OtherActivity.class);
                Log.i(TAG, "Support is Clicked");
                runDelayedIntent(intent);
            }
        }));
        isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_CONTACT;
        navigationItems.add(new NavigationItemMenuItem(isSelected, "Operator Contact", count, R.drawable.ic_icon_contact, R.drawable.ic_icon_contact, isSelected ? null : new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, ContactActivity.class);
                runDelayedIntent(intent);
            }
        }));
        if (appUser == null) {
            isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_LOGIN;
            navigationItems.add(new NavigationItemMenuItem(isSelected, "Login", count, R.drawable.ic_icon_login, R.drawable.ic_icon_login, isSelected ? null : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BaseActivity.this, AccessActivity.class);
                    runDelayedIntent(intent);
                }
            }));
        }
        if (appUser != null) {
            isSelected = getMenuItemType() == NAVIGATION_MENU_ITEM_LOGIN;
            navigationItems.add(new NavigationItemMenuItem(isSelected, "Logout", count, R.drawable.ic_icon_login, R.drawable.ic_icon_login, isSelected ? null : new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearUserData();
                }
            }));
        }
        navigationBarAdapter.notifyDataSetChanged();
    }

    private void clearUserData() {
        dbService = DBService.getInstance(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Dao<AppUser, Long> dao = dbService.getDao(AppUser.class);
                    dao.delete(dao.queryForAll());
                    Pref.saveUser(BaseActivity.this, null);
                    Log.i("BaseActivity", "Cleared current user data");
                    Toast.makeText(BaseActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
        reloadMenuItems();
        Intent intent = new Intent(BaseActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void runDelayedIntent(final Intent intent) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                if (!isHomeActivity()) {
                    finish();
                }
            }
        }, NAVIGATION_INTENT_DELAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadMenuItems();
    }

    public static class NavigationBarAdapter extends RecyclerView.Adapter<NavigationBarAdapter.NavigationItemViewHolder> {

        private List<NavigationItem> navigationItems;
        private Context context;

        public NavigationBarAdapter(Context context, List<NavigationItem> navigationItems) {
            this.navigationItems = navigationItems;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
            return navigationItems.get(position).getType();
        }

        @Override
        public NavigationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = null;
            LayoutInflater inflater = LayoutInflater.from(context);
            switch (viewType) {
                case NAVIGATION_ITEM_TYPE_MENU_ITEM:
                    itemView = inflater.inflate(R.layout.navigation_item_menuitem, parent, false);
                    break;
                case NAVIGATION_ITEM_TYPE_SEPARATOR:
                    itemView = inflater.inflate(R.layout.navigation_item_seperator, parent, false);
                    break;
                case NAVIGATION_ITEM_TYPE_ACCOUNT:
                    itemView = inflater.inflate(R.layout.navigation_item_user_accout, parent, false);
            }
            return new NavigationItemViewHolder(context, itemView, viewType);
        }

        @Override
        public void onBindViewHolder(NavigationItemViewHolder holder, int position) {
            switch (navigationItems.get(position).getType()) {
                case NAVIGATION_ITEM_TYPE_MENU_ITEM:
                    bindMenuItem(holder, navigationItems.get(position));
                    break;
                case NAVIGATION_ITEM_TYPE_SEPARATOR:
                    bindSeparator(holder, navigationItems.get(position));
                    break;
                case NAVIGATION_ITEM_TYPE_ACCOUNT:
                    bindAccount(holder, navigationItems.get(position));
            }
        }

        private void bindMenuItem(NavigationItemViewHolder holder, NavigationItem navigationItem) {
            NavigationItemMenuItem menuItem = (NavigationItemMenuItem) navigationItem;
            holder.navigationLabelTextView.setText(menuItem.getTitle());

            if (menuItem.isSelected()) {
                holder.navigationItemBaseView.setBackgroundColor(context.getResources().getColor(R.color.selectedItem));
                holder.navigationIconImageView.setImageResource(menuItem.getIconSelected());
            } else {
                holder.navigationIconImageView.setImageResource(menuItem.getIconNormal());
                holder.navigationItemBaseView.setBackgroundDrawable(holder.selectorDrawable);
            }

            if (menuItem.getBadgeCount() > 0) {
                holder.badge.setVisibility(View.VISIBLE);
                holder.badge.setText(menuItem.getBadgeCount() + "");
            } else {
                holder.badge.setVisibility(View.INVISIBLE);
            }

            holder.navigationItemBaseView.setOnClickListener(menuItem.getOnClickListener());
        }

        private void bindSeparator(NavigationItemViewHolder holder, NavigationItem navigationItem) {

        }

        private void bindAccount(NavigationItemViewHolder holder, NavigationItem navigationItem) {
            NavigationItemAccount itemAccount = (NavigationItemAccount) navigationItem;
            holder.userNameTextView.setText(itemAccount.getUserName());
            holder.userEmailTextView.setText(itemAccount.getUserEmail());
            try {
                if (itemAccount.accountImage == null || itemAccount.accountImage.isEmpty()) {
                    //new ImageLoader(holder.accountProfileImageView).execute(Pref.getUser(context));
                } else {
                    byte[] bytes = Base64.decode(itemAccount.accountImage.getBytes(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    if (bitmap != null) {
                        holder.accountProfileImageView.setImageBitmap(bitmap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return navigationItems.size();
        }


        public static class NavigationItemViewHolder extends RecyclerView.ViewHolder {

            private CircularImageView accountProfileImageView;
            private TextView userNameTextView;
            private TextView userEmailTextView;
            private TextView navigationLabelTextView;
            private ImageView navigationIconImageView;
            private View navigationItemBaseView;
            private View seperator;
            private TextView badge;
            private Drawable selectorDrawable;

            public NavigationItemViewHolder(Context context, View itemView, int type) {
                super(itemView);
                switch (type) {
                    case NAVIGATION_ITEM_TYPE_SEPARATOR:
                        setUpForSeparator(itemView);
                        break;
                    case NAVIGATION_ITEM_TYPE_ACCOUNT:
                        setUpForAccount(context, itemView);
                        break;
                    case NAVIGATION_ITEM_TYPE_MENU_ITEM:
                        setUpForMenuItem(context, itemView);
                        break;
                }
                selectorDrawable = itemView.getBackground();
            }

            private void setUpForMenuItem(Context context, View itemView) {
                navigationItemBaseView = itemView;
                navigationIconImageView = (ImageView) itemView.findViewById(R.id.icon);
                navigationLabelTextView = (TextView) itemView.findViewById(R.id.title);
                badge = (TextView) itemView.findViewById(R.id.badge);
                FontUtil.setDefaultTypeFace(context, navigationLabelTextView, badge);
            }

            private void setUpForAccount(Context context, View itemView) {
                accountProfileImageView = (CircularImageView) itemView.findViewById(R.id.profile_picture);
                userNameTextView = (TextView) itemView.findViewById(R.id.userNameTextView);
                userEmailTextView = (TextView) itemView.findViewById(R.id.userEmailTextView);
                FontUtil.setDefaultTypeFace(context, userNameTextView);
            }

            private void setUpForSeparator(View itemView) {
                seperator = itemView.findViewById(R.id.separator);
            }
        }
    }

    public interface NavigationItem {
        int getType();
    }

    public class NavigationItemAccount implements NavigationItem {

        private String accountImage;
        private String userName;
        private String userEmail;
        private String userCommunity;

        public NavigationItemAccount(String accountImage, String userName, String userEmail, String userCommunity) {
            this.accountImage = accountImage;
            this.userName = userName;
            this.userEmail = userEmail;
            this.userCommunity = userCommunity;
        }

        public String getAccountImage() {
            return accountImage;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserCommunity() {
            return userCommunity;
        }

        @Override
        public int getType() {
            return NAVIGATION_ITEM_TYPE_ACCOUNT;
        }
    }

    public class NavigationItemSeperator implements NavigationItem {

        @Override
        public int getType() {
            return NAVIGATION_ITEM_TYPE_SEPARATOR;
        }
    }

    public class NavigationItemMenuItem implements NavigationItem {

        private boolean selected;

        private String title;
        private int badgeCount;
        private int iconNormal;
        private int iconSelected;

        public int getBadgeCount() {
            return badgeCount;
        }

        public void setBadgeCount(int badgeCount) {
            this.badgeCount = badgeCount;
        }

        private View.OnClickListener onClickListener;

        public NavigationItemMenuItem(boolean selected, String title, int badgeCount, int iconNormal, int iconSelected, View.OnClickListener onClickListener) {
            this.selected = selected;
            this.title = title;
            this.badgeCount = badgeCount;
            this.iconNormal = iconNormal;
            this.iconSelected = iconSelected;
            this.onClickListener = onClickListener;
        }

        public boolean isSelected() {
            return selected;
        }

        public String getTitle() {
            return title;
        }

        public int getIconNormal() {
            return iconNormal;
        }

        public int getIconSelected() {
            return iconSelected;
        }

        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }

        @Override
        public int getType() {
            return NAVIGATION_ITEM_TYPE_MENU_ITEM;
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(Gravity.LEFT);
        return true;
    }


    private boolean isHomeActivity() {
        return getMenuItemType() == NAVIGATION_MENU_ITEM_HOME;
    }
}
