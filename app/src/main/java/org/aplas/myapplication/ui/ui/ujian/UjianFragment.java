package org.aplas.myapplication.ui.ui.ujian;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Adapter.AdapterUjian;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.Ujian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class UjianFragment extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterUjian adapter;

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String ID_KLS = "KEY_ID_KLS";
    private static final String ID_JRS = "KEY_ID_JRS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ujian, container, false);

        recyclerView = view.findViewById(R.id.rv_ujian);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return view;
    }

    private void refresh() {
        sharedPreferences = getActivity().getSharedPreferences(SHARE, MODE_PRIVATE);
        String kls = sharedPreferences.getString(ID_KLS, "");
        String jrs = sharedPreferences.getString(ID_JRS, "");

        Call<Ujian> call = apiInterface.getUjian(kls, jrs);
        call.enqueue(new Callback<Ujian>() {
            @Override
            public void onResponse(Call<Ujian> call, Response<Ujian> response) {
                Ujian ujian = response.body();
                if (response.isSuccessful()) {
                    if (ujian != null) {
                        for (int i=0; i<ujian.getData().length; i++) {
                            adapter = new AdapterUjian(ujian, getContext());
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Ujian> call, Throwable t) {
                Toast.makeText(getActivity(), "" +t, Toast.LENGTH_LONG).show();
            }
        });

    }
}
