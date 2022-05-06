package friendcirclequeries;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'countGroups' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY related as parameter.
     */

    public static int countGroups(List<String> related) {

        int dimension = related.size();
        int[][] matrix = new int[dimension][dimension];

        int currentRow = 0;
        for (String row : related) {
            for (int i = 0; i < row.length(); i++) {
                int cellVal = Integer.parseInt(String.valueOf(row.charAt(i)));
                matrix[currentRow][i] = cellVal;
            }
            currentRow += 1;
        }

        List<Set<Integer>> groups = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            Set<Integer> localGroup = new HashSet();
            for (int j = 0; j < dimension; j++) {
                if (matrix[i][j] == 1) {
                    localGroup.add(j + 1);
                }
            }

            mergeGroups(groups, localGroup);
        }

        return groups.size();
    }

    static void mergeGroups(List<Set<Integer>> allGroups, Set<Integer> newGroup) {
        boolean found = false;
        for (Set<Integer> knownGroup : allGroups) {
            for (int member : newGroup) {
                if (knownGroup.contains(member)) {
                    found = true;
                    knownGroup.addAll(newGroup);
                    break;
                }
            }
        }
        if (!found) {
            allGroups.add(newGroup);
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {

        String sourceFilePath = "/input-connectedgroups.txt";
        InputStream inputStream = Solution.class.getResourceAsStream(sourceFilePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int relatedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> related = IntStream.range(0, relatedCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int result = Result.countGroups(related);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
