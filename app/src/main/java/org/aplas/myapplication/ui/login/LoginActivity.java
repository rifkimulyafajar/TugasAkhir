package org.aplas.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.MainActivity;
import org.aplas.myapplication.ui.SplashActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;
    private TextView textLupaPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.edtTextUsername);
        editTextPassword = findViewById(R.id.edtTextPassword);
        textLupaPassword = findViewById(R.id.textLupaPassword);
        loginButton = findViewById(R.id.button);

        textLupaPassword.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }
    private void processLogin() {
        if (editTextUsername.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else if (editTextPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            Intent masuk = new Intent(this,MainActivity.class);
            startActivity(masuk);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textLupaPassword : ;
            case R.id.button : processLogin();
        }
    }
}
