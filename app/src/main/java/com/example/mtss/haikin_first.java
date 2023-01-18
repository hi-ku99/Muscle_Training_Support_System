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

public class haikin_first extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    boolean third_level_check = false;
    String today_back_lat_pull_down_title = null;
    String today_reverse_snow_angel_title = null;
    String today_dumbbell_overflow_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.haikin_first);


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
        today_back_lat_pull_down_title = date + "_back_lat_pull_down";
        today_reverse_snow_angel_title = date + "_reverse_snow_angel";
        today_dumbbell_overflow_title = date + "_dumbbell_overflow";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_back_lat_pull_down_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_reverse_snow_angel_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);


        ContentValues val4 = new ContentValues();
        val4.put("title", today_dumbbell_overflow_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);




        View back_lat_pull_down_movie = findViewById(R.id.back_lat_pull_down_movie);
        back_lat_pull_down_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int back_lat_pull_down_check = back_lat_pull_down_readData();  //現在の動画再生数を確認

                if(back_lat_pull_down_check ==0) {
                    back_lat_pull_down_check++;
                }else if(back_lat_pull_down_check == 1){
                    back_lat_pull_down_check++;
                }else if(back_lat_pull_down_check == 2){
                    back_lat_pull_down_check++;
                }

                back_lat_pull_down_upDate(db,back_lat_pull_down_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.home_back_rat_pull_down);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View reverse_snow_angel_movie = findViewById(R.id.reverse_snow_angel_movie);
        reverse_snow_angel_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_snow_angel_check = reverse_snow_angel_readData();
                Log.d("現在の再生数","は"+reverse_snow_angel_check);//現在の動画再生数を確認

                if(reverse_snow_angel_check ==0) {
                    reverse_snow_angel_check++;
                }else if(reverse_snow_angel_check == 1){
                    reverse_snow_angel_check++;
                }else if(reverse_snow_angel_check == 2){
                    reverse_snow_angel_check++;
                }

                reverse_snow_angel_upDate(db,reverse_snow_angel_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_snow_angel);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        View dumbbell_overflow_movie = findViewById(R.id.dumbbell_overflow_movie);
        dumbbell_overflow_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dumbbell_overflow_check = dumbbell_overflow_readData();
                Log.d("現在の再生数","は"+dumbbell_overflow_check);//現在の動画再生数を確認

                if(dumbbell_overflow_check ==0) {
                    dumbbell_overflow_check++;
                }else if(dumbbell_overflow_check == 1){
                    dumbbell_overflow_check++;
                }else if(dumbbell_overflow_check == 2){
                    dumbbell_overflow_check++;
                }

                dumbbell_overflow_upDate(db,dumbbell_overflow_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbelbent_over_flow);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button back_lat_pull_down_CountButtonForMinus = findViewById(R.id.back_lat_pull_down_minus);
        back_lat_pull_down_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.back_lat_pull_down_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_lat_pull_down_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button back_lat_pull_down_CountButtonForPlus = findViewById(R.id.back_lat_pull_down_plus);
        back_lat_pull_down_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.back_lat_pull_down_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_back_lat_pull_down_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button reverse_snow_angel_CountButtonForMinus = findViewById(R.id.reverse_snow_angel_minus);
        reverse_snow_angel_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_snow_angel_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_lat_pull_down_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_snow_angel_CountButtonForPlus = findViewById(R.id.reverse_snow_angel_plus);
        reverse_snow_angel_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_snow_angel_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_snow_angel_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });



        Button dumbbell_overflow_CountButtonForMinus = findViewById(R.id.dumbbell_overflow_minus);
        dumbbell_overflow_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_overflow_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_lat_pull_down_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button dumbbell_overflow_CountButtonForPlus = findViewById(R.id.dumbbell_overflow_plus);
        dumbbell_overflow_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_overflow_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_dumbbell_overflow_upDate(db,num);
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

        Button no_dumbbell_button = findViewById(R.id.no_dumbbell_button);
        no_dumbbell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),no_dambel.class);
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

        Button no_dambel_button = findViewById(R.id.no_dumbbell_button);
        no_dambel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),no_dambel.class);
                startActivity(intent);
            }
        });

        //設定に応じたセット数テキストに変更
        setting_data = setting_readData();
        TextView back_lat_pull_down_setNum = findViewById(R.id.back_lat_pull_down_setNum);
        TextView reverse_snow_angel_setNum = findViewById(R.id.reverse_snow_angel_setNum);
        TextView dumbbell_overflow_setNum = findViewById(R.id.dumbbell_overflow_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("20回×3");
            reverse_snow_angel_setNum.setText("20回×3");
            dumbbell_overflow_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("15回×3");
            reverse_snow_angel_setNum.setText("15回×3");
            dumbbell_overflow_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("10回×3");
            reverse_snow_angel_setNum.setText("10回×3");
            dumbbell_overflow_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            back_lat_pull_down_setNum.setText("15回×3");
            reverse_snow_angel_setNum.setText("15回×3");
            dumbbell_overflow_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            back_lat_pull_down_setNum.setText("10回×3");
            reverse_snow_angel_setNum.setText("10回×3");
            dumbbell_overflow_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            back_lat_pull_down_setNum.setText("10回×2");
            reverse_snow_angel_setNum.setText("10回×2");
            dumbbell_overflow_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("20回×2");
            reverse_snow_angel_setNum.setText("20回×2");
            dumbbell_overflow_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("15回×2");
            reverse_snow_angel_setNum.setText("15回×2");
            dumbbell_overflow_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            back_lat_pull_down_setNum.setText("10回×2");
            reverse_snow_angel_setNum.setText("10回×2");
            dumbbell_overflow_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            back_lat_pull_down_setNum.setText("15回×2");
            reverse_snow_angel_setNum.setText("15回×2");
            dumbbell_overflow_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            back_lat_pull_down_setNum.setText("10回×2");
            reverse_snow_angel_setNum.setText("10回×2");
            dumbbell_overflow_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            back_lat_pull_down_setNum.setText("10回×1");
            reverse_snow_angel_setNum.setText("10回×1");
            dumbbell_overflow_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView back_lat_pull_down_star1 = findViewById(R.id.back_lat_pull_down_star1);
        ImageView back_lat_pull_down_star2 = findViewById(R.id.back_lat_pull_down_star2);
        ImageView back_lat_pull_down_star3 = findViewById(R.id.back_lat_pull_down_star3);

        ImageView reverse_snow_angel_star1 = findViewById(R.id.reverse_snow_angel_star1);
        ImageView reverse_snow_angel_star2 = findViewById(R.id.reverse_snow_angel_star2);
        ImageView reverse_snow_angel_star3 = findViewById(R.id.reverse_snow_angel_star3);


        ImageView dumbbell_overflow_star1 = findViewById(R.id.dumbbell_overflow_star1);
        ImageView dumbbell_overflow_star2 = findViewById(R.id.dumbbell_overflow_star2);
        ImageView dumbbell_overflow_star3 = findViewById(R.id.dumbbell_overflow_star3);


        int back_lat_pull_down_score = back_lat_pull_down_readData();
        int reverse_snow_angel_score = reverse_snow_angel_readData();
        int dumbbell_overflow_score = dumbbell_overflow_readData();


        if(back_lat_pull_down_score >= 1){
            back_lat_pull_down_star1.setImageResource(R.drawable.cleared_star);
        }
        if(back_lat_pull_down_score >= 2){
            back_lat_pull_down_star2.setImageResource(R.drawable.cleared_star);
        }
        if(back_lat_pull_down_score >= 3){
            back_lat_pull_down_star3.setImageResource(R.drawable.cleared_star);
        }

        if(reverse_snow_angel_score >= 1){
            reverse_snow_angel_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(reverse_snow_angel_score >= 2){
            reverse_snow_angel_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(reverse_snow_angel_score >= 3){
            reverse_snow_angel_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(dumbbell_overflow_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }


        if(dumbbell_overflow_score >= 1){
            dumbbell_overflow_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_overflow_score >= 2){
            dumbbell_overflow_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_overflow_score >= 3){
            dumbbell_overflow_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(reverse_snow_angel_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }

        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.second_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),haikin_second.class);
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
                    Intent intent = new Intent(getApplication(), haikin_third.class);
                    startActivity(intent);
                }
            });
        }

        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_back_lat_pull_down_num = Integer.toString(today_back_lat_pull_down_readData());
        TextView text = findViewById(R.id.back_lat_pull_down_countNum);
        text.setText(today_back_lat_pull_down_num);

        String today_reverse_snow_angel_num = Integer.toString(today_reverse_snow_angel_readData());
        TextView text2 = findViewById(R.id.reverse_snow_angel_countNum);
        text2.setText(today_reverse_snow_angel_num);

        String today_dumbbell_overflow_num = Integer.toString(today_dumbbell_overflow_readData());
        TextView text4 = findViewById(R.id.dumbbell_overflow_countNum);
        text4.setText(today_dumbbell_overflow_num);


    }

    private int back_lat_pull_down_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'back_lat_pull_down_check'", null);
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


    private int reverse_snow_angel_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_snow_angel_check'", null);
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


    private int dumbbell_overflow_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'dumbbell_overflow_check'", null);
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




    public void back_lat_pull_down_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"back_lat_pull_down_check"});
    }

    public void reverse_snow_angel_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_snow_angel_check"});
    }


    public void dumbbell_overflow_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"dumbbell_overflow_check"});
    }



    public void today_back_lat_pull_down_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_back_lat_pull_down_title});
    }

    public void today_reverse_snow_angel_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_snow_angel_title});
    }


    public void today_dumbbell_overflow_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_dumbbell_overflow_title});
    }


    private int today_back_lat_pull_down_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_back_lat_pull_down_title + "'", null);
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

    private int today_reverse_snow_angel_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_snow_angel_title + "'", null);
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


    private int today_dumbbell_overflow_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_dumbbell_overflow_title + "'", null);
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
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'back_extension_check'", null);
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
