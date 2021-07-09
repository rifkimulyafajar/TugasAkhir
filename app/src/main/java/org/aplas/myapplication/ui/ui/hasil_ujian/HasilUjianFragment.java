package org.aplas.myapplication.ui.ui.hasil_ujian;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.aplas.myapplication.Adapter.AdapterHasilUjian;
import org.aplas.myapplication.Adapter.AdapterUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.HasilUjian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class HasilUjianFragment extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterHasilUjian adapter;

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String ID = "KEY_ID";

    Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hasil_ujian, container, false);

        recyclerView = view.findViewById(R.id.rv_hasil_ujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh = view.findViewById(R.id.btn_REFRESH);

        refresh();

        return view;
    }

    private void refresh() {
        sharedPreferences = getActivity().getSharedPreferences(SHARE, MODE_PRIVATE);
        String sid = sharedPreferences.getString(ID, "");

        Call<HasilUjian> call = apiInterface.getHasilUjian(sid);
        call.enqueue(new Callback<HasilUjian>() {
            @Override
            public void onResponse(Call<HasilUjian> call, Response<HasilUjian> response) {
                HasilUjian hasil = response.body();
                if (response.isSuccessful()) {
                    if (hasil != null) {
                        for (int i=0; i<hasil.getData().length; i++) {
                            adapter = new AdapterHasilUjian(hasil, getContext());
                            recyclerView.setAdapter(adapter);
                        }
                        refresh.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Anda belum mengerjakan ujian", Toast.LENGTH_LONG).show();
                    refresh.setVisibility(View.VISIBLE);
                    refresh.setOnClickListener(view -> {
                        refresh();
                    });
                }
            }

            @Override
            public void onFailure(Call<HasilUjian> call, Throwable t) {
                Toast.makeText(getActivity(), "" +t, Toast.LENGTH_LONG).show();
                refresh.setVisibility(View.VISIBLE);
                refresh.setOnClickListener(view -> {
                    refresh();
                });
            }
        });
    }
}
