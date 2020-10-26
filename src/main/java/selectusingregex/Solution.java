package selectusingregex;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Regex_Test tester = new Regex_Test();
        tester.checker("[mjbcphsf].*[^n][strke]$"); // Use \\ instead of using \

    }
}

class Regex_Test {

    public void checker(String Regex_Pattern) {

        //Scanner Input = new Scanner(System.in);
        String sourceFilePath = "/input-selectusingregex.txt";
        Scanner Input = new Scanner(Solution.class.getResourceAsStream(sourceFilePath));

        int T = Integer.parseInt(Input.nextLine());

        while(T-- > 0) {
            String Test_String = Input.nextLine();
            Pattern p = Pattern.compile(Regex_Pattern);
            Matcher m = p.matcher(Test_String);
            System.out.println(m.find());

            if(Regex_Pattern.length() == 0) {
                System.out.println("Warning : Empty regular expression.");
            }

            if(Regex_Pattern.length() > 25) {
                System.out.println("Warning : Length of regular expression greater than 25 characters.");
            }
        }
    }

}