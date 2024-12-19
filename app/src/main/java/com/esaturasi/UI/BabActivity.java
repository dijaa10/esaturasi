package com.esaturasi.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.BabModel;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BabAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BabActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BabAdapter babAdapter;
    private List<BabModel> babList = new ArrayList<>();

    private static final String BASE_URL = "http://10.0.2.2/esaturasi_web/";

    interface ApiService {
        @GET("page/api/get_bab.php")
        Call<ApiResponse<List<BabModel>>> getBabsByMapel(@Query("nama_mapel") String mapel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bab);

        recyclerView = findViewById(R.id.babRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String mapelName = intent.getStringExtra("nama_mapel");
        String kdBab = intent.getStringExtra("kd_bab");
        String namabab = intent.getStringExtra("nama_bab");

        if (mapelName != null) {
            Toast.makeText(this, "Mapel Terpilih: " + mapelName, Toast.LENGTH_SHORT).show();
            loadBabData(mapelName);
        } else {
            Toast.makeText(this, "Mapel tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
        Log.d("BabActivity", "Mapel Name: " + mapelName);
    }

    private void loadBabData(String mapelName) {
        // Bersihkan daftar bab sebelum memuat data baru
        babList.clear();

        // Membuat instance Retrofit dan ApiService secara langsung
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Panggil API untuk mendapatkan data bab
        apiService.getBabsByMapel(mapelName).enqueue(new Callback<ApiResponse<List<BabModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<BabModel>>> call, Response<ApiResponse<List<BabModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<BabModel>> apiResponse = response.body();

                    // Cek apakah status "success" dan data tidak null
                    if ("success".equalsIgnoreCase(apiResponse.getStatus()) && apiResponse.getData() != null) {
                        babList.addAll(apiResponse.getData());

                        // Jika adapter belum ada, buat adapter dan set ke recyclerView
                        if (babAdapter == null) {
                            babAdapter = new BabAdapter(babList, bab -> {
                                // Navigasi ke IsiBabActivity saat item bab diklik
                                Intent intent = new Intent(BabActivity.this, IsiBabActivity.class);
                                intent.putExtra("nama_bab", bab.getNamaBab()); // Kirimkan nama atau id bab
                                intent.putExtra("nama_mapel", mapelName);// Kirimkan nama mata pelajaran
                                intent.putExtra("kd_bab", bab.getKdBab());

                                startActivity(intent);
                            });
                            recyclerView.setAdapter(babAdapter);
                        } else {
                            babAdapter.updateBabList(babList);
                        }
                    } else {
                        // Jika tidak ada data bab
                        Toast.makeText(BabActivity.this, "Tidak ada data bab", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Jika response tidak berhasil
                    Toast.makeText(BabActivity.this, "Gagal memuat data bab", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<BabModel>>> call, Throwable t) {
                // Jika gagal terhubung ke server
                Toast.makeText(BabActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
