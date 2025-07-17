package org.codekart.algo.twopointer;

import java.util.Arrays;

public class PlayerMatching {
    // leetcode 2410 - Maximum Matching of Players With Trainers

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int i = 0;
        int j = 0;
        int count = 0;

        while(i < players.length && j < trainers.length) {
            if(players[i] <= trainers[j]) {
                count++;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return count;
    }
}
