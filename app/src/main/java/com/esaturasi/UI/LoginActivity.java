package com.esaturasi.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    private ProgressDialog progressDialog;

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
        showLoading(true);

        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        Call<LoginResponse> call = apiService.login(nisn, password);

        Log.d("LOGIN_REQUEST", "Mengirim NISN: " + nisn + ", Password: " + password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                showLoading(false);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Log.d("LOGIN_RESPONSE", "Response: " + response.body().toString());

                    if ("success".equals(loginResponse.getStatus())) {
                        LoginResponse.UserData userData = loginResponse.getData();
                        saveUserSession(userData);

                        Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();

                        // Berpindah ke aktivitas berikutnya
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.d("LOGIN_RESPONSE", "Error: " + loginResponse.getMessage());
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("LOGIN_ERROR_BODY", "Server Error: " + errorBody);
                        Toast.makeText(LoginActivity.this, "Login gagal, respons server tidak valid", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e("LOGIN_ERROR", "Error parsing error body", e);
                        Toast.makeText(LoginActivity.this, "Login gagal, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showLoading(false);
                Log.e("LOGIN_ERROR", "Kesalahan koneksi: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Kesalahan koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserSession(LoginResponse.UserData userData) {
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nisn", userData.getNisn());
        editor.putString("nama_siswa", userData.getNamaSiswa());
        editor.putString("kd_kelas", userData.getKdKelas());
        editor.putString("profile_siswa", userData.getFotoProfilSiswa());
        editor.apply();
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Sedang memproses login...");
                progressDialog.setCancelable(false);
            }
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


}
