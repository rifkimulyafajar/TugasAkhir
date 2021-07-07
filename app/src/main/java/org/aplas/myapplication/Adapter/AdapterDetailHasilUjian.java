package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.DetailHasilUjian;
import org.aplas.myapplication.R;
import org.jetbrains.annotations.NotNull;

public class AdapterDetailHasilUjian extends RecyclerView.Adapter<AdapterDetailHasilUjian.ViewHolder> {

    DetailHasilUjian detail;
    Context context;

    int no = 0;

    public AdapterDetailHasilUjian(DetailHasilUjian detail, Context context) {
        this.detail = detail;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_detail_hasil_ujian, parent, false);
        AdapterDetailHasilUjian.ViewHolder viewHolder = new AdapterDetailHasilUjian.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        no++;
        holder.nama.setText(String.valueOf(no));
        holder.nama.setText(detail.getData()[position].getNama());
        holder.nilai.setText(detail.getData()[position].getNilai());

    }

    @Override
    public int getItemCount() {
        return detail.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomor, nama, nilai;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nomor = itemView.findViewById(R.id.tv_nomor);
            nama = itemView.findViewById(R.id.tv_siswa);
            nilai = itemView.findViewById(R.id.tv_nilai);
        }
    }
}
