package org.aplas.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.mapel.setText(bank_soal.getData()[position].getMapel());
        holder.soal.setText("Soal : " +bank_soal.getData()[position].getSoal());
        holder.a.setText(bank_soal.getData()[position].getPilihan_a());
        holder.b.setText(bank_soal.getData()[position].getPilihan_b());
        holder.c.setText(bank_soal.getData()[position].getPilihan_c());
        holder.d.setText(bank_soal.getData()[position].getPilihan_d());
        holder.e.setText(bank_soal.getData()[position].getPilihan_e());

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
        TextView soal, mapel;
        Button jawab;
        RadioGroup jawaban;
        RadioButton a,b,c,d,e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapel = itemView.findViewById(R.id.txtMapel);
            soal = itemView.findViewById(R.id.txtSoal);
            jawab = itemView.findViewById(R.id.btn_jawab);
            jawaban = itemView.findViewById(R.id.rg_jawaban);
            a = itemView.findViewById(R.id.rb_a);
            b = itemView.findViewById(R.id.rb_b);
            c = itemView.findViewById(R.id.rb_c);
            d = itemView.findViewById(R.id.rb_d);
            e = itemView.findViewById(R.id.rb_e);
        }
    }
}
