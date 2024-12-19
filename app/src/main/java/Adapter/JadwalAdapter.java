package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esaturasi.Model.ScheduleItem;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private List<ScheduleItem> scheduleList;

    public JadwalAdapter(List<ScheduleItem> scheduleList) {
        this.scheduleList = (scheduleList != null) ? scheduleList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item untuk setiap item jadwal
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleItem item = scheduleList.get(position);

        // Set data dari model ke UI
        holder.subjectTextView.setText(item.getNamaMapel());
        holder.timeTextView.setText(item.getDariJam() + " - " + item.getSampaiJam());
        holder.teacherNameTextView.setText(item.getNamaGuru());


        Glide.with(holder.itemView.getContext())
                .load( item.getFotoProfilGuru())
                .into(holder.teacherImageView);


        Glide.with(holder.itemView.getContext())
                .load(item.getFotoMapelPerkelas())
                .into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectTextView, timeTextView, teacherNameTextView;
        ImageView teacherImageView, iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisialisasi elemen UI dari layout item_schedule
            subjectTextView = itemView.findViewById(R.id.tv_subject);
            timeTextView = itemView.findViewById(R.id.tv_time);
            teacherNameTextView = itemView.findViewById(R.id.tv_teacher_name);
            teacherImageView = itemView.findViewById(R.id.iv_teacher_photo);
            iconImageView = itemView.findViewById(R.id.iv_book_cover); // Pastikan ID ini ada di layout
        }
    }
}
