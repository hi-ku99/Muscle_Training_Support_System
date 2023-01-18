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

public class nitoukin_first extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    String today_dumbbell_curl_title = null;
    String today_hammer_curl_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nitoukin_first);


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
        today_dumbbell_curl_title = date + "_dumbbell_curl";
        today_hammer_curl_title = date + "_hammer_curl";



        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_dumbbell_curl_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_hammer_curl_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        View dumbbell_curl_movie = findViewById(R.id.dumbbell_curl_movie);
        dumbbell_curl_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dumbbell_curl_check = dumbbell_curl_readData();  //現在の動画再生数を確認

                if(dumbbell_curl_check ==0) {
                    dumbbell_curl_check++;
                }else if(dumbbell_curl_check == 1){
                    dumbbell_curl_check++;
                }else if(dumbbell_curl_check == 2){
                    dumbbell_curl_check++;
                }

                dumbbell_curl_upDate(db,dumbbell_curl_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.dumbbell_curl);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View hammer_curl_movie = findViewById(R.id.hammer_curl_movie);
        hammer_curl_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hammer_curl_check = hammer_curl_readData();
                Log.d("現在の再生数","は"+hammer_curl_check);//現在の動画再生数を確認

                if(hammer_curl_check ==0) {
                    hammer_curl_check++;
                }else if(hammer_curl_check == 1){
                    hammer_curl_check++;
                }else if(hammer_curl_check == 2){
                    hammer_curl_check++;
                }

                hammer_curl_upDate(db,hammer_curl_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.hammer_curl);
                // 画面移行スタート,
                startActivity(intent);
            }
        });

        Button dumbbell_curl_CountButtonForMinus = findViewById(R.id.dumbbell_curl_minus);
        dumbbell_curl_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_curl_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_curl_upDate(db,num);
                text.setText(String.valueOf(num));

                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button dumbbell_curl_CountButtonForPlus = findViewById(R.id.dumbbell_curl_plus);
        dumbbell_curl_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_curl_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_dumbbell_curl_upDate(db,num);
                text.setText(String.valueOf(num));

                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button hammer_curl_CountButtonForMinus = findViewById(R.id.hammer_curl_minus);
        hammer_curl_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hammer_curl_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_hammer_curl_upDate(db,num);
                text.setText(String.valueOf(num));

                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button hammer_curl_CountButtonForPlus = findViewById(R.id.hammer_curl_plus);
        hammer_curl_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hammer_curl_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_hammer_curl_upDate(db,num);
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

        Button no_dumbbell_button = findViewById(R.id.no_dumbbell_button);
        no_dumbbell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),no_dambel.class);
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
        TextView dumbbell_curl_setNum = findViewById(R.id.dumbbell_curl_setNum);
        TextView hammer_curl_setNum = findViewById(R.id.hammer_curl_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("20回×3");
            hammer_curl_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("15回×3");
            hammer_curl_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("10回×3");
            hammer_curl_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_curl_setNum.setText("15回×3");
            hammer_curl_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            dumbbell_curl_setNum.setText("10回×3");
            hammer_curl_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            dumbbell_curl_setNum.setText("10回×2");
            hammer_curl_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("20回×2");
            hammer_curl_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("15回×2");
            hammer_curl_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("10回×2");
            hammer_curl_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_curl_setNum.setText("15回×2");
            hammer_curl_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            dumbbell_curl_setNum.setText("10回×2");
            hammer_curl_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            dumbbell_curl_setNum.setText("10回×1");
            hammer_curl_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView dumbbell_curl_star1 = findViewById(R.id.dumbbell_curl_star1);
        ImageView dumbbell_curl_star2 = findViewById(R.id.dumbbell_curl_star2);
        ImageView dumbbell_curl_star3 = findViewById(R.id.dumbbell_curl_star3);

        ImageView hammer_curl_star1 = findViewById(R.id.hammer_curl_star1);
        ImageView hammer_curl_star2 = findViewById(R.id.hammer_curl_star2);
        ImageView hammer_curl_star3 = findViewById(R.id.hammer_curl_star3);



        int dumbbell_curl_score = dumbbell_curl_readData();
        int hammer_curl_score = hammer_curl_readData();

        Log.d("戻ってきたときの再生数","は"+hammer_curl_score);//現在の動画再生数を確認

        if(dumbbell_curl_score >= 1){
            dumbbell_curl_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_curl_score >= 2){
            dumbbell_curl_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_curl_score >= 3){
            dumbbell_curl_star3.setImageResource(R.drawable.cleared_star_key);
            if(second_level_check == false) {
                ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                second_level_button_locked.setId(R.id.second_level_button);
                second_level_check = true;
            }
            
        }

        if(hammer_curl_score >= 1){
            hammer_curl_star1.setImageResource(R.drawable.cleared_star);
        }
        if(hammer_curl_score >= 2){
            hammer_curl_star2.setImageResource(R.drawable.cleared_star);
        }
        if(hammer_curl_score >= 3){
            hammer_curl_star3.setImageResource(R.drawable.cleared_star);
        }

        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.second_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), nitoukin_second.class);
                    startActivity(intent);
                }
            });
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_dumbbell_curl_num = Integer.toString(today_dumbbell_curl_readData());
        TextView text = findViewById(R.id.dumbbell_curl_countNum);
        text.setText(today_dumbbell_curl_num);

        String today_hammer_curl_num = Integer.toString(today_hammer_curl_readData());
        TextView text2 = findViewById(R.id.hammer_curl_countNum);
        text2.setText(today_hammer_curl_num);

    }

    private int dumbbell_curl_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'dumbbell_curl_check'", null);
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


    private int hammer_curl_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'hammer_curl_check'", null);
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


    public void dumbbell_curl_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"dumbbell_curl_check"});
    }

    public void hammer_curl_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"hammer_curl_check"});
    }


    public void today_dumbbell_curl_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_dumbbell_curl_title});
    }

    public void today_hammer_curl_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_hammer_curl_title});
    }

    private int today_dumbbell_curl_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_dumbbell_curl_title + "'", null);
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

    private int today_hammer_curl_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_hammer_curl_title + "'", null);
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
        Cursor cursor = db.rawQuery("select title ,score from calendar_table where title ='" + date + "'", null);
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
