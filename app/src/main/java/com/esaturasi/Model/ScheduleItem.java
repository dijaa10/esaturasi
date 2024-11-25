package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class ScheduleItem {

    @SerializedName("kd_mapel")
    private String kodeMapel;

    @SerializedName("nama_mapel")
    private String namaMapel;

    @SerializedName("dari_jam")
    private String dariJam;

    @SerializedName("sampai_jam")
    private String sampaiJam;

    @SerializedName("nama_guru")
    private String namaGuru;

    @SerializedName("foto_profil_guru")
    private String fotoProfilGuru;

    @SerializedName("foto_mapel_perkelas")
    private String fotoMapelPerkelas;

    // Getters and Setters
    public String getKodeMapel() {
        return kodeMapel;
    }

    public void setKodeMapel(String kodeMapel) {
        this.kodeMapel = kodeMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public String getDariJam() {
        return dariJam;
    }

    public void setDariJam(String dariJam) {
        this.dariJam = dariJam;
    }

    public String getSampaiJam() {
        return sampaiJam;
    }

    public void setSampaiJam(String sampaiJam) {
        this.sampaiJam = sampaiJam;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getFotoProfilGuru() {
        return fotoProfilGuru;
    }

    public void setFotoProfilGuru(String fotoProfilGuru) {
        this.fotoProfilGuru = fotoProfilGuru;
    }

    public String getFotoMapelPerkelas() {
        return fotoMapelPerkelas;
    }

    public void setFotoMapelPerkelas(String fotoMapelPerkelas) {
        this.fotoMapelPerkelas = fotoMapelPerkelas;
    }
}
