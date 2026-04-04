package org.codekart.strings;

public class EqualString {
    // leetcode 2839:

    public boolean canBeEqual(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        if (l1 != l2)
            return false;

        if (s1.equals(s2))
            return true;

        int i = 0;
        int j = 0;

        while (i + 2 < l1) {
            if (s1.charAt(i) != s2.charAt(j)) {
                s1 = swapChar(s1, i);
            }
            i++;
            j++;
        }

        if (s1.equals(s2))
            return true;

        return false;
    }

    private String swapChar(String s1, int i) {
        char[] arr = s1.toCharArray();
        char temp = arr[i];
        arr[i] = arr[i + 2];
        arr[i + 2] = temp;
        return new String(arr);
    }

    public static void main(String[] args) {
        EqualString equalString = new EqualString();
        String s1 = "abcd";
        String s2 = "cdab";
        System.out.println(equalString.canBeEqual(s1, s2));
    }

    // leetcode 2840: 
    public boolean checkStrings2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if(n != m) return false;

        int[]even = new int[26];
        int[]odd = new int[26];

        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            } else {
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }

        for(int i = 0; i < 26; i++) {
            if(even[i] != 0 || odd[i] != 0) {
                return false;
            }
        }

        return true;
        
    }
}
