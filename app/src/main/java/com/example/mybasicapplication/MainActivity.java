package com.example.mybasicapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    EditText textMemo;
    TextView viewMemo;

    private SimpleDatabaseHelper helper = null;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SimpleDatabaseHelper(this);

        try(SQLiteDatabase db = helper.getWritableDatabase()) {
            Toast toast = Toast.makeText(this, "接続しました", Toast.LENGTH_SHORT);
            toast.show();
        }


        textMemo = findViewById(R.id.textMemo);
        viewMemo = findViewById(R.id.viewMemo);

        StringBuffer str = displayMemo();
        viewMemo.setText(str.toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveText(View view) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("memo.dat", Context.MODE_APPEND)))) {
            writer.write(textMemo.getText().toString());

            Context context = getApplicationContext();
            CharSequence text = "保存しました!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            StringBuffer str = displayMemo();
            viewMemo.setText(str.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public StringBuffer displayMemo() {
        StringBuffer str = new StringBuffer();

        try (BufferedReader reader = new BufferedReader((new InputStreamReader(openFileInput("memo.dat"))))){
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append(System.getProperty("line separator"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
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
            intent.putExtra("name", "あーりーまーろー");
            setResult(RESULT_OK, intent);
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
