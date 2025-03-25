package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen); // ✅ Ensure this XML file exists

        progressBar = findViewById(R.id.progress_bar); // ✅ Ensure ID exists in splash_screen.xml

        // Simulate progress bar loading
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10; // Increment progress
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try {
                        Thread.sleep(500); // Wait 500ms between increments
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Move to LoginActivity after progress completes
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // ✅ Prevents going back to splash screen
                    }
                });
            }
        }).start();
    }
}
