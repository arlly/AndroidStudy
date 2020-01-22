package com.example.mybasicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AsyncNetworkTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = new AsyncNetworkTask(this);
        task.execute("https://wings.msn.to/");

    }

    public void buttonOnClick(View view) {
        task.cancel(true);
    }

}
