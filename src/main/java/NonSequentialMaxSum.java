import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/max-array-sum
public class NonSequentialMaxSum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] in) {

        int currentMax = 0;
        int previousMax = 0;
        int temp;

        for(int i = 0; i < in.length; i++) {
            temp = currentMax;
            currentMax = max(in[i]+ previousMax, temp);
            previousMax = temp;
        }
        return max(previousMax, currentMax);
    }

    private static int max(int a, int b){
        return a > b ? a:b;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = maxSubsetSum(arr);

        System.out.println(result);

        scanner.close();
    }
}
