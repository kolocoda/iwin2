package com.ugarsoft.chyma.iwin.models;

import java.util.List;

/**
 * Created by Chyma on 5/7/2016.
 */
public class ResponseObject {
    private int statusCode;
    private String base64EncodedData;
    private List<PowerNews> recentPowerNews;
    private List<FAQ> faqList;
    private List<Announcement> announcementList;
    private List<PowerStats> powerStatsList;
    private List<PowerChart> powerChartList;
    private AppUser appUser;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBase64EncodedData() {
        return base64EncodedData;
    }

    public void setBase64EncodedData(String base64EncodedData) {
        this.base64EncodedData = base64EncodedData;
    }

    public List<PowerNews> getRecentPowerNews() {
        return recentPowerNews;
    }

    public void setRecentPowerNews(List<PowerNews> recentPowerNews) {
        this.recentPowerNews = recentPowerNews;
    }

    public List<FAQ> getFaqList() {
        return faqList;
    }

    public void setFaqList(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    public List<PowerStats> getPowerStatsList() {
        return powerStatsList;
    }

    public void setPowerStatsList(List<PowerStats> powerStatsList) {
        this.powerStatsList = powerStatsList;
    }

    public List<PowerChart> getPowerChartList() {
        return powerChartList;
    }

    public void setPowerChartList(List<PowerChart> powerChartList) {
        this.powerChartList = powerChartList;
    }
}
