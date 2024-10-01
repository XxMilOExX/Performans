package Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class task2 {
    public static void main(String[] args) throws IOException {
        File myFile = new File("C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task2\\pointsFile.txt");
        FileInputStream inputStream = new FileInputStream(myFile);
        byte[] buffer = new byte[1024];
        int bytesRead;



        File myFile2 = new File("C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task2\\circleFile.txt");
        FileInputStream inputStream2 = new FileInputStream(myFile2);
        byte[] buffer2 = new byte[1024];
        int bytesRead2;
        inputStream.close();

        double centerX = 0;
        double centerY = 0;
        double radius = 0;

        try (Scanner circleScanner = new Scanner(myFile2)) {
            centerX = circleScanner.nextDouble();
            centerY = circleScanner.nextDouble();
            radius = circleScanner.nextDouble();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден: " + e.getMessage());
            return;
        }
        try (Scanner pointsScanner = new Scanner(myFile)) {
            while (pointsScanner.hasNextLine()) {
                String[] pointData = pointsScanner.nextLine().split(" ");
                double x = Double.parseDouble(pointData[0]);
                double y = Double.parseDouble(pointData[1]);

                int position = calculatePointPosition(x, y, centerX, centerY, radius);
                if (position == 0){
                    System.out.println("Точка на окружности");
                } else if (position == 1) {
                    System.out.println("Точка внутри");
                }
                else {
                    System.out.println("Точка снаружи");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден: " + e.getMessage());
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
