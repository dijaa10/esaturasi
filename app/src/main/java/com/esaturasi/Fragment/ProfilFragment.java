package com.esaturasi.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.esaturasi.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;

public class ProfilFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private ShapeableImageView profileImage;
    private Button btnUbahProfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout fragment
        View view = inflater.inflate(R.layout.profile, container, false);

        // Ambil data dari SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences("UserSession", requireContext().MODE_PRIVATE);
        String nama = prefs.getString("nama_siswa", "Nama tidak ditemukan");
        String nisn = prefs.getString("nisn", "NISN tidak ditemukan");
        String kelas = prefs.getString("kd_kelas", "Kelas tidak ditemukan");

        // Bind data ke TextView di layout
        TextView namaTextView = view.findViewById(R.id.namaTextView);
        TextView nisnTextView = view.findViewById(R.id.nisnTextView);
        TextView kelasTextView = view.findViewById(R.id.kelasTextView);

        namaTextView.setText(nama);
        nisnTextView.setText(nisn);
        kelasTextView.setText(kelas);

        // Inisialisasi ShapeableImageView dan Button
        profileImage = view.findViewById(R.id.profile_image);
        btnUbahProfil = view.findViewById(R.id.btn_ubah_profil);

        // Atur klik pada Button untuk memilih gambar
        btnUbahProfil.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        PERMISSION_REQUEST_CODE);
            } else {
                showImagePickerDialog();
            }
        });

        return view;
    }

    // Fungsi untuk menampilkan dialog pemilih gambar
    private void showImagePickerDialog() {
        String[] options = {"Ambil Foto", "Pilih Foto dari Galeri"};
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        builder.setTitle("Pilih Foto Profil");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    ambilFotoDariKamera();
                } else if (which == 1) {
                    pilihFotoDariGaleri();
                }
            }
        });
        builder.show();
    }

    // Fungsi untuk membuka kamera
    private void ambilFotoDariKamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(requireContext(), "Kamera tidak tersedia", Toast.LENGTH_SHORT).show();
        }
    }

    // Fungsi untuk membuka galeri
    private void pilihFotoDariGaleri() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    // Menangani hasil dari kamera atau galeri
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && data != null) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                profileImage.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri selectedImage = data.getData();
                profileImage.setImageURI(selectedImage);
            }
        }
    }

    // Menangani hasil permintaan izin
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                showImagePickerDialog();
            } else {
                Toast.makeText(requireContext(), "Izin diperlukan untuk mengakses galeri dan kamera.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
