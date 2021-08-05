package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.HasilUjian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.ui.hasil_ujian.DetailHasilUjianActivity;
import org.jetbrains.annotations.NotNull;

public class AdapterHasilUjian extends RecyclerView.Adapter<AdapterHasilUjian.ViewHolder>{

    HasilUjian hasilUjian;
    Context context;

    public AdapterHasilUjian(HasilUjian hasilUjian, Context context) {
        this.hasilUjian = hasilUjian;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterHasilUjian.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_hasil_ujian, parent, false);
        AdapterHasilUjian.ViewHolder viewHolder = new AdapterHasilUjian.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterHasilUjian.ViewHolder holder, int position) {

        holder.judul.setText(hasilUjian.getData()[position].getJudul_ujian());
        holder.guru.setText(hasilUjian.getData()[position].getNama());
        holder.mapel.setText(hasilUjian.getData()[position].getMapel());
        holder.nilai.setText("Nilai  =  " + hasilUjian.getData()[position].getNilai());

    }

    @Override
    public int getItemCount() {
        return hasilUjian.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, guru, mapel, nilai;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.TV_judul);
            guru = itemView.findViewById(R.id.TV_namaguru);
            mapel = itemView.findViewById(R.id.TV_mapel);
            nilai = itemView.findViewById(R.id.TV_nilai);
        }
    }
}
