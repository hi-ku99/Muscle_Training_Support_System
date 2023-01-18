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

public class hukusya_second extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean third_level_check = false;
    String today_dumbbell_twist_title = null;
    String today_heel_touch_crunch_title = null;
    String today_side_elbow_bridge_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukusya_second);


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


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_dumbbell_twist_title = date + "_dumbbell_twist";
        today_heel_touch_crunch_title = date + "_heel_touch_crunch";
        today_side_elbow_bridge_title = date + "_side_elbow_bridge";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_dumbbell_twist_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_heel_touch_crunch_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_side_elbow_bridge_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);
        


        View dumbbell_twist_movie = findViewById(R.id.dumbbell_twist_movie);
        dumbbell_twist_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dumbbell_twist_check = dumbbell_twist_readData();  //現在の動画再生数を確認

                if(dumbbell_twist_check ==0) {
                    dumbbell_twist_check++;
                }else if(dumbbell_twist_check == 1){
                    dumbbell_twist_check++;
                }else if(dumbbell_twist_check == 2){
                    dumbbell_twist_check++;
                }

                dumbbell_twist_upDate(db,dumbbell_twist_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbel_twist);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View heel_touch_crunch_movie = findViewById(R.id.heel_touch_crunch_movie);
        heel_touch_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int heel_touch_crunch_check = heel_touch_crunch_readData();
                Log.d("現在の再生数","は"+heel_touch_crunch_check);//現在の動画再生数を確認

                if(heel_touch_crunch_check ==0) {
                    heel_touch_crunch_check++;
                }else if(heel_touch_crunch_check == 1){
                    heel_touch_crunch_check++;
                }else if(heel_touch_crunch_check == 2){
                    heel_touch_crunch_check++;
                }

                heel_touch_crunch_upDate(db,heel_touch_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.heal_touch_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View side_elbow_bridge_movie = findViewById(R.id.side_elbow_bridge_movie);
        side_elbow_bridge_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_elbow_bridge_check = side_elbow_bridge_readData();
                Log.d("現在の再生数","は"+side_elbow_bridge_check);//現在の動画再生数を確認

                if(side_elbow_bridge_check ==0) {
                    side_elbow_bridge_check++;
                }else if(side_elbow_bridge_check == 1){
                    side_elbow_bridge_check++;
                }else if(side_elbow_bridge_check == 2){
                    side_elbow_bridge_check++;
                }

                side_elbow_bridge_upDate(db,side_elbow_bridge_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_elbow_brich);
                // 画面移行スタート
                startActivity(intent);
            }
        });


        //セット数増減と表示

        Button dumbbell_twist_CountButtonForMinus = findViewById(R.id.dumbbell_twist_minus);
        dumbbell_twist_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button dumbbell_twist_CountButtonForPlus = findViewById(R.id.dumbbell_twist_plus);
        dumbbell_twist_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.dumbbell_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_dumbbell_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button heel_touch_crunch_CountButtonForMinus = findViewById(R.id.heel_touch_crunch_minus);
        heel_touch_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.heel_touch_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button heel_touch_crunch_CountButtonForPlus = findViewById(R.id.heel_touch_crunch_plus);
        heel_touch_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.heel_touch_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_heel_touch_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button side_elbow_bridge_CountButtonForMinus = findViewById(R.id.side_elbow_bridge_minus);
        side_elbow_bridge_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_elbow_bridge_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_dumbbell_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button side_elbow_bridge_CountButtonForPlus = findViewById(R.id.side_elbow_bridge_plus);
        side_elbow_bridge_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_elbow_bridge_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_side_elbow_bridge_upDate(db,num);
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
        TextView dumbbell_twist_setNum = findViewById(R.id.dumbbell_twist_setNum);
        TextView heel_touch_crunch_setNum = findViewById(R.id.heel_touch_crunch_setNum);
        TextView side_elbow_bridge_setNum = findViewById(R.id.side_elbow_bridge_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("20回×3");
            heel_touch_crunch_setNum.setText("20回×3");
            side_elbow_bridge_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("15回×3");
            heel_touch_crunch_setNum.setText("15回×3");
            side_elbow_bridge_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("10回×3");
            heel_touch_crunch_setNum.setText("10回×3");
            side_elbow_bridge_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_twist_setNum.setText("15回×3");
            heel_touch_crunch_setNum.setText("15回×3");
            side_elbow_bridge_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            dumbbell_twist_setNum.setText("10回×3");
            heel_touch_crunch_setNum.setText("10回×3");
            side_elbow_bridge_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            dumbbell_twist_setNum.setText("10回×2");
            heel_touch_crunch_setNum.setText("10回×2");
            side_elbow_bridge_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("20回×2");
            heel_touch_crunch_setNum.setText("20回×2");
            side_elbow_bridge_setNum.setText("20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("15回×2");
            heel_touch_crunch_setNum.setText("15回×2");
            side_elbow_bridge_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_twist_setNum.setText("10回×2");
            heel_touch_crunch_setNum.setText("10回×2");
            side_elbow_bridge_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_twist_setNum.setText("15回×2");
            heel_touch_crunch_setNum.setText("15回×2");
            side_elbow_bridge_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            dumbbell_twist_setNum.setText("10回×2");
            heel_touch_crunch_setNum.setText("10回×2");
            side_elbow_bridge_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            dumbbell_twist_setNum.setText("10回×1");
            heel_touch_crunch_setNum.setText("10回×1");
            side_elbow_bridge_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView dumbbell_twist_star1 = findViewById(R.id.dumbbell_twist_star1);
        ImageView dumbbell_twist_star2 = findViewById(R.id.dumbbell_twist_star2);
        ImageView dumbbell_twist_star3 = findViewById(R.id.dumbbell_twist_star3);

        ImageView heel_touch_crunch_star1 = findViewById(R.id.heel_touch_crunch_star1);
        ImageView heel_touch_crunch_star2 = findViewById(R.id.heel_touch_crunch_star2);
        ImageView heel_touch_crunch_star3 = findViewById(R.id.heel_touch_crunch_star3);

        ImageView side_elbow_bridge_star1 = findViewById(R.id.side_elbow_bridge_star1);
        ImageView side_elbow_bridge_star2 = findViewById(R.id.side_elbow_bridge_star2);
        ImageView side_elbow_bridge_star3 = findViewById(R.id.side_elbow_bridge_star3);
        

        int dumbbell_twist_score = dumbbell_twist_readData();
        int heel_touch_crunch_score = heel_touch_crunch_readData();
        int side_elbow_bridge_score = side_elbow_bridge_readData();


        if(dumbbell_twist_score >= 1){
            dumbbell_twist_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_twist_score >= 2){
            dumbbell_twist_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(dumbbell_twist_score >= 3){
            dumbbell_twist_star3.setImageResource(R.drawable.cleared_star_key);
            if (third_level_check == false) {
                ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                third_level_button_locked.setId(R.id.third_level_button);
                third_level_check = true;
            }
        }

        if(heel_touch_crunch_score >= 1){
            heel_touch_crunch_star1.setImageResource(R.drawable.cleared_star);
        }
        if(heel_touch_crunch_score >= 2){
            heel_touch_crunch_star2.setImageResource(R.drawable.cleared_star);
        }
        if(heel_touch_crunch_score >= 3){
            heel_touch_crunch_star3.setImageResource(R.drawable.cleared_star);
        }


        if(side_elbow_bridge_score >= 1){
            side_elbow_bridge_star1.setImageResource(R.drawable.cleared_star);
        }
        if(side_elbow_bridge_score >= 2){
            side_elbow_bridge_star2.setImageResource(R.drawable.cleared_star);
        }
        if(side_elbow_bridge_score >= 3){
            side_elbow_bridge_star3.setImageResource(R.drawable.cleared_star);
        }



        if(third_level_check == true) {
            ImageButton third_level_button = findViewById(R.id.third_level_button);
            third_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),hukusya_third.class);
                    startActivity(intent);
                }
            });
        }




        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_dumbbell_twist_num = Integer.toString(today_dumbbell_twist_readData());
        TextView text = findViewById(R.id.dumbbell_twist_countNum);
        text.setText(today_dumbbell_twist_num);

        String today_heel_touch_crunch_num = Integer.toString(today_heel_touch_crunch_readData());
        TextView text2 = findViewById(R.id.heel_touch_crunch_countNum);
        text2.setText(today_heel_touch_crunch_num);

        String today_side_elbow_bridge_num = Integer.toString(today_side_elbow_bridge_readData());
        TextView text3 = findViewById(R.id.side_elbow_bridge_countNum);
        text3.setText(today_side_elbow_bridge_num);

    }

    private int dumbbell_twist_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'dumbbell_twist_check'", null);
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


    private int heel_touch_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'heel_touch_crunch_check'", null);
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

    private int side_elbow_bridge_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'side_elbow_bridge_check'", null);
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
    



    public void dumbbell_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"dumbbell_twist_check"});
    }

    public void heel_touch_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"heel_touch_crunch_check"});
    }

    public void side_elbow_bridge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_elbow_bridge_check"});
    }
    

    public void today_dumbbell_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_dumbbell_twist_title});
    }

    public void today_heel_touch_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_heel_touch_crunch_title});
    }

    public void today_side_elbow_bridge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_side_elbow_bridge_title});
    }
    
    
    private int today_dumbbell_twist_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_dumbbell_twist_title + "'", null);
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

    private int today_heel_touch_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_heel_touch_crunch_title + "'", null);
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

    private int today_side_elbow_bridge_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_side_elbow_bridge_title + "'", null);
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
