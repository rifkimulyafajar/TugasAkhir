package org.aplas.myapplication.ui.ui.materi;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.aplas.myapplication.Adapter.AdapterMateri;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.Materi;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import java.net.IDN;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MateriFragment extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterMateri adapter;

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String ID_KLS = "KEY_ID_KLS";
    private static final String ID_JRS = "KEY_ID_JRS";

    Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_materi, container, false);

        recyclerView = view.findViewById(R.id.rv_materi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh = view.findViewById(R.id.btn_rf);

        refresh();

        return view;
    }

    private void refresh() {
        sharedPreferences = getActivity().getSharedPreferences(SHARE, MODE_PRIVATE);
        String kls = sharedPreferences.getString(ID_KLS, "");
        String jrs = sharedPreferences.getString(ID_JRS, "");

        Call<Materi> materiCall = apiInterface.getMateri(kls, jrs);
        materiCall.enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {
                Materi materi = response.body();
                if (response.isSuccessful()) {

                    if (materi != null) {
                        for (int i = 0; i<materi.getData().length; i++) {
                            adapter = new AdapterMateri(materi, getContext());
                            recyclerView.setAdapter(adapter);
                        }
                        refresh.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Belum Ada Materi Untuk Saat Ini", Toast.LENGTH_LONG).show();
                    refresh.setVisibility(View.VISIBLE);
                    refresh.setOnClickListener(view -> {
                        refresh();
                    });
                }
            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                Toast.makeText(getActivity(), "" +t, Toast.LENGTH_LONG).show();
                refresh.setVisibility(View.VISIBLE);
                refresh.setOnClickListener(view -> {
                    refresh();
                });
            }
        });
    }
}
