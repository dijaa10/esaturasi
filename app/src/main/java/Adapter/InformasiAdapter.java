package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esaturasi.Model.InformasiModel;
import com.esaturasi.R;

import java.util.List;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.ViewHolder> {

    private List<InformasiModel> informasiList;

    public InformasiAdapter(List<InformasiModel> informasiList) {
        this.informasiList = informasiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InformasiModel informasi = informasiList.get(position);

        holder.judul.setText(informasi.getJudul());
        holder.tanggal.setText(informasi.getTanggal());
        holder.deskripsi.setText(informasi.getDeskripsi());

        // Load gambar menggunakan Glide
        if (informasi.getGambar() != null && !informasi.getGambar().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(informasi.getGambar())
                    .placeholder(R.drawable.ic_sample1)
                    .error(R.drawable.ic_sample1)
                    .into(holder.gambar);
        } else {
            holder.gambar.setImageResource(R.drawable.ic_sample1);
        }
    }

    @Override
    public int getItemCount() {
        return informasiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, tanggal, deskripsi;
        ImageView gambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul_pengumuman);
            tanggal = itemView.findViewById(R.id.tgl_pengumuman);
            deskripsi = itemView.findViewById(R.id.deskripsi_pengumuman);
            gambar = itemView.findViewById(R.id.file_pengumuman);
        }
    }
}
