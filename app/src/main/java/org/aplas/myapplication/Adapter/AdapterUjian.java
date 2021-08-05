package org.aplas.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.Ujian;
import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.ui.ujian.SoalUjian;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterUjian extends RecyclerView.Adapter<AdapterUjian.ViewHolder> {

    Ujian ujian;
    Context context;

    ArrayList<String> datasiswa = new ArrayList<>();
    ArrayList<String> dataUjian = new ArrayList<>();
    ArrayList<Boolean> cekSudahUjian = new ArrayList<>();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull AdapterUjian.ViewHolder holder, int position) {

        String sguru, smapel, skelas, sjurusan, swaktumulai, sdurasi;

        sguru = ujian.getData()[position].getNama();
        smapel = ujian.getData()[position].getMapel();
        skelas = ujian.getData()[position].getKelas();
        sjurusan = ujian.getData()[position].getJurusan();
        swaktumulai = ujian.getData()[position].getWaktu_mulai();
        sdurasi = ujian.getData()[position].getDurasi();

        holder.judul.setText(ujian.getData()[position].getJudul_ujian());
        holder.guru.setText(sguru);
        holder.mapel.setText(smapel);
        holder.kelas.setText("Kelas : " +skelas);
        holder.jurusan.setText("Jurusan : " +sjurusan);
        holder.mulai.setText("Waktu Mulai : " +swaktumulai);
        holder.durasi.setText("Durasi : " +sdurasi+ " menit");

        String id_ujian = ujian.getData()[position].getId_ujian();
        String jenis = ujian.getData()[position].getJenis();
        String token = ujian.getData()[position].getToken();
        String akhir = ujian.getData()[position].getWaktu_selesai();

        dataUjian.add(id_ujian);
        cekSudahUjian.add(false);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date1 = df.parse(ujian.getData()[position].getWaktu_mulai());
            Date date2 = df.parse(ujian.getData()[position].getWaktu_selesai());
            int cekawal = date1.compareTo(Calendar.getInstance().getTime());
            int cekakhir = date2.compareTo(Calendar.getInstance().getTime());

            if (!cekSudahUjian.get(position)){

                if (cekawal > 0){
                    holder.token.setVisibility(View.INVISIBLE);
                }
                else if(cekakhir < 0){
                    holder.token.setVisibility(View.INVISIBLE);
                }
                else {
                    holder.token.setVisibility(View.VISIBLE);
                }
            }
            else {
                holder.token.setVisibility(View.INVISIBLE);
                holder.ujian_item.removeAllViews();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.token.setOnClickListener(view -> {
            if (holder.et_token.getText().toString().isEmpty()) {
                Toast.makeText(context, "Token Harus Diisi !!", Toast.LENGTH_LONG).show();
            }

            else {
                if (holder.et_token.getText().toString().equals(token)) {
                    //token benar
                    holder.token.setVisibility(View.INVISIBLE);

                    LocalDateTime time = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    dateTimeFormatter.format(time);

                    Toast.makeText(context, "Token Benar, Selamat Mengerjakan !!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, SoalUjian.class);
                    i.putExtra("id", id_ujian); i.putExtra("jenis", jenis);
                    i.putExtra("keyguru", sguru); i.putExtra("keymapel", smapel);
                    i.putExtra("keykelas", skelas); i.putExtra("keyjurusan", sjurusan);
                    i.putExtra("keydurasi", sdurasi); i.putExtra("keyakhir", akhir);

                    context.startActivity(i);
                    Activity act = new Activity();
                    act.getBaseContext();
                    act.finish();
                }
                else {
                    //token salah
                    Toast.makeText(context, "Token Salah !!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ujian.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, guru, mapel, kelas, jurusan, mulai, durasi, tv14;
        EditText et_token;
        Button token;
        ConstraintLayout ujian_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ujian_item = itemView.findViewById(R.id.item_ujian);
            judul = itemView.findViewById(R.id.tv_judul);
            guru = itemView.findViewById(R.id.tv_guru); mapel = itemView.findViewById(R.id.tv_mapel);
            kelas = itemView.findViewById(R.id.tv_kelas); jurusan = itemView.findViewById(R.id.tv_jurusan);
            mulai = itemView.findViewById(R.id.tv_mulai); durasi = itemView.findViewById(R.id.tv_durasi);
            et_token = itemView.findViewById(R.id.edtTxt_token);
            token = itemView.findViewById(R.id.btn_kerjakan);
            tv14 = itemView.findViewById(R.id.TVdurasi);

        }
    }

    public void addDataSiswa(String value){
        datasiswa.add(value);
    }

    public void cari(){
        for (int i = 0; i < dataUjian.size(); i++) {
            for (int j = 0; j < datasiswa.size() ; j++) {
                if (dataUjian.get(i).equals(datasiswa.get(j))){
                    cekSudahUjian.set(i,true);
                    notifyDataSetChanged();
                }
            }
        }
    }

}