package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
//import com.example.mtss.R;
//import android.view.View;
//import android.widget.ImageButton;

public class setting_maccho extends AppCompatActivity {

    TestOpenHelperForTitle helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seting_maccho);

         /*ImageButton toToolButton = findViewById(R.id.gorila_body_Button);
        toToolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), setting_tool.class);
                startActivity(intent);
            }
        });

        ImageButton toToolButton1 = findViewById(R.id.slim_body_Button);
        toToolButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), setting_tool.class);
                startActivity(intent);
            }
        });*/
    }

    public void gorimaccho(View v){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        upDate(db,0);
        startActivity(new Intent(this,setting_tool.class));
    }

    public void hosomaccho(View v){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        upDate(db,1);
        startActivity(new Intent(this,setting_tool.class));
    }

    public void upDate(SQLiteDatabase db, int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"body_style"});
    }

}


