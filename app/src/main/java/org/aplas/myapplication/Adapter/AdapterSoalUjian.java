package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.SoalUjian;
import org.aplas.myapplication.R;

public class AdapterSoalUjian extends RecyclerView.Adapter<AdapterSoalUjian.ViewHolder> {

    SoalUjian soal;
    Context context;

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

    }

    @Override
    public int getItemCount() {
        return soal.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView soal, a,b,c,d,e ;
        WebView wvsoal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wvsoal = itemView.findViewById(R.id.WV_soal);
            a = itemView.findViewById(R.id.pil_a);
            b = itemView.findViewById(R.id.pil_b);
            c = itemView.findViewById(R.id.pil_c);
            d = itemView.findViewById(R.id.pil_d);
            e = itemView.findViewById(R.id.pil_e);

        }
    }
}
