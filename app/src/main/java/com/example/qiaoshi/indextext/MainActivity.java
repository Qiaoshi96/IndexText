package com.example.qiaoshi.indextext;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.mylibrary.mySlantedTextView.TAG_LEFT_BAR;

public class MainActivity extends AppCompatActivity {
    private com.example.mylibrary.mySlantedTextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.left_test);

        testView.setText("打折中")
                .setMode(TAG_LEFT_BAR)
                .setBackground(Color.parseColor("#ff6677"))
                .setTextColor(Color.parseColor("#000000"))
                .setSlantedHeight(50)
                .setTextSize(29);
    }
}
