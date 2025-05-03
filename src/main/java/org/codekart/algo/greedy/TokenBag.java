package org.codekart.algo.greedy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TokenBag {
    public static void main(String[] args) {
        int[] tokens = {100, 200, 300, 400};
        int power = 200;
        System.out.println(bagOfTokensScore(tokens, power));
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0;
        int right = tokens.length - 1;
        int score = 0;
        int maxScore = 0;
        while(left <= right) {
            if(power >= tokens[left]) {
                power -= tokens[left];
                score++;
                left++;
                maxScore = Math.max(maxScore, score);
            } else if(score > 0) {
                power += tokens[right];
                score--;
                right--;
            } else {
                break;
            }
        }
        return maxScore;
    }

    //Boards to save people
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int countBoat = 0;

        while(left < right) {
            if(people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            countBoat++;
        }

        if(left == right && people[left] <= limit) {
            countBoat++;
        }
        return countBoat;
        
    }

    // Break a Palindrome
    public String breakPalindrome(String palindrome) {
        if(palindrome.length() == 1) {
            return "";
        }

        int n = palindrome.length();
        char[] palindromeArray = palindrome.toCharArray();

        for(int i = 0; i < n / 2; i++) {
            if(palindromeArray[i] != 'a') {
                palindromeArray[i] = 'a';
                return new String(palindromeArray);
            }
        }
        palindromeArray[n - 1] = 'b';
        return new String(palindromeArray);
        
    }

    // Broken Calculator
    public int brokenCalc(int startValue, int target) {
        if(startValue >= target) return startValue - target;
        if(target % 2 == 0) return 1 + brokenCalc(startValue, target/2);
        return 1 + brokenCalc(startValue, target + 1);
    }



    // broken calculator iterative
    public int brokenCalcIterative(int startValue, int target) {
        int count = 0;
        while(startValue < target) {
            if(target % 2 == 0) target /= 2;
            else target++;
            count++;
        }
        return count + startValue - target;
    }

    


    
}
