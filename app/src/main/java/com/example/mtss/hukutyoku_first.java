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

public class hukutyoku_first extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    boolean third_level_check = false;
    String today_crunch_title = null;
    String today_leg_raise_title = null;
    String today_plank_title = null;
    String today_reverse_crunch_title = null;
    String today_knee_to_chest_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukutyoku_first);


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
        today_crunch_title = date + "_crunch";
        today_leg_raise_title = date + "_leg_raise";
        today_plank_title = date + "_plank";
        today_reverse_crunch_title = date + "_reverse_crunch";
        today_knee_to_chest_title = date + "_knee_to_chest";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_crunch_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_leg_raise_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_plank_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);

        ContentValues val4 = new ContentValues();
        val4.put("title", today_reverse_crunch_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);

        ContentValues val5 = new ContentValues();
        val5.put("title", today_knee_to_chest_title);
        val5.put("score", 0);
        db.insert("title_table", null, val5);



        View crunch_movie = findViewById(R.id.crunch_movie);
        crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int crunch_check = crunch_readData();  //現在の動画再生数を確認

                if(crunch_check ==0) {
                    crunch_check++;
                }else if(crunch_check == 1){
                    crunch_check++;
                }else if(crunch_check == 2){
                    crunch_check++;
                }

                crunch_upDate(db,crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View leg_raise_movie = findViewById(R.id.leg_raise_movie);
        leg_raise_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int leg_raise_check = leg_raise_readData();
                Log.d("現在の再生数","は"+leg_raise_check);//現在の動画再生数を確認

                if(leg_raise_check ==0) {
                    leg_raise_check++;
                }else if(leg_raise_check == 1){
                    leg_raise_check++;
                }else if(leg_raise_check == 2){
                    leg_raise_check++;
                }

                leg_raise_upDate(db,leg_raise_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.leg_raise);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View plank_movie = findViewById(R.id.plank_movie);
        plank_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int plank_check = plank_readData();
                Log.d("現在の再生数","は"+plank_check);//現在の動画再生数を確認

                if(plank_check ==0) {
                    plank_check++;
                }else if(plank_check == 1){
                    plank_check++;
                }else if(plank_check == 2){
                    plank_check++;
                }

                plank_upDate(db,plank_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.prank);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View reverse_crunch_movie = findViewById(R.id.reverse_crunch_movie);
        reverse_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int reverse_crunch_check = reverse_crunch_readData();
                Log.d("現在の再生数","は"+reverse_crunch_check);//現在の動画再生数を確認

                if(reverse_crunch_check ==0) {
                    reverse_crunch_check++;
                }else if(reverse_crunch_check == 1){
                    reverse_crunch_check++;
                }else if(reverse_crunch_check == 2){
                    reverse_crunch_check++;
                }

                reverse_crunch_upDate(db,reverse_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View knee_to_chest_movie = findViewById(R.id.knee_to_chest_movie);
        knee_to_chest_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int knee_to_chest_check = knee_to_chest_readData();
                Log.d("現在の再生数","は"+knee_to_chest_check);//現在の動画再生数を確認

                if(knee_to_chest_check ==0) {
                    knee_to_chest_check++;
                }else if(knee_to_chest_check == 1){
                    knee_to_chest_check++;
                }else if(knee_to_chest_check == 2){
                    knee_to_chest_check++;
                }

                knee_to_chest_upDate(db,knee_to_chest_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.knee_to_chest);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button crunch_CountButtonForMinus = findViewById(R.id.crunch_minus);
        crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button crunch_CountButtonForPlus = findViewById(R.id.crunch_plus);
        crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button leg_raise_CountButtonForMinus = findViewById(R.id.leg_raise_minus);
        leg_raise_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_raise_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button leg_raise_CountButtonForPlus = findViewById(R.id.leg_raise_plus);
        leg_raise_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.leg_raise_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_leg_raise_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button plank_CountButtonForMinus = findViewById(R.id.plank_minus);
        plank_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button plank_CountButtonForPlus = findViewById(R.id.plank_plus);
        plank_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_plank_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button reverse_crunch_CountButtonForMinus = findViewById(R.id.reverse_crunch_minus);
        reverse_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button reverse_crunch_CountButtonForPlus = findViewById(R.id.reverse_crunch_plus);
        reverse_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.reverse_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_reverse_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button knee_to_chest_CountButtonForMinus = findViewById(R.id.knee_to_chest_minus);
        knee_to_chest_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.knee_to_chest_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button knee_to_chest_CountButtonForPlus = findViewById(R.id.knee_to_chest_plus);
        knee_to_chest_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.knee_to_chest_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_knee_to_chest_upDate(db,num);
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
        TextView crunch_setNum = findViewById(R.id.crunch_setNum);
        TextView leg_raise_setNum = findViewById(R.id.leg_raise_setNum);
        TextView plank_setNum = findViewById(R.id.plank_setNum);
        TextView reverse_crunch_setNum = findViewById(R.id.reverse_crunch_setNum);
        TextView knee_to_chest_setNum = findViewById(R.id.knee_to_chest_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            crunch_setNum.setText("20回×3");
            leg_raise_setNum.setText("20回×3");
            plank_setNum.setText("20回×3");
            reverse_crunch_setNum.setText("20回×3");
            knee_to_chest_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            crunch_setNum.setText("15回×3");
            leg_raise_setNum.setText("15回×3");
            plank_setNum.setText("15回×3");
            reverse_crunch_setNum.setText("15回×3");
            knee_to_chest_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            crunch_setNum.setText("10回×3");
            leg_raise_setNum.setText("10回×3");
            plank_setNum.setText("10回×3");
            reverse_crunch_setNum.setText("10回×3");
            knee_to_chest_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            crunch_setNum.setText("15回×3");
            leg_raise_setNum.setText("15回×3");
            plank_setNum.setText("15回×3");
            reverse_crunch_setNum.setText("15回×3");
            knee_to_chest_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            crunch_setNum.setText("10回×3");
            leg_raise_setNum.setText("10回×3");
            plank_setNum.setText("10回×3");
            reverse_crunch_setNum.setText("10回×3");
            knee_to_chest_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            crunch_setNum.setText("10回×2");
            leg_raise_setNum.setText("10回×2");
            plank_setNum.setText("10回×2");
            reverse_crunch_setNum.setText("10回×2");
            knee_to_chest_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            crunch_setNum.setText("20回×2");
            leg_raise_setNum.setText("20回×2");
            plank_setNum.setText("20回×3");
            reverse_crunch_setNum.setText("20回×3");
            knee_to_chest_setNum.setText("20回×3");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            crunch_setNum.setText("15回×2");
            leg_raise_setNum.setText("15回×2");
            plank_setNum.setText("15回×2");
            reverse_crunch_setNum.setText("15回×2");
            knee_to_chest_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            crunch_setNum.setText("10回×2");
            leg_raise_setNum.setText("10回×2");
            plank_setNum.setText("10回×2");
            reverse_crunch_setNum.setText("10回×2");
            knee_to_chest_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            crunch_setNum.setText("15回×2");
            leg_raise_setNum.setText("15回×2");
            plank_setNum.setText("15回×2");
            reverse_crunch_setNum.setText("15回×2");
            knee_to_chest_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            crunch_setNum.setText("10回×2");
            leg_raise_setNum.setText("10回×2");
            plank_setNum.setText("10回×2");
            reverse_crunch_setNum.setText("10回×2");
            knee_to_chest_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            crunch_setNum.setText("10回×1");
            leg_raise_setNum.setText("10回×1");
            plank_setNum.setText("10回×1");
            reverse_crunch_setNum.setText("10回×1");
            knee_to_chest_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView crunch_star1 = findViewById(R.id.crunch_star1);
        ImageView crunch_star2 = findViewById(R.id.crunch_star2);
        ImageView crunch_star3 = findViewById(R.id.crunch_star3);

        ImageView leg_raise_star1 = findViewById(R.id.leg_raise_star1);
        ImageView leg_raise_star2 = findViewById(R.id.leg_raise_star2);
        ImageView leg_raise_star3 = findViewById(R.id.leg_raise_star3);

        ImageView plank_star1 = findViewById(R.id.plank_star1);
        ImageView plank_star2 = findViewById(R.id.plank_star2);
        ImageView plank_star3 = findViewById(R.id.plank_star3);

        ImageView reverse_crunch_star1 = findViewById(R.id.reverse_crunch_star1);
        ImageView reverse_crunch_star2 = findViewById(R.id.reverse_crunch_star2);
        ImageView reverse_crunch_star3 = findViewById(R.id.reverse_crunch_star3);

        ImageView knee_to_chest_star1 = findViewById(R.id.knee_to_chest_star1);
        ImageView knee_to_chest_star2 = findViewById(R.id.knee_to_chest_star2);
        ImageView knee_to_chest_star3 = findViewById(R.id.leg_raise_star3);



        int crunch_score = crunch_readData();
        int leg_raise_score = leg_raise_readData();
        int plank_score = plank_readData();
        int reverse_crunch_score = reverse_crunch_readData();
        int knee_to_chest_score = knee_to_chest_readData();


        if(crunch_score >= 1){
            crunch_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(crunch_score >= 2){
            crunch_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(crunch_score >= 3){
            crunch_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(leg_raise_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }

        if(leg_raise_score >= 1){
            leg_raise_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(leg_raise_score >= 2){
            leg_raise_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(leg_raise_score >= 3){
            leg_raise_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(crunch_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }

        if(plank_score >= 1){
            plank_star1.setImageResource(R.drawable.cleared_star);
        }
        if(plank_score >= 2){
            plank_star2.setImageResource(R.drawable.cleared_star);
        }
        if(plank_score >= 3){
            plank_star3.setImageResource(R.drawable.cleared_star);
        }


        if(reverse_crunch_score >= 1){
            reverse_crunch_star1.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_crunch_score >= 2){
            reverse_crunch_star2.setImageResource(R.drawable.cleared_star);
        }
        if(reverse_crunch_score >= 3){
            reverse_crunch_star3.setImageResource(R.drawable.cleared_star);
        }


        if(knee_to_chest_score >= 1){
            knee_to_chest_star1.setImageResource(R.drawable.cleared_star);
        }
        if(knee_to_chest_score >= 2){
            knee_to_chest_star2.setImageResource(R.drawable.cleared_star);
        }
        if(knee_to_chest_score >= 3){
            knee_to_chest_star3.setImageResource(R.drawable.cleared_star);
        }

        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.second_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),hukutyoku_second.class);
                    startActivity(intent);
                }
            });
        }

        //上級も解除されているかチェック

        if(third_level_check == false) {
            if(third_level_check_readData1() >= 3 && third_level_check_readData2() >= 3){
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
                    Intent intent = new Intent(getApplication(), hukutyoku_third.class);
                    startActivity(intent);
                }
            });
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_crunch_num = Integer.toString(today_crunch_readData());
        TextView text = findViewById(R.id.crunch_countNum);
        text.setText(today_crunch_num);

        String today_leg_raise_num = Integer.toString(today_leg_raise_readData());
        TextView text2 = findViewById(R.id.leg_raise_countNum);
        text2.setText(today_leg_raise_num);

        String today_plank_num = Integer.toString(today_plank_readData());
        TextView text3 = findViewById(R.id.plank_countNum);
        text3.setText(today_plank_num);

        String today_reverse_crunch_num = Integer.toString(today_reverse_crunch_readData());
        TextView text4 = findViewById(R.id.reverse_crunch_countNum);
        text4.setText(today_reverse_crunch_num);

        String today_knee_to_chest_num = Integer.toString(today_knee_to_chest_readData());
        TextView text5 = findViewById(R.id.knee_to_chest_countNum);
        text5.setText(today_knee_to_chest_num);

    }

    private int crunch_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'crunch_check'", null);
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


    private int leg_raise_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'leg_raise_check'", null);
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

    private int plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'plank_check'", null);
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

    private int reverse_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'reverse_crunch_check'", null);
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

    private int knee_to_chest_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'knee_to_chest_check'", null);
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


    public void crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"crunch_check"});
    }

    public void leg_raise_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"leg_raise_check"});
    }

    public void plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"plank_check"});
    }

    public void reverse_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"reverse_crunch_check"});
    }

    public void knee_to_chest_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"knee_to_chest_check"});
    }


    public void today_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_crunch_title});
    }

    public void today_leg_raise_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_leg_raise_title});
    }

    public void today_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_plank_title});
    }

    public void today_reverse_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_reverse_crunch_title});
    }

    public void today_knee_to_chest_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_knee_to_chest_title});
    }

    private int today_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_crunch_title + "'", null);
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

    private int today_leg_raise_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_leg_raise_title + "'", null);
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

    private int today_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_plank_title + "'", null);
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

    private int today_reverse_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_reverse_crunch_title + "'", null);
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

    private int today_knee_to_chest_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_knee_to_chest_title + "'", null);
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

    private int third_level_check_readData1() {
        if(helper == null){
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

    private int third_level_check_readData2() {
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
}
