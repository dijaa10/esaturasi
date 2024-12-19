package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esaturasi.Model.MapelModel;
import com.esaturasi.R;
import com.esaturasi.UI.BabActivity;

import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.MapelViewHolder> {

    private Context context;
    private List<MapelModel> mapelList;
    private OnItemClickListener listener;

    public MapelAdapter(Context context, List<MapelModel> mapelList) {
        this.context = context;
        this.mapelList = mapelList;
    }

    @NonNull
    @Override
    public MapelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new MapelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapelViewHolder holder, int position) {
        MapelModel mapel = mapelList.get(position);

        // Set data to UI elements
        holder.namaMapel.setText(mapel.getNamaMapel());
        Glide.with(holder.itemView.getContext())
                .load(mapel.getFotoMapelPerkelas())
                .into(holder.fotoMapel);

        // Set listener untuk item klik
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(mapel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapelList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MapelModel mapel);
    }

    public static class MapelViewHolder extends RecyclerView.ViewHolder {
        TextView namaMapel;
        ImageView fotoMapel;

        public MapelViewHolder(@NonNull View itemView) {
            super(itemView);
            namaMapel = itemView.findViewById(R.id.subject_name);
            fotoMapel = itemView.findViewById(R.id.subject_image);
        }
    }
}
