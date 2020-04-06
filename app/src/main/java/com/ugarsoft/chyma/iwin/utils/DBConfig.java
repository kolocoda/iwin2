package com.ugarsoft.chyma.iwin.utils;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class DBConfig extends OrmLiteConfigUtil {
    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile("orm_config.txt");
    }
}
