package org.codekart.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubSetWords {

    public static void main(String[] args) {
        // 1 → "" 2 -> a,b,c 3 -> d,e,f 4 -> g,h,i 5 -> j,k,l 6 -> m,n,o 7 -> p,q,r,s
        // 8 -> t,u,v 9 -> w,x,y,z
        // KNOWN_WORDS= ['careers', 'linkedin', 'hiring', 'interview', 'linkedgo',
        // 'apple']

        // 2273377 - [careers]
        // 54653346 - [linkedin, linkedgo]

        Map<Integer, String> map = getMap();

        String[] knownWords = { "careers", "linkedin", "hiring", "interview", "linkedgo" };
        String input = "54653346";
        System.out.println(findSubSetWords(knownWords, input, map));
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        return map;
    }

    public static List<String> findSubSetWords(String[] knownWords, String input, Map<Integer, String> map) {
        List<String> result = new ArrayList<>();
        // insert all known words into trie
        Trie trie = new Trie();
        for (String word : knownWords) {
            trie.insert(word);
        }
        // search for possible words for given input (2273377)
        // Use backtracking to find all possible words
        findWordsHelper(trie, input, 0, "", result, map);
        return result;
    }

    private static void findWordsHelper(Trie trie, String input, int index, String currentWord,
            List<String> result, Map<Integer, String> map) {
        // Base case: if we've processed all digits
        if (index == input.length()) {
            // Check if the current word exists in our trie
            if (trie.search(trie.root, currentWord)) {
                result.add(currentWord);
            }
            return;
        }

        int digit = input.charAt(index) - '0';
        String letters = map.get(digit);

        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            String newWord = currentWord + letter;
            if (trie.startsWith(trie.root, newWord)) {
                findWordsHelper(trie, input, index + 1, newWord, result, map);
            }
        }
    }

}
