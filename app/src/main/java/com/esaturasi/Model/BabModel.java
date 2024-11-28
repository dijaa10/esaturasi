package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class BabModel {
    @SerializedName("nama_bab")
    private String namaBab;

    @SerializedName("judul_bab")
    private String judulBab;

    public String getNamaBab() {
        return namaBab;
    }
    public String getJudulBab() {
        return judulBab;
    }


}
