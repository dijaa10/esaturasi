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
    private String status; // Menambahkan status

    // Constructor
    public Task(String taskId, String classCode, String subject, String chapterTitle, String deadline, String status) {
        this.taskId = taskId;
        this.classCode = classCode;
        this.subject = subject;
        this.chapterTitle = chapterTitle;
        this.deadline = deadline;
        this.status = status; // Menginisialisasi status
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

    public String getStatus() { // Menambahkan getter untuk status
        return status;
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

    public void setStatus(String status) { // Menambahkan setter untuk status
        this.status = status;
    }
}
