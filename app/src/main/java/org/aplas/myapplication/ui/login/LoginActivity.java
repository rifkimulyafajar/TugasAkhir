package org.aplas.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.LoginResponse;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;
import org.aplas.myapplication.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;
    private TextView textLupaPassword;
    private Button loginButton;

    String id, nis, id_kls, id_jrs, nama, user, pass, kls, jrs;

    SharedPreferences sharedPreferences;

    private static final String SHARE = "KEY_SHARE";
    private static final String ID = "KEY_ID";
    private static final String NAME = "KEY_NAME";
    private static final String NIS = "KEY_NIS";
    private static final String ID_KLS = "KEY_ID_KLS";
    private static final String ID_JRS = "KEY_ID_JRS";
    private static final String USERNAME = "KEY_USERNAME";
    private static final String PASSWORD = "KEY_PASSWORD";
    private static final String KELAS = "KEY_KELAS";
    private static final String JURUSAN = "KEY_JURUSAN";

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textLupaPassword : ;
            case R.id.button : processLogin();
        }
    }


    private void processLogin() {
        if (editTextUsername.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else if (editTextPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            String username, password;
            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();
            login(username, password);

        }
    }

    private void login(String username, String password) {
        ApiInterface apiInterface = ApiClient.getService();
        Call<LoginResponse> call = apiInterface.login(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    id = loginResponse.getData()[0].getId_siswa();
                    nis = loginResponse.getData()[0].getNis();
                    nama = loginResponse.getData()[0].getNama();
                    id_kls = loginResponse.getData()[0].getId_kelas();
                    id_jrs = loginResponse.getData()[0].getId_jurusan();
                    user = loginResponse.getData()[0].getUsername();
                    pass = loginResponse.getData()[0].getPassword();
                    kls = loginResponse.getData()[0].getKelas();
                    jrs = loginResponse.getData()[0].getJurusan();

                    sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ID, id);
                    editor.putString(NIS, nis);
                    editor.putString(NAME, nama);
                    editor.putString(ID_KLS, id_kls);
                    editor.putString(ID_JRS, id_jrs);
                    editor.putString(USERNAME, user);
                    editor.putString(PASSWORD, pass);
                    editor.putString(KELAS, kls);
                    editor.putString(JURUSAN, jrs);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login Berhasil! " +nama, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "" +t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
