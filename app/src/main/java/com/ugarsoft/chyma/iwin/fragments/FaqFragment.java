package com.ugarsoft.chyma.iwin.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.FAQsDetailsActivity;
import com.ugarsoft.chyma.iwin.custom.EndlessRecyclerOnScrollListener;
import com.ugarsoft.chyma.iwin.models.FAQ;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.Logger;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.apache.commons.lang.WordUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FaqFragment extends Fragment {
    RecyclerView recyclerView;
    View emptyListView;
    ProgressBar progressBarTop;
    ProgressBar progressBarBottom;
    List<FAQ> faqArrayList = new ArrayList<>();
    FAQAdapter faqAdapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

    private int paginationIndex = 0;
    private int count = 150;
    private DBService dbService;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private static Logger logger = Logger.getLogger(FAQ.class);
    private SwipeRefreshLayout swipeRefreshLayout;

    public FaqFragment() {
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
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        setUpViews(view);
        loadPrevious();
        return view;
    }

    private void setUpViews(View view) {
        dbService = DBService.getInstance(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBarTop = (ProgressBar) view.findViewById(R.id.progressBarTop);
        progressBarTop.setVisibility(View.GONE);
        progressBarBottom = (ProgressBar) view.findViewById(R.id.progressBarBottom);
        progressBarBottom.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

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
        faqAdapter = new FAQAdapter(getActivity(), faqArrayList);

        recyclerView.setAdapter(faqAdapter);
        recyclerView.setOnScrollListener(endlessRecyclerOnScrollListener);
    }

    private void refreshContent() {
        swipeRefreshLayout.setRefreshing(true);
        loadPrevious();
    }

    void loadPrevious() {
        if (faqArrayList.isEmpty()) {
            try {
                Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.limit((long) count);
                queryBuilder.orderBy("postCode", false);
                faqArrayList.addAll(queryBuilder.query());
                if (faqArrayList.isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2000);
                    new NewsLoader(0, null, null, count).execute();
                } else {
                    faqAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Long latestId = faqArrayList.get(0).getPostCode();
                Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
                QueryBuilder queryBuilder = dao.queryBuilder();
                queryBuilder.where().gt("postCode", latestId);
                queryBuilder.orderBy("postCode", false);
                queryBuilder.limit((long) count);
                List<FAQ> faqs = queryBuilder.query();

                if (faqs != null && !faqs.isEmpty()) {
                    faqs.addAll(faqArrayList);
                    faqArrayList.clear();
                    faqArrayList.addAll(faqs);
                    faqAdapter.notifyDataSetChanged();
                    return;
                }
                new NewsLoader(0, null, latestId, 1000).execute();
            } catch (Exception e) {
                logger.e("Exception at load more", e);
            }
        }
    }

    private void loadMore() {
        if (faqArrayList.size() < count || faqArrayList.size() % count != 0) {
            return;
        }
        try {
            Long endId = faqArrayList.get(faqArrayList.size() - 1).getPostCode();
            Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.where().lt("postCode", endId);
            queryBuilder.limit((long) count);
            List<FAQ> myNewsList = queryBuilder.query();

            if (myNewsList != null && !myNewsList.isEmpty()) {
                faqArrayList.addAll(myNewsList);
                faqAdapter.notifyDataSetChanged();
                return;
            }
            new NewsLoader(paginationIndex, endId, null, count).execute();
        } catch (Exception e) {
            logger.e("Exception at load more", e);
        }
    }

    private class FAQAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        Context context;
        List<FAQ> faqList;
        DBService dbService;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

        public FAQAdapter(Context context, List<FAQ> faqs) {
            this.context = context;
            this.faqList = faqs;
            dbService = DBService.getInstance(context);
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_faqs, null);
            return new TaskViewHolder(context, view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            FAQ news = faqList.get(position);
            Long newsDate = news.getPostDate();


            if (newsDate != null) {
                holder.newsPostDateTextView.setText(dateFormat.format(newsDate));
            } else {
                holder.newsPostDateTextView.setText("--/--/-- --:--");
            }
            holder.newsPostByTextView.setText(WordUtils.capitalize(news.getPostBy()));


            holder.newsTitleTextView.setText(news.getPostTitle());
            holder.newsContentTextView.setText(Html.fromHtml(news.getPostContent()));
            holder.newsCard.setTag(news);
            holder.newsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FAQ faq = (FAQ) view.getTag();
                    Pref.saveFAQToSharedPref(getActivity(), faq);
                    Pref.saveToSharedPreference(getActivity(), Constant.TOGGLE.name(), Constant.FAQ.name());
                    Intent intent = new Intent(getActivity(), FAQsDetailsActivity.class);
                    startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return faqList.size();
        }

    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitleTextView;
        private TextView newsContentTextView;
        private TextView newsPostDateTextView;
        private TextView newsPostByTextView;
        private CardView newsCard;


        public TaskViewHolder(Context context, View itemView) {
            super(itemView);
            newsTitleTextView = (TextView) itemView.findViewById(R.id.newsTitleTextView);
            newsContentTextView = (TextView) itemView.findViewById(R.id.newsContentTextView);
            newsPostDateTextView = (TextView) itemView.findViewById(R.id.newsPostDateTextView);
            newsPostByTextView = (TextView) itemView.findViewById(R.id.newsPostByTextView);
            newsCard = (CardView) itemView.findViewById(R.id.newsCard);

            FontUtil.setDefaultTypeFace(context, newsPostByTextView, newsPostDateTextView);
            FontUtil.setDefaultTypeFace(context, newsTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);


        }
    }

    private class NewsLoader extends AsyncTask<Void, Void, Boolean> {
        int index;
        int count;

        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(getActivity());

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
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                responseObject = WebServiceImpl.getFaq();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<FAQ> faqs = responseObject.getFaqList();
                    for (FAQ news : faqs) {
                        try {
                            Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
                            dao.create(news);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    for (FAQ faq : faqArrayList) {
                        faqs.add(faq);
                    }
                    faqArrayList.clear();
                    faqArrayList.addAll(faqs);
                    return true;
                }
            }else  if (idAfter != null && idBefore == null ) {
                responseObject = WebServiceImpl.getFaqAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<FAQ> newsList = responseObject.getFaqList();

                    for (FAQ news : newsList) {
                        try {
                            Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
                            dao.create(news);
                        } catch (Exception e) {
                            logger.e("Exception while creating task", e);
                        }
                    }
                    for (FAQ faq : faqArrayList) {
                        newsList.add(faq);
                    }
                    faqArrayList.clear();
                    faqArrayList.addAll(newsList);
                    return true;
                }
            } else {
                /*responseObject = WebServiceImpl.getNewsBefore(dateFormat.format(idBefore));
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    List<FAQ> newsList = responseObject.getFaqList();
                    for (FAQ news : newsList) {
                        try {
                            Dao<FAQ, Long> dao = dbService.getDao(FAQ.class);
                            dao.create(news);
                            faqArrayList.add(news);
                        } catch (Exception e) {
                            logger.e("Exception while adding news", e);
                        }
                    }
                    return true;
                }*/
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == Boolean.TRUE) {
                faqAdapter.notifyDataSetChanged();
            }
            if (idBefore != null) {
                progressBarBottom.setVisibility(View.GONE);

            } else {
                progressBarTop.setVisibility(View.GONE);
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
