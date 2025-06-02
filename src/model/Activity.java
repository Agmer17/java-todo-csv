package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Activity {
    private String activityName;
    private String activityDesc;
    private Boolean activityStatus;
    private String activityEntryDate;

    public Activity(String paramsName, String paramsDesc, String paramsStatus, String paramsDate) {
        this.activityName = paramsName;
        this.activityDesc = paramsDesc;
        this.activityStatus = Boolean.valueOf(paramsStatus);
        this.activityEntryDate = paramsDate;
    }

    public Activity(String paramsName, String paramsDesc, Boolean paramsActivityStatus) {
        this.activityName = paramsName;
        this.activityDesc = paramsDesc;
        this.activityStatus = Boolean.valueOf(paramsActivityStatus);
        this.activityEntryDate = this.getDateNow();
    }

    private String getDateNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd yyyy HH:mm",
                Locale.forLanguageTag("id-ID"));
        LocalDateTime currentDate = LocalDateTime.now();

        return formatter.format(currentDate);

    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Boolean getActivityStatus() {
        return this.activityStatus;
    }

    public void setActivityStatus(Boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityEntryDate() {
        return this.activityEntryDate;
    }

    public void setActivityEntryDate(String activityEntryDate) {
        this.activityEntryDate = activityEntryDate;
    }

    public void printAllData() {
        System.out.printf("| %s | %s | %b | %s |\n", this.getActivityName(), this.getActivityDesc(),
                this.getActivityStatus(), this.getActivityEntryDate());
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", this.activityName, this.activityDesc,
                this.activityStatus.toString().toUpperCase(), this.activityEntryDate);
    }

}
