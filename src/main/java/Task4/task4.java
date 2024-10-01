package Task4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task4\\massFile.txt");
        FileInputStream inputStream = new FileInputStream(myFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        List<Integer> nums = new ArrayList<>();
        try (Scanner massScanner = new Scanner(myFile)) {
            while (massScanner.hasNextInt()) {
                nums.add(massScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден: " + e.getMessage());
        }
        int minMovesCount = minMoves(nums);
        System.out.println(minMovesCount);
    }
    public static int minMoves(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }

        int minNum = nums.get(0);
        for (int num : nums) {
            if (num < minNum) {
                minNum = num;
            }
        }

        int totalMoves = 0;
        int cr = 0;
        for (int i = 0; i < nums.size(); i++) {
            cr += nums.get(i);
        }
        cr = cr / nums.size();
        if (!nums.equals(cr)){
            for (int i = 0; i < nums.size(); i++) {
                if(cr >= nums.get(i)){
                    totalMoves += cr - nums.get(i);
                }else {
                    totalMoves += nums.get(i) - cr;
                }
            }
        }
        if(nums.equals(cr)){
            cr -= 1;
        }



        return totalMoves;
    }

}

