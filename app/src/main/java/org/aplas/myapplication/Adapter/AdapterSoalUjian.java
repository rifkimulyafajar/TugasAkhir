package org.aplas.myapplication.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.SoalUjian;
import org.aplas.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSoalUjian extends RecyclerView.Adapter<AdapterSoalUjian.ViewHolder> {

    SoalUjian soal;
    Context context;
    private Onclickjawab onclickjawab;

    public int getNilai() {
        calculate();
        return nilai;
    }

    ArrayList<Integer> hasil = new ArrayList<Integer>();

    int nilai;

    private void calculate() {
        for (int i = 0; i < hasil.size(); i++) {
            nilai+=hasil.get(i);
            Log.d("asdf", "calculate: "+hasil.get(i));
            Log.d("asdf", "calculate: nilai"+nilai);

        }
    }

    public AdapterSoalUjian(SoalUjian soal, Context context) {
        this.soal = soal;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_soalujian, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.wvsoal.loadData(soal.getData()[position].getSoal(), "text/html", "UTF-8");

        holder.a.setText(soal.getData()[position].getPilihan_a());
        holder.b.setText(soal.getData()[position].getPilihan_b());
        holder.c.setText(soal.getData()[position].getPilihan_c());
        holder.d.setText(soal.getData()[position].getPilihan_d());
        holder.e.setText(soal.getData()[position].getPilihan_e());

        String kunci = soal.getData()[position].getKunci();
        int bobot = Integer.parseInt(soal.getData()[position].getNilai());

        Log.d("asdf", "onBindViewHolder: "+nilai);
        hasil.add(0);

//        boolean tmp;
//        hasil[position] = tmp;

        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            boolean tmp;
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = holder.rg.getCheckedRadioButtonId();
                switch (id){
                    case R.id.pil_a:
                        if (checkTrue(holder.a.getText(), kunci)){
                            Log.d("asdf", "benar");
                            tmp=true;
                            hasil.set(position,bobot);
                        }

                        break;
                    case R.id.pil_b:
                        if (checkTrue(holder.b.getText(), kunci)){
                            Log.d("asdf", "benar");
                            hasil.set(position,bobot);
                        }
                        break;
                    case R.id.pil_c:
                        if (checkTrue(holder.c.getText(), kunci)){
                            Log.d("asdf", "benar");
                            hasil.set(position,bobot);
                        }
                        break;
                    case R.id.pil_d:
                        if (checkTrue(holder.d.getText(), kunci)){
                            Log.d("asdf", "benar");
                            hasil.set(position,bobot);
                        }
                        break;
                    case R.id.pil_e:
                        if (checkTrue(holder.e.getText(), kunci)){
                            Log.d("asdf", "benar");
                            hasil.set(position,bobot);
                        }
                        break;

                }
            }
            public boolean checkTrue(CharSequence text, String kunci) {
                if (kunci.equals(text)){
                    return true;
                }
                return false ;
            }

        });


    }


    @Override
    public int getItemCount() {
        return soal.getData().length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

//        TextView durasi, a,b,c,d,e ;
        TextView durasi;

        WebView wvsoal;

        RadioGroup rg;
        RadioButton a,b,c,d,e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wvsoal = itemView.findViewById(R.id.wv_soal);
            a = itemView.findViewById(R.id.pil_a);
            b = itemView.findViewById(R.id.pil_b);
            c = itemView.findViewById(R.id.pil_c);
            d = itemView.findViewById(R.id.pil_d);
            e = itemView.findViewById(R.id.pil_e);
            durasi = itemView.findViewById(R.id.textView14);
            rg = itemView.findViewById(R.id.radioGroup2);

        }
    }
    public interface Onclickjawab{
        public void onClickjawab(Intent intent);
    }
}
