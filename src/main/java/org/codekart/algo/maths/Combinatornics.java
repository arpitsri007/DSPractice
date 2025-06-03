package org.codekart.algo.maths;

public class Combinatornics {
    // leetcode 2929 - Distribute Candies Among Children
    // brute force using recursion
    public static long distributeCandies(int n, int limit) {
        return distributeCandiesHelper(0, n, limit);
    }

    private static long distributeCandiesHelper(int children, int remainingCandies, int limit) {
       
        if(children == 3) {
            if(remainingCandies == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        long ways = 0;
        for(int assigned = 0; assigned <= Math.min(limit, remainingCandies); assigned++) {
            ways += distributeCandiesHelper(children + 1, remainingCandies - assigned, limit);
        }
        return ways;
    }

    // O(n^3) solution using nested loops
    public static long distributeCandies3NestedLoops(int n, int limit) {
        long ways = 0;
        for(int i = 0; i <= Math.min(limit, n); i++) {
            for(int j = 0; j <= Math.min(limit, n - i); j++) {
                for(int k = 0; k <= Math.min(limit, n - i - j); k++) {
                    if(i + j + k == n) {
                        ways++;
                    }
                }
            }
        }
        return ways;
    }

    public static long distributeCandies2NestedLoops(int n, int limit) {
        long ways = 0;
        for(int i = 0; i <= Math.min(limit, n); i++) {
            for(int j = 0; j <= Math.min(limit, n - i); j++) {
                int remaining = Math.min(limit, n - i - j);

                if(i + j + remaining == n) {
                    ways++;
                }
            }
        }
        return ways;
    }

    // O(n) 
    public static long distributeCandies1NestedLoops(int n, int limit) {
        long ways = 0;

        int minCandiesChild1 = Math.max(0, n - (2*limit));
        int maxCandiesChild1 = Math.min(limit, n);

        for(int assignedCandiesChild1 = minCandiesChild1; assignedCandiesChild1 <= maxCandiesChild1; assignedCandiesChild1++) {
           int N = n - assignedCandiesChild1;
           int minCandiesChild2 = Math.max(0, N - limit);
           int maxCandiesChild2 = Math.min(limit, N);

           ways += maxCandiesChild2 - minCandiesChild2 + 1;
        }

        return ways;
    }   
    
}
