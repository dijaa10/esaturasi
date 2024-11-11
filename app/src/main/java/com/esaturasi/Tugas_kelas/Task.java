package com.esaturasi.Tugas_kelas;

public class Task {
    private String title;
    private String subject;
    private String deadline;
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
