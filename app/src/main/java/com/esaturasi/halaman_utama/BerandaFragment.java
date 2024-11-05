package com.esaturasi.halaman_utama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Adapter.JadwalAdapter;
import com.esaturasi.Model.ItemJadwal;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private JadwalAdapter jadwalAdapter;
    private List<ItemJadwal> jadwalList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Menginflate layout fragment_halaman
        View view = inflater.inflate(R.layout.fragment_halaman, container, false);

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Mengisi data jadwal
        jadwalList = new ArrayList<>();
        jadwalList.add(new ItemJadwal(R.drawable.dija,R.drawable.ic_bahasa,"Bahasa Indonesia", "07.00-08.30 WIB", "Chodijah", "Guru"));
        jadwalList.add(new ItemJadwal(R.drawable.dijaa,R.drawable.ic_sejarah,"Sejarah Indonesia", "08.30-11.30 WIB", "Dija", "Guru"));
        jadwalList.add(new ItemJadwal(R.drawable.dija,R.drawable.ic_bahasa,"Bahasa Indonesia", "07.00-08.30 WIB", "Chodijah", "Guru"));
        jadwalList.add(new ItemJadwal(R.drawable.dijaa,R.drawable.ic_sejarah,"Sejarah Indonesia", "08.30-11.30 WIB", "Dija", "Guru"));

        jadwalAdapter = new JadwalAdapter(jadwalList);
        recyclerView.setAdapter(jadwalAdapter);

        return view;
    }
}
