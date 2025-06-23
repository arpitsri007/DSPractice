package org.codekart.designDataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import util.Pair;

public class FoodRating {
    // leetcode 2353

    public static class FoodRatings {
        private Map<String, PriorityQueue<Pair<String, Integer>>> cuisineToFoods;
        private Map<String, Integer> foodToRatingMap;
        private Map<String, String> foodToCuisineMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            foodToRatingMap = new HashMap<>();
            cuisineToFoods = new HashMap<>();
            foodToCuisineMap = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                // sort based on rating if rating is same, sort based on lexicographical order
                cuisineToFoods
                        .computeIfAbsent(cuisine, k -> new PriorityQueue<>((a, b) -> {
                            if (a.getSecond().equals(b.getSecond())) {
                                return a.getFirst().compareTo(b.getFirst());
                            }
                            return b.getSecond() - a.getSecond();
                        }))
                        .offer(new Pair<>(food, rating));
            }
            for (int i = 0; i < foods.length; i++) {
                foodToRatingMap.put(foods[i], ratings[i]);
                foodToCuisineMap.put(foods[i], cuisines[i]);
            }
        }

        public void changeRating(String food, int newRating) {
            int oldRating = foodToRatingMap.get(food);
            String cuisine = foodToCuisineMap.get(food);
            foodToRatingMap.put(food, newRating);
            cuisineToFoods.get(cuisine).remove(new Pair<String, Integer>(food, oldRating));
            cuisineToFoods.get(cuisine).offer(new Pair<String, Integer>(food, newRating));
        }

        public String highestRated(String cuisine) {
            return cuisineToFoods.get(cuisine).peek().getFirst();
        }

        // main method
        public static void main(String[] args) {
            FoodRatings foodRatings = new FoodRatings(
                    new String[] { "emgqdbo", "jmvfxjohq", "qnvseohnoe", "yhptazyko", "ocqmvmwjq" },
                    new String[] { "snaxol", "snaxol", "snaxol", "fajbervsj", "fajbervsj" },
                    new int[] { 2, 6, 18, 6, 5 });
            foodRatings.changeRating("qnvseohnoe", 11);
            System.out.println(foodRatings.highestRated("fajbervsj"));

            foodRatings.changeRating("emgqdbo", 3);
            foodRatings.changeRating("jmvfxjohq", 9);
            foodRatings.changeRating("emgqdbo", 14);
            System.out.println(foodRatings.highestRated("fajbervsj"));
            System.out.println(foodRatings.highestRated("snaxol"));
        }
    }

    // leetcode 2353 - Food Ratings - Optimised approach
    public static class FoodRatings2 {

        class Food {
            String name;
            int rating;
            String cuisine;

            public Food(String name, int rating, String cuisine) {
                this.name = name;
                this.rating = rating;
                this.cuisine = cuisine;
            }
        }

        Map<String, PriorityQueue<Food>> cuisineToFoods;
        Map<String, Food> foodInfoMap;

        public FoodRatings2(String[] foods, String[] cuisines, int[] ratings) {
            cuisineToFoods = new HashMap<>();
            foodInfoMap = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                Food foodObj = new Food(food, rating, cuisine);
                cuisineToFoods.computeIfAbsent(cuisine, k -> new PriorityQueue<>((a, b) -> {
                    if (a.rating == b.rating) {
                        return a.name.compareTo(b.name);
                    }
                    return b.rating - a.rating;
                })).offer(foodObj);
                foodInfoMap.put(food, foodObj);
            }
        }

        public void changeRating(String food, int newRating) {
            Food foodObj = foodInfoMap.get(food);
            foodObj.rating = newRating;
            cuisineToFoods.get(foodObj.cuisine).remove(foodObj);
            cuisineToFoods.get(foodObj.cuisine).offer(foodObj);
        }

        public String highestRated(String cuisine) {
            return cuisineToFoods.get(cuisine).peek().name;
        }
    }

    // main method
    public static void main(String[] args) {
        FoodRatings2 foodRatings = new FoodRatings2(
                new String[] { "emgqdbo", "jmvfxjohq", "qnvseohnoe", "yhptazyko", "ocqmvmwjq" },
                new String[] { "snaxol", "snaxol", "snaxol", "fajbervsj", "fajbervsj" },
                new int[] { 2, 6, 18, 6, 5 });
        foodRatings.changeRating("qnvseohnoe", 11);
        System.out.println(foodRatings.highestRated("fajbervsj"));

        foodRatings.changeRating("emgqdbo", 3);
        foodRatings.changeRating("jmvfxjohq", 9);
        foodRatings.changeRating("emgqdbo", 14);
        System.out.println(foodRatings.highestRated("fajbervsj"));
    }
}
