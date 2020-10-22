import java.io.*;
import java.util.List;
import java.util.Scanner;

public class PrimeFinder {

    static int[] getPrimes(){
        return new int[]{2,3,5,7,11,13,17,19,23,
        29,31,37,41,43,47,53,59,61,67,
        71,73,79,83,89,97,101,103,107,109,
        113,127,131,137,139,149,151,157,163,167,
        173,179,181,191,193,197,199,211,223,227,
        229,233,239,241,251,257,263,269,271,277,
        281,283,293,307,311,313,317,331,337,347,
        349,353,359,367,373,379,383,389,397,401,
        409,419,421,431,433,439,443,449,457,461,
        463,467,479,487,491,499,503,509,521,523,
        541,547,557,563,569,571,577,587,593,599,
        601,607,613,617,619,631,641,643,647,653,
        659,661,673,677,683,691,701,709,719,727,
        733,739,743,751,757,761,769,773,787,797,
        809,811,821,823,827,829,839,853,857,859,
        863,877,881,883,887,907,911,919,929,937,
        941,947,953,967,971,977,983,991,997};
    }

    static boolean isPrime(int number){
        int[] primes = getPrimes();
        for (int prime : primes) {

            if (number == prime)
                return true;
        }
        return false;
    }

    static boolean dividesByPrime(int number){

        int[] primes = getPrimes();
        for (int prime : primes) {

            if (number % prime == 0)
                return true;
        }
        return false;
    }

    // Complete the primality function below.
    static String primality(int n) {

        String result = "Not prime";

        if(n == 1){
           result = "Not prime";
        }
        else if (isPrime(n)) {
            result = "Prime";
        } else if (dividesByPrime(n)) {
            result = "Not prime";
        } else {
            result = "Prime";
        }
        return result;
    }

    //    DELETE DELETE DELETE

    private static Scanner scanner;

    static {
        Class clazz = PrimeFinder.class;
        scanner = new Scanner(clazz.getResourceAsStream("input-primefinder.txt"));

    }
//    DELETE DELETE DELETE

    //private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int pItr = 0; pItr < p; pItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int total_inputs = 0;

            String result = primality(n);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
