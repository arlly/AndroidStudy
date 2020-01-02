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
    private final static String TAG_JWORG = "4";
    private final static String TAG_VOD = "5";
    private final static String TAG_OLL = "6";
    private TextView textView;
    private final static int REQUEST_TEXT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.BLUE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        layout.addView(makeButton("JW.org", TAG_JWORG));
        layout.addView(makeButton("ブロードキャスティング", TAG_VOD));
        layout.addView(makeButton("オンラインライブラリ", TAG_OLL));
        layout.addView(makeButton("yahoo!", TAG_WEB));

        textView = new TextView(this);
        textView.setText("これはテストです。");
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        layout.addView(textView);

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

        if (TAG_JWORG.equals(tag)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.jw.org/ja/"));
            startActivity(intent);
        }

        if (TAG_OLL.equals(tag)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://wol.jw.org/ja/wol/h/r7/lp-j"));
            startActivity(intent);
        }

        if (TAG_VOD.equals(tag)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.jw.org/ja/%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA%E3%83%BC/%E3%83%93%E3%83%87%E3%82%AA/#/ja/categories/VODStudio"));
            startActivity(intent);
        }

        if (TAG_WEB.equals(tag)) {
            //Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.yahoo.co.jp"));
            //startActivity(intent);

            Intent intent = new Intent(this, MyActivity.class);
            intent.putExtra("text", textView.getText().toString());
            startActivityForResult(intent, REQUEST_TEXT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQUEST_TEXT && resultCode == RESULT_OK) {
            String text = "";
            Bundle extras = intent.getExtras();
            if (extras != null) text = extras.getString("text");

            textView.setText(text);
        }

    }

}
