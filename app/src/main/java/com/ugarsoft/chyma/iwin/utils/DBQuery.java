package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;

import com.ugarsoft.chyma.iwin.models.AllMonth;
import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.Disco;
import com.ugarsoft.chyma.iwin.models.MessagePriority;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chyma on 5/14/2016.
 */
public class DBQuery {
    static DBService dbService;

    public static List<String> getDiscoList(Context context){
        List<String> discos = new ArrayList<>();
        dbService = DBService.getInstance(context);
        try {
            Dao<Disco, Long> dao = dbService.getDao(Disco.class);
            QueryBuilder<Disco, Long> qb = dao.queryBuilder();
            qb.orderBy("displayName", true);
            List<Disco> list = new ArrayList<>();
            list.addAll(qb.query());
            for (Disco con : list){
                discos.add(con.getDisplayName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discos;
    }

    public static Double getDiscoTariffByDiscoName(Context con, String discoName){
        dbService = DBService.getInstance(con);
        try {
            Dao<Disco, Long> dao = dbService.getDao(Disco.class);
            QueryBuilder<Disco, Long> qb = dao.queryBuilder();
            qb.where().eq("displayName", discoName);
            List<Disco> list = new ArrayList<>();
            list.addAll(qb.query());
            return list.get(0).getTariff();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static List<AllMonth> getAllMonths(Context con){
        dbService = DBService.getInstance(con);
        List<AllMonth> list = new ArrayList<>();
        try {
            Dao<AllMonth, Long> dao = dbService.getDao(AllMonth.class);
            QueryBuilder<AllMonth, Long> qb = dao.queryBuilder();
            qb.orderBy("monthCode", true);
            list.addAll(qb.query());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<MessagePriority> getAllMessagePriority(Context con){
        dbService = DBService.getInstance(con);
        List<MessagePriority> list = new ArrayList<>();
        try {
            Dao<MessagePriority, Long> dao = dbService.getDao(MessagePriority.class);
            list.addAll(dao.queryForAll());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int countUnreadNews(Context con){
        dbService = DBService.getInstance(con);
        try {
            Dao<PowerNews, Long> dao = dbService.getDao(PowerNews.class);
            QueryBuilder<PowerNews, Long> qb = dao.queryBuilder();
            qb.where().eq("isRead", Boolean.FALSE);
            return qb.query().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countUnreadAnnouncement(Context con){
        dbService = DBService.getInstance(con);
        try {
            Dao<Announcement, Long> dao = dbService.getDao(Announcement.class);
            QueryBuilder<Announcement, Long> qb = dao.queryBuilder();
            qb.where().eq("isRead", Boolean.FALSE);
            return qb.query().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
