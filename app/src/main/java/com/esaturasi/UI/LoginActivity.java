package com.esaturasi.UI;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esaturasi.API.ApiClient;
import com.esaturasi.API.ApiService;
import com.esaturasi.MainActivity;
import com.esaturasi.Model.LoginResponse;
import com.esaturasi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etNisn, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNisn = findViewById(R.id.etNisn);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String nisn = etNisn.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (nisn.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "NISN dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            login(nisn, password);
        });
    }

    private void login(String nisn, String password) {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<LoginResponse> call = apiService.login(nisn, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if ("success".equals(loginResponse.getStatus())) {
                        LoginResponse.UserData userData = loginResponse.getData();

                        // Simpan sesi ke SharedPreferences
                        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("nisn", userData.getNisn());
                        editor.putString("nama_siswa", userData.getNamaSiswa());
                        editor.putString("kd_kelas", userData.getKdKelas());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();

                        // Berpindah ke aktivitas berikutnya
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login gagal, periksa kembali data Anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN_ERROR", t.getMessage());
                Toast.makeText(LoginActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

