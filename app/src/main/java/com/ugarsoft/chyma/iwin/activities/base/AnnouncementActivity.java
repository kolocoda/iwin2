package com.ugarsoft.chyma.iwin.activities.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.AnnouncementsDetailsActivity;
import com.ugarsoft.chyma.iwin.custom.EndlessRecyclerOnScrollListener;
import com.ugarsoft.chyma.iwin.gcm.GCMConstant;
import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Logger;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.apache.commons.lang.WordUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnnouncementActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView emptyListTextView;
    View emptyListView;
    ProgressBar progressBarTop;
    ProgressBar progressBarBottom;
    AnnouncementAdapter announcementAdapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

    private int paginationIndex = 0;
    private int count = 150;
    private DBService dbService;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<Announcement> announcementList = new ArrayList<>();
    private View emptyList;
    private static Logger logger = Logger.getLogger(Announcement.class);

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        setUpNavigationBar();
        setupViews();
        loadPrevious();
        setupGCM();
    }

    private void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBarTop = (ProgressBar) findViewById(R.id.progressBarTop);
        progressBarTop.setVisibility(View.GONE);
        progressBarBottom = (ProgressBar) findViewById(R.id.progressBarBottom);
        progressBarBottom.setVisibility(View.GONE);
        emptyListTextView = (TextView) findViewById(R.id.emptyListTextView);
        FontUtil.setDefaultTypeFace(this, emptyListTextView, FontUtil.FontType.ROBOTO_MEDIUM);
        emptyListView = findViewById(R.id.emptyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

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
                loadMore();
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        announcementAdapter = new AnnouncementAdapter(this, announcementList);
        emptyListView.setVisibility(View.GONE);

        announcementAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (announcementAdapter.getItemCount() != 0) {
                    emptyListView.setVisibility(View.GONE);
                } else {
                    emptyListView.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView.setAdapter(announcementAdapter);
        recyclerView.setOnScrollListener(endlessRecyclerOnScrollListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        AppUser user =Pref.getUser(AnnouncementActivity.this);
        if( user != null){
            if(user.getUsertype() != null) {
                if (user.getUsertype().trim().equalsIgnoreCase("admin")) {
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.GONE);
                }
            }
        } else {
            fab.setVisibility(View.GONE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = LayoutInflater.from(AnnouncementActivity.this).inflate(R.layout.dialog_new_announcement, null);


                final EditText title = (EditText) view.findViewById(R.id.announce_title);
                final EditText content = (EditText) view.findViewById(R.id.announce_text);


                AlertDialog.Builder builder = new AlertDialog.Builder(AnnouncementActivity.this);
                builder.setView(view);
                builder.setPositiveButton("POST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Announcement a = new Announcement();
                        a.setPostTitle(title.getText().toString());
                        a.setPostContent(content.getText().toString());
                        a.setDiscoCode("General");
                        new PostAnnouncement(a).execute();
                        Log.i("Announcement", "Attempt Post");
                        dialogInterface.dismiss();

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();


            }

        });

    }

    private void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        loadPrevious();
    }

    private void setupGCM() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.i("Announcement Activity","Notification Broadcast Received");
            }
        };
        // Registering BroadcastReceiver
        registerReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
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
                    new IntentFilter(GCMConstant.NEW_ANNOUNCEMENT));
            isReceiverRegistered = true;
        }
    }

    private void loadPrevious() {
        dbService = DBService.getInstance(AnnouncementActivity.this);
        if (announcementList.isEmpty()) {
            try {
                Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.limit((long) count);
                queryBuilder.distinct();
                queryBuilder.orderBy("postId", false);
                announcementList.addAll(queryBuilder.query());
                if (announcementList.isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2000);
                    new AnnoucementLoader(0, null, null, count).execute();
                } else {
                    Pref.saveToSharedPreference(AnnouncementActivity.this, Constant.UNREAD_ANNOUNCEMENT_COUNT.name(), announcementList.size());
                    announcementAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadNewAnnouncement();
        }
    }

    public void loadNewAnnouncement() {
        try {
            Long latestId = announcementList.get(0).getPostId();
            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.where().gt("postId", latestId);
            queryBuilder.orderBy("postId", false);
            queryBuilder.limit((long) count);
            List<Announcement> announcements = queryBuilder.query();

            if (announcements != null && !announcements.isEmpty()) {
                announcements.addAll(announcementList);
                announcementList.clear();
                announcementList.addAll(announcements);
                announcementAdapter.notifyDataSetChanged();
                return;
            }
            new AnnoucementLoader(0, null, latestId, 1000).execute();
        } catch (Exception e) {
            logger.e("Exception at load more", e);
        }
    }

    private void loadMore() {
        if (announcementList.size() < count || announcementList.size() % count != 0) {
            return;
        }
        try {
            Long endId = announcementList.get(announcementList.size() - 1).getPostId();
            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.where().lt("postId", endId);
            queryBuilder.limit((long) count);
            List<Announcement> myNewsList = queryBuilder.query();

            if (myNewsList != null && !myNewsList.isEmpty()) {
                announcementList.addAll(myNewsList);
                announcementAdapter.notifyDataSetChanged();
                return;
            }
            new AnnoucementLoader(paginationIndex, endId, null, count).execute();
        } catch (Exception e) {
            logger.e("Exception at load more", e);
        }
    }


    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_OPERATOR_ANNOUNCEMENT;
    }

    private class AnnouncementAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        Context context;
        List<Announcement> newsList;
        DBService dbService;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

        public AnnouncementAdapter(Context context, List<Announcement> newsList) {
            this.context = context;
            this.newsList = newsList;
            dbService = DBService.getInstance(context);
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_announcement, null);
            return new TaskViewHolder(context, view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            Announcement news = newsList.get(position);
            Long newsDate = news.getPostDate();


            if (newsDate != null) {
                holder.newsPostDateTextView.setText(dateFormat.format(newsDate));
            } else {
                holder.newsPostDateTextView.setText("--/--/-- --:--");
            }
            holder.newsPostByTextView.setText(WordUtils.capitalize(news.getPostBy()));


            holder.newsTitleTextView.setText(news.getPostTitle());
            holder.newsCard.setTag(news);
            holder.newsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Announcement news = (Announcement) view.getTag();
                    Pref.saveAnnouncementContent(AnnouncementActivity.this, news);
                    Pref.saveToSharedPreference(AnnouncementActivity.this, Constant.TOGGLE.name(), Constant.ANNOUNCEMENT.name());
                    Intent intent = new Intent(AnnouncementActivity.this, AnnouncementsDetailsActivity.class);
                    startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return newsList.size();
        }

    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitleTextView;
        private TextView newsPostDateTextView;
        private TextView newsPostByTextView;
        private CardView newsCard;


        public TaskViewHolder(Context context, View itemView) {
            super(itemView);
            newsTitleTextView = (TextView) itemView.findViewById(R.id.newsTitleTextView);
            newsPostDateTextView = (TextView) itemView.findViewById(R.id.newsPostDateTextView);
            newsPostByTextView = (TextView) itemView.findViewById(R.id.newsPostByTextView);
            newsCard = (CardView) itemView.findViewById(R.id.newsCard);

            FontUtil.setDefaultTypeFace(context, newsPostByTextView, newsPostDateTextView);
            FontUtil.setDefaultTypeFace(context, newsTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);

        }
    }


    private class AnnoucementLoader extends AsyncTask<Void, Void, Boolean> {
        int index;
        int count;

        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(AnnouncementActivity.this);

        public AnnoucementLoader(int index, Long idBefore, Long idAfter, int count) {
            this.index = index;
            this.idBefore = idBefore;
            this.idAfter = idAfter;
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
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                Log.i("Announce Activity","Getting announcement");
                responseObject = WebServiceImpl.getAnnouncements();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<Announcement> faqs = responseObject.getAnnouncementList();
                    for (Announcement announcement : faqs) {
                        try {
                            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
                            dao.create(announcement);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    for (Announcement faq : announcementList) {
                        faqs.add(faq);
                    }
                    announcementList.clear();
                    announcementList.addAll(faqs);
                    return true;
                }
            } else if (idAfter != null && idBefore == null) {
                Log.i("Announce Activity","Getting announcement after");
                responseObject = WebServiceImpl.getAnnouncementAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<Announcement> newsList = responseObject.getAnnouncementList();

                    for (Announcement news : newsList) {
                        try {
                            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
                            dao.create(news);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    for (Announcement announcement : announcementList) {
                        newsList.add(announcement);
                    }
                    announcementList.clear();
                    announcementList.addAll(newsList);
                    return true;
                }
            } else {
                //do nothing
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == Boolean.TRUE) {

            }
            if (idBefore != null) {
                progressBarBottom.setVisibility(View.GONE);

            } else {
                progressBarTop.setVisibility(View.GONE);
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class PostAnnouncement extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog progressDialog;
        AppUser user;
        Announcement announcement;

        public PostAnnouncement(Announcement announcement) {
            this.announcement = announcement;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(AnnouncementActivity.this, "", "Posting announcement ...");
            user = Pref.getUser(AnnouncementActivity.this);
            Log.i("Announcement >>", "Attempting Post");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;

            responseObject = WebServiceImpl.postAnnouncement(announcement, user);
            if (responseObject != null && responseObject.getStatusCode() == 200) {

                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            if (aBoolean == Boolean.TRUE) {
                MessageUtil.showAlert(AnnouncementActivity.this, "Your announcement was sent successfully");
            } else {
                MessageUtil.showAlert(AnnouncementActivity.this, "Oops!! something went wrong. Try again later or contact support.");
            }
        }
    }
}
