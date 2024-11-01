package com.esaturasi.Halaman_informasi;

import android.os.Bundle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


import com.esaturasi.R;

public class UtamaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Set adapter untuk ViewPager2
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        viewPager.setAdapter(sectionsPagerAdapter);

        // Menghubungkan TabLayout dan ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // Atur teks tab berdasarkan posisi
                switch (position) {
                    case 0:
                        tab.setText("Terbaru");
                        break;
                    case 1:
                        tab.setText("Informasi");
                        break;
                    case 2:
                        tab.setText("Prestasi");
                        break;
                }
            }
        }).attach();
    }
}