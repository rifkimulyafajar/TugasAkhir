package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.Ujian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.ui.ujian.SoalUjian;

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

        String sguru, smapel, skelas, sjurusan;

        sguru = ujian.getData()[position].getNama();
        smapel = ujian.getData()[position].getMapel();
        skelas = ujian.getData()[position].getKelas();
        sjurusan = ujian.getData()[position].getJurusan();

        holder.guru.setText(sguru);
        holder.mapel.setText(smapel);
        holder.kelas.setText("Kelas : " +skelas);
        holder.jurusan.setText("Jurusan : " +sjurusan);
        holder.mulai.setText("Waktu Mulai : " +ujian.getData()[position].getWaktu_mulai());
        holder.durasi.setText("Durasi : " +ujian.getData()[position].getDurasi()+ " menit");

        String id_ujian = ujian.getData()[position].getId_ujian();
        String token = ujian.getData()[position].getToken();

        holder.token.setOnClickListener(view -> {
            if (holder.et_token.getText().toString().isEmpty()) {
                Toast.makeText(context, "Token Harus Diisi !!", Toast.LENGTH_LONG).show();
            }
            else {
                if (holder.et_token.getText().toString().equals(token)) {
                    Toast.makeText(context, "Token Benar, Selamat Mengerjakan !!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, SoalUjian.class);
                    i.putExtra("id", id_ujian);
                    i.putExtra("keyguru", sguru); i.putExtra("keymapel", smapel);
                    i.putExtra("keykelas", skelas); i.putExtra("keyjurusan", sjurusan);
                    context.startActivity(i);
                }
                else {
                    Toast.makeText(context, "Token Salah !!", Toast.LENGTH_LONG).show();
                }
            }
        });

//  get local datetime
//        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        String date = df.format(Calendar.getInstance().getTime());
//        holder.tgl.setText(date);

    }

    @Override
    public int getItemCount() {
        return ujian.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guru, mapel, kelas, jurusan, mulai, durasi;
        EditText et_token;
        Button token;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guru = itemView.findViewById(R.id.tv_guru); mapel = itemView.findViewById(R.id.tv_mapel);
            kelas = itemView.findViewById(R.id.tv_kelas); jurusan = itemView.findViewById(R.id.tv_jurusan);
            mulai = itemView.findViewById(R.id.tv_mulai); durasi = itemView.findViewById(R.id.tv_durasi);
            et_token = itemView.findViewById(R.id.edtTxt_token);
            token = itemView.findViewById(R.id.btn_kerjakan);

        }
    }
}