package com.example.mybasicapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    AsyncNetworkTask task;
    TextView textResult;
    ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        progressBar = findViewById(R.id.spinner);

        wv = findViewById(R.id.wv);
        wv.setWebViewClient(new MyWebViewClient());

        //Bundle bundle = new Bundle();
        //bundle.putString("url", "http://weather.livedoor.com/forecast/webservice/json/v1?city=250010");
        //LoaderManager.getInstance(this).initLoader(1, bundle, this);

        task = new AsyncNetworkTask(this);
        task.execute("http://weather.livedoor.com/forecast/webservice/json/v1?city=250010");

    }

    public void buttonOnClick(View view) {
        task.cancel(true);

        mediaPlayer = MediaPlayer.create(this, R.raw.cat1a);
        mediaPlayer.setLooping(false);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();

        wv.loadUrl("https://weather.yahoo.co.jp/weather/jp/25/6010.html");
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == 1) {
            progressBar.setVisibility(ProgressBar.VISIBLE);
            MyAsyncLoader loader = new MyAsyncLoader(this, args.getString("url"));
            loader.forceLoad();
            return loader;
        }

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        textResult.setText(data);
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        //
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
