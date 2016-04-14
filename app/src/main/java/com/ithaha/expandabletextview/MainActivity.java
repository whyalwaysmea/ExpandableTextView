package com.ithaha.expandabletextview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button main;
    private Button second;
    private Button thrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        final TextView tv1 = (TextView) findViewById(R.id.text1);
        final TextView tv2 = (TextView) findViewById(R.id.text2);
        ImageButton expandBtn = (ImageButton) findViewById(R.id.expand);

        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv1.getVisibility() == View.VISIBLE) {
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.VISIBLE);
                } else if (tv2.getVisibility() == View.VISIBLE) {
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.GONE);
                }
            }
        });
    }


    private void initView() {
        main = (Button) findViewById(R.id.main);
        second = (Button) findViewById(R.id.second);
        thrid = (Button) findViewById(R.id.thrid);

        main.setOnClickListener(this);
        second.setOnClickListener(this);
        thrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.second:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.thrid:
                intent = new Intent(this, ThridActivity.class);
                startActivity(intent);
                break;
        }
    }
}
