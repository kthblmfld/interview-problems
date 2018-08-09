import java.util.Scanner;

// https://www.hackerrank.com/challenges/determining-dna-health/problem
// TODO: Use a trie and the Aho-Corasick algorithm to complete the problem
public class DnaHealthMax {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] genes = new String[n];

        String[] genesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String genesItem = genesItems[i];
            genes[i] = genesItem;
        }

        int[] health = new int[n];

        String[] healthItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int healthItem = Integer.parseInt(healthItems[i]);
            health[i] = healthItem;
        }

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long maxHealth = 0;
        long minHealth = 0;

        for (int sItr = 0; sItr < s; sItr++) {

            String[] firstLastd = scanner.nextLine().split(" ");
            int first = Integer.parseInt(firstLastd[0]);
            int last = Integer.parseInt(firstLastd[1]);
            String dnaSequence = firstLastd[2];

            int currentHealth = 0;

            for (int dnaSeqIndex = 0; dnaSeqIndex < dnaSequence.length(); dnaSeqIndex++) {

                for (int geneToMatchIndex = first; geneToMatchIndex <= last; geneToMatchIndex++) {

                    String gene = genes[geneToMatchIndex];

                    if (gene.charAt(0) == dnaSequence.charAt(dnaSeqIndex)) {

                        if(gene.length() > 1){
                            for(int k = 1; k < gene.length(); k++){

                                if((dnaSequence.length() > (dnaSeqIndex + k)) &&
                                        gene.charAt(k) == dnaSequence.charAt(dnaSeqIndex + k)){

                                    if(k == gene.length() - 1){
                                        currentHealth += health[geneToMatchIndex];
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                        }
                        else{
                            currentHealth += health[geneToMatchIndex];
                        }
                    }
                }
            }

            if (currentHealth > maxHealth) {
                maxHealth = currentHealth;
            }
            if (currentHealth < minHealth) {
                minHealth = currentHealth;
            }

            if (sItr == 0) {
                minHealth = currentHealth;
                maxHealth = currentHealth;
            }
        }

        System.out.println(minHealth + " " + maxHealth);

        scanner.close();
    }
}

