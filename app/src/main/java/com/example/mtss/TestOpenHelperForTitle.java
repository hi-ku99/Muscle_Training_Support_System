package com.example.mtss;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TestOpenHelperForTitle extends SQLiteOpenHelper {

    // データーベースのバージョン管理。データベースは端末に保存されるので新しくしたい場合はこの数字を変更
    private static final int DATABASE_VERSION = 5;

    // データーベースのパーツに名前をつける(カレンダー以外)
    private static final String DATABASE_NAME = "TitleDB.db";//データベース名（表の集まり）
    private static final String TABLE_NAME = "title_table";//テーブル名（表の名前）
    private static final String ID = "id";
    private static final String COLUMN_NAME_TITLE = "title"; //カラム(表の列)
    private static final String COLUMN_NAME_SCORE = "score";

    //データーベースのパーツに名前をつける(カレンダー用)
    private static final String TABLE_NAME2 = "calendar_table";//テーブル名（表の名前）
    private static final String ID2 = "id";
    private static final String COLUMN_NAME_TITLE2 = "title"; //カラム(表の列)
    private static final String COLUMN_NAME_SCORE2 = "score";

    //データベースの各パーツや用語が存在するので1度ググると理解度が上がる気がする


        //テーブル作成のSQL文をjavaで書いている感じ↓コピペでいい
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_TITLE + " TEXT UNIQUE, " +
                    COLUMN_NAME_SCORE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    ID2 +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_TITLE2 + " TEXT UNIQUE, " +
                    COLUMN_NAME_SCORE2 + " INTEGER)";

    //テーブルを消すときのSQL文だと思います
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + TABLE_NAME2;

    TestOpenHelperForTitle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // 端末にSQLiteファイルがなければSQLiteファイルが作成されます
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);

        //初期値を入れておく。
        String sql = "insert into title_table (title,score) values ('setting_check', 0)";
        db.execSQL(sql);

        //上腕二頭筋用の動画再生数データ初期値
        String sql_dumbbell_curl = "insert into title_table (title,score) values ('dumbbell_curl_check', 0)";
        String sql_hammer_curl = "insert into title_table (title,score) values ('hammer_curl_check', 0)";
        String sql_concentration_curl = "insert into title_table (title,score) values ('concentration_curl_check', 0)";
        String sql_palm_curl = "insert into title_table (title,score) values ('palm_curl_check', 0)";
        db.execSQL(sql_dumbbell_curl);
        db.execSQL(sql_hammer_curl);
        db.execSQL(sql_concentration_curl);
        db.execSQL(sql_palm_curl);

        //上腕三頭筋用の動画再生数データ初期値
        String sql_normal_pushup = "insert into title_table (title,score) values ('normal_pushup_check', 0)";
        String sql_triceps_kickback = "insert into title_table (title,score) values ('triceps_kickback_check', 0)";
        String sql_decline_pushup = "insert into title_table (title,score) values ('decline_pushup_check', 0)";
        String sql_reverse_pushup = "insert into title_table (title,score) values ('reverse_pushup_check', 0)";
        String sql_french_press= "insert into title_table (title,score) values ('french_press_check', 0)";
        db.execSQL(sql_normal_pushup);
        db.execSQL(sql_triceps_kickback);
        db.execSQL(sql_decline_pushup);
        db.execSQL(sql_reverse_pushup);
        db.execSQL(sql_french_press);

        //胸筋用の動画再生数データ初期値
        String sql_dumbbell_adduction = "insert into title_table (title,score) values ('dumbbell_adduction_check', 0)";
        String sql_rear_raise = "insert into title_table (title,score) values ('rear_raise_check', 0)";
        db.execSQL(sql_dumbbell_adduction);
        db.execSQL(sql_rear_raise);

        //肩用の動画再生数データ初期値
        String sql_side_raise = "insert into title_table (title,score) values ('side_raise_check', 0)";
        String sql_front_raise = "insert into title_table (title,score) values ('front_raise_check', 0)";
        String sql_upright_row = "insert into title_table (title,score) values ('upright_row_check', 0)";
        String sql_pike_press = "insert into title_table (title,score) values ('pike_press_check', 0)";
        db.execSQL(sql_side_raise);
        db.execSQL(sql_front_raise);
        db.execSQL(sql_upright_row);
        db.execSQL(sql_pike_press);

        //上腕二頭筋用の動画再生数データ初期値
        String sql_gu_pa = "insert into title_table (title,score) values ('gu_pa_check', 0)";
        String sql_radial_flexion = "insert into title_table (title,score) values ('radial_flexion_check', 0)";
        String sql_wrist_curl = "insert into title_table (title,score) values ('wrist_curl_check', 0)";
        String sql_supination = "insert into title_table (title,score) values ('supination_check', 0)";
        db.execSQL(sql_gu_pa);
        db.execSQL(sql_radial_flexion);
        db.execSQL(sql_wrist_curl);
        db.execSQL(sql_supination);

        //腹直筋用の動画再生数データ初期値
        String sql_crunch = "insert into title_table (title,score) values ('crunch_check', 0)";
        String sql_leg_raise = "insert into title_table (title,score) values ('leg_raise_check', 0)";
        String sql_plank = "insert into title_table (title,score) values ('plank_check', 0)";
        String sql_reverse_crunch = "insert into title_table (title,score) values ('reverse_crunch_check', 0)";
        String sql_knee_to_chest = "insert into title_table (title,score) values ('knee_to_chest_check', 0)";
        String sql_hip_raise = "insert into title_table (title,score) values ('hip_raise_check', 0)";
        String sql_leg_lift_crunch = "insert into title_table (title,score) values ('leg_lift_crunch_check', 0)";
        String sql_knee_raise = "insert into title_table (title,score) values ('knee_raise_check', 0)";
        String sql_v_sit = "insert into title_table (title,score) values ('v_sit_check', 0)";
        String sql_extended_plank = "insert into title_table (title,score) values ('extended_plank_check', 0)";
        String sql_reverse_to_thrust = "insert into title_table (title,score) values ('reverse_to_thrust_check', 0)";
        String sql_dumbbell_crunch = "insert into title_table (title,score) values ('dumbbell_crunch_check', 0)";
        String sql_dumbbell_sit_up = "insert into title_table (title,score) values ('dumbbell_sit_up_check', 0)";
        db.execSQL(sql_crunch);
        db.execSQL(sql_leg_raise);
        db.execSQL(sql_plank);
        db.execSQL(sql_reverse_crunch);
        db.execSQL(sql_knee_to_chest);
        db.execSQL(sql_hip_raise);
        db.execSQL(sql_leg_lift_crunch);
        db.execSQL(sql_knee_raise);
        db.execSQL(sql_v_sit);
        db.execSQL(sql_extended_plank);
        db.execSQL(sql_reverse_to_thrust);
        db.execSQL(sql_dumbbell_crunch);
        db.execSQL(sql_dumbbell_sit_up);

        //腹斜筋用の動画再生数データ初期値
        String sql_side_crunch = "insert into title_table (title,score) values ('side_crunch_check', 0)";
        String sql_side_plank = "insert into title_table (title,score) values ('side_plank_check', 0)";
        String sql_twist_crunch = "insert into title_table (title,score) values ('twist_crunch_check', 0)";
        String sql_russian_twist = "insert into title_table (title,score) values ('russian_twist_check', 0)";
        String sql_side_bend = "insert into title_table (title,score) values ('side_bend_check', 0)";
        String sql_dumbbell_twist = "insert into title_table (title,score) values ('dumbbell_twist_check', 0)";
        String sql_heel_touch_crunch = "insert into title_table (title,score) values ('heel_touch_crunch_check', 0)";
        String sql_side_elbow_bridge = "insert into title_table (title,score) values ('side_elbow_bridge_check', 0)";
        String sql_bicycle_crunch = "insert into title_table (title,score) values ('bicycle_crunch_check', 0)";
        String sql_reverse_trunk_twist = "insert into title_table (title,score) values ('reverse_trunk_twist_check', 0)";
        String sql_leg_up_cross_crunch = "insert into title_table (title,score) values ('leg_up_cross_crunch_check', 0)";
        db.execSQL(sql_side_crunch);
        db.execSQL(sql_side_plank);
        db.execSQL(sql_twist_crunch);
        db.execSQL(sql_russian_twist);
        db.execSQL(sql_side_bend);
        db.execSQL(sql_dumbbell_twist);
        db.execSQL(sql_heel_touch_crunch);
        db.execSQL(sql_side_elbow_bridge);
        db.execSQL(sql_bicycle_crunch);
        db.execSQL(sql_reverse_trunk_twist);
        db.execSQL(sql_leg_up_cross_crunch);


        //背筋用の動画再生数データ初期値
        String sql_back_lat_pull_down = "insert into title_table (title,score) values ('back_lat_pull_down_check', 0)";
        String sql_reverse_snow_angel = "insert into title_table (title,score) values ('reverse_snow_angel_check', 0)";
        String sql_back_extension = "insert into title_table (title,score) values ('back_extension_check', 0)";
        String sql_reverse_elbow_pushup = "insert into title_table (title,score) values ('reverse_elbow_pushup_check', 0)";
        String sql_reverse_plank= "insert into title_table (title,score) values ('reverse_plank_check', 0)";
        String sql_bird_dog = "insert into title_table (title,score) values ('bird_dog_check', 0)";
        String sql_back_cross_crunch_hold = "insert into title_table (title,score) values ('back_cross_crunch_hold_check', 0)";
        String sql_dumbbell_overflow = "insert into title_table (title,score) values ('dumbbell_overflow_check', 0)";
        db.execSQL(sql_back_lat_pull_down);
        db.execSQL(sql_reverse_snow_angel);
        db.execSQL(sql_back_extension);
        db.execSQL(sql_reverse_elbow_pushup);
        db.execSQL(sql_reverse_plank);
        db.execSQL(sql_bird_dog);
        db.execSQL(sql_back_cross_crunch_hold);
        db.execSQL(sql_dumbbell_overflow);


        //臀部用の動画再生数データ初期値
        String sql_hip_lift = "insert into title_table (title,score) values ('hip_lift_check', 0)";
        String sql_wide_squat = "insert into title_table (title,score) values ('wide_squat_check', 0)";
        String sql_side_lunge = "insert into title_table (title,score) values ('side_lunge_check', 0)";
        String sql_diagonal = "insert into title_table (title,score) values ('diagonal_check', 0)";
        String sql_high_reverse_plank = "insert into title_table (title,score) values ('high_reverse_plank_check', 0)";
        String sql_hip_extension = "insert into title_table (title,score) values ('hip_extension_check', 0)";
        String sql_bulgarian_squat = "insert into title_table (title,score) values ('bulgarian_squat_check', 0)";
        db.execSQL(sql_hip_lift);
        db.execSQL(sql_wide_squat);
        db.execSQL(sql_side_lunge);
        db.execSQL(sql_diagonal);
        db.execSQL(sql_high_reverse_plank);
        db.execSQL(sql_hip_extension);
        db.execSQL(sql_bulgarian_squat);


        //脚部用の動画再生数データ初期値
        String sql_standing_calf_raise = "insert into title_table (title,score) values ('standing_calf_raise_check', 0)";
        String sql_standing_leg_curl = "insert into title_table (title,score) values ('standing_leg_curl_check', 0)";
        String sql_normal_squat = "insert into title_table (title,score) values ('normal_squat_check', 0)";
        String sql_flog_jump = "insert into title_table (title,score) values ('flog_jump_check', 0)";
        String sql_jumping_squat = "insert into title_table (title,score) values ('jumping_squat_check', 0)";
        db.execSQL(sql_standing_calf_raise);
        db.execSQL(sql_standing_leg_curl);
        db.execSQL(sql_normal_squat);
        db.execSQL(sql_flog_jump);
        db.execSQL(sql_jumping_squat);


        //設定画面の初期値
        String sql_gender = "insert into title_table (title,score) values ('gender', 0)";
        db.execSQL(sql_gender);

        String sql_body = "insert into title_table (title,score) values ('body_style', 0)";
        db.execSQL(sql_body);

        String sql_tool = "insert into title_table (title,score) values ('training_style', 0)";
        db.execSQL(sql_tool);


        //カレンダー用データ
        String sql_1 = "insert into calendar_table (title,score) values ('2020年8月1日', 1)";
        String sql_2 = "insert into calendar_table (title,score) values ('2020年8月3日', 4)";
        String sql_3 = "insert into calendar_table (title,score) values ('2020年8月4日', 7)";
        db.execSQL(sql_1);
        db.execSQL(sql_2);
        db.execSQL(sql_3);

        //部位選択画面の説明
        String sql_dialog = "insert into title_table (title,score) values ('explain_dialog', 0)";
        db.execSQL(sql_dialog);

        //拡大画面の説明
        String sql_ex_dialog = "insert into title_table (title,score) values ('explain_ex_dialog', 0)";
        db.execSQL(sql_ex_dialog);
    }


   /* private void saveData(SQLiteDatabase db, String title, int score){
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("score", score);

        db.insert("title_table", null, values);
    }*/

    //ここから下でアップデートの管理をしているもよう。
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        db.execSQL(
                SQL_DELETE_ENTRIES2
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
