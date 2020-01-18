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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String titles[] = {"トルクフレックス", "パワースペリア", "テクニカルパフォーマー", "エアロブースター", "グラハント"};
        String tags[] = {"75H", "73MH", "610M", "610ML", "70H"};
        String descs[] = {
                "超異例の取り組みだ。若手野手の台頭が課題のソフトバンクが今春キャンプで期待の育成野手をＡ組に抜てきする「特別枠」を設ける計画が明らかになった。",
                "投手、野手それぞれ２０人程度で構成されるＡ組。",
                "森ヘッドコーチが異例のプランを明かした。「野手の２０人目のところに枠をつくって、そこに毎クール育成から若手を呼びたいと思っている。",
                "かねてフロント、一軍、ファームで緊密に情報共有し、若手育成に力を入れているソフトバンク。",
                "メンタルや取り組み方に問題がある。成功している人間がどれだけ自分を律しているかをいくら言い聞かせても伝わらないところがある。だから見学程度じゃなく、ある程度のスパンで主力が本気でやっている姿を見せたい。",
        };

        ArrayList<ListItem> data = new ArrayList<>();

        for(int i = 0; i < titles.length; i++) {
            ListItem item = new ListItem();
            item.setId((new Random()).nextLong());
            item.setTitle(titles[i]);
            item.setTag(tags[i]);
            item.setDesc(descs[i]);
            data.add(item);
        }

        MyListAdapter adapter = new MyListAdapter(this, data, R.layout.list_item);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    private void playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.cat1a);

        mediaPlayer.setLooping(false);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
}
