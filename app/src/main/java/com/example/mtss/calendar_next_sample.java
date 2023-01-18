package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calendar_next_sample extends AppCompatActivity {

    TestOpenHelperForTitle helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_next_sample);
        Intent intent = getIntent();
        String day = intent.getStringExtra("day");
        readData_and_setImage(day);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void readData_and_setImage(String day) {
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        Resources res = getResources();
        //筋トレ要素数63
        String[] kintore_name;
        kintore_name = new String[]{"dumbbell_curl","hammer_curl","concentration_curl","palm_curl","normal_pushup","triceps_kickback","decline_pushup",
                                "reverse_pushup","french_press","dumbbell_adduction","rear_raise","side_raise","front_raise","upright_row","pike_press",
                                "gu_pa","radial_flexion","wrist_curl","supination","crunch","leg_raise","plank","reverse_crunch","knee_to_chest","hip_raise",
                                "leg_lift_crunch","knee_raise","v_sit","extended_plank","reverse_to_thrust","dumbbell_crunch","dumbbell_sit_up","side_crunch",
                                "side_plank","twist_crunch","russian_twist","side_bend","dumbbell_twist","heel_touch_crunch","side_elbow_bridge","bicycle_crunch",
                                "reverse_trunk_twist","leg_up_cross_crunch","back_lat_pull_down","reverse_snow_angel","back_extension","reverse_elbow_pushup",
                                "reverse_plank","bird_dog","back_cross_crunch_hold","dumbbell_overflow","hip_lift","wide_squat","side_lunge","diagonal",
                                "high_reverse_plank","hip_extension","bulgarian_squat","standing_calf_raise","standing_leg_curl","normal_squat","flog_jump","jumping_squat"};
        int scoreData = 0;

        for(int i = 0; i < kintore_name.length; i++) {
            String kintore = kintore_name[i];
                //カーソルを移動して上から順に読み込んでるイメージ。
                Cursor cursor = db.rawQuery("select title , score from title_table where title = '" + day + "_" + kintore + "'", null);
                boolean next = cursor.moveToFirst();
                while (next) {
                    int viewId = 0;
                    scoreData = cursor.getInt(1);
                    if(scoreData >= 1){
                        try{
                            viewId = res.getIdentifier(kintore_name[i], "id", getPackageName()); //筋トレ名がついた場所のidを取得
                        }catch(Exception e){
                            cursor.close();
                            i++;
                        }
                        View kintore_place = findViewById(viewId);
                        kintore_place.setBackgroundResource(R.drawable.muscle);
                    }
                    // 次の行が存在するか確認
                    next = cursor.moveToNext();
                }
                // dbを開いたら確実にclose
                cursor.close();
            }
    }
}
