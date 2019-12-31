package com.example.mybasicapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final static  int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_MESSAGE = "0";
    private final static String TAG_YESNO = "1";
    private final static String TAG_TEXT = "2";
    private final static String TAG_WEB = "3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.BLUE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        layout.addView(makeButton("メッセージダイアログの表示", TAG_MESSAGE));
        layout.addView(makeButton("YesNoダイアログの表示", TAG_YESNO));
        layout.addView(makeButton("テキスト入力ダイアログの表示", TAG_TEXT));
        layout.addView(makeButton("WEBページの表示", TAG_WEB));
    }

    /**
     *
     * @param text
     * @param tag
     * @return
     */
    private Button makeButton(String text, String tag) {
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        return button;
    }

    /**
     * @param view
     */
    public void changeLabel(View view) {
        TextView tv = (TextView) findViewById(R.id.chage);
        tv.setText("ありまろ！！");
    }



    /**
     *
     * @param view
     */
    public void onClick(View view) {
        String tag = (String) view.getTag();

        if (TAG_WEB.equals(tag)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        }
    }
}
