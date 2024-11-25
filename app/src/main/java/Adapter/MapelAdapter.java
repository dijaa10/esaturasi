package Adapter;
import android.content.Context;
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

import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.MapelViewHolder> {
    private Context context;
    private List<MapelModel> mapelList;

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

        // Set nama mapel
        holder.namaMapel.setText(mapel.getNamaMapel());

        // Load gambar menggunakan Glide
        String imageUrl = "http://10.0.2.2/esaturasi_web/images/" + mapel.getFotoMapelPerkelas();
        Glide.with(context).load(imageUrl).into(holder.fotoMapel);
    }

    @Override
    public int getItemCount() {
        return mapelList.size();
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
