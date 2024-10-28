package Fungsi;

public class ScheduleItem {
    private String subjectName;
    private String time;
    private String teacher;
    private int profileImageResId;  // Resource ID untuk gambar profil
    private int materialImageResId; // Resource ID untuk gambar materi

    // Constructor
    public ScheduleItem(String subjectName, String time, String teacher, int profileImageResId, int materialImageResId) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.profileImageResId = profileImageResId;
        this.materialImageResId = materialImageResId;
    }

    // Getter untuk nama pelajaran
    public String getSubjectName() { return subjectName; }

    // Getter untuk waktu
    public String getTime() { return time; }

    // Getter untuk nama guru
    public String getTeacher() { return teacher; }

    // Getter untuk ID resource gambar profil
    public int getProfileImageResId() { return profileImageResId; }

    // Getter untuk ID resource gambar materi
    public int getMaterialImageResId() { return materialImageResId; }
}


