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

public class denbu_first extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    boolean third_level_check = false;
    String today_hip_lift_title = null;
    String today_wide_squat_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.denbu_first);


        scrollView = findViewById(R.id.scroll_view);
        scrollView.scrollTo(0,0);

        scrollView.post(new Runnable() {
            @Override
            public void run(){
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_hip_lift_title = date + "_hip_lift";
        today_wide_squat_title = date + "_wide_squat";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_hip_lift_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_wide_squat_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        View hip_lift_movie = findViewById(R.id.hip_lift_movie);
        hip_lift_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hip_lift_check = hip_lift_readData();  //現在の動画再生数を確認

                if(hip_lift_check ==0) {
                    hip_lift_check++;
                }else if(hip_lift_check == 1){
                    hip_lift_check++;
                }else if(hip_lift_check == 2){
                    hip_lift_check++;
                }

                hip_lift_upDate(db,hip_lift_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.hip_lift);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View wide_squat_movie = findViewById(R.id.wide_squat_movie);
        wide_squat_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int wide_squat_check = wide_squat_readData();
                Log.d("現在の再生数","は"+wide_squat_check);//現在の動画再生数を確認

                if(wide_squat_check ==0) {
                    wide_squat_check++;
                }else if(wide_squat_check == 1){
                    wide_squat_check++;
                }else if(wide_squat_check == 2){
                    wide_squat_check++;
                }

                wide_squat_upDate(db,wide_squat_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.wide_squat);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        Button hip_lift_CountButtonForMinus = findViewById(R.id.hip_lift_minus);
        hip_lift_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hip_lift_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_hip_lift_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button hip_lift_CountButtonForPlus = findViewById(R.id.hip_lift_plus);
        hip_lift_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hip_lift_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_hip_lift_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button wide_squat_CountButtonForMinus = findViewById(R.id.wide_squat_minus);
        wide_squat_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.wide_squat_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_hip_lift_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button wide_squat_CountButtonForPlus = findViewById(R.id.wide_squat_plus);
        wide_squat_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.wide_squat_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_wide_squat_upDate(db,num);
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

        Button calendar_button = findViewById(R.id.calendar_button);
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),calendar_main.class);
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

        //設定に応じたセット数テキストに変更
        setting_data = setting_readData();
        TextView hip_lift_setNum = findViewById(R.id.hip_lift_setNum);
        TextView wide_squat_setNum = findViewById(R.id.wide_squat_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            hip_lift_setNum.setText("20回×3");
            wide_squat_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            hip_lift_setNum.setText("15回×3");
            wide_squat_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            hip_lift_setNum.setText("10回×3");
            wide_squat_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            hip_lift_setNum.setText("15回×3");
            wide_squat_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            hip_lift_setNum.setText("10回×3");
            wide_squat_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            hip_lift_setNum.setText("10回×2");
            wide_squat_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            hip_lift_setNum.setText("20回×2");
            wide_squat_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            hip_lift_setNum.setText("15回×2");
            wide_squat_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            hip_lift_setNum.setText("10回×2");
            wide_squat_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            hip_lift_setNum.setText("15回×2");
            wide_squat_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            hip_lift_setNum.setText("10回×2");
            wide_squat_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            hip_lift_setNum.setText("10回×1");
            wide_squat_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView hip_lift_star1 = findViewById(R.id.hip_lift_star1);
        ImageView hip_lift_star2 = findViewById(R.id.hip_lift_star2);
        ImageView hip_lift_star3 = findViewById(R.id.hip_lift_star3);

        ImageView wide_squat_star1 = findViewById(R.id.wide_squat_star1);
        ImageView wide_squat_star2 = findViewById(R.id.wide_squat_star2);
        ImageView wide_squat_star3 = findViewById(R.id.wide_squat_star3);



        int hip_lift_score = hip_lift_readData();
        int wide_squat_score = wide_squat_readData();

        Log.d("戻ってきたときの再生数","は"+wide_squat_score);//現在の動画再生数を確認

        if(hip_lift_score >= 1){
            hip_lift_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(hip_lift_score >= 2){
            hip_lift_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(hip_lift_score >= 3){
            hip_lift_star3.setImageResource(R.drawable.cleared_star_key);
            if(second_level_check == false) {
                ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                second_level_button_locked.setId(R.id.second_level_button);
                second_level_check = true;
            }

        }

        if(wide_squat_score >= 1){
            wide_squat_star1.setImageResource(R.drawable.cleared_star);
        }
        if(wide_squat_score >= 2){
            wide_squat_star2.setImageResource(R.drawable.cleared_star);
        }
        if(wide_squat_score >= 3){
            wide_squat_star3.setImageResource(R.drawable.cleared_star);
        }

        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.second_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),denbu_second.class);
                    startActivity(intent);
                }
            });
        }

        //上級も解除されているかチェック

        if(third_level_check == false) {
            if(third_level_check_readData() >= 3){
                ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                third_level_button_locked.setId(R.id.third_level_button);
                third_level_check = true;
            }
        }

        if(third_level_check == true) {
            ImageButton third_level_button = findViewById(R.id.third_level_button);
            third_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), denbu_third.class);
                    startActivity(intent);
                }
            });
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_hip_lift_num = Integer.toString(today_hip_lift_readData());
        TextView text = findViewById(R.id.hip_lift_countNum);
        text.setText(today_hip_lift_num);

        String today_wide_squat_num = Integer.toString(today_wide_squat_readData());
        TextView text2 = findViewById(R.id.wide_squat_countNum);
        text2.setText(today_wide_squat_num);

    }

    private int hip_lift_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'hip_lift_check'", null);
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


    private int wide_squat_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'wide_squat_check'", null);
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


    public void hip_lift_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"hip_lift_check"});
    }

    public void wide_squat_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"wide_squat_check"});
    }


    public void today_hip_lift_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_hip_lift_title});
    }

    public void today_wide_squat_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_wide_squat_title});
    }

    private int today_hip_lift_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_hip_lift_title + "'", null);
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

    private int today_wide_squat_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_wide_squat_title + "'", null);
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

    private int third_level_check_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'hip_extension_check'", null);
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
