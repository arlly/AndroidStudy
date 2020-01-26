package com.example.mybasicapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    AsyncNetworkTask task;
    TextView textResult;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        progressBar = findViewById(R.id.spinner);

        Bundle bundle = new Bundle();
        bundle.putString("url", "https://wings.msn.to/");
        LoaderManager.getInstance(this).initLoader(1, bundle, this);

        //task = new AsyncNetworkTask(this);
        //task.execute("http://weather.livedoor.com/forecast/webservice/json/v1?city=250010");

    }

    public void buttonOnClick(View view) {
        task.cancel(true);
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
}
