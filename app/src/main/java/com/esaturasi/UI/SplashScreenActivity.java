package com.esaturasi.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.esaturasi.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500; // waktu delay 1.5 detik (3000 ms)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spalshscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setelah timer selesai, berpindah ke MainActivity
                Intent intent = new Intent(SplashScreenActivity.this, Start1Activity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}