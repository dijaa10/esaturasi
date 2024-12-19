package com.esaturasi.Fragment;

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

import java.util.ArrayList;
import java.util.List;

import Adapter.TaskAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Panggil API untuk mengambil data tugas
        fetchPendingTasks();

        return view;
    }

    private void fetchPendingTasks() {
        // Membuat instance ApiService untuk memanggil API
        ApiService apiService = ApiClient.getApi().create(ApiService.class);

        // Memanggil API getTugas yang mengirimkan parameter kd_kelas
        Call<ApiResponse<List<Task>>> call = apiService.getTugas("kd_kelas_value"); // Ganti dengan nilai kd_kelas yang sesuai
        call.enqueue(new Callback<ApiResponse<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Task>>> call, Response<ApiResponse<List<Task>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mendapatkan data tugas dari respons API
                    ApiResponse<List<Task>> apiResponse = response.body();
                    taskList = apiResponse.getData();

                    if (taskList != null && !taskList.isEmpty()) {
                        // Filter tugas yang statusnya "Belum dikumpulkan"
                        List<Task> pendingTasks = new ArrayList<>();
                        for (Task task : taskList) {
                            if ("Belum dikumpulkan".equals(task.getStatus())) {
                                pendingTasks.add(task);
                            }
                        }

                        // Inisialisasi adapter dan set ke RecyclerView
                        taskAdapter = new TaskAdapter(pendingTasks);
                        recyclerView.setAdapter(taskAdapter);
                    } else {
                        Log.e("PendingTasksFragment", "Tidak ada tugas yang belum dikumpulkan.");
                    }
                } else {
                    Log.e("PendingTasksFragment", "Gagal mengambil data tugas.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Task>>> call, Throwable t) {
                Log.e("PendingTasksFragment", "Kesalahan koneksi: " + t.getMessage());
            }
        });
    }
}
