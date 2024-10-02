package Task1;
import java.util.Arrays;

public class Task1 {
        public static void main(String[] args) {
            if (args.length != 2) {
                System.err.println("Необходимо передать два аргумента: количество элементов и длину интервала.");
                return;
            }

            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);

            if (n <= 0 || m <= 0) {
                System.err.println("Количество элементов и длина интервала должны быть положительными числами.");
                return;
            }

            int[] arr = new int[n];
            Arrays.setAll(arr, i -> ++i);

            int current = 0;
            System.out.print("Path: ");
            do {
                System.out.print(arr[current]);
                current = (current + m - 1) % n;
            } while (current != 0);
        }
    }


