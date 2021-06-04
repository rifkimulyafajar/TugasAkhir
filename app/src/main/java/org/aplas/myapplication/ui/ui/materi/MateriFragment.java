package org.aplas.myapplication.ui.ui.materi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aplas.myapplication.Adapter.AdapterMateri;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.Materi;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriFragment extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterMateri adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_materi, container, false);

        recyclerView = view.findViewById(R.id.rv_materi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return view;
    }

    private void refresh() {
        Call<Materi> materiCall = apiInterface.getMateri();
        materiCall.enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {
                Materi materi = response.body();

                if (response.isSuccessful()) {
                    for (int i = 0; i<materi.getData().length; i++) {
                        adapter = new AdapterMateri(materi, getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
