package com.esaturasi.Fragment;

import android.os.Bundle;
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

public class TerbaruFragment extends Fragment {
    private RecyclerView recyclerView;
    private InformasiAdapter adapter;
    private List<InformasiModel> informasiList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment yang sesuai
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);

        // Inisialisasi RecyclerView untuk menampilkan data
        recyclerView = view.findViewById(R.id.recycler_view_informasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set adapter awal
        adapter = new InformasiAdapter(informasiList);
        recyclerView.setAdapter(adapter);

        // Ambil data terbaru dari API atau sumber lain
        fetchTerbaruData();

        return view;
    }

    private void fetchTerbaruData() {
        // Panggil API untuk mengambil data terbaru
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<ApiResponse<List<InformasiModel>>> call = apiService.getTerbaruData(); // Anda bisa membuat metode untuk API terbaru

        call.enqueue(new Callback<ApiResponse<List<InformasiModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<InformasiModel>>> call, Response<ApiResponse<List<InformasiModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InformasiModel> data = response.body().getData();
                    if (data != null && !data.isEmpty()) {
                        informasiList.clear();
                        informasiList.addAll(data); // Menambahkan data terbaru ke dalam list
                        adapter.notifyDataSetChanged(); // Memberitahu adapter untuk memperbarui RecyclerView
                    } else {
                        Toast.makeText(getContext(), "Data terbaru kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal mendapatkan data terbaru", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<InformasiModel>>> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan jaringan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
