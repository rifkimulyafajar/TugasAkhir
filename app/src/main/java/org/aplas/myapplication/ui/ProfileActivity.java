package org.aplas.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.Login;
import org.aplas.myapplication.Model.LoginResponse;
import org.aplas.myapplication.Model.UpdateResponse;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String ID = "KEY_ID";
    private static final String NAME = "KEY_NAME";
    private static final String NIS = "KEY_NIS";
    private static final String USERNAME = "KEY_USERNAME";
    private static final String PASSWORD = "KEY_PASSWORD";
    private static final String KELAS = "KEY_KELAS";
    private static final String JURUSAN = "KEY_JURUSAN";

    TextView nis, nama, kelas, jurusan;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nis = findViewById(R.id.textView_nis);
        nama = findViewById(R.id.textView_nama);
        kelas = findViewById(R.id.textView_kelas);
        jurusan = findViewById(R.id.textView_jurusan);
        user = findViewById(R.id.edtUsername);
        pass = findViewById(R.id.edtPassword);

        sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE);
        String snis = sharedPreferences.getString(NIS, "");
        String snama = sharedPreferences.getString(NAME, "");
        String skelas = sharedPreferences.getString(KELAS, "");
        String sjurusan = sharedPreferences.getString(JURUSAN, "");
        String suser = sharedPreferences.getString(USERNAME, "");
        String spass = sharedPreferences.getString(PASSWORD, "");

        if (snama != null && snis != null && skelas != null && sjurusan != null && suser != null && spass != null) {
            nis.setText(""+snis);
            nama.setText(""+snama);
            kelas.setText(""+skelas);
            jurusan.setText(""+sjurusan);
            user.setText(""+suser);
        }

    }

    public void update(View view) {

        String password;

        sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE);
        String sid = sharedPreferences.getString(ID, "");
        String spass = sharedPreferences.getString(PASSWORD, "");

        String etuser = user.getText().toString();
        String etpass = pass.getText().toString();

        if (etpass.equals(null)) {
            password = spass;
        } else {
            password = etpass;
        }

        ApiInterface apiInterface = ApiClient.getService();
        Call<UpdateResponse> call = apiInterface.profile(sid, etuser, password);
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "" +response.body().getMessage(), Toast.LENGTH_LONG).show();
                    sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USERNAME, etuser);
                    editor.putString(PASSWORD, etpass);
                    editor.apply();
                    Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(ProfileActivity.this, "Update Gagal !!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, ""+t, Toast.LENGTH_LONG).show();
            }
        });
    }

}
