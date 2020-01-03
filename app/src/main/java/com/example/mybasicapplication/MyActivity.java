package com.example.mybasicapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setResult(Activity.RESULT_CANCELED);

        String text = "";
        String arllyName = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            text = extras.getString("text");
            arllyName = extras.getString("name");
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        Button button = new Button(this);
        button.setText("おけー");
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
        layout.addView(button);

        editText = new EditText(this);
        editText.setText(text + arllyName);
        editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
        layout.addView(editText);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("text", editText.getText().toString());
        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            String name = intent.getExtras().getString("text");
            Toast toast = Toast.makeText(this, name, Toast.LENGTH_LONG);
            // 位置調整
            toast.setGravity(Gravity.CENTER, 0, -200);
            toast.show();
        }
    }


}
