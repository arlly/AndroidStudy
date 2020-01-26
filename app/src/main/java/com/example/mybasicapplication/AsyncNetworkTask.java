package com.example.mybasicapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.mybasicapplication.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncNetworkTask extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> textResult;
    private WeakReference<ProgressBar> progress;

    AsyncNetworkTask(Context context) {
        super();
        MainActivity activity = (MainActivity) context;
        textResult = new WeakReference<>((TextView) activity.findViewById(R.id.textResult));
        progress = new WeakReference<>((ProgressBar) activity.findViewById(R.id.spinner));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {
        publishProgress(30);
        SystemClock.sleep(3000);
        publishProgress(60);
        StringBuilder builder = new StringBuilder();
        StringBuilder list = new StringBuilder();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            try {
                JSONObject json = new JSONObject(builder.toString());
                JSONArray weathers = json.getJSONArray("forecasts");

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

        publishProgress(100);
        return list.toString();
    }

    @Override
    protected void onPreExecute() {
        progress.get().setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        textResult.get().setText(result);
        progress.get().setVisibility(ProgressBar.GONE);
    }

    @Override
    protected void onCancelled() {
        textResult.get().setText("キャンセルされました。");
        progress.get().setVisibility(ProgressBar.GONE);
    }


}
