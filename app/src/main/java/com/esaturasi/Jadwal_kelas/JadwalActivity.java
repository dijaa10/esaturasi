package com.esaturasi.Jadwal_kelas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.esaturasi.API.ApiService;
import Adapter.DaysPagerAdapter;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.ScheduleItem;
import com.esaturasi.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalActivity extends AppCompatActivity {

    private List<List<ScheduleItem>> weeklySchedule = new ArrayList<>();
    private DaysPagerAdapter pagerAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Initialize empty data for all days (Senin - Jumat)
        for (int i = 0; i < 5; i++) {
            weeklySchedule.add(new ArrayList<>());
        }

        // Set up the adapter for ViewPager2
        pagerAdapter = new DaysPagerAdapter(this, weeklySchedule);
        viewPager.setAdapter(pagerAdapter);

        // Connect TabLayout with ViewPager2
        String[] days = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(days[position])).attach();

        // Load schedule data
        loadWeeklySchedule();
    }

    private void loadWeeklySchedule() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://esaturasi.my.id/") // Sesuaikan URL server Anda
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Retrieve kd_kelas from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String kdKelas = prefs.getString("kd_kelas", null);

        if (kdKelas == null) {
            Toast.makeText(this, "Kode kelas tidak ditemukan!", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] days = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};

        for (String currentDay : days) {
            Log.d("DEBUG_API_CALL", "KD_KELAS: " + kdKelas + ", HARI: " + currentDay);

            apiService.getJadwal(kdKelas, currentDay).enqueue(new Callback<ApiResponse<List<ScheduleItem>>>() {
                @Override
                public void onResponse(Call<ApiResponse<List<ScheduleItem>>> call, Response<ApiResponse<List<ScheduleItem>>> response) {
                    Log.d("DEBUG_RESPONSE", "Response untuk " + currentDay + ": " + new Gson().toJson(response.body()));

                    if (response.isSuccessful() && response.body() != null && "success".equals(response.body().getStatus())) {
                        List<ScheduleItem> daySchedule = response.body().getData();

                        if (daySchedule != null) {
                            int dayIndex = getDayIndex(currentDay);
                            if (dayIndex != -1) {
                                weeklySchedule.set(dayIndex, daySchedule);
                                pagerAdapter.notifyDataSetChanged();
                            }
                        }
                    } else {
                        Log.e("API_ERROR", "Gagal memuat jadwal untuk " + currentDay);
                        Toast.makeText(JadwalActivity.this, "Gagal memuat jadwal untuk " + currentDay, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<List<ScheduleItem>>> call, Throwable t) {
                    Log.e("API_FAILURE", "Kesalahan koneksi: " + t.getMessage());
                    Toast.makeText(JadwalActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private int getDayIndex(String day) {
        switch (day) {
            case "Senin": return 0;
            case "Selasa": return 1;
            case "Rabu": return 2;
            case "Kamis": return 3;
            case "Jumat": return 4;
            default: return -1; // Invalid day
        }
    }
}
