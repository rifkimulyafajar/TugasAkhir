package org.aplas.myapplication.ui.ui.hasil_ujian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.aplas.myapplication.Adapter.AdapterDetailHasilUjian;
import org.aplas.myapplication.Adapter.AdapterSoalUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.DetailHasilUjian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;
import org.aplas.myapplication.ui.ui.ujian.SoalUjian;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHasilUjianActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterDetailHasilUjian adapter;

    TextView judul, guru, mapel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hasil_ujian);

        judul = findViewById(R.id.tv_JUDUL);
        guru = findViewById(R.id.tv_GURU);
        mapel = findViewById(R.id.tv_MAPEL);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String bjudul = bundle.getString("judul");
        String bguru = bundle.getString("guru");
        String bmapel = bundle.getString("mapel");

        judul.setText(bjudul);  guru.setText(bguru);  mapel.setText(bmapel);

        recyclerView = findViewById(R.id.rv_detail_hasil_ujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailHasilUjianActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh();

    }

    private void refresh() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        Log.d("asdf", "refresh: "+id);

        Call<DetailHasilUjian> call = apiInterface.getDetailHasilUjian(id);
        call.enqueue(new Callback<DetailHasilUjian>() {
            @Override
            public void onResponse(Call<DetailHasilUjian> call, Response<DetailHasilUjian> response) {
                DetailHasilUjian detail = response.body();
                if (response.isSuccessful()) {
                    if (detail != null) {
                        for (int i=0; i<detail.getData().length; i++) {
                            adapter = new AdapterDetailHasilUjian(detail, DetailHasilUjianActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailHasilUjian> call, Throwable t) {

            }
        });
    }
}