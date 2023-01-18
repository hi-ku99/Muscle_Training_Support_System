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

public class hukutyoku_second extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean third_level_check = false;
    String today_dumbbell_crunch_title = null;
    String today_knee_raise_title = null;
    String today_leg_lift_crunch_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukutyoku_second);


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


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_dumbbell_crunch_title = date + "_dumbbell_crunch";
        today_knee_raise_title = date + "_knee_raise";
        today_leg_lift_crunch_title = date + "_leg_lift_crunch";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_dumbbell_crunch_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_knee_raise_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);


        ContentValues val4 = new ContentValues();
        val4.put("title", today_leg_lift_crunch_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);
        



        View dumbbell_crunch_movie = findViewById(R.id.dumbbell_crunch_movie);
        dumbbell_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dumbbell_crunch_check = dumbbell_crunch_readData();  //現在の動画再生数を確認

                if(dumbbell_crunch_check ==0) {
                    dumbbell_crunch_check++;
                }else if(dumbbell_crunch_check == 1){
                    dumbbell_crunch_check++;
                }else if(dumbbell_crunch_check == 2){
                    dumbbell_crunch_check++;
                }

                dumbbell_crunch_upDate(db,dumbbell_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbel_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View knee_raise_movie = findViewById(R.id.knee_raise_movie);
        knee_raise_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int knee_raise_check = knee_raise_readData();
                Log.d("現在の再生数","は"+knee_raise_check);//現在の動画再生数を確認

                if(knee_raise_check ==0) {
                    knee_raise_check++;
                }else if(knee_raise_check == 1){
                    knee_raise_check++;
                }else if(knee_raise_check == 2){
                    knee_raise_check++;
                }

                knee_raise_upDate(db,knee_raise_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.knee_raise);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        View leg_lift_crunch_movie = findViewById(R.id.leg_lift_crunch_movie);
        leg_lift_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int leg_lift_crunch_check = leg_lift_crunch_readData();
                Log.d("現在の再生数","は"+leg_lift_crunch_check);//現在の動画再生数を確認

                if(leg_lift_crunch_check ==0) {
                    leg_lift_crunch_check++;
                }else if(leg_lift_crunch_check == 1){
                    leg_lift_crunch_check++;
                }else if(leg_lift_crunch_check == 2){
                    leg_lift_crunch_check++;
                }

                leg_lift_crunch_upDate(db,leg_lift_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.leg_lift_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button dumbbell_crunch_CountButtonForMinus = findViewById(R.id.dumbbell_crunch_minus);
        dumbbell_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button dumbbell_crunch_CountButtonForPlus = findViewById(R.id.dumbbell_crunch_plus);
        dumbbell_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_dumbbell_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button knee_raise_CountButtonForMinus = findViewById(R.id.knee_raise_minus);
        knee_raise_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.knee_raise_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button knee_raise_CountButtonForPlus = findViewById(R.id.knee_raise_plus);
        knee_raise_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.knee_raise_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_knee_raise_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });


        Button leg_lift_crunch_CountButtonForMinus = findViewById(R.id.leg_lift_crunch_minus);
        leg_lift_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_lift_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button leg_lift_crunch_CountButtonForPlus = findViewById(R.id.leg_lift_crunch_plus);
        leg_lift_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_lift_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_leg_lift_crunch_upDate(db,num);
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
        TextView dumbbell_crunch_setNum = findViewById(R.id.dumbbell_crunch_setNum);
        TextView knee_raise_setNum = findViewById(R.id.knee_raise_setNum);
        TextView leg_lift_crunch_setNum = findViewById(R.id.leg_lift_crunch_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("20回×3");
            knee_raise_setNum.setText("20回×3");
            leg_lift_crunch_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("15回×3");
            knee_raise_setNum.setText("15回×3");
            leg_lift_crunch_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("10回×3");
            knee_raise_setNum.setText("10回×3");
            leg_lift_crunch_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_crunch_setNum.setText("15回×3");
            knee_raise_setNum.setText("15回×3");
            leg_lift_crunch_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            dumbbell_crunch_setNum.setText("10回×3");
            knee_raise_setNum.setText("10回×3");
            leg_lift_crunch_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            dumbbell_crunch_setNum.setText("10回×2");
            knee_raise_setNum.setText("10回×2");
            leg_lift_crunch_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("20回×2");
            knee_raise_setNum.setText("20回×2");
            leg_lift_crunch_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("15回×2");
            knee_raise_setNum.setText("15回×2");
            leg_lift_crunch_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_crunch_setNum.setText("10回×2");
            knee_raise_setNum.setText("10回×2");
            leg_lift_crunch_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_crunch_setNum.setText("15回×2");
            knee_raise_setNum.setText("15回×2");
            leg_lift_crunch_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            dumbbell_crunch_setNum.setText("10回×2");
            knee_raise_setNum.setText("10回×2");
            leg_lift_crunch_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            dumbbell_crunch_setNum.setText("10回×1");
            knee_raise_setNum.setText("10回×1");
            leg_lift_crunch_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView dumbbell_crunch_star1 = findViewById(R.id.dumbbell_crunch_star1);
        ImageView dumbbell_crunch_star2 = findViewById(R.id.dumbbell_crunch_star2);
        ImageView dumbbell_crunch_star3 = findViewById(R.id.dumbbell_crunch_star3);

        ImageView knee_raise_star1 = findViewById(R.id.knee_raise_star1);
        ImageView knee_raise_star2 = findViewById(R.id.knee_raise_star2);
        ImageView knee_raise_star3 = findViewById(R.id.knee_raise_star3);

        ImageView leg_lift_crunch_star1 = findViewById(R.id.leg_lift_crunch_star1);
        ImageView leg_lift_crunch_star2 = findViewById(R.id.leg_lift_crunch_star2);
        ImageView leg_lift_crunch_star3 = findViewById(R.id.leg_lift_crunch_star3);


        int dumbbell_crunch_score = dumbbell_crunch_readData();
        int knee_raise_score = knee_raise_readData();
        int leg_lift_crunch_score = leg_lift_crunch_readData();


        if(dumbbell_crunch_score >= 1){
            dumbbell_crunch_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_crunch_score >= 2){
            dumbbell_crunch_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_crunch_score >= 3){
            dumbbell_crunch_star3.setImageResource(R.drawable.cleared_star_key);
            if (third_level_check == false) {
                if(knee_raise_score >= 3 ) {
                    ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                    third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                    third_level_button_locked.setId(R.id.third_level_button);
                    third_level_check = true;
                }
            }
        }

        if(knee_raise_score >= 1){
            knee_raise_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(knee_raise_score >= 2){
            knee_raise_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(knee_raise_score >= 3){
            knee_raise_star3.setImageResource(R.drawable.cleared_star_key);
            if(third_level_check == false) {
                if(dumbbell_crunch_score >= 3 ) {
                    ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                    third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                    third_level_button_locked.setId(R.id.third_level_button);
                    third_level_check = true;
                }
            }
        }


        if(leg_lift_crunch_score >= 1){
            leg_lift_crunch_star1.setImageResource(R.drawable.cleared_star);
        }
        if(leg_lift_crunch_score >= 2){
            leg_lift_crunch_star2.setImageResource(R.drawable.cleared_star);
        }
        if(leg_lift_crunch_score >= 3){
            leg_lift_crunch_star3.setImageResource(R.drawable.cleared_star);
        }



        if(third_level_check == true) {
            ImageButton third_level_button = findViewById(R.id.third_level_button);
            third_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),hukutyoku_third.class);
                    startActivity(intent);
                }
            });
        }




        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_dumbbell_crunch_num = Integer.toString(today_dumbbell_crunch_readData());
        TextView text = findViewById(R.id.dumbbell_crunch_countNum);
        text.setText(today_dumbbell_crunch_num);

        String today_knee_raise_num = Integer.toString(today_knee_raise_readData());
        TextView text2 = findViewById(R.id.knee_raise_countNum);
        text2.setText(today_knee_raise_num);


        String today_leg_lift_crunch_num = Integer.toString(today_leg_lift_crunch_readData());
        TextView text4 = findViewById(R.id.leg_lift_crunch_countNum);
        text4.setText(today_leg_lift_crunch_num);


    }

    private int dumbbell_crunch_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'dumbbell_crunch_check'", null);
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


    private int knee_raise_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'knee_raise_check'", null);
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


    private int leg_lift_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'leg_lift_crunch_check'", null);
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




    public void dumbbell_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"dumbbell_crunch_check"});
    }

    public void knee_raise_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"knee_raise_check"});
    }

    public void leg_lift_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"leg_lift_crunch_check"});
    }



    public void today_dumbbell_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_dumbbell_crunch_title});
    }

    public void today_knee_raise_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_knee_raise_title});
    }


    public void today_leg_lift_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_leg_lift_crunch_title});
    }


    private int today_dumbbell_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_dumbbell_crunch_title + "'", null);
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

    private int today_knee_raise_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_knee_raise_title + "'", null);
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


    private int today_leg_lift_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_leg_lift_crunch_title + "'", null);
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
