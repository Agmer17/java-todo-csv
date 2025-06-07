package util;

import java.lang.reflect.Field;
import java.util.Scanner;

import handler.ActivityHandler;
import model.Activity;

public class MenuUtil {

    private Scanner userInput = new Scanner(System.in);
    private ActivityHandler activityHandler = new ActivityHandler();

    public void start() {

        while (true) {
            System.out.println("Aplikasi todolist sederhana!");
            System.out.println(
                    "1. Lihat semua aktivitas\n2. Delete Aktivitas\n3. Edit Aktivitas\n4. Tambah aktivitas\n5. Keluar");
            System.out.print("Masukan pilihanmu : ");

            int UserChoice = userInput.nextInt();
            userInput.nextLine();

            if (UserChoice == 1) {
                activityHandler.printAllData();
                System.out.print("Tekan [enter] untuk kembali");
                userInput.nextLine();
            } else if (UserChoice == 2) {
                this.deleteMenu();
            } else if (UserChoice == 3) {
                this.editMenu();
            } else if (UserChoice == 4) {
                this.addDataMenu();
            } else if (UserChoice == 5) {
                break;
            }
        }

    }

    private void deleteMenu() {
        activityHandler.printAllData();
        System.out.print("Masukan index dari data yang pengen dihapus : ");
        int indexToDelete = userInput.nextInt();

        activityHandler.deleteData(indexToDelete);
        System.out.println("DATA BERHASIL DIHAPUS!");
    }

    private void editMenu() {
        activityHandler.printAllData();
        System.out.print("Masukan  data untuk diedit : ");
        int indexToEdit = userInput.nextInt();
        userInput.nextLine();

        Activity actObject = activityHandler.findActivity(indexToEdit);

        if (actObject == null) {
            System.out.println("DATA TIDAK DITEMUKAN!");
            return;
        }

        this.editWithDefaultInput(actObject);

        System.out.println("DATA BERHASIL DIEDIT!");
    }

    private Field[] getFieldsFromObject(Activity activityObj) {
        Field[] fields = activityObj.getClass().getDeclaredFields();
        return fields;
    }

    private <T> T getDefaultInput(T newVal, T oldVal) {
        if (newVal != null) {
            return newVal;
        }

        return oldVal;
    }

    private void editWithDefaultInput(Activity actObject) {

        for (Field field : this.getFieldsFromObject(actObject)) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            String userNewInput = null;

            try {
                System.out.printf("Masukan value untuk %s ( %s ) : ", field.getName(), field.get(actObject));
                userNewInput = userInput.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                if (fieldType == String.class) {
                    field.set(actObject,
                            getDefaultInput(validateInputString(userNewInput), field.get(actObject)));

                } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                    field.set(actObject, Boolean.valueOf(
                            getDefaultInput(validateInputString(userNewInput), field.get(actObject).toString())

                    ));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        this.activityHandler.saveData();
    }

    private String validateInputString(String data) {
        if (data.trim().isEmpty() || data == null) {
            return null;
        }

        return data;
    }

    private void addDataMenu() {
        System.out.println("NAMBAH AKTIVITAS!");

        System.out.print("Nama aktivitas : \n> ");
        String newActivityName = userInput.nextLine();

        System.out.print("\ndeskripsi aktivitas : \n> ");
        String newActivityDesc = userInput.nextLine();

        System.out.print("\nstatus aktivitas (true/false) : \n> ");
        Boolean newActivityStatus = userInput.nextBoolean();
        userInput.nextLine();

        Activity newActivity = new Activity(newActivityName, newActivityDesc, newActivityStatus);

        this.activityHandler.addData(newActivity);
        System.out.println("data berhasil ditambahkan!");
    }
}
