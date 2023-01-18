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

public class hukusya_third extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    String today_bicycle_crunch_title = null;
    String today_reverse_trunk_twist_title = null;
    String today_leg_up_cross_crunch_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukusya_third);


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
                Intent intent = new Intent(getApplication(), hukusya_first.class);
                //finish();
                startActivity(intent);
            }
        });

        //中級画面に戻る
        ImageButton second_level_button = findViewById(R.id.second_level_button);
        second_level_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), hukusya_second.class);
                //finish();
                startActivity(intent);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_bicycle_crunch_title = date + "_bicycle_crunch";
        today_reverse_trunk_twist_title = date + "_reverse_trunk_twist";
        today_leg_up_cross_crunch_title = date + "_leg_up_cross_crunch";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_bicycle_crunch_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_reverse_trunk_twist_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);


        ContentValues val4 = new ContentValues();
        val4.put("title", today_leg_up_cross_crunch_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);




        View bicycle_crunch_movie = findViewById(R.id.bicycle_crunch_movie);
        bicycle_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int bicycle_crunch_check = bicycle_crunch_readData();  //現在の動画再生数を確認

                if(bicycle_crunch_check ==0) {
                    bicycle_crunch_check++;
                }else if(bicycle_crunch_check == 1){
                    bicycle_crunch_check++;
                }else if(bicycle_crunch_check == 2){
                    bicycle_crunch_check++;
                }

                bicycle_crunch_upDate(db,bicycle_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.dumbbell_curl);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View reverse_trunk_twist_movie = findViewById(R.id.reverse_trunk_twist_movie);
        reverse_trunk_twist_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_trunk_twist_check = reverse_trunk_twist_readData();
                Log.d("現在の再生数","は"+reverse_trunk_twist_check);//現在の動画再生数を確認

                if(reverse_trunk_twist_check ==0) {
                    reverse_trunk_twist_check++;
                }else if(reverse_trunk_twist_check == 1){
                    reverse_trunk_twist_check++;
                }else if(reverse_trunk_twist_check == 2){
                    reverse_trunk_twist_check++;
                }

                reverse_trunk_twist_upDate(db,reverse_trunk_twist_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_trank_twist);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        View leg_up_cross_crunch_movie = findViewById(R.id.leg_up_cross_crunch_movie);
        leg_up_cross_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int leg_up_cross_crunch_check = leg_up_cross_crunch_readData();
                Log.d("現在の再生数","は"+leg_up_cross_crunch_check);//現在の動画再生数を確認

                if(leg_up_cross_crunch_check ==0) {
                    leg_up_cross_crunch_check++;
                }else if(leg_up_cross_crunch_check == 1){
                    leg_up_cross_crunch_check++;
                }else if(leg_up_cross_crunch_check == 2){
                    leg_up_cross_crunch_check++;
                }

                leg_up_cross_crunch_upDate(db,leg_up_cross_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.cross_leg_reverse_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button bicycle_crunch_CountButtonForMinus = findViewById(R.id.bicycle_crunch_minus);
        bicycle_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.bicycle_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_bicycle_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button bicycle_crunch_CountButtonForPlus = findViewById(R.id.bicycle_crunch_plus);
        bicycle_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.bicycle_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_bicycle_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button reverse_trunk_twist_CountButtonForMinus = findViewById(R.id.reverse_trunk_twist_minus);
        reverse_trunk_twist_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_trunk_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_bicycle_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_trunk_twist_CountButtonForPlus = findViewById(R.id.reverse_trunk_twist_plus);
        reverse_trunk_twist_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_trunk_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_trunk_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });



        Button leg_up_cross_crunch_CountButtonForMinus = findViewById(R.id.leg_up_cross_crunch_minus);
        leg_up_cross_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_up_cross_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_bicycle_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button leg_up_cross_crunch_CountButtonForPlus = findViewById(R.id.leg_up_cross_crunch_plus);
        leg_up_cross_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_up_cross_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_leg_up_cross_crunch_upDate(db,num);
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
        TextView bicycle_crunch_setNum = findViewById(R.id.bicycle_crunch_setNum);
        TextView reverse_trunk_twist_setNum = findViewById(R.id.reverse_trunk_twist_setNum);
        TextView leg_up_cross_crunch_setNum = findViewById(R.id.leg_up_cross_crunch_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("20回×3");
            reverse_trunk_twist_setNum.setText("20回×3");
            leg_up_cross_crunch_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("15回×3");
            reverse_trunk_twist_setNum.setText("15回×3");
            leg_up_cross_crunch_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("10回×3");
            reverse_trunk_twist_setNum.setText("10回×3");
            leg_up_cross_crunch_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            bicycle_crunch_setNum.setText("15回×3");
            reverse_trunk_twist_setNum.setText("15回×3");
            leg_up_cross_crunch_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            bicycle_crunch_setNum.setText("10回×3");
            reverse_trunk_twist_setNum.setText("10回×3");
            leg_up_cross_crunch_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            bicycle_crunch_setNum.setText("10回×2");
            reverse_trunk_twist_setNum.setText("10回×2");
            leg_up_cross_crunch_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("20回×2");
            reverse_trunk_twist_setNum.setText("20回×2");
            leg_up_cross_crunch_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("15回×2");
            reverse_trunk_twist_setNum.setText("15回×2");
            leg_up_cross_crunch_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            bicycle_crunch_setNum.setText("10回×2");
            reverse_trunk_twist_setNum.setText("10回×2");
            leg_up_cross_crunch_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            bicycle_crunch_setNum.setText("15回×2");
            reverse_trunk_twist_setNum.setText("15回×2");
            leg_up_cross_crunch_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            bicycle_crunch_setNum.setText("10回×2");
            reverse_trunk_twist_setNum.setText("10回×2");
            leg_up_cross_crunch_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            bicycle_crunch_setNum.setText("10回×1");
            reverse_trunk_twist_setNum.setText("10回×1");
            leg_up_cross_crunch_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView bicycle_crunch_star1 = findViewById(R.id.bicycle_crunch_star1);
        ImageView bicycle_crunch_star2 = findViewById(R.id.bicycle_crunch_star2);
        ImageView bicycle_crunch_star3 = findViewById(R.id.bicycle_crunch_star3);

        ImageView reverse_trunk_twist_star1 = findViewById(R.id.reverse_trunk_twist_star1);
        ImageView reverse_trunk_twist_star2 = findViewById(R.id.reverse_trunk_twist_star2);
        ImageView reverse_trunk_twist_star3 = findViewById(R.id.reverse_trunk_twist_star3);


        ImageView leg_up_cross_crunch_star1 = findViewById(R.id.leg_up_cross_crunch_star1);
        ImageView leg_up_cross_crunch_star2 = findViewById(R.id.leg_up_cross_crunch_star2);
        ImageView leg_up_cross_crunch_star3 = findViewById(R.id.leg_up_cross_crunch_star3);


        int bicycle_crunch_score = bicycle_crunch_readData();
        int reverse_trunk_twist_score = reverse_trunk_twist_readData();
        int leg_up_cross_crunch_score = leg_up_cross_crunch_readData();


        if(bicycle_crunch_score >= 1){
            bicycle_crunch_star1.setImageResource(R.drawable.cleared_star);
        }
        if(bicycle_crunch_score >= 2){
            bicycle_crunch_star2.setImageResource(R.drawable.cleared_star);
        }
        if(bicycle_crunch_score >= 3){
            bicycle_crunch_star3.setImageResource(R.drawable.cleared_star);
        }

        if(reverse_trunk_twist_score >= 1){
            reverse_trunk_twist_star1.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_trunk_twist_score >= 2){
            reverse_trunk_twist_star2.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_trunk_twist_score >= 3){
            reverse_trunk_twist_star3.setImageResource(R.drawable.cleared_star);
        }


        if(leg_up_cross_crunch_score >= 1){
            leg_up_cross_crunch_star1.setImageResource(R.drawable.cleared_star);
        }
        if(leg_up_cross_crunch_score >= 2){
            leg_up_cross_crunch_star2.setImageResource(R.drawable.cleared_star);
        }
        if(leg_up_cross_crunch_score >= 3){
            leg_up_cross_crunch_star3.setImageResource(R.drawable.cleared_star);
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_bicycle_crunch_num = Integer.toString(today_bicycle_crunch_readData());
        TextView text = findViewById(R.id.bicycle_crunch_countNum);
        text.setText(today_bicycle_crunch_num);

        String today_reverse_trunk_twist_num = Integer.toString(today_reverse_trunk_twist_readData());
        TextView text2 = findViewById(R.id.reverse_trunk_twist_countNum);
        text2.setText(today_reverse_trunk_twist_num);

        String today_leg_up_cross_crunch_num = Integer.toString(today_leg_up_cross_crunch_readData());
        TextView text4 = findViewById(R.id.leg_up_cross_crunch_countNum);
        text4.setText(today_leg_up_cross_crunch_num);


    }

    private int bicycle_crunch_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'bicycle_crunch_check'", null);
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


    private int reverse_trunk_twist_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_trunk_twist_check'", null);
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


    private int leg_up_cross_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'leg_up_cross_crunch_check'", null);
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




    public void bicycle_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"bicycle_crunch_check"});
    }

    public void reverse_trunk_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_trunk_twist_check"});
    }


    public void leg_up_cross_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"leg_up_cross_crunch_check"});
    }



    public void today_bicycle_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_bicycle_crunch_title});
    }

    public void today_reverse_trunk_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_trunk_twist_title});
    }


    public void today_leg_up_cross_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_leg_up_cross_crunch_title});
    }


    private int today_bicycle_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_bicycle_crunch_title + "'", null);
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

    private int today_reverse_trunk_twist_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_trunk_twist_title + "'", null);
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


    private int today_leg_up_cross_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_leg_up_cross_crunch_title + "'", null);
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
