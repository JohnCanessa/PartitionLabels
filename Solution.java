import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * 763. Partition Labels 
 * https://leetcode.com/problems/partition-labels/
 */
public class Solution {


    /**
     * A string S of lowercase English letters is given. 
     * We want to partition this string into as many parts as possible 
     * so that each letter appears in at most one part, 
     * and return a list of integers representing the size of these parts.
     */
    static List<Integer> partitionLabels0(String S) {
        
        // **** for results ****
        List<Integer> sizes = new ArrayList<Integer>();

        // **** first and last positions of characters in S ****
        TreeMap<Character, Integer> firstPos = new TreeMap<Character, Integer>();
        TreeMap<Character, Integer>  lastPos = new TreeMap<Character, Integer>();

        // **** populate the first and last position of all characters ****
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            firstPos.putIfAbsent(ch, i);
            if (lastPos.get(ch) == null)
                lastPos.put(ch, i);
            else
                lastPos.replace(ch, lastPos.get(ch), i);
        }

        // **** instead of lastPos hash map ****
        int[] lastIndex = new int[26];
        for (int i = 0; i < S.length(); i++)
            lastIndex[S.charAt(i) - 'a'] = i;

        // **** first min and max ****
        int min = firstPos.get(S.charAt(0));
        int max = lastPos.get(S.charAt(0));

        // **** traverse S ****
        int l   = 0;
        int r   = 0;
        for (int i = 1; i < S.length() && (r < S.length() - 1); i++) {

            // **** ****
            char ch = S.charAt(i);
            l       = firstPos.get(ch);
            r       = lastIndex[ch -'a'];

            // **** update min and max (if left > max) ****
            if (l > max) {

                // **** compute size and add to list ****
                sizes.add(max - min + 1);

                // **** update min and max ****
                min = firstPos.get(ch);
                max = lastIndex[ch - 'a'];

            } else if (r > max) {
                max = r;
            }
        }

        // **** compute size and add to list ****
        sizes.add(max - min + 1);

        // **** return list of sizes ****
        return sizes;
    }


    /**
     * A string S of lowercase English letters is given. 
     * We want to partition this string into as many parts as possible 
     * so that each letter appears in at most one part, 
     * and return a list of integers representing the size of these parts.
     * 
     * Execution time O(n * 2)
     * 
     * Runtime: 7 ms, faster than 23.91% of Java online submissions.
     * Memory Usage: 39 MB, less than 37.43% of Java online submissions.
     */
    static List<Integer> partitionLabels(String S) {
        
        // **** for results ****
        List<Integer> sizes = new ArrayList<Integer>();

        // **** first position of characters in S ****
        TreeMap<Character, Integer> firstPos = new TreeMap<Character, Integer>();

        // **** populate the first and last position of all characters ****
        for (int i = 0; i < S.length(); i++)
            firstPos.putIfAbsent(S.charAt(i), i);

        // **** instead of lastPos hash map ****
        int[] lastIndex = new int[26];
        for (int i = 0; i < S.length(); i++)
            lastIndex[S.charAt(i) - 'a'] = i;

        // **** first min and max ****
        int min = firstPos.get(S.charAt(0));
        int max = lastIndex[S.charAt(0) - 'a'];

        // **** traverse S ****
        int l   = 0;
        int r   = 0;
        for (int i = 1; i < S.length() && (r < S.length() - 1); i++) {

            // **** ****
            char ch = S.charAt(i);
            l       = firstPos.get(ch);
            r       = lastIndex[ch -'a'];

            // **** update min and max (if left > max) ****
            if (l > max) {

                // **** compute size and add to list ****
                sizes.add(max - min + 1);

                // **** update min and max ****
                min = firstPos.get(ch);
                max = lastIndex[ch - 'a'];

            } else if (r > max) {
                max = r;
            }
        }

        // **** compute size and add to list ****
        sizes.add(max - min + 1);

        // **** return list of sizes ****
        return sizes;
    }


    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read string ****
        String S = sc.nextLine().trim();

        // **** close scanner ****
        sc.close();

        // ???? ????
        System.out.println("main <<< S ==>" + S + "<== length: " + S.length());

        // **** partition and display sizes ****
        System.out.println("main <<<  partitionLabels0: " + partitionLabels0(S));
        
        // **** partition and display sizes ****
        System.out.println("main <<<   partitionLabels: " + partitionLabels(S));
    }

}