package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ex_right_arm extends AppCompatActivity {
    private static int parts_obj;
    TestOpenHelperForTitle helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_right_arm);
        if(dialog_readData()==0) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View customAlertView = layoutInflater.inflate(R.layout.dialog_layout, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(customAlertView);

            // タイトルの変更
            TextView title = customAlertView.findViewById(R.id.title);
            title.setText("拡大画面における部位選択の仕方");

            // メッセージの変更
            TextView message = customAlertView.findViewById(R.id.message);
            message.setText("＜説明＞\n鍛えたい部位の名称をタップしてください。その部位の筋トレ動画を見ることができます。");

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

    public void zyou2(View v) {
        parts_obj = v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void zyou3(View v){
        parts_obj = v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void front_arm(View v){
        parts_obj = v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void shoulder(View v){
        parts_obj = v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void reset(){
        parts_obj = 0;
    }

    public int getParts() {
        return parts_obj;
    }

    public void re_activity(View v){
        startActivity(new Intent(this,ob_zintai.class));
    }

    private int dialog_readData() {
        if (helper == null) {
            helper = new TestOpenHelperForTitle(getApplicationContext());
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        int scoreData = 0;

        //カーソルを移動して上から順に読み込んでるイメージ。
        Cursor cursor = db.rawQuery("select title ,score from title_table where title = 'explain_ex_dialog'", null);
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

        db.update("title_table",values,"title=?",new String[]{"explain_ex_dialog"});
    }

}
