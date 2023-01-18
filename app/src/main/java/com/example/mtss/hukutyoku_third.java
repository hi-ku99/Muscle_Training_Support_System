package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class hukutyoku_third extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    String today_v_sit_title = null;
    String today_reverse_to_thrust_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukutyoku_third);


        scrollView = findViewById(R.id.scroll_view);
        scrollView.scrollTo(0,0);

        scrollView.post(new Runnable() {
            @Override
            public void run(){
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        //初級画面に戻る
        ImageButton first_level_button = findViewById(R.id.first_level_button);
        first_level_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), hukutyoku_first.class);
                //finish();
                startActivity(intent);
            }
        });

        //中級画面に戻る
        ImageButton second_level_button = findViewById(R.id.second_level_button);
        second_level_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), hukutyoku_second.class);
                //finish();
                startActivity(intent);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_v_sit_title = date + "_v_sit";
        today_reverse_to_thrust_title = date + "_reverse_to_thrust";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val2 = new ContentValues();
        val2.put("title", today_v_sit_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);


        ContentValues val4 = new ContentValues();
        val4.put("title", today_reverse_to_thrust_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);


        View v_sit_movie = findViewById(R.id.v_sit_movie);
        v_sit_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int v_sit_check = v_sit_readData();
                Log.d("現在の再生数","は"+v_sit_check);//現在の動画再生数を確認

                if(v_sit_check ==0) {
                    v_sit_check++;
                }else if(v_sit_check == 1){
                    v_sit_check++;
                }else if(v_sit_check == 2){
                    v_sit_check++;
                }

                v_sit_upDate(db,v_sit_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.v_sit);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        View reverse_to_thrust_movie = findViewById(R.id.reverse_to_thrust_movie);
        reverse_to_thrust_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_to_thrust_check = reverse_to_thrust_readData();
                Log.d("現在の再生数","は"+reverse_to_thrust_check);//現在の動画再生数を確認

                if(reverse_to_thrust_check ==0) {
                    reverse_to_thrust_check++;
                }else if(reverse_to_thrust_check == 1){
                    reverse_to_thrust_check++;
                }else if(reverse_to_thrust_check == 2){
                    reverse_to_thrust_check++;
                }

                reverse_to_thrust_upDate(db,reverse_to_thrust_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.rverse_to_srast);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示



        Button v_sit_CountButtonForMinus = findViewById(R.id.v_sit_minus);
        v_sit_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.v_sit_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_v_sit_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button v_sit_CountButtonForPlus = findViewById(R.id.v_sit_plus);
        v_sit_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.v_sit_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_v_sit_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });



        Button reverse_to_thrust_CountButtonForMinus = findViewById(R.id.reverse_to_thrust_minus);
        reverse_to_thrust_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_to_thrust_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_reverse_to_thrust_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_to_thrust_CountButtonForPlus = findViewById(R.id.reverse_to_thrust_plus);
        reverse_to_thrust_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_to_thrust_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_to_thrust_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });



        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ob_zintai.class);
                startActivity(intent);
            }

        });

        Button setting_button = findViewById(R.id.setting_button);
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),setting_human.class);
                startActivity(intent);
            }
        });

        Button menu_button = findViewById(R.id.menu_button);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MenuActivity.class);
                startActivity(intent);
            }
        });


        Button calendar_button = findViewById(R.id.calendar_button);
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),calendar_main.class);
                startActivity(intent);
            }
        });

        //設定に応じたセット数テキストに変更
        setting_data = setting_readData();
        TextView v_sit_setNum = findViewById(R.id.v_sit_setNum);
        TextView reverse_to_thrust_setNum = findViewById(R.id.reverse_to_thrust_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            v_sit_setNum.setText("20回×3");
            reverse_to_thrust_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            v_sit_setNum.setText("15回×3");
            reverse_to_thrust_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            v_sit_setNum.setText("10回×3");
            reverse_to_thrust_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            v_sit_setNum.setText("15回×3");
            reverse_to_thrust_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            v_sit_setNum.setText("10回×3");
            reverse_to_thrust_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            v_sit_setNum.setText("10回×2");
            reverse_to_thrust_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            v_sit_setNum.setText("20回×2");
            reverse_to_thrust_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            v_sit_setNum.setText("15回×2");
            reverse_to_thrust_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            v_sit_setNum.setText("10回×2");
            reverse_to_thrust_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            v_sit_setNum.setText("15回×2");
            reverse_to_thrust_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            v_sit_setNum.setText("10回×2");
            reverse_to_thrust_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            v_sit_setNum.setText("10回×1");
            reverse_to_thrust_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView v_sit_star1 = findViewById(R.id.v_sit_star1);
        ImageView v_sit_star2 = findViewById(R.id.v_sit_star2);
        ImageView v_sit_star3 = findViewById(R.id.v_sit_star3);


        ImageView reverse_to_thrust_star1 = findViewById(R.id.reverse_to_thrust_star1);
        ImageView reverse_to_thrust_star2 = findViewById(R.id.reverse_to_thrust_star2);
        ImageView reverse_to_thrust_star3 = findViewById(R.id.reverse_to_thrust_star3);


        int v_sit_score = v_sit_readData();
        int reverse_to_thrust_score = reverse_to_thrust_readData();


        if(v_sit_score >= 1){
            v_sit_star1.setImageResource(R.drawable.cleared_star);
        }
        if(v_sit_score >= 2){
            v_sit_star2.setImageResource(R.drawable.cleared_star);
        }
        if(v_sit_score >= 3){
            v_sit_star3.setImageResource(R.drawable.cleared_star);
        }


        if(reverse_to_thrust_score >= 1){
            reverse_to_thrust_star1.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_to_thrust_score >= 2){
            reverse_to_thrust_star2.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_to_thrust_score >= 3){
            reverse_to_thrust_star3.setImageResource(R.drawable.cleared_star);
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示

        String today_v_sit_num = Integer.toString(today_v_sit_readData());
        TextView text2 = findViewById(R.id.v_sit_countNum);
        text2.setText(today_v_sit_num);

        String today_reverse_to_thrust_num = Integer.toString(today_reverse_to_thrust_readData());
        TextView text4 = findViewById(R.id.reverse_to_thrust_countNum);
        text4.setText(today_reverse_to_thrust_num);


    }



    private int v_sit_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'v_sit_check'", null);
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


    private int reverse_to_thrust_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_to_thrust_check'", null);
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


    public void v_sit_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"v_sit_check"});
    }


    public void reverse_to_thrust_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_to_thrust_check"});
    }

    public void today_v_sit_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_v_sit_title});
    }

    public void today_reverse_to_thrust_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_to_thrust_title});
    }


    private int today_v_sit_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_v_sit_title + "'", null);
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


    private int today_reverse_to_thrust_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_to_thrust_title + "'", null);
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


    private int[] setting_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int[] scoreData = new int[3];

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'gender'", null);
        Cursor cursor2 = db.rawQuery("select title ,score from title_table where title = 'training_style'", null);
        Cursor cursor3 = db.rawQuery("select title ,score from title_table where title = 'body_style'", null);

        boolean next = cursor.moveToFirst();
        while (next) {
            // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
            scoreData[0] = cursor.getInt(1);// scoreを取得
            next = cursor.moveToNext();
        }
        cursor.close();

        boolean next2 = cursor2.moveToFirst();
        while (next2) {
            // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
            scoreData[1] = cursor2.getInt(1);
            next2 = cursor2.moveToNext();
        }
        cursor2.close();

        boolean next3 = cursor3.moveToFirst();
        while (next3) {
            // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
            scoreData[2] = cursor3.getInt(1);
            next3 = cursor3.moveToNext();
        }
        // dbを開いたら確実にclose
        cursor3.close();

        return scoreData;
    }

    public void calendar_upDate(SQLiteDatabase db,int score){
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("calendar_table",values,"title=?",new String[]{date});
    }

    private int calendar_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from calendar_table where title = '" + date + "'", null);
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

}
