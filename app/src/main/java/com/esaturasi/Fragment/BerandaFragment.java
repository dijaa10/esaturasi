package com.esaturasi.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.API.ApiService;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.ScheduleItem;
import com.esaturasi.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapter.JadwalAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private JadwalAdapter jadwalAdapter;
    private List<ScheduleItem> scheduleList = new ArrayList<>();
    private SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_halaman, container, false);

        // Inisialisasi SharedPreferences
        prefs = requireActivity().getSharedPreferences("UserSession", requireContext().MODE_PRIVATE);


        String nama = prefs.getString("nama_siswa", "Nama tidak ditemukan");
        String nisn = prefs.getString("nisn", "NISN tidak ditemukan");
        String kdKelas = prefs.getString("kd_kelas", null);

        // Bind data profil ke layout
        bindProfileData(view, nama, nisn, kdKelas);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.recycler_viewhalaman);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jadwalAdapter = new JadwalAdapter(scheduleList);
        recyclerView.setAdapter(jadwalAdapter);


        if (kdKelas != null) {
            loadJadwal(kdKelas);
        } else {
            Toast.makeText(requireContext(), "Kode kelas tidak ditemukan", Toast.LENGTH_SHORT).show();
        }


        setTodayDate(view);


        setUpButtonListeners(view);

        return view;
    }

    private void bindProfileData(View view, String nama, String nisn, String kdKelas) {

        TextView namaTextView = view.findViewById(R.id.namaTextView);
        TextView nisnTextView = view.findViewById(R.id.nisnTextView);
        TextView kelasTextView = view.findViewById(R.id.kelasTextView);

        namaTextView.setText(nama);
        nisnTextView.setText(nisn);
        kelasTextView.setText(kdKelas != null ? kdKelas : "Kelas tidak ditemukan");
    }

    private void loadJadwal(String kdKelas) {
        // Konfigurasi Retrofit untuk API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/esaturasi_web/") // Ganti URL sesuai dengan API Anda
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);


        String hari = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date());

        // Debugging log untuk memastikan data yang dikirim
        Log.d("DEBUG_API_CALL", "Mengirimkan kode kelas: " + kdKelas + ", Hari: " + hari);


        apiService.getJadwal(kdKelas, hari).enqueue(new Callback<ApiResponse<List<ScheduleItem>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ScheduleItem>>> call, Response<ApiResponse<List<ScheduleItem>>> response) {

                Log.d("DEBUG_RESPONSE", "Response: " + new Gson().toJson(response.body()));

                if (response.isSuccessful() && response.body() != null && "success".equals(response.body().getStatus())) {
                    List<ScheduleItem> data = response.body().getData();


                    Log.d("DEBUG_API_RESPONSE", "Jumlah data jadwal yang diterima: " + (data != null ? data.size() : 0));

                    if (data != null && !data.isEmpty()) {
                        scheduleList.clear();
                        scheduleList.addAll(data);
                        jadwalAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("DEBUG_API_RESPONSE", "Tidak ada data jadwal untuk kelas dan hari ini");
                        Toast.makeText(requireContext(), "Tidak ada data jadwal untuk hari ini", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("DEBUG_API_ERROR", "Gagal memuat jadwal");
                    Toast.makeText(requireContext(), "Gagal memuat jadwal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<ScheduleItem>>> call, Throwable t) {
                Log.e("DEBUG_API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(requireContext(), "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTodayDate(View view) {
        TextView textViewDate = view.findViewById(R.id.textViewDate);

        // Format tanggal hari ini
        String currentDate = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(new Date());

        // Set tanggal ke TextView
        textViewDate.setText(currentDate);
    }

    private void setUpButtonListeners(View view) {

        ImageView jadwalImageView = view.findViewById(R.id.jadwal);
        jadwalImageView.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.esaturasi.Jadwal_kelas.JadwalActivity.class);
            startActivity(intent);
        });


        ImageView tugasImageView = view.findViewById(R.id.tugas);
        tugasImageView.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.esaturasi.Tugas_kelas.TugasActivity.class);
            startActivity(intent);
        });


        ImageView mapelImageview = view.findViewById(R.id.mapel);
        mapelImageview.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.esaturasi.Mata_Pelajaran.MapelActivity.class);
            startActivity(intent);
        });


        ImageView kalenderImageview = view.findViewById(R.id.kalender);
        kalenderImageview.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.esaturasi.Calender.KalenderActivity.class);
            startActivity(intent);
        });
    }


    public void jadwal(View view) {
        Intent intent = new Intent(requireContext(), com.esaturasi.Jadwal_kelas.JadwalActivity.class);
        startActivity(intent);
    }

    public void tugas(View view) {
        Intent intent = new Intent(requireContext(), com.esaturasi.Tugas_kelas.TugasActivity.class);
        startActivity(intent);
    }

    public void mapel(View view) {
        Intent intent = new Intent(requireContext(), com.esaturasi.Mata_Pelajaran.MapelActivity.class);
        startActivity(intent);
    }

    public void kalender(View view) {
        Intent intent = new Intent(requireContext(), com.esaturasi.Calender.KalenderActivity.class);
        startActivity(intent);
    }
}
