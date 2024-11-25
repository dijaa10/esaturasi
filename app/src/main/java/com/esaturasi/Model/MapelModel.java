package com.esaturasi.Model;
import com.google.gson.annotations.SerializedName;

public class MapelModel {
    @SerializedName("nama_mapel")
    private String namaMapel;

    @SerializedName("foto_mapel_perkelas")
    private String fotoMapelPerkelas;

    // Getters and Setters
    public String getNamaMapel() {
        return namaMapel;
    }

    public String getFotoMapelPerkelas() {
        return fotoMapelPerkelas;
    }
}




