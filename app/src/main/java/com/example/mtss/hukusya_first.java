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

public class hukusya_first extends AppCompatActivity {
    ScrollView scrollView;
    TestOpenHelperForTitle helper;
    SQLiteDatabase db;
    boolean second_level_check = false;
    boolean third_level_check = false;
    String today_side_crunch_title = null;
    String today_twist_crunch_title = null;
    String today_side_plank_title = null;
    String today_russian_twist_title = null;
    String today_side_bend_title = null;
    int[] setting_data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        db = helper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hukusya_first);


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
        today_side_crunch_title = date + "_side_crunch";
        today_twist_crunch_title = date + "_twist_crunch";
        today_side_plank_title = date + "_side_plank";
        today_russian_twist_title = date + "_russian_twist";
        today_side_bend_title = date + "_side_bend";


        //日付を含む筋トレ方法についてのデータをデータベースに登録(重複無し)
        ContentValues val = new ContentValues();
        val.put("title", today_side_crunch_title);
        val.put("score", 0);
        db.insert("title_table", null, val);

        ContentValues val2 = new ContentValues();
        val2.put("title", today_twist_crunch_title);
        val2.put("score", 0);
        db.insert("title_table", null, val2);

        ContentValues val3 = new ContentValues();
        val3.put("title", today_side_plank_title);
        val3.put("score", 0);
        db.insert("title_table", null, val3);

        ContentValues val4 = new ContentValues();
        val4.put("title", today_russian_twist_title);
        val4.put("score", 0);
        db.insert("title_table", null, val4);

        ContentValues val5 = new ContentValues();
        val5.put("title", today_side_bend_title);
        val5.put("score", 0);
        db.insert("title_table", null, val5);



        View side_crunch_movie = findViewById(R.id.side_crunch_movie);
        side_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_crunch_check = side_crunch_readData();  //現在の動画再生数を確認

                if(side_crunch_check ==0) {
                    side_crunch_check++;
                }else if(side_crunch_check == 1){
                    side_crunch_check++;
                }else if(side_crunch_check == 2){
                    side_crunch_check++;
                }

                side_crunch_upDate(db,side_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View twist_crunch_movie = findViewById(R.id.twist_crunch_movie);
        twist_crunch_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int twist_crunch_check = twist_crunch_readData();
                Log.d("現在の再生数","は"+twist_crunch_check);//現在の動画再生数を確認

                if(twist_crunch_check ==0) {
                    twist_crunch_check++;
                }else if(twist_crunch_check == 1){
                    twist_crunch_check++;
                }else if(twist_crunch_check == 2){
                    twist_crunch_check++;
                }

                twist_crunch_upDate(db,twist_crunch_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.twist_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View side_plank_movie = findViewById(R.id.side_plank_movie);
        side_plank_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_plank_check = side_plank_readData();
                Log.d("現在の再生数","は"+side_plank_check);//現在の動画再生数を確認

                if(side_plank_check ==0) {
                    side_plank_check++;
                }else if(side_plank_check == 1){
                    side_plank_check++;
                }else if(side_plank_check == 2){
                    side_plank_check++;
                }

                side_plank_upDate(db,side_plank_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_prank);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View russian_twist_movie = findViewById(R.id.russian_twist_movie);
        russian_twist_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int russian_twist_check = russian_twist_readData();
                Log.d("現在の再生数","は"+russian_twist_check);//現在の動画再生数を確認

                if(russian_twist_check ==0) {
                    russian_twist_check++;
                }else if(russian_twist_check == 1){
                    russian_twist_check++;
                }else if(russian_twist_check == 2){
                    russian_twist_check++;
                }

                russian_twist_upDate(db,russian_twist_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.rosian_twist);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        View side_bend_movie = findViewById(R.id.side_bend_movie);
        side_bend_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int side_bend_check = side_bend_readData();
                Log.d("現在の再生数","は"+side_bend_check);//現在の動画再生数を確認

                if(side_bend_check ==0) {
                    side_bend_check++;
                }else if(side_bend_check == 1){
                    side_bend_check++;
                }else if(side_bend_check == 2){
                    side_bend_check++;
                }

                side_bend_upDate(db,side_bend_check); //再生数を更新(最大で3まで)

                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_bent);
                // 画面移行スタート
                startActivity(intent);
            }
        });



        //セット数増減と表示

        Button side_crunch_CountButtonForMinus = findViewById(R.id.side_crunch_minus);
        side_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button side_crunch_CountButtonForPlus = findViewById(R.id.side_crunch_plus);
        side_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button twist_crunch_CountButtonForMinus = findViewById(R.id.twist_crunch_minus);
        twist_crunch_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.twist_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button twist_crunch_CountButtonForPlus = findViewById(R.id.twist_crunch_plus);
        twist_crunch_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.twist_crunch_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_twist_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button side_plank_CountButtonForMinus = findViewById(R.id.side_plank_minus);
        side_plank_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button side_plank_CountButtonForPlus = findViewById(R.id.side_plank_plus);
        side_plank_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_plank_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_side_plank_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button russian_twist_CountButtonForMinus = findViewById(R.id.russian_twist_minus);
        russian_twist_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.russian_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button russian_twist_CountButtonForPlus = findViewById(R.id.russian_twist_plus);
        russian_twist_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.russian_twist_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_russian_twist_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                calendarNum++;
                calendar_upDate(db,calendarNum);
            }

        });

        Button side_bend_CountButtonForMinus = findViewById(R.id.side_bend_minus);
        side_bend_CountButtonForMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_bend_countNum);
                int num = Integer.parseInt(text.getText().toString());
                if(num > 0) {
                    num--;
                }
                today_side_crunch_upDate(db,num);
                text.setText(String.valueOf(num));
                //カレンダー用のデータを更新
                int calendarNum = calendar_readData();
                if(calendarNum > 0) {
                    calendarNum--;
                }
                calendar_upDate(db,calendarNum);
            }



        });

        Button side_bend_CountButtonForPlus = findViewById(R.id.side_bend_plus);
        side_bend_CountButtonForPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.side_bend_countNum);
                int num = Integer.parseInt(text.getText().toString());
                num++;
                today_side_bend_upDate(db,num);
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
        TextView side_crunch_setNum = findViewById(R.id.side_crunch_setNum);
        TextView twist_crunch_setNum = findViewById(R.id.twist_crunch_setNum);
        TextView side_plank_setNum = findViewById(R.id.side_plank_setNum);
        TextView russian_twist_setNum = findViewById(R.id.russian_twist_setNum);
        TextView side_bend_setNum = findViewById(R.id.side_bend_setNum);

        //性別男パターン
        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            side_crunch_setNum.setText("20回×3");
            twist_crunch_setNum.setText("20回×3");
            side_plank_setNum.setText("20回×3");
            russian_twist_setNum.setText("20回×3");
            side_bend_setNum.setText("20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            side_crunch_setNum.setText("15回×3");
            twist_crunch_setNum.setText("15回×3");
            side_plank_setNum.setText("15回×3");
            russian_twist_setNum.setText("15回×3");
            side_bend_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            side_crunch_setNum.setText("10回×3");
            twist_crunch_setNum.setText("10回×3");
            side_plank_setNum.setText("10回×3");
            russian_twist_setNum.setText("10回×3");
            side_bend_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            side_crunch_setNum.setText("15回×3");
            twist_crunch_setNum.setText("15回×3");
            side_plank_setNum.setText("15回×3");
            russian_twist_setNum.setText("15回×3");
            side_bend_setNum.setText("15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            side_crunch_setNum.setText("10回×3");
            twist_crunch_setNum.setText("10回×3");
            side_plank_setNum.setText("10回×3");
            russian_twist_setNum.setText("10回×3");
            side_bend_setNum.setText("10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            side_crunch_setNum.setText("10回×2");
            twist_crunch_setNum.setText("10回×2");
            side_plank_setNum.setText("10回×2");
            russian_twist_setNum.setText("10回×2");
            side_bend_setNum.setText("10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            side_crunch_setNum.setText("20回×2");
            twist_crunch_setNum.setText("20回×2");
            side_plank_setNum.setText("20回×3");
            russian_twist_setNum.setText("20回×3");
            side_bend_setNum.setText("20回×3");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            side_crunch_setNum.setText("15回×2");
            twist_crunch_setNum.setText("15回×2");
            side_plank_setNum.setText("15回×2");
            russian_twist_setNum.setText("15回×2");
            side_bend_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            side_crunch_setNum.setText("10回×2");
            twist_crunch_setNum.setText("10回×2");
            side_plank_setNum.setText("10回×2");
            russian_twist_setNum.setText("10回×2");
            side_bend_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            side_crunch_setNum.setText("15回×2");
            twist_crunch_setNum.setText("15回×2");
            side_plank_setNum.setText("15回×2");
            russian_twist_setNum.setText("15回×2");
            side_bend_setNum.setText("15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            side_crunch_setNum.setText("10回×2");
            twist_crunch_setNum.setText("10回×2");
            side_plank_setNum.setText("10回×2");
            russian_twist_setNum.setText("10回×2");
            side_bend_setNum.setText("10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            side_crunch_setNum.setText("10回×1");
            twist_crunch_setNum.setText("10回×1");
            side_plank_setNum.setText("10回×1");
            russian_twist_setNum.setText("10回×1");
            side_bend_setNum.setText("10回×1");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageView side_crunch_star1 = findViewById(R.id.side_crunch_star1);
        ImageView side_crunch_star2 = findViewById(R.id.side_crunch_star2);
        ImageView side_crunch_star3 = findViewById(R.id.side_crunch_star3);

        ImageView twist_crunch_star1 = findViewById(R.id.twist_crunch_star1);
        ImageView twist_crunch_star2 = findViewById(R.id.twist_crunch_star2);
        ImageView twist_crunch_star3 = findViewById(R.id.twist_crunch_star3);

        ImageView side_plank_star1 = findViewById(R.id.side_plank_star1);
        ImageView side_plank_star2 = findViewById(R.id.side_plank_star2);
        ImageView side_plank_star3 = findViewById(R.id.side_plank_star3);

        ImageView russian_twist_star1 = findViewById(R.id.russian_twist_star1);
        ImageView russian_twist_star2 = findViewById(R.id.russian_twist_star2);
        ImageView russian_twist_star3 = findViewById(R.id.russian_twist_star3);

        ImageView side_bend_star1 = findViewById(R.id.side_bend_star1);
        ImageView side_bend_star2 = findViewById(R.id.side_bend_star2);
        ImageView side_bend_star3 = findViewById(R.id.twist_crunch_star3);



        int side_crunch_score = side_crunch_readData();
        int twist_crunch_score = twist_crunch_readData();
        int side_plank_score = side_plank_readData();
        int russian_twist_score = russian_twist_readData();
        int side_bend_score = side_bend_readData();


        if(side_crunch_score >= 1){
            side_crunch_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(side_crunch_score >= 2){
            side_crunch_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(side_crunch_score >= 3){
            side_crunch_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(twist_crunch_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }

        if(twist_crunch_score >= 1){
            twist_crunch_star1.setImageResource(R.drawable.cleared_star_key);
        }
        if(twist_crunch_score >= 2){
            twist_crunch_star2.setImageResource(R.drawable.cleared_star_key);
        }
        if(twist_crunch_score >= 3){
            twist_crunch_star3.setImageResource(R.drawable.cleared_star_key);
            if (second_level_check == false) {
                if(side_crunch_score >= 3) {
                    ImageButton second_level_button_locked = findViewById(R.id.second_level_button_locked);
                    second_level_button_locked.setBackgroundResource(R.drawable.second_level);
                    second_level_button_locked.setId(R.id.second_level_button);
                    second_level_check = true;
                }
            }
        }

        if(side_plank_score >= 1){
            side_plank_star1.setImageResource(R.drawable.cleared_star);
        }
        if(side_plank_score >= 2){
            side_plank_star2.setImageResource(R.drawable.cleared_star);
        }
        if(side_plank_score >= 3){
            side_plank_star3.setImageResource(R.drawable.cleared_star);
        }


        if(russian_twist_score >= 1){
            russian_twist_star1.setImageResource(R.drawable.cleared_star);
        }
        if(russian_twist_score >= 2){
            russian_twist_star2.setImageResource(R.drawable.cleared_star);
        }
        if(russian_twist_score >= 3){
            russian_twist_star3.setImageResource(R.drawable.cleared_star);
        }


        if(side_bend_score >= 1){
            side_bend_star1.setImageResource(R.drawable.cleared_star);
        }
        if(side_bend_score >= 2){
            side_bend_star2.setImageResource(R.drawable.cleared_star);
        }
        if(side_bend_score >= 3){
            side_bend_star3.setImageResource(R.drawable.cleared_star);
        }

        if(second_level_check == true) {
            ImageButton second_level_button = findViewById(R.id.second_level_button);
            second_level_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),hukusya_second.class);
                    startActivity(intent);
                }
            });
        }

        //上級も解除されているかチェック

        if(third_level_check == false) {
            if(third_level_check_readData1() >= 3){
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
                    Intent intent = new Intent(getApplication(), hukusya_third.class);
                    startActivity(intent);
                }
            });
        }


        //日付を含む筋トレ方法のデータを読み込んで回数を表示
        String today_side_crunch_num = Integer.toString(today_side_crunch_readData());
        TextView text = findViewById(R.id.side_crunch_countNum);
        text.setText(today_side_crunch_num);

        String today_twist_crunch_num = Integer.toString(today_twist_crunch_readData());
        TextView text2 = findViewById(R.id.twist_crunch_countNum);
        text2.setText(today_twist_crunch_num);

        String today_side_plank_num = Integer.toString(today_side_plank_readData());
        TextView text3 = findViewById(R.id.side_plank_countNum);
        text3.setText(today_side_plank_num);

        String today_russian_twist_num = Integer.toString(today_russian_twist_readData());
        TextView text4 = findViewById(R.id.russian_twist_countNum);
        text4.setText(today_russian_twist_num);

        String today_side_bend_num = Integer.toString(today_side_bend_readData());
        TextView text5 = findViewById(R.id.side_bend_countNum);
        text5.setText(today_side_bend_num);

    }

    private int side_crunch_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'side_crunch_check'", null);
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


    private int twist_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'twist_crunch_check'", null);
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

    private int side_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'side_plank_check'", null);
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

    private int russian_twist_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'russian_twist_check'", null);
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

    private int side_bend_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'side_bend_check'", null);
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


    public void side_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_crunch_check"});
    }

    public void twist_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"twist_crunch_check"});
    }

    public void side_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_plank_check"});
    }

    public void russian_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"russian_twist_check"});
    }

    public void side_bend_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"side_bend_check"});
    }


    public void today_side_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.update("title_table",values,"title=?",new String[]{today_side_crunch_title});
    }

    public void today_twist_crunch_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_twist_crunch_title});
    }

    public void today_side_plank_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_side_plank_title});
    }

    public void today_russian_twist_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_russian_twist_title});
    }

    public void today_side_bend_upDate(SQLiteDatabase db,int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{today_side_bend_title});
    }

    private int today_side_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_side_crunch_title + "'", null);
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

    private int today_twist_crunch_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_twist_crunch_title + "'", null);
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

    private int today_side_plank_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_side_plank_title + "'", null);
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

    private int today_russian_twist_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_russian_twist_title + "'", null);
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

    private int today_side_bend_readData() {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = '" + today_side_bend_title + "'", null);
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

}
