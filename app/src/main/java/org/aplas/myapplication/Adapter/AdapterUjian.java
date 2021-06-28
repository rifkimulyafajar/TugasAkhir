package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.Ujian;
import org.aplas.myapplication.R;

public class AdapterUjian extends RecyclerView.Adapter<AdapterUjian.ViewHolder> {

    Ujian ujian;
    Context context;

    public AdapterUjian(Ujian ujian, Context context) {
        this.ujian = ujian;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterUjian.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_ujian, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUjian.ViewHolder holder, int position) {
        holder.guru.setText(ujian.getData()[position].getNama());
        holder.mapel.setText(ujian.getData()[position].getMapel());
        holder.kelas.setText("Kelas : " +ujian.getData()[position].getKelas());
        holder.jurusan.setText("Jurusan : " +ujian.getData()[position].getJurusan());
        holder.mulai.setText("Waktu Mulai : " +ujian.getData()[position].getWaktu_mulai());
        holder.durasi.setText("Durasi : " +ujian.getData()[position].getDurasi()+ " menit");
    }

    @Override
    public int getItemCount() {
        return ujian.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guru, mapel, kelas, jurusan, mulai, durasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guru = itemView.findViewById(R.id.tv_guru); mapel = itemView.findViewById(R.id.tv_mapel);
            kelas = itemView.findViewById(R.id.tv_kelas); jurusan = itemView.findViewById(R.id.tv_jurusan);
            mulai = itemView.findViewById(R.id.tv_mulai); durasi = itemView.findViewById(R.id.tv_durasi);

        }
    }
}
