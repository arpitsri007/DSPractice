package org.codekart.algo.maths;

public class Remainder {
    // count pairs in array whose sum is divisible by k
    public static int countPairs(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] + arr[j]) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // count pairs in array whose sum is divisible by k - frequency array
    public static int countPairsFrequencyArray(int[] arr, int k) {
        int n = arr.length;
        int[] frequency = new int[k];
        for (int i = 0; i < n; i++) {
            frequency[arr[i] % k]++;
        }

        int count = 0;
        // count pairs of 0 and 0
        count += frequency[0] * (frequency[0] - 1) / 2;

        // count pairs of i and k-i
        for (int i = 1; i <= k / 2 && i != k - i; i++) {
            count += frequency[i] * frequency[k - i];
        }

        // count pairs of i and i
        if (k % 2 == 0) {
            count += frequency[k / 2] * (frequency[k / 2] - 1) / 2;
        }

        return count;
    }
    // main function
    public static void main(String[] args) {
        int[] arr = { 2, 2, 1, 7, 5, 3 };
        int k = 4;
        //System.out.println(countPairs(arr, k));
        System.out.println(countPairsFrequencyArray(arr, k));
    }
}
