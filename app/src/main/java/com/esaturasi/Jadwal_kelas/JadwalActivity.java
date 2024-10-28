package com.esaturasi.Jadwal_kelas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esaturasi.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Fungsi.ScheduleItem;

public class JadwalActivity extends AppCompatActivity {

    private TextView tabSenin, tabSelasa, tabRabu, tabKamis, tabJumat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal); // Pastikan sesuai dengan nama layout Anda

        // Inisialisasi TextView untuk setiap hari
        tabSenin = findViewById(R.id.tab_senin);
        tabSelasa = findViewById(R.id.tab_selasa);
        tabRabu = findViewById(R.id.tab_rabu);
        tabKamis = findViewById(R.id.tab_kamis);
        tabJumat = findViewById(R.id.tab_jumat);

        // Set onClickListener untuk setiap TextView hari
        tabSenin.setOnClickListener(v -> {
            displayScheduleForDay("Senin");
            highlightSelectedDay(tabSenin);
        });

        tabSelasa.setOnClickListener(v -> {
            displayScheduleForDay("Selasa");
            highlightSelectedDay(tabSelasa);
        });

        tabRabu.setOnClickListener(v -> {
            displayScheduleForDay("Rabu");
            highlightSelectedDay(tabRabu);
        });

        tabKamis.setOnClickListener(v -> {
            displayScheduleForDay("Kamis");
            highlightSelectedDay(tabKamis);
        });

        tabJumat.setOnClickListener(v -> {
            displayScheduleForDay("Jumat");
            highlightSelectedDay(tabJumat);
        });

        // Default tampilan awal untuk hari Senin
        displayScheduleForDay("Senin");
        highlightSelectedDay(tabSenin);
    }

    private void displayScheduleForDay(String day) {
        LinearLayout scheduleContainer = findViewById(R.id.schedule_container);
        scheduleContainer.removeAllViews(); // Bersihkan tampilan sebelumnya

        List<ScheduleItem> scheduleForDay = getScheduleForDay(day);

        for (ScheduleItem item : scheduleForDay) {
            View scheduleView = LayoutInflater.from(this).inflate(R.layout.item_schedule, scheduleContainer, false);

            TextView subjectName = scheduleView.findViewById(R.id.subject_name);
            TextView time = scheduleView.findViewById(R.id.subject_time);
            TextView teacher = scheduleView.findViewById(R.id.subject_teacher);
            ImageView profileImage = scheduleView.findViewById(R.id.profile_image);
            ImageView materialImage = scheduleView.findViewById(R.id.material_image);

            // Isi data ke tampilan
            subjectName.setText(item.getSubjectName());
            time.setText(item.getTime());
            teacher.setText(item.getTeacher());
            profileImage.setImageResource(item.getProfileImageResId());
            materialImage.setImageResource(item.getMaterialImageResId());

            scheduleContainer.addView(scheduleView);
        }
    }

    // Fungsi untuk mendapatkan jadwal sesuai hari
    private List<ScheduleItem> getScheduleForDay(String day) {
        switch (day) {
            case "Senin":
                return Arrays.asList(
                        new ScheduleItem("Bahasa Indonesia", "07.00 - 08.30", "Chodijah", R.drawable.ic_profildija, R.drawable.ic_bahasa),
                        new ScheduleItem("Matematika", "10.00 - 11.30", "Gilang Bayu", R.drawable.ic_person, R.drawable.ic_mtk)
                );
            case "Selasa":
                return Arrays.asList(
                        new ScheduleItem("Pemrograman Web", "08.00 - 09.30", "Bachtiar", R.drawable.ic_person, R.drawable.ic_pemrograman),
                        new ScheduleItem("Beladiri", "10.00 - 11.30", "Dwi Srikandi", R.drawable.ic_profildwi, R.drawable.ic_beladiri)
                );
            case "Rabu":
                return Arrays.asList(
                        new ScheduleItem("Sejarah Indonesia", "08.00 - 09.30", "Naela Zahwa", R.drawable.ic_profilnaela, R.drawable.ic_sejarah)
                );
            // Tambahkan data jadwal untuk hari lainnya
            default:
                return new ArrayList<>();
        }
    }

    // Fungsi untuk menyorot hari yang dipilih
    private void highlightSelectedDay(TextView selectedTab) {
        // Reset semua tab menjadi warna default (putih)
        tabSenin.setTextColor(Color.BLACK);
        tabSelasa.setTextColor(Color.BLACK);
        tabRabu.setTextColor(Color.BLACK);
        tabKamis.setTextColor(Color.BLACK);
        tabJumat.setTextColor(Color.BLACK);

        // Sorot tab yang dipilih dengan warna biru
        selectedTab.setTextColor(Color.WHITE);
    }
}
