package com.esaturasi.API;

import com.esaturasi.Model.LoginResponse;
import com.esaturasi.Model.MapelModel;
import com.esaturasi.Model.ScheduleItem;
import com.esaturasi.Model.Siswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import com.esaturasi.Model.ApiResponse;
import com.esaturasi.Model.Task;

public interface ApiService {
    @FormUrlEncoded
    @POST("page/api/loginn.php")
    Call<LoginResponse> login(
            @Field("nisn") String nisn,
            @Field("password") String password
    );
    @GET("page/api/get_mapel.php")
    Call<ApiResponse<List<MapelModel>>> getMapel();


    @GET("page/api/get_jadwal.php?kd_kelas=K2401&hari=senin")
    Call<ApiResponse<List<ScheduleItem>>> getJadwal(
            @Query("kd_kelas") String kdKelas,
            @Query("hari") String hari
    );
    @GET("page/api/get_tugas.php") // Ganti dengan nama endpoint API Anda
    Call<List<Task>> getTasks(@Query("nisn") String nisn);

}



