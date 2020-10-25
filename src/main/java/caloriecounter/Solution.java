package caloriecounter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {


    // Complete the marcsCakewalk function below.
    static long marcsCakewalk(Integer[] calorie) {

        Arrays.sort(calorie, Collections.reverseOrder());

        long total = 0;

        for (int i = 0; i < calorie.length; i++) {
            total = total + (calorie[i] * (long) Math.pow(2, i));
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Integer[] calorie = new Integer[n];

        String[] calorieItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int calorieItem = Integer.parseInt(calorieItems[i]);
            calorie[i] = calorieItem;
        }

        long result = marcsCakewalk(calorie);

        System.out.println(result);

        scanner.close();
    }
}