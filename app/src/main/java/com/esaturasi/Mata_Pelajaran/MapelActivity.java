package com.esaturasi.Mata_Pelajaran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.MapelModel;
import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;
import com.esaturasi.R;
import com.esaturasi.UI.BabActivity;
import com.google.gson.Gson;

import Adapter.MapelAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MapelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MapelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.subjectsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Memanggil data mapel dari API
        fetchMapel();
    }

    private void fetchMapel() {
        // Membuat instance ApiService
        ApiService apiService = ApiClient.getApi().create(ApiService.class);

        // Memanggil API menggunakan endpoint getMapel
        Call<ApiResponse<List<MapelModel>>> call = apiService.getMapel();
        call.enqueue(new Callback<ApiResponse<List<MapelModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<MapelModel>>> call, Response<ApiResponse<List<MapelModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<MapelModel>> apiResponse = response.body();
                    Log.d("MapelActivity", "Response: " + new Gson().toJson(apiResponse.getData()));

                    // Memastikan status dari respons adalah "success"
                    if ("success".equals(apiResponse.getStatus())) {
                        // Ambil data mapel dari respons
                        List<MapelModel> mapelList = apiResponse.getData();

                        // Set adapter RecyclerView
                        adapter = new MapelAdapter(MapelActivity.this, mapelList);
                        recyclerView.setAdapter(adapter);

                        // Menambahkan listener klik pada setiap item mapel
                        adapter.setOnItemClickListener(new MapelAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(MapelModel mapel) {
                                // Mengirimkan nama mapel yang dipilih ke BabActivity
                                Intent intent = new Intent(MapelActivity.this, BabActivity.class);
                                intent.putExtra("nama_mapel", mapel.getNamaMapel());
                                startActivity(intent);
                            }
                        });

                    } else {
                        Toast.makeText(MapelActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Log error jika respons gagal
                    Log.e("MapelActivity", "Gagal memuat data: " + response.errorBody());
                    Toast.makeText(MapelActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<MapelModel>>> call, Throwable t) {
                Log.e("MapelActivity", "Kesalahan koneksi: " + t.getMessage());
                Toast.makeText(MapelActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
