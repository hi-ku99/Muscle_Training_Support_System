package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class calendar_main extends AppCompatActivity {
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    String dateStr = null;
    String today = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        final Calendar calendar = Calendar.getInstance();
        Time time = new Time("Asia/Tokyo");
        time.setToNow();

        today =  time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";
        if(calendar_readData(today) >= 1){
            View mark = findViewById(R.id.kintore_mark);
            mark.setBackgroundResource(R.drawable.muscle);
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),calendar_next_sample.class);
                    intent.putExtra("day",today);
                    startActivity(intent);
                }
            });
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });




        final DatePicker datePicker =  findViewById(R.id.datePicker);
        datePicker.init(time.year, time.month, time.monthDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                // 日付を選択した時に実行される
                dateStr = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                if(calendar_readData(dateStr) >= 1){
                    View mark = findViewById(R.id.kintore_mark);
                    mark.setBackgroundResource(R.drawable.muscle);
                }else{
                    View mark = findViewById(R.id.kintore_mark);
                    mark.setBackgroundResource(0);
                }

                if(calendar_readData(dateStr) >= 1){
                    View next = findViewById(R.id.next_button);
                    Button nextButton = findViewById(R.id.next_button);
                    next.setBackgroundResource(R.drawable.shape_button_second);
                    nextButton.setEnabled(true);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplication(),calendar_next_sample.class);
                            intent.putExtra("day",dateStr);
                            startActivity(intent);
                        }
                    });
                }else{
                    View next = findViewById(R.id.next_button);
                    Button nextButton = findViewById(R.id.next_button);
                    next.setBackgroundResource(R.drawable.shape_button_forth);
                    nextButton.setEnabled(false);
                }
            }
        });

    }

    private int calendar_readData (String day) {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(this);
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from calendar_table where title = '" +  day+  "'", null);
        boolean next = cursor.moveToFirst();

        while (next) {
            // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
            scoreData =cursor.getInt(1);// 日付を取得
            // 次の行が存在するか確認
            next = cursor.moveToNext();
        }


        // dbを開いたら確実にclose
        cursor.close();

        return scoreData;
    }
}
