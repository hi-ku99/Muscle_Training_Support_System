package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.os.Bundle;


import android.content.Intent;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;

public class Title extends AppCompatActivity {

    TestOpenHelperForTitle helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
            Button toSettingButton = findViewById(R.id.setting_button);
            toSettingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SQLiteDatabase db = helper.getReadableDatabase();
                    upDate(db,1);
                    Intent intent = new Intent(getApplication(), setting_human.class);
                    startActivity(intent);
                }
            });

            Button toZintaiButton = findViewById(R.id.start_button);
            toZintaiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(readData() == 1){
                        Intent intent = new Intent(getApplication(), ob_zintai.class);
                        startActivity(intent);
                    }
                }



            });

        //日付取得
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //カレンダー用のデータをデータベースに登録(重複無し)
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put("title", date);
        val.put("score", 0);
        db.insert("calendar_table", null, val);
    }

    @Override
    public void onResume(){
        super.onResume();

        Button toMovieButton = findViewById(R.id.start_button);
        if(readData() == 1){
            toMovieButton.setBackgroundResource(R.drawable.start_image);
        }
    }


    private int readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

            //カーソルを移動して上から順に読み込んでるイメージ。
            Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'setting_check'", null);

            boolean next = cursor.moveToFirst();

            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                scoreData = cursor.getInt(1);// scoreを取得
                // 次の行が存在するか確認
                next = cursor.moveToNext();
            }


        // dbを開いたら確実にclose
            cursor.close();

        return scoreData;
    }

    public void upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"setting_check"});
    }

}
