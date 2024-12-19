package com.esaturasi.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.Materi;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MateriAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsiBabActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MateriAdapter materiAdapter;
    private List<Materi> materiList;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isibab);

        recyclerView = findViewById(R.id.recyclerViewMateri);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        materiList = new ArrayList<>();
        materiAdapter = new MateriAdapter(materiList);
        recyclerView.setAdapter(materiAdapter);

        apiService = ApiClient.getApi().create(ApiService.class);

        // Ambil data nama_mapel dan kd_bab dari Intent yang diterima
        Intent intent = getIntent();
        String namaMapel = intent.getStringExtra("nama_mapel");
        String kdBab = intent.getStringExtra("kd_bab");
        String namaBab = intent.getStringExtra("nama_bab");

        // Pastikan kedua parameter ada dan valid
        if (namaMapel != null && !namaMapel.isEmpty() && kdBab != null && !kdBab.isEmpty() && namaBab != null) {
            Log.d("IsiBabActivity", "nama_mapel: " + namaMapel + ", kd_bab: " + kdBab + ", nama_bab: " + namaBab);
            getMateri(namaMapel, kdBab, namaBab);
        } else {
            Toast.makeText(IsiBabActivity.this, "Data tidak lengkap", Toast.LENGTH_SHORT).show();
            Log.e("IsiBabActivity", "Nama Mapel, Kode Bab, atau Nama Bab tidak ditemukan");
        }

    }

    private void getMateri(String namaMapel, String kdBab, String namaBab) {
        // Menambahkan log untuk memastikan data yang dikirimkan
        Log.d("IsiBabActivity", "Memanggil API dengan nama_mapel: " + namaMapel + ", kd_bab: " + kdBab);

        apiService.getMateri(namaMapel, kdBab).enqueue(new Callback<ApiResponse<List<Materi>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Materi>>> call, Response<ApiResponse<List<Materi>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<Materi>> apiResponse = response.body();

                    // Mengecek apakah statusnya "success"
                    if ("success".equalsIgnoreCase(apiResponse.getStatus())) {
                        List<Materi> materiList = apiResponse.getData();

                        // Pastikan data materi tidak kosong
                        if (materiList != null && !materiList.isEmpty()) {
                            // Menampilkan log untuk debug
                            for (Materi materi : materiList) {
                                Log.d("IsiBabActivity", "File Materi: " + materi.getFileMateri());
                                Log.d("IsiBabActivity", "Kode Bab: " + materi.getKdBab());
                                Log.d("IsiBabActivity", "Nama Mapel: " + materi.getNamaMapel());
                                Log.d("IsiBabActivity", "Nama Bab: " + materi.getNamaBab());
                            }
                            // Memperbarui adapter dengan data materi
                            materiAdapter.updateMateriList(materiList);
                        } else {
                            Toast.makeText(IsiBabActivity.this, "Materi tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(IsiBabActivity.this, "Gagal mendapatkan materi. Status: " + apiResponse.getStatus(), Toast.LENGTH_LONG).show();
                        Log.e("IsiBabActivity", "Status API: " + apiResponse.getStatus());
                    }
                } else {
                    Toast.makeText(IsiBabActivity.this, "Gagal mendapatkan materi, server error", Toast.LENGTH_SHORT).show();
                    Log.e("IsiBabActivity", "Error response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Materi>>> call, Throwable t) {
                Toast.makeText(IsiBabActivity.this, "Terjadi kesalahan saat menghubungi server", Toast.LENGTH_SHORT).show();
                Log.e("IsiBabActivity", "Error: " + t.getMessage(), t); // Logging error message untuk debugging
            }
        });
    }
}
