package com.ugarsoft.chyma.iwin.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.ugarsoft.chyma.iwin.activities.base.HomeActivity;
import com.ugarsoft.chyma.iwin.activities.base.PowerStatsActivity;
import com.ugarsoft.chyma.iwin.models.PowerChart;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.models.PowerStats;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.Constant;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.Notifications;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class NewsRefreshService extends IntentService {

    DBService dbService;

    public NewsRefreshService() {
        super("NewsRefreshService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i("NewsRefreshService", "Service running");
            boolean check = Pref.getBooleanValue(NewsRefreshService.this, Constant.LOADING_DATA.name(), false);
            Log.i("NewsRefreshService", "Loading data in activity: " + check);
            if (!check) {
                Long idAfter = getLatestNewsId();
                if (idAfter != 0L) {
                    new NewsLoader(null, idAfter).getData();
                } else {
                    new NewsLoader(null, null).getData();
                }
                Long reportId = getLatestReportId();
                Log.i("NewsRefreshService", "ReportId: " + reportId);
                if (reportId != 0L) {
                    new ReportLoader(null, reportId).getReports();
                } else {
                    new ReportLoader(null, null).getReports();
                }
                Long chartId = getLatestChartId();
                Log.i("NewsRefreshService", "chartId: " + chartId);
                if (chartId != 0L) {
                    new ChartLoader(null, chartId).getCharts();
                } else {
                    new ChartLoader(null, null).getCharts();
                }
            }

        }
    }

    private Long getLatestNewsId() {
        dbService = DBService.getInstance(NewsRefreshService.this);
        List<PowerNews> powerNewsList = new ArrayList<>();
        try {
            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy("postId", false);
            powerNewsList.addAll(queryBuilder.query());
            if (!powerNewsList.isEmpty()) {
                return powerNewsList.get(0).getPostId();
            } else {
                return 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private Long getLatestReportId() {
        dbService = DBService.getInstance(NewsRefreshService.this);
        List<PowerStats> statsList = new ArrayList<>();
        try {
            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy("reportId", false);
            statsList.addAll(queryBuilder.query());
            if (!statsList.isEmpty()) {
                return statsList.get(0).getReportId();
            } else {
                return 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private Long getLatestChartId() {
        dbService = DBService.getInstance(NewsRefreshService.this);
        List<PowerChart> statsList = new ArrayList<>();
        try {
            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy("chartId", false);
            statsList.addAll(queryBuilder.query());
            if (!statsList.isEmpty()) {
                return statsList.get(0).getChartId();
            } else {
                return 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private class NewsLoader {


        Long idAfter;
        Long idBefore;
        DBService dbService = DBService.getInstance(NewsRefreshService.this);
        List<PowerNews> NewMessages;

        public NewsLoader(Long dateBefore, Long dateAfter) {

            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
        }


        public void getData() {
            ResponseObject responseObject;
            if (idAfter != null && idBefore == null) {
                Log.i("NewsRefreshService", "Getting News After");
                responseObject = WebServiceImpl.getNewsAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    NewMessages = responseObject.getRecentPowerNews();

                    for (PowerNews news : NewMessages) {
                        try {
                            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                            dao.create(news);
                        } catch (Exception e) {
                            Log.e("NewsRefreshService", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    notifyUser();
                }
            } else if (idAfter == null && idBefore == null) {
                Log.i("NewsRefreshService >>", "Getting News");
                responseObject = WebServiceImpl.getNews();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    NewMessages = responseObject.getRecentPowerNews();

                    for (PowerNews news : NewMessages) {
                        try {
                            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
                            dao.create(news);
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                    notifyUser();
                }
            }
        }

        private void notifyUser() {
            if (NewMessages != null && NewMessages.size() > 0) {
                List<String> msgList = new ArrayList<>();
                for (PowerNews news : NewMessages) {
                    msgList.add(news.getPostTitle());
                }
                Intent intent = new Intent(NewsRefreshService.this, HomeActivity.class);
                if (NewMessages.size() == 1) {
                    Notifications.notify(NewsRefreshService.this, "Power Sector News", msgList, intent);
                } else {
                    Notifications.notify(NewsRefreshService.this, "Power Sector News", msgList, intent);
                }
                int count = Pref.getIntegerValue(NewsRefreshService.this, Constant.NOTIFICATION_COUNT.name(), 0);
                count += NewMessages.size();
                Pref.saveToSharedPreference(NewsRefreshService.this, Constant.NOTIFICATION_COUNT.name(), count);
                int badgeCount = count;
                ShortcutBadger.applyCount(NewsRefreshService.this, badgeCount);
            }
        }
    }

    private class ReportLoader {
        int index;
        int count;

        Long idAfter;
        Long idBefore;
        List<PowerStats> newStats;
        DBService dbService = DBService.getInstance(NewsRefreshService.this);

        public ReportLoader(Long dateBefore, Long dateAfter) {
            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
        }


        public void getReports() {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                Log.i("NewsRefreshService", "Getting Reports");
                responseObject = WebServiceImpl.getReports();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    newStats = responseObject.getPowerStatsList();
                    for (PowerStats news : newStats) {
                        try {
                            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
                            dao.create(news);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    notifyUserReport();
                }
            } else if (idAfter != null && idBefore == null) {
                Log.i("NewsRefreshService", "Getting Report After");
                responseObject = WebServiceImpl.getReportAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    newStats = responseObject.getPowerStatsList();
                    for (PowerStats news : newStats) {
                        try {
                            Dao<PowerStats, Long> dao = dbService.getDao(PowerStats.class);
                            dao.create(news);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    notifyUserReport();
                }
            }
        }

        private void notifyUserReport() {
            if (newStats != null && newStats.size() > 0) {
                List<String> msgList = new ArrayList<>();
                for (PowerStats news : newStats) {
                    msgList.add("Available Power Generation Capacity: " + String.valueOf(news.getAvailableCapMW()));
                }
                Intent intent = new Intent(NewsRefreshService.this, PowerStatsActivity.class);
                if (newStats.size() == 1) {
                    Notifications.notifyReport(NewsRefreshService.this, "Power Gen. Report", msgList, intent);
                } else {
                    Notifications.notifyReport(NewsRefreshService.this, "Power Gen, Report", msgList, intent);
                }
                int count = Pref.getIntegerValue(NewsRefreshService.this, Constant.NOTIFICATION_COUNT.name(), 0);
                count += newStats.size();
                Pref.saveToSharedPreference(NewsRefreshService.this, Constant.NOTIFICATION_COUNT.name(), count);
                int badgeCount = count;
                ShortcutBadger.applyCount(NewsRefreshService.this, badgeCount);
            }
        }
    }

    private class ChartLoader {
        int index;
        int count;

        Long idAfter;
        Long idBefore;
        List<PowerChart> chartList;
        DBService dbService = DBService.getInstance(NewsRefreshService.this);

        public ChartLoader(Long dateBefore, Long dateAfter) {
            this.idBefore = dateBefore;
            this.idAfter = dateAfter;
        }


        public void getCharts() {
            ResponseObject responseObject;
            if (idAfter == null && idBefore == null) {
                Log.i("NewsRefreshService", "Getting Reports");
                responseObject = WebServiceImpl.getChart();
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    chartList = responseObject.getPowerChartList();
                    for (PowerChart chart : chartList) {
                        try {
                            Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
                            dao.create(chart);
                        } catch (Exception e) {
                            Log.i("PowerStatActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            } else if (idAfter != null && idBefore == null) {
                Log.i("NewsRefreshService", "Getting Chart After");
                responseObject = WebServiceImpl.getChartAfter(idAfter);
                if (responseObject != null && responseObject.getStatusCode() == 200) {
                    chartList = responseObject.getPowerChartList();
                    if(!chartList.isEmpty()) {
                        for (PowerChart chart : chartList) {
                            try {
                                Dao<PowerChart, Long> dao = dbService.getDao(PowerChart.class);
                                dao.create(chart);
                            } catch (Exception e) {
                                Log.i("NewsRefreshService", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
