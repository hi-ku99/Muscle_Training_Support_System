package com.example.mtss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class re_zintai extends AppCompatActivity {
    private static int parts_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_zintai);

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

    }

    public void obverse(View v){
        startActivity(new Intent(this,ob_zintai.class));
    }

    public void re_right_arm(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void re_left_arm(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void re_right_leg(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void re_left_leg(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void back(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public void ass(View v){
        parts_obj=v.getId();
        DialogFragment ex = new ExDialogFragment();
        ex.show(getSupportFragmentManager(),"dialog_basic");
    }

    public int getParts_obj(){
        return parts_obj;
    }

    public void reset(){
        parts_obj = 0;
    }
}
