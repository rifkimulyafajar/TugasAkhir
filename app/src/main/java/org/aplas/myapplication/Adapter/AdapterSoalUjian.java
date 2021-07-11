package org.aplas.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.aplas.myapplication.Model.SoalUjian;
import org.aplas.myapplication.R;

import java.util.ArrayList;

public class AdapterSoalUjian extends RecyclerView.Adapter<AdapterSoalUjian.ViewHolder> {

    SoalUjian soal;
    Context context;

    ArrayList<Integer> hasil = new ArrayList<>();

    int nilai;
    int jml_benar;
    String id_ujian;

    public String getId_ujian() {
        return id_ujian;
    }

    public int getNilai() {
        calculate();
        return nilai;
    }

    private void calculate() {
        for (int i = 0; i < hasil.size(); i++) {
            nilai+=hasil.get(i);
            if (hasil.get(i) != 0){
                jml_benar++;
            }

        }
    }

    public int getJml_benar() {
        return jml_benar;
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
        String url = "https://smai-ujian.xyz/smai-admin/upload/soal/";

        id_ujian= soal.getData()[position].getId_ujian();
        holder.wvsoal.loadData(soal.getData()[position].getSoal(), "text/html", "UTF-8");

        if (soal.getData()[position].getFile_soal() != null) {
            holder.imgSoal.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load("" +url +soal.getData()[position].getFile_soal())
                    .into(holder.imgSoal);
        }

        holder.a.setText(soal.getData()[position].getPilihan_a());
        if (soal.getData()[position].getFile_a() != null) {
            holder.imgA.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +soal.getData()[position].getFile_a())
                    .into(holder.imgA);
            holder.a.setText(soal.getData()[position].getFile_a());
            holder.a.setTextColor(Color.parseColor("#00000000"));
        }

        holder.b.setText(soal.getData()[position].getPilihan_b());
        if (soal.getData()[position].getFile_b() != null) {
            holder.imgB.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +soal.getData()[position].getFile_b())
                    .into(holder.imgB);
            holder.b.setText(soal.getData()[position].getFile_b());
            holder.b.setTextColor(Color.parseColor("#00000000"));
        }

        holder.c.setText(soal.getData()[position].getPilihan_c());
        if (soal.getData()[position].getFile_c() != null) {
            holder.imgC.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +soal.getData()[position].getFile_c())
                    .into(holder.imgC);
            holder.c.setText(soal.getData()[position].getFile_c());
            holder.c.setTextColor(Color.parseColor("#00000000"));
        }

        holder.d.setText(soal.getData()[position].getPilihan_d());
        if (soal.getData()[position].getFile_d() != null) {
            holder.imgD.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +soal.getData()[position].getFile_d())
                    .into(holder.imgD);
            holder.d.setText(soal.getData()[position].getFile_d());
            holder.d.setTextColor(Color.parseColor("#00000000"));
        }

        holder.e.setText(soal.getData()[position].getPilihan_e());
        if (soal.getData()[position].getFile_e() != null) {
            holder.imgE.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +soal.getData()[position].getFile_e())
                    .into(holder.imgE);
            holder.e.setText(soal.getData()[position].getFile_e());
            holder.e.setTextColor(Color.parseColor("#00000000"));
        }

        String kunci = soal.getData()[position].getKunci();
        int bobot = Integer.parseInt(soal.getData()[position].getNilai());

        hasil.add(0);


        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            boolean tmp;
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = holder.rg.getCheckedRadioButtonId();
                switch (id){
                    case R.id.pil_a:
                        if (checkTrue(holder.a.getText(), kunci)){
                            tmp=true;
                            hasil.set(position,bobot);
                        }
                        else{
                            hasil.set(position,0);
                        }

                        break;
                    case R.id.pil_b:
                        if (checkTrue(holder.b.getText(), kunci)){
                            hasil.set(position,bobot);
                        }
                        else{
                            hasil.set(position,0);
                        }
                        break;
                    case R.id.pil_c:
                        if (checkTrue(holder.c.getText(), kunci)){
                            hasil.set(position,bobot);
                        }
                        else{
                            hasil.set(position,0);
                        }
                        break;
                    case R.id.pil_d:
                        if (checkTrue(holder.d.getText(), kunci)){
                            hasil.set(position,bobot);
                        }
                        else{
                            hasil.set(position,0);
                        }
                        break;
                    case R.id.pil_e:
                        if (checkTrue(holder.e.getText(), kunci)){
                            hasil.set(position,bobot);
                        }
                        else{
                            hasil.set(position,0);
                        }
                        break;

                }
            }
            public boolean checkTrue(CharSequence text, String kunci) {
                if (kunci.equals(text)){
                    return true;
                }
                return false;
            }

        });


    }


    @Override
    public int getItemCount() {
        return soal.getData().length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        WebView wvsoal;

        ImageView imgSoal, imgA, imgB, imgC, imgD, imgE;

        RadioGroup rg;
        RadioButton a,b,c,d,e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wvsoal = itemView.findViewById(R.id.WV_soal);
            imgSoal = itemView.findViewById(R.id.img_soalUjian);

            a = itemView.findViewById(R.id.pil_a);
            b = itemView.findViewById(R.id.pil_b);
            c = itemView.findViewById(R.id.pil_c);
            d = itemView.findViewById(R.id.pil_d);
            e = itemView.findViewById(R.id.pil_e);
            rg = itemView.findViewById(R.id.radioGroup2);

            imgA = itemView.findViewById(R.id.img_pil_a);
            imgB = itemView.findViewById(R.id.img_pil_b);
            imgC = itemView.findViewById(R.id.img_pil_c);
            imgD = itemView.findViewById(R.id.img_pil_d);
            imgE = itemView.findViewById(R.id.img_pil_e);

        }
    }

}
