
package com.esaturasi;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.esaturasi.Fragment.BerandaFragment;
import com.esaturasi.Fragment.InformasiFragment;
import com.esaturasi.Fragment.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                // untuk menentukan fragment yang akan dimuat
                if (itemId == R.id.nav_beranda) {
                    loadFragment(new BerandaFragment());
                    return true;
                } else if (itemId == R.id.nav_informasi) {
                    loadFragment(new InformasiFragment());
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    loadFragment(new ProfilFragment());
                    return true;
                }
                return false; // Jika tidak ada item yang cocok
            }
        });

        if (savedInstanceState == null) {
            loadFragment(new BerandaFragment()); // fragment Home saat pertama kali dibuka
        }
    }
    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment) // ID dari container yang ada di layout
                .commit();
    }
}
