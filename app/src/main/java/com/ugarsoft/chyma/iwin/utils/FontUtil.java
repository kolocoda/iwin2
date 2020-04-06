package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.custom.CustomTypeFaceSpan;


/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class FontUtil {
    public static void setDefaultTypeFace(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), FontType.ROBOTO_REGULAR.toString()));
    }

    public static CharSequence getCustomText(Context context, String text, FontType fontType) {
        Typeface font2 = Typeface.createFromAsset(context.getAssets(), fontType.toString());
        SpannableStringBuilder SS = new SpannableStringBuilder(text);
        SS.setSpan(new CustomTypeFaceSpan("", font2), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return SS;
    }

    public static CharSequence getCustomText(Context context, CharSequence text) {
        Typeface font2 = Typeface.createFromAsset(context.getAssets(), FontType.ROBOTO_REGULAR.toString());
        SpannableStringBuilder SS = new SpannableStringBuilder(text);
        SS.setSpan(new CustomTypeFaceSpan("", font2), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return SS;
    }

    public static void setDefaultTypeFace(Context context, TextView textView, FontType fontType) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), fontType.toString()));
    }

    public static void setDefaultTypeFace(Context context, FontType fontType, TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), fontType.toString()));
    }

    public static void setDefaultTypeFace(Context context, TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), FontType.ROBOTO_REGULAR.toString()));
    }

    public static void setNewsBoldTypeFace(Context context, TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), FontType.RALEWAY_BOLD.toString()));
    }

    public static void setNewsNormalTypeFace(Context context, TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), FontType.RALEWAY_REGULAR.toString()));
    }
    public enum FontType {

        ROBOTO_REGULAR("fonts/Roboto-Regular.ttf"),
        ROBOTO_MEDIUM("fonts/Roboto-Medium.ttf"),
        RALEWAY_REGULAR("fonts/Raleway-Regular.ttf"),
        RALEWAY_BOLD("fonts/Raleway-Bold.ttf");

        private String assetName;

        private FontType(String assetName) {
            this.assetName = assetName;
        }

        @Override
        public String toString() {
            return assetName;
        }
    }
}
