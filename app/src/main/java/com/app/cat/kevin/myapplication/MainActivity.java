package com.app.cat.kevin.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import es.dmoral.toasty.Toasty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnImageClick{
    private Button calendarBtm, bottomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindBtn();
        setBtn();

    }

    private void bindBtn() {
        calendarBtm = findViewById(R.id.calendar);
        bottomBtn = findViewById(R.id.bottom);
    }

    private void setBtn() {
        bottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.Companion.bottomDialog(MainActivity.this, MainActivity.this);
            }
        });

        calendarBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.Companion.dateDialog(MainActivity.this);
            }
        });
    }

    @Override
    public void onImageClickListener(@NotNull String title, int position) {
        Toasty.success(this, title+" "+position, Toast.LENGTH_SHORT, true).show();
    }
}
