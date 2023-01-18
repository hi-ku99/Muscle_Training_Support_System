package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TestOpenHelperForTitle helper;
    int[] setting_data = new int[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setting_data = setting_readData();
        TextView dumbbell_curl_setNum = findViewById(R.id.dumbbell_curl_setNum);
        TextView concentration_curl_setNum = findViewById(R.id.concentration_curl_setNum);
        TextView normal_pushup_setNum = findViewById(R.id.normal_pushup_setNum);
        TextView french_press_setNum = findViewById(R.id.french_press_setNum);
        TextView reverse_pushup_setNum = findViewById(R.id.reverse_pushup_setNum);
        TextView gu_pa_setNum = findViewById(R.id.gu_pa_setNum);
        TextView radial_flexion_setNum = findViewById(R.id.radial_flexion_setNum);
        TextView wrist_curl_setNum = findViewById(R.id.wrist_curl_setNum);
        TextView dumbbell_adduction_setNum = findViewById(R.id.dumbbell_adduction_setNum);
        TextView normal_pushup_setNum1 = findViewById(R.id.normal_pushup_setNum1);
        TextView reverse_pushup_setNum1 = findViewById(R.id.reverse_pushup_setNum1);
        TextView side_raise_setNum = findViewById(R.id.side_raise_setNum);
        TextView front_raise_setNum = findViewById(R.id.front_raise_setNum);
        TextView upright_row_setNum = findViewById(R.id.upright_row_setNum);
        TextView hip_lift_setNum = findViewById(R.id.hip_lift_setNum);
        TextView high_reverse_plank_setNum = findViewById(R.id.high_reverse_plank_setNum);
        TextView leg_raise_setNum = findViewById(R.id.leg_raise_setNum);
        TextView dumbbell_crunch_setNum = findViewById(R.id.dumbbell_crunch_setNum);
        TextView side_crunch_setNum = findViewById(R.id.side_crunch_setNum);
        TextView dumbbell_twist_setNum = findViewById(R.id.dumbbell_twist_setNum);
        TextView dumbbell_overflow_setNum = findViewById(R.id.dumbbell_overflow_setNum);
        TextView back_extension_setNum = findViewById(R.id.back_extension_setNum);

        Button dumbbell_curl_button = findViewById(R.id.dumbbell_curl);
        Button concentration_curl_button = findViewById(R.id.concentration_curl);
        Button nomal_pushup_button = findViewById(R.id.nomal_pushup);
        Button french_press_button = findViewById(R.id.french_press);
        Button reverse_pushup_button = findViewById(R.id.reverse_pushup);
        Button gu_pa_button = findViewById(R.id.gu_pa);
        Button radial_flexion_button = findViewById(R.id.radial_flexion);
        Button wrist_curl_button = findViewById(R.id.wrist_curl);
        Button dumbell_adduction_button = findViewById(R.id.dumbbell_adduction);
        Button nomal_pushup1_button = findViewById(R.id.nomal_pushup1);
        Button reverse_pushup1_button = findViewById(R.id.reverse_pushup1);
        Button side_raise_button = findViewById(R.id.side_raise);
        Button front_raise_button = findViewById(R.id.front_raise);
        Button upright_row_button = findViewById(R.id.upright_row);
        Button hip_lift_button = findViewById(R.id.hip_lift);
        Button high_reverse_plank_button = findViewById(R.id.high_reverse_plank);
        Button leg_raise_button = findViewById(R.id.leg_raise);
        Button dumbbell_crunch_button = findViewById(R.id.dumbbell_crunch);
        Button side_crunch_button = findViewById(R.id.side_crunch);
        Button dumbbell_twist_button = findViewById(R.id.dumbbell_twist);
        Button dumbbell_overflow_button = findViewById(R.id.dumbbell_overflow);
        Button back_extension_button = findViewById(R.id.back_extension);



        dumbbell_curl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.dumbbell_curl);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        concentration_curl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.concentration_curl);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        french_press_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.french_press);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        nomal_pushup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.normal_pushup);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        reverse_pushup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_pushup);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        radial_flexion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.radial_flextion);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        wrist_curl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.wrist_curl);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        gu_pa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.gu_pa);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        dumbell_adduction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.dumbbell_adduction);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        nomal_pushup1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.normal_pushup);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        reverse_pushup1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.reverse_pushup);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        upright_row_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.upright_row);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        front_raise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.front_raise);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        side_raise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_raise);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        high_reverse_plank_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.high_reverse_plank);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        hip_lift_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.hip_lift);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        leg_raise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.leg_raise);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        //ダンベルクランチの動画入れ直す
        dumbbell_crunch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbel_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        side_crunch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.side_kranch);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        dumbbell_twist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbel_twist);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        dumbbell_overflow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.danbelbent_over_flow);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        back_extension_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),movie_play_scene.class);
                // 移行後に再生する動画を指定
                intent.putExtra("mp4", R.raw.back_extetian);
                // 画面移行スタート
                startActivity(intent);
            }
        });

        if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：男、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　20回×3");
            concentration_curl_setNum.setText("　　　20回×3");
            normal_pushup_setNum.setText("　　　20回×3");
            french_press_setNum.setText("　　　20回×3");
            reverse_pushup_setNum.setText("　　　20回×3");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　20回×3");
            wrist_curl_setNum.setText("　　　20回×3");
            normal_pushup_setNum1.setText("　　　20回×3");
            dumbbell_adduction_setNum.setText("　　　20回×3");
            reverse_pushup_setNum1.setText("　　　20回×3");
            side_raise_setNum.setText("　　　20回×3");
            front_raise_setNum.setText("　　　20回×3");
            upright_row_setNum.setText("　　　20回×3");
            hip_lift_setNum.setText("　　　20回×3");
            high_reverse_plank_setNum.setText("　　　20回×3");
            leg_raise_setNum.setText("　　　20回×3");
            dumbbell_crunch_setNum.setText("　　　20回×3");
            side_crunch_setNum.setText("　　　20回×3");
            dumbbell_twist_setNum.setText("　　　20回×3");
            dumbbell_overflow_setNum.setText("　　　20回×3");
            back_extension_setNum.setText("　　　20回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：男、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　15回×3");
            concentration_curl_setNum.setText("　　　15回×3");
            normal_pushup_setNum.setText("15×3");
            french_press_setNum.setText("　　　15回×3");
            reverse_pushup_setNum.setText("　　　15回×3");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　15回×3");
            wrist_curl_setNum.setText("　　　15回×3");
            normal_pushup_setNum1.setText("　　　15回×3");
            dumbbell_adduction_setNum.setText("　　　15回×3");
            reverse_pushup_setNum1.setText("　　　15回×3");
            side_raise_setNum.setText("　　　15回×3");
            front_raise_setNum.setText("　　　15回×3");
            upright_row_setNum.setText("　　　15回×3");
            hip_lift_setNum.setText("　　　15回×3");
            high_reverse_plank_setNum.setText("　　　15回×3");
            leg_raise_setNum.setText("　　　15回×3");
            dumbbell_crunch_setNum.setText("　　　15回×3");
            side_crunch_setNum.setText("　　　15回×3");
            dumbbell_twist_setNum.setText("　　　15回×3");
            dumbbell_overflow_setNum.setText("　　　15回×3");
            back_extension_setNum.setText("　　　15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：男、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　10回×3");
            concentration_curl_setNum.setText("　　　10回×3");
            normal_pushup_setNum.setText("　　　10回×3");
            french_press_setNum.setText("　　　10回×3");
            reverse_pushup_setNum.setText("　　　10回×3");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　10回×3");
            wrist_curl_setNum.setText("　　　10回×3");
            normal_pushup_setNum1.setText("　　　10回×3");
            dumbbell_adduction_setNum.setText("　　　10回×3");
            reverse_pushup_setNum1.setText("　　　10回×3");
            side_raise_setNum.setText("　　　10回×3");
            front_raise_setNum.setText("　　　10回×3");
            upright_row_setNum.setText("　　　10回×3");
            hip_lift_setNum.setText("　　　10回×3");
            high_reverse_plank_setNum.setText("　　　10回×3");
            leg_raise_setNum.setText("　　　10回×3");
            dumbbell_crunch_setNum.setText("　　　10回×3");
            side_crunch_setNum.setText("　　　10回×3");
            dumbbell_twist_setNum.setText("　　　10回×3");
            dumbbell_overflow_setNum.setText("　　　10回×3");
            back_extension_setNum.setText("　　　10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：男、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　15回×3");
            concentration_curl_setNum.setText("　　　15回×3");
            normal_pushup_setNum.setText("　　　15回×3");
            french_press_setNum.setText("　　　15回×3");
            reverse_pushup_setNum.setText("　　　15回×3");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　15回×3");
            wrist_curl_setNum.setText("　　　15回×3");
            normal_pushup_setNum1.setText("　　　15回×3");
            dumbbell_adduction_setNum.setText("　　　15回×3");
            reverse_pushup_setNum1.setText("　　　15回×3");
            side_raise_setNum.setText("　　　15回×3");
            front_raise_setNum.setText("　　　15回×3");
            upright_row_setNum.setText("　　　15回×3");
            hip_lift_setNum.setText("　　　15回×3");
            high_reverse_plank_setNum.setText("　　　15回×3");
            leg_raise_setNum.setText("　　　15回×3");
            dumbbell_crunch_setNum.setText("　　　15回×3");
            side_crunch_setNum.setText("　　　15回×3");
            dumbbell_twist_setNum.setText("　　　15回×3");
            dumbbell_overflow_setNum.setText("　　　15回×3");
            back_extension_setNum.setText("　　　15回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：男、ダンベル：5k～9g、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　10回×3");
            concentration_curl_setNum.setText("　　　10回×3");
            normal_pushup_setNum.setText("　　　10回×3");
            french_press_setNum.setText("　　　10回×3");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　10回×3");
            wrist_curl_setNum.setText("　　　10回×3");
            normal_pushup_setNum1.setText("　　　10回×3");
            dumbbell_adduction_setNum.setText("　　　10回×3");
            reverse_pushup_setNum1.setText("　　　10回×3");
            side_raise_setNum.setText("　　　10回×3");
            front_raise_setNum.setText("　　　10回×3");
            upright_row_setNum.setText("　　　10回×3");
            hip_lift_setNum.setText("　　　10回×3");
            high_reverse_plank_setNum.setText("　　　10回×3");
            leg_raise_setNum.setText("　　　10回×3");
            dumbbell_crunch_setNum.setText("　　　10回×3");
            side_crunch_setNum.setText("　　　10回×3");
            dumbbell_twist_setNum.setText("　　　10回×3");
            dumbbell_overflow_setNum.setText("　　　10回×3");
            back_extension_setNum.setText("　　　10回×3");
        }else if(setting_data[0] == 0 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：男、ダンベル：10kg～、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　10回×2");
            concentration_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum.setText("　　　10回×2");
            french_press_setNum.setText("　　　10回×2");
            reverse_pushup_setNum.setText("　　　10回×2");
            gu_pa_setNum.setText("　　　100回×2");
            radial_flexion_setNum.setText("　　　10回×2");
            wrist_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum.setText("　　　10回×2");
            dumbbell_adduction_setNum.setText("　　　10回×2");
            reverse_pushup_setNum1.setText("　　　10回×2");
            side_raise_setNum.setText("　　　10回×2");
            front_raise_setNum.setText("　　　10回×2");
            upright_row_setNum.setText("　　　10回×2");
            hip_lift_setNum.setText("　　　10回×2");
            high_reverse_plank_setNum.setText("　　　10回×2");
            leg_raise_setNum.setText("　　　10回×2");
            dumbbell_crunch_setNum.setText("　　　10回×2");
            side_crunch_setNum.setText("　　　10回×2");
            dumbbell_twist_setNum.setText("　　　10回×2");
            dumbbell_overflow_setNum.setText("　　　10回×2");
            back_extension_setNum.setText("　　　10回×2");
        }

        //性別女パターン
        if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 0){
            //性別：女、ダンベル：なし～4kg、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　20回×2");
            concentration_curl_setNum.setText("　　　20回×2");
            normal_pushup_setNum.setText("　　　20回×2");
            french_press_setNum.setText("　　　20回×2");
            reverse_pushup_setNum.setText("　　　20回×2");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　20回×2");
            wrist_curl_setNum.setText("　　　20回×2");
            normal_pushup_setNum1.setText("　　　20回×2");
            dumbbell_adduction_setNum.setText("　　　20回×2");
            reverse_pushup_setNum1.setText("　　　20回×2");
            side_raise_setNum.setText("　　　20回×2");
            front_raise_setNum.setText("　　　20回×2");
            upright_row_setNum.setText("　　　20回×2");
            hip_lift_setNum.setText("　　　20回×2");
            high_reverse_plank_setNum.setText("　　　20回×2");
            leg_raise_setNum.setText("　　　20回×2");
            dumbbell_crunch_setNum.setText("　　　20回×2");
            side_crunch_setNum.setText("　　　20回×2");
            dumbbell_twist_setNum.setText("　　　20回×2");
            dumbbell_overflow_setNum.setText("　　　20回×2");
            back_extension_setNum.setText("　　　20回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 0){
            //性別：女、ダンベル：5k～9g、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　15回×2");
            concentration_curl_setNum.setText("　　　15回×2");
            normal_pushup_setNum.setText("　　　15回×2");
            french_press_setNum.setText("　　　15回×2");
            reverse_pushup_setNum.setText("　　　15回×2");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　15回×2");
            wrist_curl_setNum.setText("　　　15回×2");
            normal_pushup_setNum1.setText("　　　15回×2");
            dumbbell_adduction_setNum.setText("　　　15回×2");
            reverse_pushup_setNum1.setText("　　　15回×2");
            side_raise_setNum.setText("　　　15回×2");
            front_raise_setNum.setText("　　　15回×2");
            upright_row_setNum.setText("　　　15回×2");
            hip_lift_setNum.setText("　　　15回×2");
            high_reverse_plank_setNum.setText("　　　15回×2");
            leg_raise_setNum.setText("　　　15回×3");
            dumbbell_crunch_setNum.setText("　　　15回×3");
            side_crunch_setNum.setText("　　　15回×3");
            dumbbell_twist_setNum.setText("　　　15回×3");
            dumbbell_overflow_setNum.setText("　　　15回×3");
            back_extension_setNum.setText("　　　15回×3");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 0){
            //性別：女、ダンベル：10kg～、ゴリ細：ゴリ
            dumbbell_curl_setNum.setText("　　　10回×2");
            concentration_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum.setText("　　　10回×2");
            french_press_setNum.setText("　　　10回×2");
            reverse_pushup_setNum.setText("　　　10回×2");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　10回×2");
            wrist_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum1.setText("　　　10回×2");
            dumbbell_adduction_setNum.setText("　　　10回×2");
            side_raise_setNum.setText("　　　10回×2");
            front_raise_setNum.setText("　　　10回×2");
            upright_row_setNum.setText("　　　10回×2");
            hip_lift_setNum.setText("　　　10回×2");
            high_reverse_plank_setNum.setText("　　　10回×2");
            leg_raise_setNum.setText("　　　10回×2");
            dumbbell_crunch_setNum.setText("　　　10回×2");
            side_crunch_setNum.setText("　　　10回×2");
            dumbbell_twist_setNum.setText("　　　10回×2");
            dumbbell_overflow_setNum.setText("　　　10回×2");
            back_extension_setNum.setText("　　　10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 1 && setting_data[2] == 1){
            //性別：女、ダンベル：なし～4kg、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　15回×2");
            concentration_curl_setNum.setText("　　　15回×2");
            normal_pushup_setNum.setText("　　　15回×2");
            french_press_setNum.setText("　　　15回×2");
            reverse_pushup_setNum.setText("　　　15回×2");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　15回×2");
            wrist_curl_setNum.setText("　　　15回×2");
            normal_pushup_setNum1.setText("　　　15回×2");
            dumbbell_adduction_setNum.setText("　　　15回×2");
            reverse_pushup_setNum1.setText("　　　15回×2");
            side_raise_setNum.setText("　　　15回×2");
            front_raise_setNum.setText("　　　15回×2");
            upright_row_setNum.setText("　　　15回×2");
            hip_lift_setNum.setText("　　　15回×2");
            high_reverse_plank_setNum.setText("　　　15回×2");
            leg_raise_setNum.setText("　　　15回×2");
            dumbbell_crunch_setNum.setText("　　　15回×2");
            side_crunch_setNum.setText("　　　15回×2");
            dumbbell_twist_setNum.setText("　　　15回×2");
            dumbbell_overflow_setNum.setText("　　　15回×2");
            back_extension_setNum.setText("　　　15回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 2 && setting_data[2] == 1){
            //性別：女、ダンベル：5k～9g、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　10回×2");
            concentration_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum.setText("　　　10回×2");
            french_press_setNum.setText("　　　10回×2");
            reverse_pushup_setNum.setText("　　　10回×2");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　10回×2");
            wrist_curl_setNum.setText("　　　10回×2");
            normal_pushup_setNum1.setText("　　　10回×2");
            dumbbell_adduction_setNum.setText("　　　10回×2");
            reverse_pushup_setNum1.setText("　　　10回×2");
            side_raise_setNum.setText("　　　10回×2");
            front_raise_setNum.setText("　　　10回×2");
            upright_row_setNum.setText("　　　10回×2");
            hip_lift_setNum.setText("　　　10回×2");
            high_reverse_plank_setNum.setText("　　　10回×2");
            leg_raise_setNum.setText("　　　10回×2");
            dumbbell_crunch_setNum.setText("　　　10回×2");
            side_crunch_setNum.setText("　　　10回×2");
            dumbbell_twist_setNum.setText("　　　10回×2");
            dumbbell_overflow_setNum.setText("　　　10回×2");
            back_extension_setNum.setText("　　　10回×2");
        }else if(setting_data[0] == 1 && setting_data[1] == 3 && setting_data[2] == 1){
            //性別：女、ダンベル：10kg～、ゴリ細：細
            dumbbell_curl_setNum.setText("　　　10回×1");
            concentration_curl_setNum.setText("　　　10回×1");
            normal_pushup_setNum.setText("　　　10回×1");
            french_press_setNum.setText("　　　10回×1");
            reverse_pushup_setNum.setText("　　　10回×1");
            gu_pa_setNum.setText("　　　100回×1");
            radial_flexion_setNum.setText("　　　10回×1");
            wrist_curl_setNum.setText("　　　10回×1");
            normal_pushup_setNum1.setText("　　　10回×1");
            dumbbell_adduction_setNum.setText("　　　10回×1");
            reverse_pushup_setNum1.setText("　　　10回×1");
            side_raise_setNum.setText("　　　10回×1");
            front_raise_setNum.setText("　　　10回×1");
            upright_row_setNum.setText("　　　10回×1");
            hip_lift_setNum.setText("　　　10回×1");
            high_reverse_plank_setNum.setText("　　　10回×1");
            leg_raise_setNum.setText("　　　10回×1");
            dumbbell_crunch_setNum.setText("　　　10回×1");
            side_crunch_setNum.setText("　　　10回×1");
            dumbbell_twist_setNum.setText("　　　10回×1");
            dumbbell_overflow_setNum.setText("　　　10回×1");
            back_extension_setNum.setText("　　　10回×1");
        }

        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
