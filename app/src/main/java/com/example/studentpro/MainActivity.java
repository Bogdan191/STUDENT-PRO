package com.example.studentpro;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

import com.google.firebase.installations.Utils;

import java.util.Timer;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        Intent intent = new Intent(this, LoginActivity.class);
        /*new CountDownTimer(3000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here

            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start(); */
        startActivity(intent);

    }




}