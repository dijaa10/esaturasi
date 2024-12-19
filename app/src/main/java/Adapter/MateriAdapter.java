package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.Materi;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriViewHolder> {
    private List<Materi> materiList;
    private OnItemClickListener listener;

    // Konstruktor default
    public MateriAdapter() {
        this.materiList = new ArrayList<>();
    }

    // Konstruktor dengan list awal
    public MateriAdapter(List<Materi> materiList) {
        this.materiList = materiList != null ? materiList : new ArrayList<>();
    }

    // Interface untuk click listener
    public interface OnItemClickListener {
        void onItemClick(Materi materi);
    }

    // Setter untuk click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MateriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_isibab, parent, false);
        return new MateriViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriViewHolder holder, int position) {
        Materi materi = materiList.get(position);

        // Tampilkan judul atau file materi dengan penanganan null
        if (materi != null) {
            String displayText = "Materi tidak tersedia";

            // Prioritaskan menampilkan file materi
            if (materi.getFileMateri() != null && !materi.getFileMateri().isEmpty()) {
                displayText = materi.getFileMateri(); // Menampilkan nama file materi
            }

            holder.fileMateri.setText(displayText);
        }
    }

    @Override
    public int getItemCount() {
        return materiList != null ? materiList.size() : 0;
    }

    // Metode untuk memperbarui seluruh list
    public void updateMateriList(List<Materi> newMateriList) {
        if (newMateriList != null) {
            materiList.clear();
            materiList.addAll(newMateriList);
            notifyDataSetChanged();
        }
    }

    // Metode untuk menambahkan item individual
    public void addMateri(Materi materi) {
        if (materi != null) {
            materiList.add(materi);
            notifyItemInserted(materiList.size() - 1);
        }
    }

    // Metode untuk mendapatkan item berdasarkan posisi
    public Materi getItem(int position) {
        return (materiList != null && position >= 0 && position < materiList.size())
                ? materiList.get(position)
                : null;
    }

    public static class MateriViewHolder extends RecyclerView.ViewHolder {
        TextView fileMateri;
        ImageView ivMateri;  // Menambahkan ImageView untuk materi yang memiliki gambar (misalnya ikon atau preview)

        public MateriViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            fileMateri = itemView.findViewById(R.id.tvfileMateri);
            ivMateri = itemView.findViewById(R.id.ivMateri); // Menghubungkan ImageView dengan layout yang sesuai

            // Setup click listener
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(((MateriAdapter)((RecyclerView)itemView.getParent()).getAdapter()).getItem(position));
                    }
                }
            });
        }
    }

    // Metode untuk mendapatkan seluruh list
    public List<Materi> getMateriList() {
        return new ArrayList<>(materiList);
    }
}
