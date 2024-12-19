package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {

        @SerializedName("nisn")
        private String nisn;

        @SerializedName("nama_siswa")
        private String namaSiswa;

        @SerializedName("kd_kelas")
        private String kdKelas;

        @SerializedName("foto_profil_siswa")
        private String fotoProfilSiswa;

        public String getNisn() {
            return nisn;
        }

        public void setNisn(String nisn) {
            this.nisn = nisn;
        }

        public String getNamaSiswa() {
            return namaSiswa;
        }

        public void setNamaSiswa(String namaSiswa) {
            this.namaSiswa = namaSiswa;
        }

        public String getKdKelas() {
            return kdKelas;
        }

        public void setKdKelas(String kdKelas) {
            this.kdKelas = kdKelas;
        }

        public String getFotoProfilSiswa() {
            return  fotoProfilSiswa;
        }

        public void setFotoProfilSiswa(String fotoProfilSiswa) {
            this.fotoProfilSiswa = fotoProfilSiswa;
        }
    }
}
