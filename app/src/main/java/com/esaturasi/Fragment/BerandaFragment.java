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
import com.bumptech.glide.Glide;

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
        prefs = requireActivity().getSharedPreferences("UserSession", requireContext().MODE_PRIVATE);

        String nama = prefs.getString("nama_siswa", "Nama tidak ditemukan");
        String nisn = prefs.getString("nisn", "NISN tidak ditemukan");
        String kdKelas = prefs.getString("kd_kelas", null);
        String fotoProfilSiswa = prefs.getString("foto_profil_siswa", null);

        bindProfileData(view, nama, nisn, kdKelas, fotoProfilSiswa);

        recyclerView = view.findViewById(R.id.recycler_viewhalaman);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jadwalAdapter = new JadwalAdapter(scheduleList);
        recyclerView.setAdapter(jadwalAdapter);

        if (kdKelas != null) {
            String hari = new SimpleDateFormat("EEEE", new Locale("id", "ID")).format(new Date());
            loadJadwal(kdKelas, hari);
            Log.d("JADWAL_DEBUG", "Format Hari: " + hari);
        } else {
            Toast.makeText(requireContext(), "Kode kelas tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        setTodayDate(view);
        setUpButtonListeners(view);

        return view;
    }

    private void bindProfileData(View view, String nama, String nisn, String kdKelas, String fotoProfilSiswa) {
        TextView namaTextView = view.findViewById(R.id.namaTextView);
        TextView nisnTextView = view.findViewById(R.id.nisnTextView);
        TextView kelasTextView = view.findViewById(R.id.kelasTextView);
        ImageView fotoProfilImageView = view.findViewById(R.id.fotoProfilImageView);

        namaTextView.setText(nama);
        nisnTextView.setText(nisn);
        kelasTextView.setText(kdKelas != null ? kdKelas : "Kelas tidak ditemukan");

        if (fotoProfilSiswa != null && !fotoProfilSiswa.isEmpty()) {
            Glide.with(requireContext())
                    .load(fotoProfilSiswa)
                    .into(fotoProfilImageView);
        }
    }

    private void loadJadwal(String kdKelas, String hari) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/esaturasi_web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Log.d("DEBUG_API_CALL", "KD_KELAS: " + kdKelas + ", Hari: " + hari);

        apiService.getJadwal(kdKelas, hari).enqueue(new Callback<ApiResponse<List<ScheduleItem>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ScheduleItem>>> call, Response<ApiResponse<List<ScheduleItem>>> response) {
                if (response.isSuccessful() && response.body() != null && "success".equals(response.body().getStatus())) {
                    List<ScheduleItem> data = response.body().getData();
                    Log.d("DEBUG_API_RESPONSE", "Jumlah data jadwal yang diterima: " + (data != null ? data.size() : 0));
                    if (data != null && !data.isEmpty()) {
                        scheduleList.clear();
                        scheduleList.addAll(data);
                        jadwalAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("DEBUG_API_RESPONSE", "Tidak ada data jadwal untuk hari ini");
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
        String currentDate = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(new Date());
        textViewDate.setText(currentDate);
    }

    private void setUpButtonListeners(View view) {
        setupButtonListener(view, R.id.jadwal, com.esaturasi.Jadwal_kelas.JadwalActivity.class);
        setupButtonListener(view, R.id.tugas, com.esaturasi.Tugas_kelas.TugasActivity.class);
        setupButtonListener(view, R.id.mapel, com.esaturasi.Mata_Pelajaran.MapelActivity.class);
        setupButtonListener(view, R.id.kalender, com.esaturasi.Calender.KalenderActivity.class);
    }

    private void setupButtonListener(View view, int buttonId, Class<?> activityClass) {
        ImageView button = view.findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), activityClass);
            startActivity(intent);
        });
    }
}
