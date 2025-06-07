package handler;

import java.util.ArrayList;

import model.Activity;

public class ActivityHandler {
    private FileHandler ioHandler = new FileHandler();
    private ArrayList<Activity> activityList;

    public ActivityHandler() {
        this.activityList = ioHandler.start();
    }

    public void printAllData() {
        for (int i = 0; i < this.activityList.size(); i++) {
            System.out.printf("%d. ", i);
            this.activityList.get(i).printAllData();

        }
    }

    private boolean isSame(Activity newActivity) {
        boolean isThereSamaData = this.activityList.stream()
                .anyMatch(act -> act.getActivityName().equalsIgnoreCase(newActivity.getActivityName()));

        return isThereSamaData;
    }

    public void addData(Activity newActivity) {
        if (this.isSame(newActivity)) {
            System.out.printf("aktivitas dengan nama : %s \nsudah ada!\n\n", newActivity.getActivityName());
            return;
        }

        this.activityList.add(newActivity);
        this.saveData();
        System.out.println("suskses menambah data!");
        return;
    }

    public void deleteData(int index) {
        this.activityList.remove(index);
        this.saveData();
    }

    public Activity findActivity(int index) {
        return this.activityList.get(index);
    }

    public void saveData() {
        this.ioHandler.editFileData(activityList);

    }
}
