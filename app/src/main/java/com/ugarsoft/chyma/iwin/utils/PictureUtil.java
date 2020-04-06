package com.ugarsoft.chyma.iwin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.ugarsoft.chyma.iwin.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class PictureUtil {
    public static Bitmap getBitmap(String encodedString) {
        byte[] bytes = Base64.decode(encodedString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public  static Bitmap getOnlinePicture(String photourl){
        try {
            URL newUrl = new URL(photourl);
            Bitmap img = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
            Log.i("IMAGE_FOUND", "url "+photourl);
            return img;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static class PictureLoader extends AsyncTask<Void, Void, Boolean> {

        ImageView view;
        String imgUrl;
        Bitmap img;

        public PictureLoader(ImageView view, String url) {
            this.view = view;
            this.imgUrl = url;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if(imgUrl != null && !imgUrl.isEmpty()){
                img = PictureUtil.getOnlinePicture(imgUrl);
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean == Boolean.TRUE) {
                view.setImageBitmap(img);
            }
            else {
                view.setImageResource(R.drawable.ic_menu_news);
            }
        }
    }
}
