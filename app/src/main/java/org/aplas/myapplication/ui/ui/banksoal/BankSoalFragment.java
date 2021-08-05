package org.aplas.myapplication.ui.ui.banksoal;

import android.app.ProgressDialog;
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

import org.aplas.myapplication.Adapter.AdapterBankSoal;
import org.aplas.myapplication.Model.ApiInterface;
import org.aplas.myapplication.Model.BankSoal;
import org.aplas.myapplication.R;
import org.aplas.myapplication.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class BankSoalFragment extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private AdapterBankSoal adapter;

    SharedPreferences sharedPreferences;
    private static final String SHARE = "KEY_SHARE";
    private static final String ID_KLS = "KEY_ID_KLS";
    private static final String ID_JRS = "KEY_ID_JRS";

    Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bank_soal, container, false);

        recyclerView = view.findViewById(R.id.rv_bankSoal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        refresh = view.findViewById(R.id.btn_rfsh);

        refresh();

        return view;
    }

    private void refresh() {
        sharedPreferences = getActivity().getSharedPreferences(SHARE, MODE_PRIVATE);
        String kls = sharedPreferences.getString(ID_KLS, "");
        String jrs = sharedPreferences.getString(ID_JRS, "");

        Call<BankSoal> call = apiInterface.getSoal(kls, jrs);
        call.enqueue(new Callback<BankSoal>() {
            @Override
            public void onResponse(Call<BankSoal> call, Response<BankSoal> response) {
                BankSoal soal = response.body();

                if (response.isSuccessful()) {
                    if (soal != null) {
                        for (int i=0; i<soal.getData().length; i++) {
                            adapter = new AdapterBankSoal(soal, getContext());
                            recyclerView.setAdapter(adapter);
                        }
                        refresh.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Belum ada Soal Latihan untuk saat ini", Toast.LENGTH_LONG).show();
                    refresh.setVisibility(View.VISIBLE);
                    refresh.setOnClickListener(view -> {
                        refresh();
                    });
                }
            }

            @Override
            public void onFailure(Call<BankSoal> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_LONG).show();
                refresh.setVisibility(View.VISIBLE);
                refresh.setOnClickListener(view -> {
                    refresh();
                });
            }
        });
    }
}
