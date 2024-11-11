package com.esaturasi.Halaman_informasi;

public class ItemInformasi {
    private int img;
    private String judul;
    private String jam;

    public ItemInformasi(int img, String judul, String jam) {
        this.img = img;
        this.judul = judul;
        this.jam = jam;
    }

    public int getImg() {
        return img;
    }

    public String getJudul() {
        return judul;
    }

    public String getJam() {
        return jam;
    }
}
