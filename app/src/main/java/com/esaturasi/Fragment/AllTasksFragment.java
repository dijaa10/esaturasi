package com.esaturasi.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esaturasi.R;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.Task;
import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;

import java.util.List;

import Adapter.TaskAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedPreferences = getActivity().getSharedPreferences("UserSession", getContext().MODE_PRIVATE);

        String kdKelas = sharedPreferences.getString("kd_kelas", null);

        if (kdKelas == null || kdKelas.isEmpty()) {
            Log.e("AllTasksFragment", "Kode Kelas tidak ditemukan.");
            return view; // Jika kode kelas tidak ada, tidak akan melanjutkan untuk mengambil tugas
        }

        fetchTasks(kdKelas);  // Ambil tugas berdasarkan kode kelas

        return view;
    }

    private void fetchTasks(String kdKelas) {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);

        // Memanggil API dengan kode kelas yang sudah diambil dari SharedPreferences
        Call<ApiResponse<List<Task>>> call = apiService.getTugas(kdKelas);

        call.enqueue(new Callback<ApiResponse<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Task>>> call, Response<ApiResponse<List<Task>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<Task>> apiResponse = response.body();
                    taskList = apiResponse.getData(); // Mengambil data tugas dari respons API

                    if (taskList != null && !taskList.isEmpty()) {
                        taskAdapter = new TaskAdapter(taskList);
                        recyclerView.setAdapter(taskAdapter);
                    } else {
                        Log.e("AllTasksFragment", "Tidak ada tugas yang tersedia.");
                    }
                } else {
                    Log.e("AllTasksFragment", "Gagal mengambil data tugas.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Task>>> call, Throwable t) {
                Log.e("AllTasksFragment", "Kesalahan koneksi: " + t.getMessage());
            }
        });
    }
}
