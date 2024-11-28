package com.esaturasi.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.InformasiModel;
import com.esaturasi.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.InformasiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiFragment extends Fragment {
    private RecyclerView recyclerView;
    private InformasiAdapter adapter;
    private List<InformasiModel> informasiList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_informasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tetapkan adapter kosong saat pertama kali
        adapter = new InformasiAdapter(informasiList);
        recyclerView.setAdapter(adapter);

        // Ambil data dari API
        fetchInformasi();

        return view;
    }

    private void fetchInformasi() {
        // Panggil API Service
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<ApiResponse<List<InformasiModel>>> call = apiService.getInformasi();

        call.enqueue(new Callback<ApiResponse<List<InformasiModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<InformasiModel>>> call, Response<ApiResponse<List<InformasiModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InformasiModel> data = response.body().getData();
                    if (data != null && !data.isEmpty()) {
                        informasiList.clear();
                        informasiList.addAll(data); // Tambahkan data ke dalam list
                        adapter.notifyDataSetChanged(); // Beritahu adapter untuk memperbarui RecyclerView
                    } else {
                        Toast.makeText(getContext(), "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<InformasiModel>>> call, Throwable t) {
                Log.e("InformasiFragment", "Gagal mengambil data: " + t.getMessage());
                Toast.makeText(getContext(), "Kesalahan jaringan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}