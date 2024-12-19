package com.esaturasi.Tugas_kelas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.esaturasi.R;

public class DetailtugasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtugas);

        // Inisialisasi Views
        TextView tvSubjectTitle = findViewById(R.id.tvSubjectTitle);
        TextView tvTeacher = findViewById(R.id.tvTeacher);
        TextView tvStatus = findViewById(R.id.tvStatus);
        TextView tvDeadline = findViewById(R.id.tvDeadline);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView imgTaskPhoto = findViewById(R.id.imgTaskPhoto);
        TextView tvAnswer = findViewById(R.id.tvAnswer);
        Button btnMarkAsDone = findViewById(R.id.btnMarkAsDone);
        Button btnSubmitTask = findViewById(R.id.btnSubmitTask);

        // Ambil data dari Intent
        String taskId = getIntent().getStringExtra("task_id");
        String subject = getIntent().getStringExtra("subject");
        String deadline = getIntent().getStringExtra("deadline");
        String description = getIntent().getStringExtra("description");
        String photoPath = getIntent().getStringExtra("photoPath");

        tvSubjectTitle.setText(taskId != null ? taskId : "Tidak tersedia");
        tvTeacher.setText(subject != null ? subject : "Tidak tersedia");
        tvDeadline.setText(deadline != null ? deadline : "Tidak tersedia");
        tvDescription.setText(description != null ? description : "Deskripsi tidak tersedia");

        // Load foto tugas menggunakan Glide
        if (photoPath != null && !photoPath.isEmpty()) {
            Glide.with(this)
                    .load(photoPath)
                    .into(imgTaskPhoto);
        } else {
            imgTaskPhoto.setImageResource(R.drawable.ic_bahasa); // Gambar default jika photoPath kosong
        }
    }
}
