package com.esaturasi.Model;

import com.google.gson.annotations.SerializedName;

public class Task {
    @SerializedName("title")
    private String title;

    @SerializedName("subject")
    private String subject;

    @SerializedName("deadline")
    private String deadline;

    @SerializedName("status")
    private String status;

    public Task(String title, String subject, String deadline, String status) {
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }
}
