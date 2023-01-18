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

public class haikin_second extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean third_level_check = false;
    String today_back_extension_title = null;
    String today_reverse_elbow_pushup_title = null;
    String today_reverse_plank_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.haikin_second);


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
                Intent intent = new Intent(getApplication(), haikin_first.class);
                //finish();
                startActivity(intent);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_back_extension_title = date + "_back_extension";
        today_reverse_elbow_pushup_title = date + "_reverse_elbow_pushup";
        today_reverse_plank_title = date + "_reverse_plank";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_back_extension_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_reverse_elbow_pushup_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_reverse_plank_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);



        View back_extension_movie = findViewById(R.id.back_extension_movie);
        back_extension_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int back_extension_check = back_extension_readData();  //現在の動画再生数を確認

                if(back_extension_check ==0) {
                    back_extension_check++;
                }else if(back_extension_check == 1){
                    back_extension_check++;
                }else if(back_extension_check == 2){
                    back_extension_check++;
                }

                back_extension_upDate(db,back_extension_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.back_extetian);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View reverse_elbow_pushup_movie = findViewById(R.id.reverse_elbow_pushup_movie);
        reverse_elbow_pushup_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_elbow_pushup_check = reverse_elbow_pushup_readData();
                Log.d("現在の再生数","は"+reverse_elbow_pushup_check);//現在の動画再生数を確認

                if(reverse_elbow_pushup_check ==0) {
                    reverse_elbow_pushup_check++;
                }else if(reverse_elbow_pushup_check == 1){
                    reverse_elbow_pushup_check++;
                }else if(reverse_elbow_pushup_check == 2){
                    reverse_elbow_pushup_check++;
                }

                reverse_elbow_pushup_upDate(db,reverse_elbow_pushup_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_elbow_push_up);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View reverse_plank_movie = findViewById(R.id.reverse_plank_movie);
        reverse_plank_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_plank_check = reverse_plank_readData();
                Log.d("現在の再生数","は"+reverse_plank_check);//現在の動画再生数を確認

                if(reverse_plank_check ==0) {
                    reverse_plank_check++;
                }else if(reverse_plank_check == 1){
                    reverse_plank_check++;
                }else if(reverse_plank_check == 2){
                    reverse_plank_check++;
                }

                reverse_plank_upDate(db,reverse_plank_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_prank);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        //セット数増減と表示

        Button back_extension_CountButtonForMinus = findViewById(R.id.back_extension_minus);
        back_extension_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.back_extension_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_extension_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button back_extension_CountButtonForPlus = findViewById(R.id.back_extension_plus);
        back_extension_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.back_extension_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_back_extension_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button reverse_elbow_pushup_CountButtonForMinus = findViewById(R.id.reverse_elbow_pushup_minus);
        reverse_elbow_pushup_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_elbow_pushup_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_extension_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_elbow_pushup_CountButtonForPlus = findViewById(R.id.reverse_elbow_pushup_plus);
        reverse_elbow_pushup_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_elbow_pushup_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_elbow_pushup_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button reverse_plank_CountButtonForMinus = findViewById(R.id.reverse_plank_minus);
        reverse_plank_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_back_extension_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_plank_CountButtonForPlus = findViewById(R.id.reverse_plank_plus);
        reverse_plank_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_plank_upDate(db,num);
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
        TextView back_extension_setNum = findViewById(R.id.back_extension_setNum);
        TextView reverse_elbow_pushup_setNum = findViewById(R.id.reverse_elbow_pushup_setNum);
        TextView reverse_plank_setNum = findViewById(R.id.reverse_plank_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            back_extension_setNum.setText("20回×3");
            reverse_elbow_pushup_setNum.setText("20回×3");
            reverse_plank_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            back_extension_setNum.setText("15回×3");
            reverse_elbow_pushup_setNum.setText("15回×3");
            reverse_plank_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            back_extension_setNum.setText("10回×3");
            reverse_elbow_pushup_setNum.setText("10回×3");
            reverse_plank_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            back_extension_setNum.setText("15回×3");
            reverse_elbow_pushup_setNum.setText("15回×3");
            reverse_plank_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            back_extension_setNum.setText("10回×3");
            reverse_elbow_pushup_setNum.setText("10回×3");
            reverse_plank_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            back_extension_setNum.setText("10回×2");
            reverse_elbow_pushup_setNum.setText("10回×2");
            reverse_plank_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            back_extension_setNum.setText("20回×2");
            reverse_elbow_pushup_setNum.setText("20回×2");
            reverse_plank_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            back_extension_setNum.setText("15回×2");
            reverse_elbow_pushup_setNum.setText("15回×2");
            reverse_plank_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            back_extension_setNum.setText("10回×2");
            reverse_elbow_pushup_setNum.setText("10回×2");
            reverse_plank_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            back_extension_setNum.setText("15回×2");
            reverse_elbow_pushup_setNum.setText("15回×2");
            reverse_plank_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            back_extension_setNum.setText("10回×2");
            reverse_elbow_pushup_setNum.setText("10回×2");
            reverse_plank_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            back_extension_setNum.setText("10回×1");
            reverse_elbow_pushup_setNum.setText("10回×1");
            reverse_plank_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView back_extension_star1 = findViewById(R.id.back_extension_star1);
        ImageView back_extension_star2 = findViewById(R.id.back_extension_star2);
        ImageView back_extension_star3 = findViewById(R.id.back_extension_star3);

        ImageView reverse_elbow_pushup_star1 = findViewById(R.id.reverse_elbow_pushup_star1);
        ImageView reverse_elbow_pushup_star2 = findViewById(R.id.reverse_elbow_pushup_star2);
        ImageView reverse_elbow_pushup_star3 = findViewById(R.id.reverse_elbow_pushup_star3);

        ImageView reverse_plank_star1 = findViewById(R.id.reverse_plank_star1);
        ImageView reverse_plank_star2 = findViewById(R.id.reverse_plank_star2);
        ImageView reverse_plank_star3 = findViewById(R.id.reverse_plank_star3);


        int back_extension_score = back_extension_readData();
        int reverse_elbow_pushup_score = reverse_elbow_pushup_readData();
        int reverse_plank_score = reverse_plank_readData();


        if(back_extension_score >= 1){
            back_extension_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(back_extension_score >= 2){
            back_extension_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(back_extension_score >= 3){
            back_extension_star3.setImageResource(R.drawable.cleared_star_key);
            if (third_level_check == false) {
                ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                third_level_button_locked.setId(R.id.third_level_button);
                third_level_check = true;
            }
        }

        if(reverse_elbow_pushup_score >= 1){
            reverse_elbow_pushup_star1.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_elbow_pushup_score >= 2){
            reverse_elbow_pushup_star2.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_elbow_pushup_score >= 3){
            reverse_elbow_pushup_star3.setImageResource(R.drawable.cleared_star);
        }


        if(reverse_plank_score >= 1){
            reverse_plank_star1.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_plank_score >= 2){
            reverse_plank_star2.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_plank_score >= 3){
            reverse_plank_star3.setImageResource(R.drawable.cleared_star);
        }



        if(third_level_check == true) {
            ImageButton third_level_button = findViewById(R.id.third_level_button);
            third_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),haikin_third.class);
                    startActivity(intent);
                }
            });
        }




        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_back_extension_num = Integer.toString(today_back_extension_readData());
        TextView text = findViewById(R.id.back_extension_countNum);
        text.setText(today_back_extension_num);

        String today_reverse_elbow_pushup_num = Integer.toString(today_reverse_elbow_pushup_readData());
        TextView text2 = findViewById(R.id.reverse_elbow_pushup_countNum);
        text2.setText(today_reverse_elbow_pushup_num);

        String today_reverse_plank_num = Integer.toString(today_reverse_plank_readData());
        TextView text3 = findViewById(R.id.reverse_plank_countNum);
        text3.setText(today_reverse_plank_num);

    }

    private int back_extension_readData() {
        if (helper == null) {
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


    private int reverse_elbow_pushup_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_elbow_pushup_check'", null);
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

    private int reverse_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_plank_check'", null);
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




    public void back_extension_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"back_extension_check"});
    }

    public void reverse_elbow_pushup_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_elbow_pushup_check"});
    }

    public void reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_plank_check"});
    }


    public void today_back_extension_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_back_extension_title});
    }

    public void today_reverse_elbow_pushup_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_elbow_pushup_title});
    }

    public void today_reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_plank_title});
    }


    private int today_back_extension_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_back_extension_title + "'", null);
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

    private int today_reverse_elbow_pushup_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_elbow_pushup_title + "'", null);
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

    private int today_reverse_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_plank_title + "'", null);
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
