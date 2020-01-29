package com.example.mybasicapplication;

import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MyAsyncLoader extends AsyncTaskLoader<String> {
    private String url;

    MyAsyncLoader(Context context, String url) {
        super(context);
        this.url = url;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public String loadInBackground() {
        SystemClock.sleep(3000);
        StringBuilder builder = new StringBuilder();
        StringBuilder list = new StringBuilder();

        try {
            URL url = new URL(this.url);
            Log.d("URLです:", this.url);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

            try {
                JSONObject json = new JSONObject(builder.toString());
                JSONArray weathers = json.getJSONArray("forecasts");
                Log.d("return値:", weathers.toString());

                for (int i = 0; i < weathers.length(); i++) {
                    JSONObject weather = weathers.getJSONObject(i);
                    list.append(weather.getString("dateLabel")).append("/");
                    list.append(weather.getString("telop")).append("\n");
                }

                JSONObject description = json.getJSONObject("description");
                list.append(description.getString("text"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return list.toString();
    }
}
