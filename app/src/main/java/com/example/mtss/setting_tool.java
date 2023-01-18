package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class setting_tool extends AppCompatActivity {
    TestOpenHelperForTitle helper;
    NumberPicker numPicker;
    Button button1;
    final String[] nothing = {"なし","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_tool);

        findViews();
        initViews();
    }

    private void findViews() {
        numPicker =  findViewById(R.id.numPicker);
        button1 =  findViewById(R.id.button1);
    }

    private void initViews() {
        numPicker.setDisplayedValues(null);
        numPicker.setMinValue(0);
        numPicker.setMaxValue(20);
        numPicker.setDisplayedValues(nothing);
    }

    public void endbutton(View v) {
        //button1.setOnClickListener(new OnClickListener() {
            //public void onClick(View v) {
            int idx = numPicker.getValue();
            if (helper == null) {
                helper = new TestOpenHelperForTitle(getApplicationContext());
            }
            if (idx >= 0 && idx <= 4) {

                SQLiteDatabase db = helper.getReadableDatabase();
                upDate(db, 1);
                startActivity(new Intent(this,ob_zintai.class));
            }

            if (idx >= 5 && idx <= 9) {

                SQLiteDatabase db = helper.getReadableDatabase();
                upDate(db, 2);
                startActivity(new Intent(this,ob_zintai.class));
            }

            if (idx >= 10) {
                SQLiteDatabase db = helper.getReadableDatabase();
                upDate(db, 3);
                startActivity(new Intent(this,ob_zintai.class));
            }

           // }
        //});
    }


    public void upDate(SQLiteDatabase db, int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"training_style"});
    }
}
