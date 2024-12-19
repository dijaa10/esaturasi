package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class InformasiModel {

    @SerializedName("kd_pengumuman") // Jika nama JSON berbeda
    private String id;

    @SerializedName("judul_pengumuman")
    private String judul;

    @SerializedName("tgl_pengumuman")
    private String tanggal;

    @SerializedName("file_pengumuman")
    private String file;

    @SerializedName("deskripsi_pengumuman")
    private String deskripsi;

    // Default Constructor
    public InformasiModel() {}

    // Constructor
    public InformasiModel(String id, String judul, String tanggal, String file, String deskripsi) {
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.file = file;
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public String getGambar() {
        // Contoh: jika 'file' hanya nama file, tambahkan base URL
        String baseUrl = "http://10.0.2.2/esaturasi_web/page/admin/uploads/pengumuman/";
        return file != null && !file.isEmpty() ? baseUrl + file : "";
    }

    @Override
    public String toString() {
        return "InformasiModel{" +
                "id='" + id + '\'' +
                ", judul='" + judul + '\'' +
                ", tanggal='" + tanggal + '\'' +
                ", file='" + file + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                '}';
    }
}
