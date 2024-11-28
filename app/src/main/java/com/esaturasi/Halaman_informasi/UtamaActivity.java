package com.esaturasi.Halaman_informasi;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import Adapter.SectionsPagerAdapter;
import com.esaturasi.R;

public class UtamaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_informasi); // Pastikan layout yang benar

        // Inisialisasi ViewPager2 dan TabLayout
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Set adapter untuk ViewPager2
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Menghubungkan TabLayout dengan ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Terbaru");
                            break;
                        case 1:
                            tab.setText("Informasi");
                            break;
                    }
                }).attach();
    }
}
