package com.esaturasi.Tugas_kelas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTitle.setText(task.getTitle());
        holder.taskSubject.setText(task.getSubject());
        holder.taskDeadline.setText(task.getDeadline());
        holder.taskStatus.setText(task.getStatus());
        // Set an image or icon for taskImage if needed
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle, taskSubject, taskDeadline, taskStatus, taskDetail;
        ImageView taskImage;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskSubject = itemView.findViewById(R.id.taskSubject);
            taskDeadline = itemView.findViewById(R.id.taskDeadline);
            taskStatus = itemView.findViewById(R.id.taskStatus);
            taskDetail = itemView.findViewById(R.id.taskDetail);
            taskImage = itemView.findViewById(R.id.taskImage);
        }
    }
}
