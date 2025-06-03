package util;

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
                break;
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

        Activity actObject = activityHandler.findActivity(indexToEdit);

        if (actObject == null) {
            System.out.println("DATA TIDAK DITEMUKAN!");
            return;
        }

    }

    private <T> T getDefaultInput(T newData, T oldData) {
        if (newData != null) {
            return newData;
        }

        return oldData;
    }
}
