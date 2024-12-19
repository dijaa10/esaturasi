package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class BabModel {

    @SerializedName("namaBab")
    private String namaBab;

    @SerializedName("judulBab")
    private String judulBab;

    @SerializedName("namaMapel")
    private String namaMapel;

    @SerializedName("kdBab") // Menambahkan anotasi SerializedName untuk kd_bab
    private String kdBab; // Menambahkan properti kdBab

    // Getters and Setters
    public String getNamaBab() {
        return namaBab;
    }

    public void setNamaBab(String namaBab) {
        this.namaBab = namaBab;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public String getKdBab() {
        return kdBab;
    }

    public void setKdBab(String kdBab) {
        this.kdBab = kdBab;
    }
}
