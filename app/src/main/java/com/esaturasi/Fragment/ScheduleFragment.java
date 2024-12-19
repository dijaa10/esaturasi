package com.esaturasi.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.ScheduleItem;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.JadwalAdapter;
public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private JadwalAdapter jadwalAdapter;
    private List<ScheduleItem> scheduleList;

    // Constructor menerima data jadwal
    public ScheduleFragment(List<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList != null ? scheduleList : new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        jadwalAdapter = new JadwalAdapter(scheduleList);
        recyclerView.setAdapter(jadwalAdapter);

        return view;
    }

    // Method untuk memperbarui data dan memberi tahu adapter
    public void setScheduleList(List<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList;
        if (jadwalAdapter != null) {
            jadwalAdapter.notifyDataSetChanged();  // Memberi tahu adapter agar memperbarui tampilan
        }
    }
}
