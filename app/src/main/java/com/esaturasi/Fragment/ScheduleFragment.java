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

import java.util.List;

import Adapter.JadwalAdapter;

public class ScheduleFragment extends Fragment {

    private List<ScheduleItem> scheduleList;

    public ScheduleFragment(List<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        JadwalAdapter adapter = new JadwalAdapter(scheduleList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}

