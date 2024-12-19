package com.esaturasi.Fragment;

import android.content.SharedPreferences;
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

public class SubmittedTasksFragment extends Fragment {

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

        // Ambil kode kelas dari SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("UserSession", getContext().MODE_PRIVATE);
        String kdKelas = sharedPreferences.getString("kd_kelas", null);

        if (kdKelas == null || kdKelas.isEmpty()) {
            Toast.makeText(getContext(), "Kode Kelas tidak ditemukan. Silakan login ulang.", Toast.LENGTH_SHORT).show();
            return view; // Jika kode kelas tidak ditemukan, tidak lanjut mengambil tugas
        }

        // Panggil API untuk mengambil data tugas yang sudah dikumpulkan
        fetchSubmittedTasks(kdKelas);

        return view;
    }

    private void fetchSubmittedTasks(String kdKelas) {
        // Membuat instance ApiService untuk memanggil API
        ApiService apiService = ApiClient.getApi().create(ApiService.class);

        // Memanggil API getTugas yang mengirimkan parameter kd_kelas
        Call<ApiResponse<List<Task>>> call = apiService.getTugas(kdKelas);

        call.enqueue(new Callback<ApiResponse<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Task>>> call, Response<ApiResponse<List<Task>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mendapatkan data tugas dari respons API
                    ApiResponse<List<Task>> apiResponse = response.body();
                    taskList = apiResponse.getData();

                    if (taskList != null && !taskList.isEmpty()) {
                        // Filter tugas yang statusnya "Sudah dikumpulkan"
                        List<Task> submittedTasks = new ArrayList<>();
                        for (Task task : taskList) {
                            if ("Sudah dikumpulkan".equals(task.getStatus())) {
                                submittedTasks.add(task);
                            }
                        }

                        if (!submittedTasks.isEmpty()) {
                            // Inisialisasi adapter dan set ke RecyclerView
                            taskAdapter = new TaskAdapter(submittedTasks);
                            recyclerView.setAdapter(taskAdapter);
                        } else {
                            Log.e("SubmittedTasksFragment", "Tidak ada tugas yang sudah dikumpulkan.");
                            Toast.makeText(getContext(), "Tidak ada tugas yang sudah dikumpulkan.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("SubmittedTasksFragment", "Tidak ada tugas yang tersedia.");
                        Toast.makeText(getContext(), "Tidak ada tugas yang tersedia.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("SubmittedTasksFragment", "Gagal mengambil data tugas.");
                    Toast.makeText(getContext(), "Gagal memuat data tugas.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Task>>> call, Throwable t) {
                Log.e("SubmittedTasksFragment", "Kesalahan koneksi: " + t.getMessage());
                Toast.makeText(getContext(), "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
