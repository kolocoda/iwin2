package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.AllMonth;
import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.Appliance;
import com.ugarsoft.chyma.iwin.models.Bill;
import com.ugarsoft.chyma.iwin.models.Contact;
import com.ugarsoft.chyma.iwin.models.Disco;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.models.FAQ;
import com.ugarsoft.chyma.iwin.models.MessagePriority;
import com.ugarsoft.chyma.iwin.models.PaymentUrl;
import com.ugarsoft.chyma.iwin.models.PowerAnouncement;
import com.ugarsoft.chyma.iwin.models.PowerChart;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.models.PowerStats;
import com.ugarsoft.chyma.iwin.models.SubAppliance;
import com.ugarsoft.chyma.iwin.models.UserRole;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class DBService extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "energy_centre.db";
    private static final int DATABASE_VERSION = 2;

    private DBService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.orm_config);
    }

    public static DBService getInstance(Context context) {
        return new DBService(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, AllMonth.class);
            TableUtils.createTable(connectionSource, Announcement.class);
            TableUtils.createTable(connectionSource, AppUser.class);
            TableUtils.createTable(connectionSource, Appliance.class);
            TableUtils.createTable(connectionSource, Bill.class);
            TableUtils.createTable(connectionSource, Contact.class);
            TableUtils.createTable(connectionSource, Disco.class);
            TableUtils.createTable(connectionSource, EPay.class);
            TableUtils.createTable(connectionSource, FAQ.class);
            TableUtils.createTable(connectionSource, PaymentUrl.class);
            TableUtils.createTable(connectionSource, PowerAnouncement.class);
            TableUtils.createTable(connectionSource, PowerNews.class);
            TableUtils.createTable(connectionSource, PowerChart.class);
            TableUtils.createTable(connectionSource, MessagePriority.class);
            TableUtils.createTable(connectionSource, PowerStats.class);
            TableUtils.createTable(connectionSource, SubAppliance.class);
            TableUtils.createTable(connectionSource, UserRole.class);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
