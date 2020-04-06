package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;

import com.ugarsoft.chyma.iwin.R;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class MessageUtil {


    public static void showAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(FontUtil.getCustomText(context, "DONE"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(FontUtil.getCustomText(context, message));
        builder.show();
    }

    public static void showAlert(Context context, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(FontUtil.getCustomText(context, "DONE"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setTitle(title);
        builder.setIcon(R.drawable.icon);
        builder.setMessage(FontUtil.getCustomText(context, Html.fromHtml(message)));
        builder.show();
    }


}
