package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Chyma on 5/11/2016.
 */
public class SetupUtil {
    private DBService dbService;

    public  <T> void insertIntoDB(Class<T> clazz, ArrayList<T> arrayList, Context context){
        dbService = DBService.getInstance(context);
        try {
            Dao<T, Long> dao = dbService.getDao(clazz);
            dao.delete(dao.queryForAll());
            for(T obj: arrayList){
                dao.create(obj);
                Log.i("Setup", "Data Inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
