package com.example.mybasicapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.mybasicapplication.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncNetworkTask extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> textResult;

    AsyncNetworkTask(Context context) {
        super();
        MainActivity activity = (MainActivity) context;
        textResult = new WeakReference<>((TextView) activity.findViewById(R.id.textResult));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {
        publishProgress(30);
        SystemClock.sleep(3000);
        publishProgress(60);
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        publishProgress(100);
        return builder.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d("url", values[0].toString());
    }

    @Override
    protected void onPostExecute(String result) {
        textResult.get().setText(result);
    }

    @Override
    protected void onCancelled() {
        textResult.get().setText("キャンセルされました。");
    }




}