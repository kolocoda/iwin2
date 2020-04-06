package com.ugarsoft.chyma.iwin.activities.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.AboutUsActivity;
import com.ugarsoft.chyma.iwin.activities.EstimateBillsActivity;
import com.ugarsoft.chyma.iwin.activities.NewsDetailsActivity;
import com.ugarsoft.chyma.iwin.custom.EndlessRecyclerOnScrollListener;
import com.ugarsoft.chyma.iwin.gcm.GCMConstant;
import com.ugarsoft.chyma.iwin.gcm.GCMUtils;
import com.ugarsoft.chyma.iwin.gcm.RegistrationIntentService;
import com.ugarsoft.chyma.iwin.interfaces.OnLoadMoreListener;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.service.AlarmReceiver;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Logger;
import com.ugarsoft.chyma.iwin.utils.Notifications;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang.WordUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class HomeActivity extends BaseActivity {
    RecyclerView recyclerView;
    TextView emptyListTextView;
    View emptyListView;
    ProgressBar progressBarTop;
    ProgressBar progressBarBottom;
    CardView estimateButton;
    List<PowerNews> powerNewsList = new ArrayList<>();
    PowerNewsAdapter powerNewsAdapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

    private int paginationIndex = 0;
    private int count = 150;
    private DBService dbService;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private static Logger logger = Logger.getLogger(HomeActivity.class);
    private SwipeRefreshLayout swipeRefreshLayout;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final int NEWS_ITEM_HEADER = 2000;
    private static final int NEWS_ITEM_OTHER = 3000;
    private List<NewsItem> newsItems = new ArrayList<>();
    private static final String TAG = "HomeActivity >> ";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    private boolean isReceiverRegistered;
    private boolean backPressed = false;
    private static final int TIME_DELAY = 2000;


    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpNavigationBar();
        setUpViews();
        setupGCM();
        loadPrevious();
        scheduleAlarm();
        clearBadgeCount();
    }

    private void clearBadgeCount() {
        int count = 0;
        Pref.saveToSharedPreference(this, Constant.NOTIFICATION_COUNT.name(), count);
        int badgeCount = count;
        ShortcutBadger.applyCount(this, badgeCount);
        Log.i("Home Activity","Cleared badge count");
    }

    private void setupGCM() {
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.progressBarTop);
        boolean sentToken = Pref.getBooleanValue(HomeActivity.this, Constant.REGISTERED_GCM.name(), false);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                boolean sentToken = Pref.getBooleanValue(context, Constant.REGISTERED_GCM.name(), false);
                if (sentToken) {
                    // Toast.makeText(HomeActivity.this, getString(R.string.gcm_send_message), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this, getString(R.string.token_error_message), Toast.LENGTH_LONG).show();
                }
            }
        };

        // Registering BroadcastReceiver
        registerReceiver();

        if (!sentToken) {
            if (GCMUtils.checkPlayServices(HomeActivity.this, this)) {
                // Start IntentService to register this application with GCM.
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }

    // Setup a recurring alarm every half hour
    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every 5 seconds
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_HALF_HOUR, pIntent);
    }

    private void setUpViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBarTop = (ProgressBar) findViewById(R.id.progressBarTop);
        progressBarTop.setVisibility(View.GONE);
        progressBarBottom = (ProgressBar) findViewById(R.id.progressBarBottom);
        progressBarBottom.setVisibility(View.GONE);
        emptyListTextView = (TextView) findViewById(R.id.emptyListTextView);
        FontUtil.setDefaultTypeFace(this, emptyListTextView, FontUtil.FontType.ROBOTO_MEDIUM);
        emptyListView = findViewById(R.id.emptyList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        estimateButton = (CardView) findViewById(R.id.estimateButton);
        ImageView estimateB1 =  (ImageView) findViewById(R.id.estimateB1);
        TextView estimateB2 =  (TextView) findViewById(R.id.estimateB2);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.colorPrimary);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int current_page) {
                paginationIndex = current_page;
                progressBarBottom.setVisibility(View.VISIBLE);
                loadMore();
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        powerNewsAdapter = new PowerNewsAdapter(this, newsItems);
        emptyListView.setVisibility(View.GONE);

        powerNewsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (powerNewsAdapter.getItemCount() != 0) {
                    emptyListView.setVisibility(View.GONE);
                } else {
                    emptyListView.setVisibility(View.VISIBLE);
                }
            }
        });

        recyclerView.setAdapter(powerNewsAdapter);
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);


        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EstimateBillsActivity.class);
                startActivity(intent);
            }
        });
        estimateB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EstimateBillsActivity.class);
                startActivity(intent);
            }
        });
        estimateB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EstimateBillsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        progressBarTop.setVisibility(View.GONE);
        loadPrevious();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
        powerNewsList.clear();
        loadPrevious();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }

    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(GCMConstant.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    void loadPrevious() {
        dbService = DBService.getInstance(this);
        if (powerNewsList.isEmpty()) {
            try {
                Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.limit((long) count);
                queryBuilder.orderBy("postId", false);
                powerNewsList.addAll(queryBuilder.query());
                if (powerNewsList.isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2000);
                    new NewsLoader(0, null, null, count).execute();
                } else {
                    newsItems = (addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                    Log.i("NEWS_ITEM", "Assigned values to news item");
                    powerNewsAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Long latestId = powerNewsList.get(0).getPostId();
                Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.where().gt("postId", latestId);
                queryBuilder.orderBy("postId", false);
                queryBuilder.limit((long) count);
                List<PowerNews> powerNewses = queryBuilder.query();

                if (powerNewses != null && !powerNewses.isEmpty()) {
                    powerNewses.addAll(powerNewsList);
                    powerNewsList.clear();
                    newsItems.clear();
                    powerNewsList.addAll(powerNewses);
                    newsItems = (addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                    powerNewsAdapter.notifyDataSetChanged();
                    return;
                }
                new NewsLoader(0, null, latestId, 1000).execute();
            } catch (Exception e) {
                logger.e("Exception at load more", e);
            }
        }
    }

    private List<NewsItem> addPowerNewListToNewsItemList(List<PowerNews> powerNewsList) {
        if (!newsItems.isEmpty()) {
            newsItems.clear();
        }
        if(!powerNewsList.isEmpty()) {
            newsItems.add(new NewsItemHeader(powerNewsList.get(0)));

            for (int i = 1; i < powerNewsList.size() - 1; i++) {
                newsItems.add(new NewsItemOther(powerNewsList.get(i)));

            }
        }
        Log.i("NEWSTAG", "News list size: " +newsItems.size());
        Pref.saveToSharedPreference(HomeActivity.this, Constant.UNREAD_NEWS_COUNT.name(), powerNewsList.size());
        return newsItems;

    }

    private void loadMore() {
        Log.i("Home Activity", "Loading More items");
        if (powerNewsList.size() < count) {
            try {
                Long endId = powerNewsList.get(powerNewsList.size() - 1).getPostId();
                Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.where().lt("postId", endId);
                queryBuilder.limit((long) 20);
                List<PowerNews> myNewsList = queryBuilder.query();

                if (myNewsList != null && !myNewsList.isEmpty()) {
                    powerNewsList.addAll(myNewsList);
                    newsItems.addAll(addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                    powerNewsAdapter.notifyDataSetChanged();
                    progressBarBottom.setVisibility(View.GONE);
                    return;
                }
                new NewsLoader(paginationIndex, endId, null, count).execute();
            } catch (Exception e) {
                logger.e("Exception at load more", e);
            }
        }
    }

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_HOME;
    }


    @Override
    public int getActionBarColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_estimate) {
            Intent intent = new Intent(HomeActivity.this, ManageBillActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_report) {
            Intent intent = new Intent(HomeActivity.this, PowerStatsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_about) {
            Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private class PowerNewsAdapter extends RecyclerView.Adapter<PowerNewsAdapter.NewsItemViewHolder> {
        private List<NewsItem> newsItems;
        private Context context;

        public PowerNewsAdapter(Context context, List<NewsItem> newsItems) {
            this.newsItems = newsItems;
            this.context = context;

        }

        @Override
        public int getItemViewType(int position) {
            if (newsItems.get(position) instanceof  NewsItemHeader){
                return NEWS_ITEM_HEADER;
            } else {
                return NEWS_ITEM_OTHER;
            }
        }

        @Override
        public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = null;
            LayoutInflater inflater = LayoutInflater.from(context);

            switch (viewType) {
                case NEWS_ITEM_HEADER:
                    itemView = inflater.inflate(R.layout.list_item_news_header, parent, false);
                    break;
                case NEWS_ITEM_OTHER:
                    itemView = inflater.inflate(R.layout.list_item_news, parent, false);
                    break;
            }
            return new NewsItemViewHolder(context, itemView, viewType);
        }

        @Override
        public void onBindViewHolder(NewsItemViewHolder holder, int position) {
            switch (newsItems.get(position).getType()) {
                case NEWS_ITEM_HEADER:
                    bindHeaderNewsItems(holder, newsItems.get(position));
                    break;
                case NEWS_ITEM_OTHER:
                    bindOtherNewsItems(holder, newsItems.get(position));
                    break;

            }
        }

        private void bindOtherNewsItems(NewsItemViewHolder holder, NewsItem newsItem) {
            NewsItemOther newsItemOther = (NewsItemOther) newsItem;
            PowerNews news = newsItemOther.getNews();
            Long newsDate = news.getPostDate();

            if (newsDate != null) {
                holder.newsPostDateTextView.setText(dateFormat.format(newsDate));
            } else {
                holder.newsPostDateTextView.setText("--/--/-- --:--");
            }
            holder.newsPostByTextView.setText(WordUtils.capitalize(news.getPostBy()));

            String imageString = news.getPostImage();
            if (imageString != null && !imageString.isEmpty()) {
                //new PictureUtil.PictureLoader(holder.newsLogo, imageString).execute();
                Picasso.with(HomeActivity.this).load(imageString)
                        .placeholder(R.drawable.ic_menu_news)
                        .resize(70, 70)
                        .centerCrop()
                        .into(holder.newsLogo);
            } else {
                holder.newsLogo.setImageResource(R.drawable.no_image);
            }

            if (!news.isRead()) {
                holder.newsTitleTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            else{
                holder.newsTitleTextView.setTextColor(getResources().getColor(R.color.greyText));
            }
            holder.newsTitleTextView.setText(news.getPostTitle());
            holder.newsContentTextView.setText(Html.fromHtml(news.getPostContent()));
            holder.newsCard.setTag(news);
            holder.newsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PowerNews news = (PowerNews) view.getTag();
                    Pref.saveNewsContent(HomeActivity.this, news);
                    Pref.saveToSharedPreference(HomeActivity.this, Constant.TOGGLE.name(), Constant.POWER_NEWS.name());

                    new MarkNews(news).execute();
                    Intent intent = new Intent(HomeActivity.this, NewsDetailsActivity.class);
                    startActivity(intent);
                }
            });
        }

        private void bindHeaderNewsItems(NewsItemViewHolder holder, NewsItem newsItem) {
            NewsItemHeader newsItemHeader = (NewsItemHeader) newsItem;
            PowerNews news = newsItemHeader.getNews();


            String imageString = news.getPostImage();
            if (imageString != null && !imageString.isEmpty()) {
                //new PictureUtil.PictureLoader(holder.newsLogo, imageString).execute();
                Picasso.with(HomeActivity.this).load(imageString)
                        .placeholder(R.drawable.no_image)
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)
                        .into(holder.newsHeaderLogo);
            } else {
                holder.newsHeaderLogo.setImageResource(R.drawable.no_image);
            }

            holder.newsHeaderTitleTextView.setText(news.getPostTitle());
            holder.newsPicture.setTag(news);
            holder.newsPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PowerNews news = (PowerNews) view.getTag();
                    Pref.saveNewsContent(HomeActivity.this, news);
                    Pref.saveToSharedPreference(HomeActivity.this, Constant.TOGGLE.name(), Constant.POWER_NEWS.name());
                    Intent intent = new Intent(HomeActivity.this, NewsDetailsActivity.class);
                    startActivity(intent);
                }
            });
        }

        public class NewsItemViewHolder extends RecyclerView.ViewHolder {

            private TextView newsTitleTextView;
            private TextView newsContentTextView;
            private TextView newsHeaderTitleTextView;
            private TextView newsPostDateTextView;
            private TextView newsPostByTextView;
            private CardView newsCard;
            private View newsPicture;

            private ImageView newsLogo;
            private ImageView newsHeaderLogo;

            public NewsItemViewHolder(Context context, View itemView, int type) {
                super(itemView);
                switch (type) {
                    case NEWS_ITEM_HEADER:
                        setUpForHeader(context, itemView);
                        break;
                    case NEWS_ITEM_OTHER:
                        setUpForOtherItem(context, itemView);
                        break;
                }
            }

            private void setUpForHeader(Context context, View itemView) {
                newsHeaderLogo = (ImageView) itemView.findViewById(R.id.newsHeaderLogo);
                newsPicture = itemView.findViewById(R.id.newsPicture);
                newsHeaderTitleTextView = (TextView) itemView.findViewById(R.id.newsHeaderTitleTextView);

                FontUtil.setNewsBoldTypeFace(context, newsHeaderTitleTextView);
            }

            private void setUpForOtherItem(Context context, View itemView) {
                newsTitleTextView = (TextView) itemView.findViewById(R.id.newsTitleTextView);
                newsContentTextView = (TextView) itemView.findViewById(R.id.newsContentTextView);
                newsPostDateTextView = (TextView) itemView.findViewById(R.id.newsPostDateTextView);
                newsPostByTextView = (TextView) itemView.findViewById(R.id.newsPostByTextView);
                newsCard = (CardView) itemView.findViewById(R.id.newsCard);

                FontUtil.setDefaultTypeFace(context, newsPostByTextView, newsPostDateTextView);
                FontUtil.setDefaultTypeFace(context, newsTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);
                newsLogo = (ImageView) itemView.findViewById(R.id.newsLogo);
            }

        }

        @Override
        public int getItemCount() {
            return newsItems.size();
        }

    }

    public interface NewsItem {
        int getType();
    }

    public class NewsItemOther implements NewsItem {

        private PowerNews news;

        public NewsItemOther(PowerNews news) {
            this.news = news;
        }

        @Override
        public int getType() {
            return NEWS_ITEM_OTHER;
        }

        public PowerNews getNews() {
            return news;
        }

        public void setNews(PowerNews news) {
            this.news = news;
        }
    }

    public class NewsItemHeader implements NewsItem {

        private PowerNews news;

        public NewsItemHeader(PowerNews news) {
            this.news = news;
        }

        @Override
        public int getType() {
            return NEWS_ITEM_HEADER;
        }

        public PowerNews getNews() {
            return news;
        }

        public void setNews(PowerNews news) {
            this.news = news;
        }
    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitleTextView;
        private TextView newsPostDateTextView;
        private TextView newsPostByTextView;
        private CardView newsCard;

        private ImageView newsLogo;

        public TaskViewHolder(Context context, View itemView) {
            super(itemView);
            newsTitleTextView = (TextView) itemView.findViewById(R.id.newsTitleTextView);
            newsPostDateTextView = (TextView) itemView.findViewById(R.id.newsPostDateTextView);
            newsPostByTextView = (TextView) itemView.findViewById(R.id.newsPostByTextView);
            newsCard = (CardView) itemView.findViewById(R.id.newsCard);

            FontUtil.setDefaultTypeFace(context, newsPostByTextView, newsPostDateTextView);
            FontUtil.setDefaultTypeFace(context, newsTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);
            newsLogo = (ImageView) itemView.findViewById(R.id.newsLogo);

        }
    }


    private class NewsLoader extends AsyncTask<Void, Void, Boolean> {
        int index;
        int count;

        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(HomeActivity.this);

        List<PowerNews> NewMessages;
        AsyncTask loader = this;

        public NewsLoader(int index, Long dateBefore, Long dateAfter, int count) {
            this.index = index;
            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
            this.count = count;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (idBefore != null) {
                progressBarBottom.setVisibility(View.VISIBLE);
            } else {
                progressBarTop.setVisibility(View.VISIBLE);
            }

            Handler mHandler = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (loader.getStatus() == Status.RUNNING){
                        loader.cancel(true);
                    }
                }
            };
            mHandler.postDelayed(r, 20000);
            Pref.saveToSharedPreference(HomeActivity.this, Constant.LOADING_DATA.name(), true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                Log.i("Response >>", "Getting News");
                responseObject = WebServiceImpl.getNews();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<PowerNews> newsList = responseObject.getRecentPowerNews();

                    for (PowerNews news : newsList) {
                        try {
                            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                            dao.create(news);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    for (PowerNews JMCTask : powerNewsList) {
                        newsList.add(JMCTask);
                    }
                    powerNewsList.clear();
                    powerNewsList.addAll(newsList);
                    newsItems.addAll(addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                    return true;
                }
            } else if (idAfter != null && idBefore == null) {
                Log.i("Response >>", "Getting News After");
                responseObject = WebServiceImpl.getNewsAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    NewMessages = responseObject.getRecentPowerNews();

                    for (PowerNews news : NewMessages) {
                        try {
                            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                            dao.create(news);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    List<PowerNews> newsList = new ArrayList<>();
                    for (PowerNews JMCTask : powerNewsList) {
                        newsList.add(JMCTask);
                    }
                    powerNewsList.clear();
                    powerNewsList.addAll(newsList);
                    newsItems.addAll(addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                    return true;
                }
            } else if (idBefore > 0){
                Log.i("Response >>", "Getting News Before");
                responseObject = WebServiceImpl.getNewsBefore(idBefore);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<PowerNews> newsList = responseObject.getRecentPowerNews();
                    for (PowerNews news : newsList) {
                        try {
                            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                            dao.create(news);
                            powerNewsList.add(news);
                            newsItems.addAll(addPowerNewListToNewsItemList(powerNewsList));//add the news item into adapter for newsList
                        } catch (Exception e) {
                            logger.e("Exception while adding news", e);
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (NewMessages != null && NewMessages.size() > 0) {
                List<String> msgList = new ArrayList<>();
                for (PowerNews news : NewMessages) {
                    msgList.add(news.getPostTitle());
                }
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                if (NewMessages.size() == 1) {
                    Notifications.notify(HomeActivity.this, "Power Sector News", msgList, intent);
                } else {
                    Notifications.notify(HomeActivity.this, "Power Sector News", msgList, intent);
                }
            }
            if (aBoolean == Boolean.TRUE) {
                powerNewsAdapter.notifyDataSetChanged();
            }
            if (idBefore != null) {
                progressBarBottom.setVisibility(View.GONE);

            } else {
                progressBarTop.setVisibility(View.GONE);
            }
            if (powerNewsList.isEmpty()) {
                emptyListView.setVisibility(View.VISIBLE);
            }
            swipeRefreshLayout.setRefreshing(false);
            Pref.saveToSharedPreference(HomeActivity.this, Constant.LOADING_DATA.name(), false);
        }
    }

    private class MarkNews extends AsyncTask<Void, Void, Void> {

        PowerNews news;
        DBService dbService;
        public  MarkNews(PowerNews news){
            this.news = news;
        }

        @Override
        protected void onPreExecute() {
            dbService = DBService.getInstance(HomeActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                QueryBuilder<PowerNews, Long> qb = dao.queryBuilder();
                qb.where().eq("postId", news.getPostId());
                PowerNews thisNews = qb.queryForFirst();

                thisNews.setRead(true);
                dao.update(thisNews);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            powerNewsAdapter.notifyDataSetChanged();
        }
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            backPressed = false;
        }
    };

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (backPressed) {
            super.onBackPressed();
            return;
        } else {
            this.backPressed = true;
            Toast.makeText(HomeActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(runnable, TIME_DELAY);
        }
    }
}
