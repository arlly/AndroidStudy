package com.example.mybasicapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_MESSAGE = "0";
    private final static String TAG_YESNO = "1";
    private final static String TAG_TEXT = "2";
    private final static String TAG_WEB = "3";
    private final static String TAG_JWORG = "4";
    private final static String TAG_VOD = "5";
    private final static String TAG_OLL = "6";
    private TextView textView;
    private final static int REQUEST_TEXT = 0;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> data = new ArrayList<>();
        data.add("トルクフレックス");
        data.add("パワースペリア");
        data.add("テクニカルパフォーマー");
        data.add("エアロブースター");
        data.add("グラハント");
        data.add("ディープマニピュレーター");
        data.add("マルチアーマメント");
        data.add("フェザーストローク");
        data.add("マグナムドライバー");
        data.add("スイッチバック");
        data.add("TAV-GP69CMJ");
        data.add("TAV-GP65CLJ");
        data.add("GWT-70CHJ");
        data.add("フロストフラワー");
        data.add("ELT-64SULJ");
        data.add("ELT-66CLJ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, data);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CharSequence msg = ((TextView) view).getText();
                        Toast.makeText(MainActivity.this, String.format("選択したのは%s", msg.toString()), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        this.playSound();
    }

    private void playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.cat1a);

        mediaPlayer.setLooping(false);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
}
