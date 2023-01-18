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

public class denbu_second extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    String today_side_lunge_title = null;
    String today_hip_extension_title = null;
    String today_high_reverse_plank_title = null;
    String today_diagonal_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.denbu_second);


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
                Intent intent = new Intent(getApplication(), denbu_first.class);
                //finish();
                startActivity(intent);
            }
        });


        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";

        //筋トレ方法名と日付を連結
        today_side_lunge_title = date + "_side_lunge";
        today_hip_extension_title = date + "_hip_extension";
        today_high_reverse_plank_title = date + "_high_reverse_plank";
        today_diagonal_title = date + "_diagonal";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_side_lunge_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_hip_extension_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_high_reverse_plank_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);

        ContentValues val4 = new ContentValues();
        val4.put("title", today_diagonal_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);



        View side_lunge_movie = findViewById(R.id.side_lunge_movie);
        side_lunge_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_lunge_check = side_lunge_readData();  //現在の動画再生数を確認

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

        View hip_extension_movie = findViewById(R.id.hip_extension_movie);
        hip_extension_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hip_extension_check = hip_extension_readData();
                Log.d("現在の再生数","は"+hip_extension_check);//現在の動画再生数を確認

                if(hip_extension_check ==0) {
                    hip_extension_check++;
                }else if(hip_extension_check == 1){
                    hip_extension_check++;
                }else if(hip_extension_check == 2){
                    hip_extension_check++;
                }

                hip_extension_upDate(db,hip_extension_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.hip_extension);
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

        View diagonal_movie = findViewById(R.id.diagonal_movie);
        diagonal_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int diagonal_check = diagonal_readData();
                Log.d("現在の再生数","は"+diagonal_check);//現在の動画再生数を確認

                if(diagonal_check ==0) {
                    diagonal_check++;
                }else if(diagonal_check == 1){
                    diagonal_check++;
                }else if(diagonal_check == 2){
                    diagonal_check++;
                }

                diagonal_upDate(db,diagonal_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.diagonal);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button side_lunge_CountButtonForMinus = findViewById(R.id.side_lunge_minus);
        side_lunge_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_lunge_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_lunge_upDate(db,num);
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

        Button hip_extension_CountButtonForMinus = findViewById(R.id.hip_extension_minus);
        hip_extension_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hip_extension_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_lunge_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button hip_extension_CountButtonForPlus = findViewById(R.id.hip_extension_plus);
        hip_extension_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.hip_extension_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_hip_extension_upDate(db,num);
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
                today_side_lunge_upDate(db,num);
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

        Button diagonal_CountButtonForMinus = findViewById(R.id.diagonal_minus);
        diagonal_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.diagonal_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_lunge_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button diagonal_CountButtonForPlus = findViewById(R.id.diagonal_plus);
        diagonal_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.diagonal_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_diagonal_upDate(db,num);
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
        TextView side_lunge_setNum = findViewById(R.id.side_lunge_setNum);
        TextView hip_extension_setNum = findViewById(R.id.hip_extension_setNum);
        TextView high_reverse_plank_setNum = findViewById(R.id.high_reverse_plank_setNum);
        TextView diagonal_setNum = findViewById(R.id.diagonal_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            side_lunge_setNum.setText("20回×3");
            hip_extension_setNum.setText("20回×3");
            high_reverse_plank_setNum.setText("20回×3");
            diagonal_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            side_lunge_setNum.setText("15回×3");
            hip_extension_setNum.setText("15回×3");
            high_reverse_plank_setNum.setText("15回×3");
            diagonal_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            side_lunge_setNum.setText("10回×3");
            hip_extension_setNum.setText("10回×3");
            high_reverse_plank_setNum.setText("10回×3");
            diagonal_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            side_lunge_setNum.setText("15回×3");
            hip_extension_setNum.setText("15回×3");
            high_reverse_plank_setNum.setText("15回×3");
            diagonal_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            side_lunge_setNum.setText("10回×3");
            hip_extension_setNum.setText("10回×3");
            high_reverse_plank_setNum.setText("10回×3");
            diagonal_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            side_lunge_setNum.setText("10回×2");
            hip_extension_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
            diagonal_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            side_lunge_setNum.setText("20回×2");
            hip_extension_setNum.setText("20回×2");
            high_reverse_plank_setNum.setText("20回×3");
            diagonal_setNum.setText("20回×3");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            side_lunge_setNum.setText("15回×2");
            hip_extension_setNum.setText("15回×2");
            high_reverse_plank_setNum.setText("15回×2");
            diagonal_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            side_lunge_setNum.setText("10回×2");
            hip_extension_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
            diagonal_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            side_lunge_setNum.setText("15回×2");
            hip_extension_setNum.setText("15回×2");
            high_reverse_plank_setNum.setText("15回×2");
            diagonal_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            side_lunge_setNum.setText("10回×2");
            hip_extension_setNum.setText("10回×2");
            high_reverse_plank_setNum.setText("10回×2");
            diagonal_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            side_lunge_setNum.setText("10回×1");
            hip_extension_setNum.setText("10回×1");
            high_reverse_plank_setNum.setText("10回×1");
            diagonal_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView side_lunge_star1 = findViewById(R.id.side_lunge_star1);
        ImageView side_lunge_star2 = findViewById(R.id.side_lunge_star2);
        ImageView side_lunge_star3 = findViewById(R.id.side_lunge_star3);

        ImageView hip_extension_star1 = findViewById(R.id.hip_extension_star1);
        ImageView hip_extension_star2 = findViewById(R.id.hip_extension_star2);
        ImageView hip_extension_star3 = findViewById(R.id.hip_extension_star3);

        ImageView high_reverse_plank_star1 = findViewById(R.id.high_reverse_plank_star1);
        ImageView high_reverse_plank_star2 = findViewById(R.id.high_reverse_plank_star2);
        ImageView high_reverse_plank_star3 = findViewById(R.id.high_reverse_plank_star3);

        ImageView diagonal_star1 = findViewById(R.id.diagonal_star1);
        ImageView diagonal_star2 = findViewById(R.id.diagonal_star2);
        ImageView diagonal_star3 = findViewById(R.id.diagonal_star3);



        int side_lunge_score = side_lunge_readData();
        int hip_extension_score = hip_extension_readData();
        int high_reverse_plank_score = high_reverse_plank_readData();
        int diagonal_score = diagonal_readData();


        if(side_lunge_score >= 1){
            side_lunge_star1.setImageResource(R.drawable.cleared_star);
        }
        if(side_lunge_score >= 2){
            side_lunge_star2.setImageResource(R.drawable.cleared_star);
        }
        if(side_lunge_score >= 3){
            side_lunge_star3.setImageResource(R.drawable.cleared_star);
        }

        if(hip_extension_score >= 1){
            hip_extension_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(hip_extension_score >= 2){
            hip_extension_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(hip_extension_score >= 3){
            hip_extension_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                    ImageButton third_level_button_locked = findViewById(R.id.third_level_button_locked);
                    third_level_button_locked.setBackgroundResource(R.drawable.third_level);
                    third_level_button_locked.setId(R.id.third_level_button);
                    second_level_check = true;
            }
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


        if(diagonal_score >= 1){
            diagonal_star1.setImageResource(R.drawable.cleared_star);
        }
        if(diagonal_score >= 2){
            diagonal_star2.setImageResource(R.drawable.cleared_star);
        }
        if(diagonal_score >= 3){
            diagonal_star3.setImageResource(R.drawable.cleared_star);
        }


        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.third_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),denbu_third.class);
                    startActivity(intent);
                }
            });
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_side_lunge_num = Integer.toString(today_side_lunge_readData());
        TextView text = findViewById(R.id.side_lunge_countNum);
        text.setText(today_side_lunge_num);

        String today_hip_extension_num = Integer.toString(today_hip_extension_readData());
        TextView text2 = findViewById(R.id.hip_extension_countNum);
        text2.setText(today_hip_extension_num);

        String today_high_reverse_plank_num = Integer.toString(today_high_reverse_plank_readData());
        TextView text3 = findViewById(R.id.high_reverse_plank_countNum);
        text3.setText(today_high_reverse_plank_num);

        String today_diagonal_num = Integer.toString(today_diagonal_readData());
        TextView text4 = findViewById(R.id.diagonal_countNum);
        text4.setText(today_diagonal_num);

    }

    private int side_lunge_readData() {
        if (helper == null) {
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


    private int hip_extension_readData() {
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

    private int diagonal_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'diagonal_check'", null);
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



    public void side_lunge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_lunge_check"});
    }

    public void hip_extension_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"hip_extension_check"});
    }

    public void high_reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"high_reverse_plank_check"});
    }

    public void diagonal_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"diagonal_check"});
    }


    public void today_side_lunge_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_side_lunge_title});
    }

    public void today_hip_extension_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_hip_extension_title});
    }

    public void today_high_reverse_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_high_reverse_plank_title});
    }

    public void today_diagonal_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_diagonal_title});
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

    private int today_hip_extension_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_hip_extension_title + "'", null);
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

    private int today_diagonal_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_diagonal_title + "'", null);
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
