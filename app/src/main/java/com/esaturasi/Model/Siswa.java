package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class Siswa {
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
        return "http://10.0.2.2/esaturasi_web/page/admin/uploads/profilesiswa/" + fotoProfilSiswa;
    }

    public void setFotoProfilSiswa(String fotoProfilSiswa) {
        this.fotoProfilSiswa = fotoProfilSiswa;
    }
}

