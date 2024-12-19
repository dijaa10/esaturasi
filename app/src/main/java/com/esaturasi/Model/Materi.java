package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Materi implements Serializable {
    private String fileMateri;  // Menggunakan String, bukan array
    private String kdBab;
    private String namaMapel;
    private String namaBab; // Menambahkan field namaBab

    // Getter dan Setter untuk fileMateri
    public String getFileMateri() {
        return fileMateri;
    }

    public void setFileMateri(String fileMateri) {
        this.fileMateri = fileMateri;
    }

    // Getter dan Setter untuk kdBab
    public String getKdBab() {
        return kdBab;
    }

    public void setKdBab(String kdBab) {
        this.kdBab = kdBab;
    }

    // Getter dan Setter untuk namaMapel
    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    // Getter dan Setter untuk namaBab
    public String getNamaBab() {
        return namaBab;
    }

    public void setNamaBab(String namaBab) {
        this.namaBab = namaBab;
    }
}
