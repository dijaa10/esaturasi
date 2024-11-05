package com.esaturasi.Model;

public class ItemJadwal {
    private int img1;
    private int img2;
    private String nama;
    private String jam;
    private String judul;
    private String role;

    // Constructor
    public ItemJadwal(int img1, int img2, String nama, String jam, String judul, String role) {
        this.img1 = img1;
        this.img2 = img2;
        this.nama = nama;
        this.jam = jam;
        this.judul = judul;
        this.role = role;
    }

    // Getter methods
    public int getImg1() {
        return img1;
    }

    public int getImg2() {
        return img2;
    }

    public String getNama() {
        return nama;
    }

    public String getJam() {
        return jam;
    }

    public String getJudul() {
        return judul;
    }

    public String getRole() {
        return role;
    }
}
