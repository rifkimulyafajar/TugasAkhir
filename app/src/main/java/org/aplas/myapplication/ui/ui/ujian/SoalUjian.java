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
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.Adapter.AdapterSoalUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;
import org.aplas.myapplication.ui.MainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class SoalUjian extends AppCompatActivity {

    ApiInterface apiInterface;
    Context context = this;


    private RecyclerView recyclerView;
    private AdapterSoalUjian adapter;

    TextView guru, mapel, kelas, jurusan;
    Button selesai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian);

//        Log.d("sharepref", "ID"+sharedPref.getString(IDSISWA));

        int calculate;

        guru = findViewById(R.id.TVguru); mapel = findViewById(R.id.TVmapel);
        kelas = findViewById(R.id.TVkelas); jurusan = findViewById(R.id.TVjurusan);
        selesai = findViewById(R.id.btnStopUjian);

        Bundle bundle = getIntent().getExtras();
        String bguru = bundle.getString("keyguru"); String bmapel = bundle.getString("keymapel");
        String bkelas = bundle.getString("keykelas"); String bjurus = bundle.getString("keyjurusan");
        String waktmulai = bundle.getString("waktumulai");
        SharedPreferences sf = getSharedPreferences("KEY_SHARE", MODE_PRIVATE);
        String IdSiswa = sf.getString("KEY_ID","");



        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        selesai.setOnClickListener(view -> {

            Log.d("asdf", "bundle: "+bundle);

            Toast.makeText(this, "Selesai", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, Integer.toString(adapter.getNilai()) , Toast.LENGTH_SHORT).show();

//            Log.d("asdf", "Selesai  "+getIntent().toUri(0));
            String IdUjian = adapter.getId_ujian();
            int nilai = adapter.getNilai();
            int jmlbenar = adapter.getJml_benar();
            Log.d("asdf", "onCreate: "+nilai);
            Log.d("asdf", "onCreate: jml benar"+jmlbenar);

            sendData(IdUjian, IdSiswa, jmlbenar,nilai);

            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        });


        // countdown function

        try {
            Date tmp = df.parse(waktmulai);


        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Toast.makeText(SoalUjian.this,date,Toast.LENGTH_LONG).show();

        TextView tv14 =findViewById(R.id.textView14);
//        tv14.setText(date);


        guru.setText(bguru); mapel.setText(bmapel); kelas.setText(bkelas); jurusan.setText(bjurus);

        recyclerView = findViewById(R.id.rv_soalujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SoalUjian.this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh();
    }

    private void sendData(String idUjian, String idSiswa, int jmlbenar, int nilai) {
        Log.d("asdf", "sendData: "+idUjian+" "+idSiswa+" "+jmlbenar+" "+nilai);

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
            }

            @Override
            public void onFailure(Call<org.aplas.myapplication.Model.SoalUjian> call, Throwable t) {
                Toast.makeText(SoalUjian.this, "" +t, Toast.LENGTH_LONG).show();
            }
        });
    }
}
