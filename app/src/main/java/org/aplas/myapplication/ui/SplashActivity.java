package org.aplas.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String NAME = "KEY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE);

                String nama = sharedPreferences.getString(NAME, "");

                if (nama == null || nama == "") {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SplashActivity.this, "Selamat Datang Kembali! "+nama, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2500);
    }
}
