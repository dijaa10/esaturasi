package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class ModelJadwal {

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
    private String fotoGuru;

    @SerializedName("foto_mapel_perkelas")
    private String fotoMapel;

    // Constructor
    public ModelJadwal(String kodeMapel, String namaMapel, String dariJam, String sampaiJam,
                       String namaGuru, String fotoGuru, String fotoMapel) {
        this.kodeMapel = kodeMapel;
        this.namaMapel = namaMapel;
        this.dariJam = dariJam;
        this.sampaiJam = sampaiJam;
        this.namaGuru = namaGuru;
        this.fotoGuru = fotoGuru;
        this.fotoMapel = fotoMapel;
    }

    // Getter methods
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

    public String getFotoGuru() {
        return fotoGuru;
    }

    public void setFotoGuru(String fotoGuru) {
        this.fotoGuru = fotoGuru;
    }

    public String getFotoMapel() {
        return fotoMapel;
    }

    public void setFotoMapel(String fotoMapel) {
        this.fotoMapel = fotoMapel;
    }
}
