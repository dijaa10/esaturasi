package com.esaturasi.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esaturasi.MainActivity;
import com.esaturasi.R;

public class LoginActivity extends AppCompatActivity {

    private EditText textPassword;
    private ImageView hide;
    private EditText input_nisn;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi EditText dan ImageView
        textPassword = findViewById(R.id.input_password);
        input_nisn = findViewById(R.id.input_nisn);
        hide = findViewById(R.id.hide);

        // Menampilkan dan menyembunyikan password
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Sembunyikan password
                    textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    textPassword.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                    hide.setImageResource(R.drawable.ic_tutupmata);
                } else {
                    // Tampilkan password
                    textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    textPassword.setTransformationMethod(null);
                    hide.setImageResource(R.drawable.ic_bukamata);
                }
                isPasswordVisible = !isPasswordVisible;
                textPassword.setSelection(textPassword.length());
            }
        });
    }
    public void kirim(View view){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}