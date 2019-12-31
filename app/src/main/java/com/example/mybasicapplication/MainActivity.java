package com.example.mybasicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * @param view
     */
    public void changeLabel(View view) {
        TextView tv = (TextView) findViewById(R.id.chage);
        tv.setText("ありまろ！！");

    }

}
