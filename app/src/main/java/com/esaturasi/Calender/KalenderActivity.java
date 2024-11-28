package com.esaturasi.Calender;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esaturasi.MainActivity;
import com.esaturasi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Adapter.CalendarAdapter;

public class KalenderActivity extends AppCompatActivity {

    private TextView monthName;
    private GridView gridCalendar;
    private Calendar currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);


        monthName = findViewById(R.id.month_name);
        gridCalendar = findViewById(R.id.grid_calendar);
        ImageButton prevButton = findViewById(R.id.prev_button);
        ImageButton nextButton = findViewById(R.id.next_button);

        // Set tanggal
        currentDate = Calendar.getInstance();

        // Menampilkan bulan dan tahun
        updateMonthName();
        updateCalendar();

        // Navigasi bulan sebelumnya
        prevButton.setOnClickListener(v -> {
            currentDate.add(Calendar.MONTH, -1);
            updateMonthName();
            updateCalendar();
        });

        // Navigasi bulan berikutnya
        nextButton.setOnClickListener(v -> {
            currentDate.add(Calendar.MONTH, 1);
            updateMonthName();
            updateCalendar();
        });
    }

    // Memperbarui nama bulan dan tahun
    private void updateMonthName() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        monthName.setText(sdf.format(currentDate.getTime()));
    }

    private void updateCalendar() {
        Calendar calendar = (Calendar) currentDate.clone();

        // Menentukan hari pertama pada bulan ini
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

        // Menentukan jumlah hari dalam bulan ini
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Mendapatkan tanggal hari ini
        Calendar today = Calendar.getInstance();
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH);
        int currentYear = today.get(Calendar.YEAR);

        // Array untuk menyimpan angka tanggal dalam grid
        String[] days = new String[42]; // 6 minggu x 7 hari
        boolean[] isToday = new boolean[42];

        // Mengisi hari pertama di grid
        int dayCounter = 1;
        for (int i = firstDayOfMonth - 1; i < 42; i++) {
            if (dayCounter <= daysInMonth) {
                days[i] = String.valueOf(dayCounter);

                // Tandai apakah tanggal tersebut adalah hari ini
                if (dayCounter == currentDay && currentMonth == calendar.get(Calendar.MONTH) && currentYear == calendar.get(Calendar.YEAR)) {
                    isToday[i] = true;
                } else {
                    isToday[i] = false;
                }
                dayCounter++;
            } else {
                days[i] = ""; // Kosongkan sisa grid
                isToday[i] = false;
            }
        }

        // Membuat adapter untuk grid dan menghubungkan dengan GridView
        CalendarAdapter adapter = new CalendarAdapter(this, days, isToday);
        gridCalendar.setAdapter(adapter);


        gridCalendar.setOnItemClickListener((parent, view, position, id) -> {
            String day = days[position];
            if (!day.isEmpty()) {
                Toast.makeText(KalenderActivity.this, "Tanggal dipilih: " + day, Toast.LENGTH_SHORT).show();
            }
        });
    }

}