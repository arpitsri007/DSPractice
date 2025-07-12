package org.codekart.algo.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingScheduler {
    // leeetcode 3439
    public static int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        List<Integer> freeGaps = new ArrayList<>();

        // ith event
        // ith start time - i-1 end time

        // 0th event
        freeGaps.add(startTime[0] - 0);

        for (int i = 1; i < n; i++) {
            int gap = startTime[i] - endTime[i - 1];
            freeGaps.add(gap);
        }

        // last event
        int lastGap = eventTime - endTime[n - 1];
        if (lastGap > 0) {
            freeGaps.add(lastGap);
        }

        // Now use sliding window to find the max gap
        int maxFreeTime = 0;
        int left = 0;
        int right = 0;
        int currentFreeTime = 0;

        while (right < freeGaps.size()) {
            currentFreeTime += freeGaps.get(right);

            while (right - left + 1 > k + 1) {
                currentFreeTime -= freeGaps.get(left);
                left++;
            }

            maxFreeTime = Math.max(maxFreeTime, currentFreeTime);
            right++;
        }
        // return the max gap
        return maxFreeTime;
    }

    // leetcode 3440- Reschedule meetings for maximum free time 2
    public static int maxFreeTime2(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        List<Integer> freeGaps = new ArrayList<>();

        // ith event
        // ith start time - i-1 end time

        // 0th event
        freeGaps.add(startTime[0] - 0);

        for (int i = 1; i < n; i++) {
            int gap = startTime[i] - endTime[i - 1];
            freeGaps.add(gap);
        }

        // last event
        int lastGap = eventTime - endTime[n - 1];
        freeGaps.add(lastGap);

        List<Integer> maxLeftFreeTime = new ArrayList<>();
        List<Integer> maxRightFreeTime = new ArrayList<>();

        for (int i = n - 2; i >= 0; i--) {
            maxRightFreeTime.add(i, Math.max(maxRightFreeTime.get(i + 1), freeGaps.get(i + 1)));
        }

        for (int i = 1; i < n; i++) {
            maxLeftFreeTime.add(i, Math.max(maxLeftFreeTime.get(i - 1), freeGaps.get(i - 1)));
        }

        // TODo: complete this logic

        return 0;
    }

    // leetcode 1229 -- two pointer approach - Meeting Scheduler
    /*
     * Given the availability time slots arrays slots1 and slots2 of two people and
     * a meeting duration duration, return the earliest time slot that works for
     * both of them and is of duration duration.
     * 
     * If there is no common time slot that satisfies the requirements, return an
     * empty array.
     * 
     * The format of a time slot is an array of two elements [start, end]
     * representing an inclusive time range from start to end.
     * 
     * It is guaranteed that no two availability slots of the same person intersect
     * with each other. That is, for any two time slots [start1, end1] and [start2,
     * end2] of the same person, either start1 > end2 or start2 > end1.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]],
     * duration = 8
     * Output: [60,68]
     * Example 2:
     * 
     * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]],
     * duration = 12
     * Output: []
     */

    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> result = new ArrayList<>();

        // sort slots1 and slots2
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int i = 0;
        int j = 0;

        while (i < slots1.length && j < slots2.length) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);

            if (end - start >= duration) {
                result.add(start);
                result.add(start + duration);
                return result;
            }

            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }

        }

        return result;
    }

    // main method
    public static void main(String[] args) {
        int[] startTime = { 1, 3 };
        int[] endTime = { 2, 5 };
        int eventTime = 5;
        int k = 1;
        int maxFreeTime = maxFreeTime(eventTime, k, startTime, endTime);
        System.out.println("Max free time: " + maxFreeTime);
    }
}
