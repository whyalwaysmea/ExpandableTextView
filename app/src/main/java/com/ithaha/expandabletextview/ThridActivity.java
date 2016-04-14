package com.ithaha.expandabletextview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ThridActivity extends AppCompatActivity implements View.OnClickListener {

    private Button main;
    private Button second;
    private Button thrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        initView();

        ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expandableTextView.setText(getString(R.string.dummy_text1));
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
