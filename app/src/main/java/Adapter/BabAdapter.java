package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.BabModel;
import com.esaturasi.R;

import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.BabViewHolder> {
    private Context context;
    private List<BabModel> babList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BabModel bab);
    }

    public BabAdapter(Context context, List<BabModel> babList, OnItemClickListener listener) {
        this.context = context;
        this.babList = babList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itembab, parent, false);
        return new BabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BabViewHolder holder, int position) {
        BabModel bab = babList.get(position);

        // Set namaBab dan judulBab
        holder.namaBab.setText(bab.getNamaBab());
        holder.judulBab.setText(bab.getJudulBab());

        // Set listener untuk item
        holder.itemView.setOnClickListener(v -> listener.onItemClick(bab));
    }

    @Override
    public int getItemCount() {
        return babList.size();
    }

    public static class BabViewHolder extends RecyclerView.ViewHolder {
        TextView namaBab, judulBab;

        public BabViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBab = itemView.findViewById(R.id.nama_bab);
            judulBab = itemView.findViewById(R.id.judul_bab);
        }
    }
}
