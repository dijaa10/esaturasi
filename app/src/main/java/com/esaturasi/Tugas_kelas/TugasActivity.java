package com.esaturasi.Tugas_kelas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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

        // Sinkronisasi TabLayout dengan ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("All Tasks");
                    break;
                case 1:
                    tab.setText("Pending Tasks");
                    break;
                case 2:
                    tab.setText("Submitted Tasks");
                    break;
                case 3:
                    tab.setText("Late Tasks");
                    break;
            }
        }).attach();

        // Ambil NISN dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String nisn = sharedPreferences.getString("nisn", null);

        if (nisn == null || nisn.isEmpty()) {
            Toast.makeText(this, "NISN tidak ditemukan. Silakan login ulang.", Toast.LENGTH_SHORT).show();
            finish(); // Tutup activity jika NISN tidak ada
            return;
        }

        // Panggil API untuk mengambil tugas
        loadTasks(nisn);
    }

    private void loadTasks(String nisn) {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<List<Task>> call = apiService.getTasks(nisn);

        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Task> tasks = response.body();

                    if (tasks.isEmpty()) {
                        Toast.makeText(TugasActivity.this, "Tidak ada tugas yang tersedia", Toast.LENGTH_SHORT).show();
                    } else {
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TugasActivity.this));
                        taskAdapter = new TaskAdapter(tasks);
                        recyclerView.setAdapter(taskAdapter);
                    }
                } else {
                    Toast.makeText(TugasActivity.this, "Gagal memuat data tugas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Toast.makeText(TugasActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
