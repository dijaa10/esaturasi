package com.esaturasi.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import com.esaturasi.Model.Task;
import com.esaturasi.R;

import Adapter.TaskAdapter;


public class SubmittedTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Data contoh untuk ditampilkan
        taskList = new ArrayList<>();
        taskList.add(new Task("Latihan soal pythagoras", "Matematika", "25 Oktober 2024, 23.59 WIB", "Sudah dikumpulkan"));
        taskList.add(new Task("Latihan soal aljabar", "Matematika", "26 Oktober 2024, 23.59 WIB", "Sudah dikumpulkan"));
        taskList.add(new Task("Latihan soal fisika", "Fisika", "27 Oktober 2024, 23.59 WIB", "Sudah dikumpilkan"));

        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);

        return view;
    }

}
