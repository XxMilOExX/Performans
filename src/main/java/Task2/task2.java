package Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class task2 {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Необходимо передать два аргумента: путь к файлу с точками и путь к файлу с данными окружности.");
            return;
        }

        String pointsFilePath = args[0];
        String circleFilePath = args[1];

        File pointsFile = new File(pointsFilePath);
        File circleFile = new File(circleFilePath);

        double centerX = 0;
        double centerY = 0;
        double radius = 0;

        try (Scanner circleScanner = new Scanner(circleFile)) {
            centerX = circleScanner.nextDouble();
            centerY = circleScanner.nextDouble();
            radius = circleScanner.nextDouble();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл с данными окружности не найден: " + e.getMessage());
            return;
        }

        try (Scanner pointsScanner = new Scanner(pointsFile)) {
            while (pointsScanner.hasNextLine()) {
                String[] pointData = pointsScanner.nextLine().split(" ");
                double x = Double.parseDouble(pointData[0]);
                double y = Double.parseDouble(pointData[1]);

                int position = calculatePointPosition(x, y, centerX, centerY, radius);
                if (position == 0) {
                    System.out.println("Точка на окружности");
                } else if (position == 1) {
                    System.out.println("Точка внутри");
                } else {
                    System.out.println("Точка снаружи");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл с точками не найден: " + e.getMessage());
        }
    }

    public static int calculatePointPosition(double x, double y, double centerX, double centerY, double radius) {
        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        if (distance == radius) {
            return 0;
        } else if (distance < radius) {
            return 1;
        } else {
            return 2;
        }
    }

}
