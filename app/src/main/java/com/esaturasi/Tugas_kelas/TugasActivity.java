package com.esaturasi.Tugas_kelas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.esaturasi.Model.ApiResponse;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;
import com.esaturasi.Model.Task;
import com.esaturasi.R;
import java.util.List;
import Adapter.TaskAdapter;
import Adapter.TasksPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TugasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TasksPagerAdapter tasksPagerAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas); // Pastikan layout Anda sesuai

        // Inisialisasi ViewPager2 dan TabLayout
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        // Set TasksPagerAdapter ke ViewPager2
        tasksPagerAdapter = new TasksPagerAdapter(this);
        viewPager.setAdapter(tasksPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Semua Tugas");
                    break;
                case 1:
                    tab.setText("Tugas Belum Dikerjakan");
                    break;
                case 2:
                    tab.setText("Tugas Sudah Dikirim");
                    break;
                case 3:
                    tab.setText("Tugas Terlambat");
                    break;
            }
        }).attach();

        // Ambil Kode Kelas dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String kdKelas = sharedPreferences.getString("kd_kelas", null);

        if (kdKelas == null || kdKelas.isEmpty()) {
            Toast.makeText(this, "Kode Kelas tidak ditemukan. Silakan login ulang.", Toast.LENGTH_SHORT).show();
            finish(); // Tutup activity jika Kode Kelas tidak ada
            return;
        }

        // Panggil API untuk mengambil tugas
        loadTasks(kdKelas);
    }

    private void loadTasks(String kdKelas) {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<ApiResponse<List<Task>>> call = apiService.getTugas(kdKelas);

        call.enqueue(new Callback<ApiResponse<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Task>>> call, Response<ApiResponse<List<Task>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<Task>> apiResponse = response.body();

                    // Ambil data tugas dari response
                    List<Task> tasks = apiResponse.getData();

                    // Debugging log untuk memeriksa data tugas yang diterima
                    Log.d("TugasActivity", "Jumlah tugas yang diterima: " + (tasks != null ? tasks.size() : 0));

                    if (tasks == null || tasks.isEmpty()) {
                        // Tugas kosong atau null
                        Toast.makeText(TugasActivity.this, "Tidak ada tugas yang tersedia", Toast.LENGTH_SHORT).show();
                    } else {
                        // Menampilkan data tugas di RecyclerView
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TugasActivity.this));

                        // Mengupdate adapter dengan data tugas
                        taskAdapter = new TaskAdapter(tasks);
                        recyclerView.setAdapter(taskAdapter);
                    }
                } else {
                    // Menangani kesalahan ketika respons API tidak berhasil
                    Toast.makeText(TugasActivity.this, "Gagal memuat data tugas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Task>>> call, Throwable t) {
                // Menangani kesalahan koneksi
                Log.e("TugasActivity", "Kesalahan koneksi: " + t.getMessage());
                Toast.makeText(TugasActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

