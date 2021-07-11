package org.aplas.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.aplas.myapplication.Model.BankSoal;
import org.aplas.myapplication.R;

public class AdapterBankSoal extends RecyclerView.Adapter<AdapterBankSoal.ViewHolder> {

    BankSoal bank_soal;
    Context context;

    public AdapterBankSoal(BankSoal bank_soal, Context context) {
        this.bank_soal = bank_soal;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBankSoal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_banksoal, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBankSoal.ViewHolder holder, int position) {
        String url = "https://smai-ujian.xyz/smai-admin/upload/soal/";

        holder.mapel.setText(bank_soal.getData()[position].getMapel());
        holder.wvSoal.loadData("Soal : " +bank_soal.getData()[position].getSoal(), "text/html", "UTF-8");

        if (bank_soal.getData()[position].getFile_soal() != null) {
            holder.img_soal.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load("" +url +bank_soal.getData()[position].getFile_soal())
                    .into(holder.img_soal);
        }


        if (bank_soal.getData()[position].getFile_a().equals(null) ||
            bank_soal.getData()[position].getFile_a().equals("")) {
            holder.a.setText(bank_soal.getData()[position].getPilihan_a());
        } else {
            holder.imgA.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +bank_soal.getData()[position].getFile_a())
                    .into(holder.imgA);
            holder.a.setText(bank_soal.getData()[position].getFile_a());
            holder.a.setTextColor(Color.parseColor("#00000000"));
        }

        if (bank_soal.getData()[position].getFile_b().equals(null) ||
            bank_soal.getData()[position].getFile_b().equals("")) {
            holder.b.setText(bank_soal.getData()[position].getPilihan_b());
        } else {
            holder.imgB.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +bank_soal.getData()[position].getFile_b())
                    .into(holder.imgB);
            holder.b.setText(bank_soal.getData()[position].getFile_b());
            holder.b.setTextColor(Color.parseColor("#00000000"));
        }

        if (bank_soal.getData()[position].getFile_c().equals(null) ||
            bank_soal.getData()[position].getFile_c().equals("")) {
            holder.c.setText(bank_soal.getData()[position].getPilihan_c());
        } else {
            holder.imgC.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +bank_soal.getData()[position].getFile_c())
                    .into(holder.imgC);
            holder.c.setText(bank_soal.getData()[position].getFile_c());
            holder.c.setTextColor(Color.parseColor("#00000000"));
        }

        if (bank_soal.getData()[position].getFile_d().equals(null) ||
            bank_soal.getData()[position].getFile_d().equals("")) {
            holder.d.setText(bank_soal.getData()[position].getPilihan_d());
        } else {
            holder.imgD.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +bank_soal.getData()[position].getFile_d())
                    .into(holder.imgD);
            holder.d.setText(bank_soal.getData()[position].getFile_d());
            holder.d.setTextColor(Color.parseColor("#00000000"));
        }

        if (bank_soal.getData()[position].getFile_e().equals(null) ||
            bank_soal.getData()[position].getFile_e().equals("")) {
            holder.e.setText(bank_soal.getData()[position].getPilihan_e());
        } else {
            holder.imgE.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(""+url +bank_soal.getData()[position].getFile_e())
                    .into(holder.imgE);
            holder.e.setText(bank_soal.getData()[position].getFile_e());
            holder.e.setTextColor(Color.parseColor("#00000000"));
        }



        String kunci = bank_soal.getData()[position].getKunci();

        holder.jawab.setOnClickListener(view -> {
            int select = holder.jawaban.getCheckedRadioButtonId();
            if (select == holder.a.getId()) {
                if (holder.a.getText().toString().equals(kunci)) {
                    Toast.makeText(context, "Tepat Sekalii !!", Toast.LENGTH_SHORT).show();
                    dialogBenar();
                } else {
                    Toast.makeText(context, "Salah ..!!!", Toast.LENGTH_SHORT).show();
                    dialogSalah();
                }
            } else if (select == holder.b.getId()) {
                if (holder.b.getText().toString().equals(kunci)) {
                    Toast.makeText(context, "Tepat Sekalii !!", Toast.LENGTH_SHORT).show();
                    dialogBenar();
                } else {
                    Toast.makeText(context, "Salah ..!!!", Toast.LENGTH_SHORT).show();
                    dialogSalah();
                }
            } else if (select == holder.c.getId()) {
                if (holder.c.getText().toString().equals(kunci)) {
                    Toast.makeText(context, "Tepat Sekalii !!", Toast.LENGTH_SHORT).show();
                    dialogBenar();
                } else {
                    Toast.makeText(context, "Salah ..!!!", Toast.LENGTH_SHORT).show();
                    dialogSalah();
                }
            } else if (select == holder.d.getId()) {
                if (holder.d.getText().toString().equals(kunci)) {
                    Toast.makeText(context, "Tepat Sekalii !!", Toast.LENGTH_SHORT).show();
                    dialogBenar();
                } else {
                    Toast.makeText(context, "Salah ..!!!", Toast.LENGTH_SHORT).show();
                    dialogSalah();
                }
            } else if (select == holder.e.getId()) {
                if (holder.e.getText().toString().equals(kunci)) {
                    Toast.makeText(context, "Tepat Sekalii !!", Toast.LENGTH_SHORT).show();
                    dialogBenar();
                } else {
                    Toast.makeText(context, "Salah ..!!!", Toast.LENGTH_SHORT).show();
                    dialogSalah();
                }
            } else {
                Toast.makeText(context, "Pilih Salah Satu Jawaban !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dialogBenar() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_benar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void  dialogSalah() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_salah);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return bank_soal.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WebView wvSoal; TextView mapel;
        ImageView img_soal, imgA, imgB, imgC, imgD, imgE;
        Button jawab;
        RadioGroup jawaban; RadioButton a,b,c,d,e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapel = itemView.findViewById(R.id.txtMapel);
            wvSoal = itemView.findViewById(R.id.wvSoal);
            img_soal = itemView.findViewById(R.id.img_soal);

            jawaban = itemView.findViewById(R.id.rg_jawaban);
            a = itemView.findViewById(R.id.rb_a);
            b = itemView.findViewById(R.id.rb_b);
            c = itemView.findViewById(R.id.rb_c);
            d = itemView.findViewById(R.id.rb_d);
            e = itemView.findViewById(R.id.rb_e);

            jawab = itemView.findViewById(R.id.btn_jawab);

            imgA = itemView.findViewById(R.id.img_jwb_a);
            imgB = itemView.findViewById(R.id.img_jwb_b);
            imgC = itemView.findViewById(R.id.img_jwb_c);
            imgD = itemView.findViewById(R.id.img_jwb_d);
            imgE = itemView.findViewById(R.id.img_jwb_e);
        }
    }
}
