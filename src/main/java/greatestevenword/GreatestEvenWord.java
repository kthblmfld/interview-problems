package greatestevenword;

import java.util.HashMap;
import java.util.Map;

public class GreatestEvenWord {

    public static void main(String[] args){

        String sentence = "Time to write@foo great tooo oeoeoe peeeee code";

        String result = getGreatestEvenWord(sentence);
        System.out.println(result);
    }

    private static String getGreatestEvenWord(String sentence){

        String[] words = sentence.split(" ");

        int maxLength = 0;
        Map<Integer, String> results = new HashMap<>();
        /*
          length of the word
          index of the word - for first occurrence
         */
        for (String word : words) {
            if (word.length() % 2 == 0) {

                if (!results.containsKey(word.length())) {
                    results.put(word.length(), word);
                    if (word.length() > maxLength) {
                        maxLength = word.length();
                    }
                }
            }
        }

        Object[] keys = results.keySet().toArray();
        return results.get(keys[keys.length-1]);
    }
}
