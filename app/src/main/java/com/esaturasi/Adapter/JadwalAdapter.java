package com.esaturasi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.ItemJadwal;
import com.esaturasi.R;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ScheduleViewHolder> {

    private List<ItemJadwal> jadwalList;

    // ViewHolder untuk item jadwal
    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, name, role;
        public ImageView img1, img2;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judul);
            time = itemView.findViewById(R.id.jam);
            name = itemView.findViewById(R.id.nama);
            role = itemView.findViewById(R.id.role);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }

    // Konstruktor untuk adapter yang menerima list jadwal
    public JadwalAdapter(List<ItemJadwal> jadwalList) {
        this.jadwalList = jadwalList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Meng-inflate layout item_jadwal
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ItemJadwal schedule = jadwalList.get(position);
        // Mengisi data ke dalam tampilan
        holder.img1.setImageResource(schedule.getImg1()); // Menetapkan gambar untuk img1
        holder.img2.setImageResource(schedule.getImg2()); // Menetapkan gambar untuk img2
        holder.title.setText(schedule.getJudul());
        holder.time.setText(schedule.getJam());
        holder.name.setText(schedule.getNama());
        holder.role.setText(schedule.getRole());
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }
}
