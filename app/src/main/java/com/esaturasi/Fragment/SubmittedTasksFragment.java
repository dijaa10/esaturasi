package com.esaturasi.Fragment;

import android.content.Intent;
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
import com.esaturasi.Tugas_kelas.DetailtugasActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.TaskAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmittedTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ambil kode kelas dari SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserSession", getContext().MODE_PRIVATE);
        String kdKelas = sharedPreferences.getString("kd_kelas", null);

        if (kdKelas == null || kdKelas.isEmpty()) {
            Toast.makeText(getContext(), "Kode Kelas tidak ditemukan. Silakan login ulang.", Toast.LENGTH_SHORT).show();
            return view; // Jika kode kelas tidak ditemukan, hentikan proses
        }

        // Panggil API untuk mengambil data tugas yang sudah dikumpulkan
        fetchSubmittedTasks(kdKelas);

        return view;
    }

    private void fetchSubmittedTasks(String kdKelas) {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);

        // Memanggil API untuk mendapatkan semua tugas berdasarkan kode kelas
        Call<ApiResponse<List<Task>>> call = apiService.getTugas(kdKelas);

        call.enqueue(new Callback<ApiResponse<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Task>>> call, Response<ApiResponse<List<Task>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<Task>> apiResponse = response.body();
                    List<Task> taskList = apiResponse.getData();

                    if (taskList != null && !taskList.isEmpty()) {
                        // Filter tugas yang statusnya "Sudah dikumpulkan"
                        List<Task> submittedTasks = new ArrayList<>();
                        for (Task task : taskList) {
                            if ("Sudah dikumpulkan".equals(task.getStatus())) {
                                submittedTasks.add(task);
                            }
                        }

                        if (!submittedTasks.isEmpty()) {
                            // Inisialisasi TaskAdapter dengan listener
                            taskAdapter = new TaskAdapter(requireContext(), submittedTasks, task -> {
                                // Klik item tugas untuk membuka halaman detail
                                Intent intent = new Intent(requireContext(), DetailtugasActivity.class);
                                intent.putExtra("task_id", task.getTaskId());
                                intent.putExtra("subject", task.getSubject());
                                intent.putExtra("deadline", task.getDeadline());
                                intent.putExtra("description", task.getDescription());
                                intent.putExtra("photoPath", task.getPhotoPath());
                                startActivity(intent);
                            });
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
