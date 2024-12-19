package Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.BabModel;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.BabViewHolder> {
    private List<BabModel> babList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BabModel bab);
    }

    public BabAdapter(List<BabModel> babList, OnItemClickListener listener) {
        this.babList = babList != null ? babList : new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public BabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itembab, parent, false);
        return new BabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BabViewHolder holder, int position) {
        BabModel bab = babList.get(position);
        Log.d("BabAdapter", "Binding Bab: " + bab.getNamaBab());
        holder.namaBab.setText(bab.getNamaBab());
        holder.judulBab.setText(bab.getJudulBab());

        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClick(bab));
        }
    }

    @Override
    public int getItemCount() {
        return babList.size();
    }

    public static class BabViewHolder extends RecyclerView.ViewHolder {
        TextView namaBab, judulBab;

        public BabViewHolder(View itemView) {
            super(itemView);
            namaBab = itemView.findViewById(R.id.nama_bab);
            judulBab = itemView.findViewById(R.id.judul_bab);
        }
    }

    public void updateBabList(List<BabModel> newBabList) {
        if (newBabList != null) {
            babList.clear();
            babList.addAll(newBabList);
            notifyDataSetChanged();
        } else {
            Log.d("BabAdapter", "New list is null or empty");
        }
    }

    public void addBab(BabModel bab) {
        if (bab != null && babList != null) {
            babList.add(bab);
            notifyItemInserted(babList.size() - 1);
        }
    }
}
