package org.aplas.myapplication.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aplas.myapplication.Model.Materi;
import org.aplas.myapplication.R;
import org.aplas.myapplication.ui.ui.materi.MateriFragment;

import java.util.List;

public class AdapterMateri extends RecyclerView.Adapter<AdapterMateri.ViewHolder> {

    private Materi materiList;
    Context context;

    public AdapterMateri(Materi materiList, Context context) {
        this.materiList = materiList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMateri.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_materi, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMateri.ViewHolder holder, int position) {

        holder.guru.setText(materiList.getData()[position].getNama());
        holder.mapel.setText(materiList.getData()[position].getMapel());
        holder.kelas.setText("Kelas : " + materiList.getData()[position].getKelas());
        holder.jurusan.setText("Jurusan : " + materiList.getData()[position].getJurusan());

        if (materiList.getData()[position].getFile1() == null || materiList.getData()[position].getFile1().equals("")) {
            holder.file1.setVisibility(View.GONE);
        } else {
            holder.file1.setText(materiList.getData()[position].getFile1());
            String url1 = "https://smai-ujian.xyz/smai-admin/upload/materi/" + materiList.getData()[position].getFile1();
            holder.file1.setOnClickListener(view -> {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url1));
                String title = URLUtil.guessFileName(url1, null, null);
                request.setTitle(title);
                request.setDescription("Dowloading File......");
                String cookie = CookieManager.getInstance().getCookie(url1);
                request.addRequestHeader("cookie", cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                Toast.makeText(context, "Download Started", Toast.LENGTH_LONG).show();
            });
        }

        if (materiList.getData()[position].getFile2() == null || materiList.getData()[position].getFile2().equals("")) {
            holder.file2.setVisibility(View.GONE);
        } else {
            holder.file2.setText(materiList.getData()[position].getFile2());
            String url2 = "https://ujian-smai.000webhostapp.com/upload/materi/" + materiList.getData()[position].getFile2();
            holder.file2.setOnClickListener(view -> {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url2));
                String title = URLUtil.guessFileName(url2, null, null);
                request.setTitle(title);
                request.setDescription("Dowloading File......");
                String cookie = CookieManager.getInstance().getCookie(url2);
                request.addRequestHeader("cookie", cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                Toast.makeText(context, "Download Started", Toast.LENGTH_LONG).show();
            });
        }

        if (materiList.getData()[position].getFile3() == null || materiList.getData()[position].getFile3().equals("")) {
            holder.file3.setVisibility(View.GONE);
        } else {
            holder.file3.setText(materiList.getData()[position].getFile3());
            String url3 = "https://ujian-smai.000webhostapp.com/upload/materi/" + materiList.getData()[position].getFile3();
            holder.file3.setOnClickListener(view -> {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url3));
                String title = URLUtil.guessFileName(url3, null, null);
                request.setTitle(title);
                request.setDescription("Dowloading File......");
                String cookie = CookieManager.getInstance().getCookie(url3);
                request.addRequestHeader("cookie", cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                Toast.makeText(context, "Download Started", Toast.LENGTH_LONG).show();
            });
        }

    }

    @Override
    public int getItemCount() {
        return materiList.getData().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView guru, mapel, kelas, jurusan, file1, file2, file3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guru = itemView.findViewById(R.id.tvGuru);
            mapel = itemView.findViewById(R.id.tvMapel);
            kelas = itemView.findViewById(R.id.tvKelas);
            jurusan = itemView.findViewById(R.id.tvJurusan);
            file1 = itemView.findViewById(R.id.btnFile1);
            file2 = itemView.findViewById(R.id.btnFile2);
            file3 = itemView.findViewById(R.id.btnFile3);
        }
    }
}
