package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ob_zintai extends AppCompatActivity {
    private static int parts_obj;
    TestOpenHelperForTitle helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ob_zintai);

        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Title.class);
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


        if(dialog_readData()==0) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View customAlertView = layoutInflater.inflate(R.layout.dialog_layout, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(customAlertView);

            // タイトルの変更
            TextView title = customAlertView.findViewById(R.id.title);
            title.setText("部位選択の仕方");

            // メッセージの変更
            TextView message = customAlertView.findViewById(R.id.message);
            message.setText("＜説明＞\n人体モデルにある鍛えたい部位を直接タップしてください。鍛えたい部位をタップすると、その部位の筋トレ動画を見ることができます。ただし、腕と腹の部位は拡大表示され、さらにそこから細かい部位を選びます。右上のボタンを押すと、人体モデルの正面と背面を切り替えることができます。\n＜選択できる部位＞\n正面：腕・脚・胸・腹\n背面：腕・脚・背中・尻");

            final AlertDialog alertDialog = builder.create();

            // ボタンの設定
            Button alertBtn = customAlertView.findViewById(R.id.btnPositive);
            alertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ボタンを押した時の処理を書く
                    if(helper == null){
                        helper = new TestOpenHelperForTitle(getApplicationContext());
                    }
                    SQLiteDatabase db = helper.getReadableDatabase();
                    dialog_upDate(db, 1);
                    // ダイアログを閉じる
                    alertDialog.dismiss();
                }
            });

            // ダイアログ表示
            alertDialog.show();
        }


    }



    public void reverse(View v){

        Intent intent = new Intent(this,re_zintai.class);
        startActivity(intent);
    }

    public void ob_right_arm(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void ob_left_arm(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void ob_right_leg(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void ob_left_leg(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void chest(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void stomach(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void menu(View v){
        startActivity(new Intent(this,MenuActivity.class));
    }

    public int getParts_obj(){
        return parts_obj;
    }

    public void reset(){
        parts_obj=0;
    }



    private int dialog_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'explain_dialog'", null);
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

    public void dialog_upDate(SQLiteDatabase db, int score){
        if(helper == null){
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        ContentValues values = new ContentValues();
        values.put("score", score);

        db.update("title_table",values,"title=?",new String[]{"explain_dialog"});
    }


}
