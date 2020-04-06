package com.ugarsoft.chyma.iwin.utils;

import android.util.Log;

import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.FAQ;
import com.ugarsoft.chyma.iwin.models.PowerChart;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.models.PowerStats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chyma on 5/17/2016.
 */
public class OnlineDataHelper {

    public static List<PowerNews> convertDataToNewsContent(String data){
        List<PowerNews> newsList = new ArrayList<>();

        try {
            JSONArray jsonArr = new JSONArray(data);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject json_data = new JSONObject(jsonArr.getString(i));
                PowerNews news = new PowerNews();
                news.setPostBy(json_data.getString("name"));
                news.setPostId(Long.valueOf(json_data.getString("id")));
                news.setPostTitle(json_data.getString("title"));

                //Get the body of text and format it
                String content = (json_data.getString("introtext"));
                String content1 = json_data.getString("fulltext");
                String filteredContent = removeImgTags(content);
                String full;
                if(content!=null && content1!=null){
                    full = filteredContent + content1;
                } else if (content != null && content1 == null){
                    full = filteredContent;
                }else {
                    continue;
                }
                news.setPostContent(full.replace("\\", ""));
                news.setPostDate(getTime(json_data.getString("published")));
                 String imgUrl= extractImgUrl(content);
                if (imgUrl != null) {
                    news.setPostImage(imgUrl);
                    Log.i("IMAGE_URL",imgUrl);
                }
                newsList.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    public static List<FAQ> convertDataToFAQContent(String data){
        List<FAQ> faqArrayList = new ArrayList<>();

        try {
            JSONArray jsonArr = new JSONArray(data);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject json_data = new JSONObject(jsonArr.getString(i));
                FAQ faq = new FAQ();
                faq.setPostBy(json_data.getString("name"));
                faq.setPostCode(Long.valueOf(json_data.getString("id")));
                faq.setPostTitle(json_data.getString("title"));
                String content = (json_data.getString("introtext"));
                String content1 = json_data.getString("fulltext");
                String full;
                if(content!=null && content1!=null){
                    full = content + content1;
                } else if (content != null && content1 == null){
                    full = content;
                }else {
                    continue;
                }
                faq.setPostContent(full.replace("\\", ""));
                faq.setPostDate(getTime(json_data.getString("created")));

                faqArrayList.add(faq);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return faqArrayList;
    }

    public static AppUser convertDataToAppUser(String data){

        AppUser user = new AppUser();
        try {
                JSONObject json_data = new JSONObject(data);

                user.setEmail(json_data.getString("email"));
                user.setName(json_data.getString("name"));
                user.setUserId(json_data.getString("id"));
                user.setUsername(json_data.getString("username"));
                user.setUsertype(json_data.getString("usertype"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }


    public static Long getTime(String created) {
        Long time = null;
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
            Date date = df.parse(created);
            Timestamp ts = new Timestamp(date.getTime());
            time = ts.getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }

    public static Boolean getPostResponse(String out) {
        try {
            int code = Integer.parseInt(out);

            if(code == 200){
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Announcement> convertDataToAnnouncement(String out) {
        List<Announcement> announcementList = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(out);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject json_data = new JSONObject(jsonArr.getString(i));
                Announcement announcement = new Announcement();
                announcement.setPostBy(json_data.getString("name"));
                announcement.setPostId(Long.valueOf(json_data.getString("id")));
                announcement.setPostTitle(json_data.getString("title"));
                String content = (json_data.getString("content"));
                announcement.setDiscoCode(json_data.getString("disco_code"));
                announcement.setPostContent(content);
                announcement.setPostDate(getTime(json_data.getString("created_date")));

                announcementList.add(announcement);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return announcementList;
    }

    public static List<PowerStats> convertDataToPowerStats(String out) {
        List<PowerStats> statsList = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(out);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject json_data = new JSONObject(jsonArr.getString(i));
                PowerStats stats = new PowerStats();
                stats.setReportId(Long.valueOf(json_data.getString("id")));
                stats.setAvailableCapMW(Float.parseFloat(json_data.getString("available_cap_mw")));
                stats.setPeakGenMW(Float.parseFloat(json_data.getString("peak_gen_mw")));
                stats.setAverageGenMW(Float.parseFloat(json_data.getString("average_gen_mw")));
                stats.setGasConstraintMW(Float.parseFloat(json_data.getString("gas_constr_mw")));
                stats.setWaterConstraintMW(Float.parseFloat(json_data.getString("water_constr_mw")));
                stats.setLoadRejectionMW(Float.parseFloat(json_data.getString("load_constr_mw")));
                stats.setTransmissionConstraintMW(Float.parseFloat(json_data.getString("trans_constr_mw")));
                stats.setTotalMW(Float.parseFloat(json_data.getString("total_constr_mw")));
                stats.setDate(getTime(json_data.getString("date")));

                statsList.add(stats);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return statsList;
    }

    public static List<PowerChart> convertDataToPowerChart(String out) {
        List<PowerChart> charts = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(out);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject json_data = new JSONObject(jsonArr.getString(i));
                PowerChart chart = new PowerChart();
                chart.setChartId(Long.valueOf(json_data.getString("id")));
                chart.setGenco(Float.parseFloat(json_data.getString("gencos")));
                chart.setNipp(Float.parseFloat(json_data.getString("nipp")));
                chart.setIpp(Float.parseFloat(json_data.getString("ipp")));
                chart.setHydro(Float.parseFloat(json_data.getString("hydro")));
                chart.setThermal(Float.parseFloat(json_data.getString("thermal")));
                chart.setDate(getTime(json_data.getString("date")));

                charts.add(chart);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return charts;
    }

    public static String extractImgUrl(String str){
        List<String> containedUrls = new ArrayList<String>();

        String imgRegex = "src=\"(.*?)\"";
        String header = "http://iwin.org.ng/";
        Pattern p = Pattern.compile(imgRegex,  Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = p.matcher(str);
        while (urlMatcher.find())
        {
            String url = str.substring(urlMatcher.start(0)+5,
                    urlMatcher.end()-1);

            if (!(url.startsWith("http"))){
                url = header + url;
            }
            else{
                //do nothing
            }
            containedUrls.add(url);
        }
        if(containedUrls.isEmpty()){
            return  null;
        }else {
            return containedUrls.get(0);
        }
    }

    public static String removeImgTags(String content){
        if(content != null){
            Document doc = Jsoup.parse(content);
            doc.select("img").remove();
            return doc.toString();
        }
        return content;
    }


}
