package org.codekart;

import org.codekart.Arrays.MergeArray;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        mergeArray.merge(nums1, m, nums2, n);
        System.out.println("Merged Array: " + Arrays.toString(nums1));

        while (true) {
            String k = "";
            String l = "";
            if(k == l) {

            }
        }
    }
}
