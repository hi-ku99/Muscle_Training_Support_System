package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class setting_human extends AppCompatActivity {

    TestOpenHelperForTitle helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_human);

        ImageButton toMacchoButton = findViewById(R.id.ManButton);
        toMacchoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), com.example.mtss.setting_maccho.class);
                if(helper == null){
                    helper = new TestOpenHelperForTitle(getApplicationContext());
                }
                SQLiteDatabase db = helper.getReadableDatabase();
                upDate(db,0);
                startActivity(intent);
            }
        });
        ImageButton toMacchoButton1 = findViewById(R.id.WomanButton);
        toMacchoButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), com.example.mtss.setting_maccho.class);
                if(helper == null){
                    helper = new TestOpenHelperForTitle(getApplicationContext());
                }
                SQLiteDatabase db = helper.getReadableDatabase();
                upDate(db,1);
                startActivity(intent);
            }
        });
    }

    public void upDate(SQLiteDatabase db, int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"gender"});
    }

}
