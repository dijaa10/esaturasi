package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("task_id")
    private String taskId;

    @SerializedName("class_code")
    private String classCode;

    @SerializedName("subject")
    private String subject;

    @SerializedName("chapter_title")
    private String chapterTitle;

    @SerializedName("deadline")
    private String deadline;

    @SerializedName("status")
    private String status;

    @SerializedName("description")
    private String description; // Tambahkan field untuk deskripsi tugas

    @SerializedName("photoPath")
    private String photoPath; // Tambahkan field untuk path foto tugas

    // Constructor
    public Task(String taskId, String classCode, String subject, String chapterTitle, String deadline, String status, String description, String photoPath) {
        this.taskId = taskId;
        this.classCode = classCode;
        this.subject = subject;
        this.chapterTitle = chapterTitle;
        this.deadline = deadline;
        this.status = status;
        this.description = description; // Inisialisasi description
        this.photoPath = photoPath; // Inisialisasi photoPath
    }

    // Getter methods
    public String getTaskId() {
        return taskId;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getSubject() {
        return subject;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() { // Getter untuk deskripsi tugas
        return  description;
    }

    public String getPhotoPath() { // Getter untuk path foto tugas
        return "http://10.0.2.2/esaturasi_web/page/guru/uploads/tugas/" + photoPath;
    }

    // Setter methods
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) { // Setter untuk deskripsi tugas
        this.description = description;
    }

    public void setPhotoPath(String photoPath) { // Setter untuk path foto tugas
        this.photoPath = photoPath;
    }
}
