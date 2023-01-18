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

public class kyakubu_second extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean third_level_check = false;
    String today_jumping_squat_title = null;
    String today_side_lunge_title = null;
    String today_high_reverse_plank_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyakubu_second);


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
                Intent intent = new Intent(getApplication(), kyakubu_first.class);
                //finish();
                startActivity(intent);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_jumping_squat_title = date + "_jumping_squat";
        today_side_lunge_title = date + "_side_lunge";
        today_high_reverse_plank_title = date + "_high_reverse_plank";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_jumping_squat_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_side_lunge_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_high_reverse_plank_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);



        View jumping_squat_movie = findViewById(R.id.jumping_squat_movie);
        jumping_squat_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int jumping_squat_check = jumping_squat_readData();  //現在の動画再生数を確認

                if(jumping_squat_check ==0) {
                    jumping_squat_check++;
                }else if(jumping_squat_check == 1){
                    jumping_squat_check++;
                }else if(jumping_squat_check == 2){
                    jumping_squat_check++;
                }

                jumping_squat_upDate(db,jumping_squat_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.jump_squat);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View side_lunge_movie = findViewById(R.id.side_lunge_movie);
        side_lunge_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_lunge_check = side_lunge_readData();
                Log.d("現在の再生数","は"+side_lunge_check);//現在の動画再生数を確認

                if(side_lunge_check ==0) {
                    side_lunge_check++;
                }else if(side_lunge_check == 1){
                    side_lunge_check++;
                }else if(side_lunge_check == 2){
                    side_lunge_check++;
                }

                side_lunge_upDate(db,side_lunge_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_lunge);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View high_reverse_plank_movie = findViewById(R.id.high_reverse_plank_movie);
        high_reverse_plank_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int high_reverse_plank_check = high_reverse_plank_readData();
                Log.d("現在の再生数","は"+high_reverse_plank_check);//現在の動画再生数を確認

                if(high_reverse_plank_check ==0) {
                    high_reverse_plank_check++;
                }else if(high_reverse_plank_check == 1){
                    high_reverse_plank_check++;
                }else if(high_reverse_plank_check == 2){
                    high_reverse_plank_check++;
                }

                high_reverse_plank_upDate(db,high_reverse_plank_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.high_reverse_plank);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        //セット数増減と表示

        Button jumping_squat_CountButtonForMinus = findViewById(R.id.jumping_squat_minus);
        jumping_squat_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.jumping_squat_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_jumping_squat_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button jumping_squat_CountButtonForPlus = findViewById(R.id.jumping_squat_plus);
        jumping_squat_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.jumping_squat_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_jumping_squat_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button side_lunge_CountButtonForMinus = findViewById(R.id.side_lunge_minus);
        side_lunge_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_lunge_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_jumping_squat_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button side_lunge_CountButtonForPlus = findViewById(R.id.side_lunge_plus);
        side_lunge_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_lunge_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_side_lunge_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button high_reverse_plank_CountButtonForMinus = findViewById(R.id.high_reverse_plank_minus);
        high_reverse_plank_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.high_reverse_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_jumping_squat_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button high_reverse_plank_CountButtonForPlus = findViewById(R.id.high_reverse_plank_plus);
        high_reverse_plank_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.high_reverse_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_high_reverse_plank_upDate(db,num);
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
        TextView jumping_squat_setNum = findViewById(R.id.jumping_squat_setNum);
        TextView side_lunge_setNum = findViewById(R.id.side_lunge_setNum);
        TextView high_reverse_plank_setNum = findViewById(R.id.high_reverse_plank_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            jumping_squat_setNum.setText("20回×3");
            side_lunge_setNum.setText("20回×3");
            high_reverse_plank_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            jumping_squat_setNum.setText("15回×3");
            side_lunge_setNum.setText("15回×3");
            high_reverse_plank_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            jumping_squat_setNum.setText("10回×3");
            side_lunge_setNum.setText("10回×3");
            high_reverse_plank_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            jumping_squat_setNum.setText("15回×3");
            side_lunge_setNum.setText("15回×3");
            high_reverse_plank_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            jumping_squat_setNum.setText("10回×3");
            side_lunge_setNum.setText("10回×3");
            high_reverse_plank_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            jumping_squat_setNum.setText("10回×2");
            side_lunge_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            jumping_squat_setNum.setText("20回×2");
            side_lunge_setNum.setText("20回×2");
            high_reverse_plank_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            jumping_squat_setNum.setText("15回×2");
            side_lunge_setNum.setText("15回×2");
            high_reverse_plank_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            jumping_squat_setNum.setText("10回×2");
            side_lunge_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            jumping_squat_setNum.setText("15回×2");
            side_lunge_setNum.setText("15回×2");
            high_reverse_plank_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            jumping_squat_setNum.setText("10回×2");
            side_lunge_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            jumping_squat_setNum.setText("10回×1");
            side_lunge_setNum.setText("10回×1");
            high_reverse_plank_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView jumping_squat_star1 = findViewById(R.id.jumping_squat_star1);
        ImageView jumping_squat_star2 = findViewById(R.id.jumping_squat_star2);
        ImageView jumping_squat_star3 = findViewById(R.id.jumping_squat_star3);

        ImageView side_lunge_star1 = findViewById(R.id.side_lunge_star1);
        ImageView side_lunge_star2 = findViewById(R.id.side_lunge_star2);
        ImageView side_lunge_star3 = findViewById(R.id.side_lunge_star3);

        ImageView high_reverse_plank_star1 = findViewById(R.id.high_reverse_plank_star1);
        ImageView high_reverse_plank_star2 = findViewById(R.id.high_reverse_plank_star2);
        ImageView high_reverse_plank_star3 = findViewById(R.id.high_reverse_plank_star3);


        int jumping_squat_score = jumping_squat_readData();
        int side_lunge_score = side_lunge_readData();
        int high_reverse_plank_score = high_reverse_plank_readData();


        if(jumping_squat_score >= 1){
            jumping_squat_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(jumping_squat_score >= 2){
            jumping_squat_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(jumping_squat_score >= 3){
            jumping_squat_star3.setImageResource(R.drawable.cleared_star_key);
            if (third_level_check == false) {
                ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                third_level_button_locked.setId(R.id.third_level_button);
                third_level_check = true;
            }
        }

        if(side_lunge_score >= 1){
            side_lunge_star1.setImageResource(R.drawable.cleared_star);
        }
        if(side_lunge_score >= 2){
            side_lunge_star2.setImageResource(R.drawable.cleared_star);
        }
        if(side_lunge_score >= 3){
            side_lunge_star3.setImageResource(R.drawable.cleared_star);
        }


        if(high_reverse_plank_score >= 1){
            high_reverse_plank_star1.setImageResource(R.drawable.cleared_star);
        }
        if(high_reverse_plank_score >= 2){
            high_reverse_plank_star2.setImageResource(R.drawable.cleared_star);
        }
        if(high_reverse_plank_score >= 3){
            high_reverse_plank_star3.setImageResource(R.drawable.cleared_star);
        }



        if(third_level_check == true) {
            ImageButton third_level_button = findViewById(R.id.third_level_button);
            third_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),kyakubu_third.class);
                    startActivity(intent);
                }
            });
        }




        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_jumping_squat_num = Integer.toString(today_jumping_squat_readData());
        TextView text = findViewById(R.id.jumping_squat_countNum);
        text.setText(today_jumping_squat_num);

        String today_side_lunge_num = Integer.toString(today_side_lunge_readData());
        TextView text2 = findViewById(R.id.side_lunge_countNum);
        text2.setText(today_side_lunge_num);

        String today_high_reverse_plank_num = Integer.toString(today_high_reverse_plank_readData());
        TextView text3 = findViewById(R.id.high_reverse_plank_countNum);
        text3.setText(today_high_reverse_plank_num);

    }

    private int jumping_squat_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'jumping_squat_check'", null);
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


    private int side_lunge_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'side_lunge_check'", null);
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

    private int high_reverse_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'high_reverse_plank_check'", null);
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




    public void jumping_squat_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"jumping_squat_check"});
    }

    public void side_lunge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_lunge_check"});
    }

    public void high_reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"high_reverse_plank_check"});
    }


    public void today_jumping_squat_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_jumping_squat_title});
    }

    public void today_side_lunge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_side_lunge_title});
    }

    public void today_high_reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_high_reverse_plank_title});
    }


    private int today_jumping_squat_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_jumping_squat_title + "'", null);
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

    private int today_side_lunge_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_side_lunge_title + "'", null);
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

    private int today_high_reverse_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_high_reverse_plank_title + "'", null);
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
