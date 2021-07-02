package org.aplas.myapplication.ui.ui.ujian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.myapplication.Adapter.AdapterSoalUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class SoalUjian extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterSoalUjian adapter;

    TextView guru, mapel, kelas, jurusan ,jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_ujian);

        guru = findViewById(R.id.TVguru); mapel = findViewById(R.id.TVmapel);
        kelas = findViewById(R.id.TVkelas); jurusan = findViewById(R.id.TVjurusan);
        jenis = findViewById(R.id.textView14);

        Bundle bundle = getIntent().getExtras();
        String bguru = bundle.getString("keyguru"); String bmapel = bundle.getString("keymapel");
        String bkelas = bundle.getString("keykelas"); String bjurus = bundle.getString("keyjurusan");

        guru.setText(bguru); mapel.setText(bmapel); kelas.setText(bkelas); jurusan.setText(bjurus);


        recyclerView = findViewById(R.id.rv_soalujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SoalUjian.this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh();
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