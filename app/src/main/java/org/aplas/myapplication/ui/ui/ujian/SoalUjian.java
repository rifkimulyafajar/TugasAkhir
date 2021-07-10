package org.aplas.myapplication.ui.ui.ujian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import android.os.CountDownTimer;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.Adapter.AdapterSoalUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.DetailHasilUjian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;
import org.aplas.myapplication.ui.MainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalUjian extends AppCompatActivity {

    ApiInterface apiInterface;
    Context context = this;

    private RecyclerView recyclerView;
    private AdapterSoalUjian adapter;

    TextView guru, mapel, kelas, jurusan, durasi;
    Button selesai, refresh;

    long diff, diffMinutes, diffHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian);

        guru = findViewById(R.id.TVguru); mapel = findViewById(R.id.TVmapel);
        kelas = findViewById(R.id.TVkelas); jurusan = findViewById(R.id.TVjurusan);
        durasi = findViewById(R.id.TVdurasi);
        selesai = findViewById(R.id.btnStopUjian);
        refresh = findViewById(R.id.button_refresh);

        Bundle bundle = getIntent().getExtras();
        String bguru = bundle.getString("keyguru"); String bmapel = bundle.getString("keymapel");
        String bkelas = bundle.getString("keykelas"); String bjurus = bundle.getString("keyjurusan");

        String bakhir = bundle.getString("keyakhir");

        SharedPreferences sf = getSharedPreferences("KEY_SHARE", MODE_PRIVATE);
        String IdSiswa = sf.getString("KEY_ID","");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //ambil berapa menit antara waktu selesai ujian dengan waktu sekarang
        Date akhir, sekarang;
        try {
            akhir = df.parse(bakhir);
            sekarang = Calendar.getInstance().getTime();

            diff = akhir.getTime() - sekarang.getTime();

            diffMinutes = (diff / (60 * 1000) % 60) +1;
            diffHours = diff / (60 * 60 * 1000) % 24;


        } catch (Exception e) {
            e.printStackTrace();
        }


        //countdown durasi
        new CountDownTimer(((Integer.parseInt(String.valueOf(diffMinutes)) * 60000) +
                Integer.parseInt(String.valueOf(diffHours)) * 3600000 ),
                1000) {

            public void onTick(long millisUntilFinished) {
                String waktu = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                durasi.setText("Waktu " +waktu);

            }

            public void onFinish() {
                durasi.setText("done!");

                Toast.makeText(SoalUjian.this, "Selesai", Toast.LENGTH_SHORT).show();

                String IdUjian = adapter.getId_ujian();
                int nilai = adapter.getNilai();
                int jmlbenar = adapter.getJml_benar();

                sendData(IdUjian, IdSiswa, jmlbenar,nilai);

                finish();
            }

        }.start();


        //btn selesai mengerjakan
        selesai.setOnClickListener(view -> {

            Toast.makeText(this, "Selesai", Toast.LENGTH_SHORT).show();

            String IdUjian = adapter.getId_ujian();
            int nilai = adapter.getNilai();
            int jmlbenar = adapter.getJml_benar();

            sendData(IdUjian, IdSiswa, jmlbenar,nilai);

            finish();
        });


        guru.setText(bguru); mapel.setText(bmapel); kelas.setText(bkelas); jurusan.setText(bjurus);

        recyclerView = findViewById(R.id.rv_soalujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SoalUjian.this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh();

    }

    private void sendData(String idUjian, String idSiswa, int jmlbenar, int nilai) {

        Call<org.aplas.myapplication.Model.SoalUjian> call = apiInterface.sendSoalUjian(idUjian, idSiswa, String.valueOf(jmlbenar), String.valueOf(nilai));
        call.enqueue(new Callback<org.aplas.myapplication.Model.SoalUjian>() {

            @Override
            public void onResponse(Call<org.aplas.myapplication.Model.SoalUjian> call, Response<org.aplas.myapplication.Model.SoalUjian> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "send success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<org.aplas.myapplication.Model.SoalUjian> call, Throwable t) {
                Toast.makeText(context, "" +t, Toast.LENGTH_LONG).show();
            }

            });
        }


    private void refresh() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String jns = bundle.getString("jenis");

        Call<org.aplas.myapplication.Model.SoalUjian> call = apiInterface.getSoalUjian(id, jns);
        call.enqueue(new Callback<org.aplas.myapplication.Model.SoalUjian>() {
            @Override
            public void onResponse(Call<org.aplas.myapplication.Model.SoalUjian> call, Response<org.aplas.myapplication.Model.SoalUjian> response) {
                org.aplas.myapplication.Model.SoalUjian soal = response.body();
                if (response.isSuccessful()) {
                    if (soal != null) {
                        for (int i=0; i<soal.getData().length; i++) {
                            adapter = new AdapterSoalUjian(soal, SoalUjian.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
                else {
                    Toast.makeText(SoalUjian.this, "Belum Ada Soal Ujian untuk saat ini", Toast.LENGTH_LONG).show();
                    refresh.setVisibility(View.VISIBLE);
                    refresh.setOnClickListener(view -> {
                        refresh();
                    });
                }
            }

            @Override
            public void onFailure(Call<org.aplas.myapplication.Model.SoalUjian> call, Throwable t) {
                Toast.makeText(SoalUjian.this, "" +t, Toast.LENGTH_LONG).show();
                refresh.setVisibility(View.VISIBLE);
                refresh.setOnClickListener(view -> {
                    refresh();
                });
            }
        });
    }
}
