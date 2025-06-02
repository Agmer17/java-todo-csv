package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Activity;

public class FileHandler {
    private String path;

    FileHandler(String path) {
        this.path = path;
    }

    FileHandler() {
        this.path = "src/data/Activities.csv";
    }

    public ArrayList<Activity> start() {
        ArrayList<Activity> data = new ArrayList<>(100);
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            data = br.lines()
                    .map(act -> act.split(","))
                    .map(arr -> new Activity(arr[0], arr[1], arr[2], arr[3]))
                    .collect(Collectors.toCollection(ArrayList::new));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public void editFileData(ArrayList<Activity> newActivityList) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(this.path))) {
            for (Activity activity : newActivityList) {
                wr.write(activity.toString());
                wr.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
