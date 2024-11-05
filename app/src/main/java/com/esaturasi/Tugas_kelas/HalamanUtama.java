package com.esaturasi.Tugas_kelas;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;

import com.esaturasi.R;

public class HalamanUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        // Set adapter untuk ViewPager2
        TasksPagerAdapter adapter = new TasksPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Sinkronkan ViewPager2 dengan TabLayout
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Semua");
                    break;
                case 1:
                    tab.setText("Belum Dikumpulkan");
                    break;
                case 2:
                    tab.setText("Sudah Dikumpulkan");
                    break;
                case 3:
                    tab.setText("Terlambat");
                    break;
            }
        }).attach();
    }
}