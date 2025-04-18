package org.codekart.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random random;
    
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    // Inserts a value to the set. Returns true if the set did not already contain the specified element.
    public boolean insert(int val) {
       if(map.containsKey(val)) {
           return false; // Value already exists
       }

       list.add(val);
       map.put(val, list.size()-1);
       return true;
    }

    // Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false; // Value does not exist
        }
        int index = map.get(val);
        int lastElement = list.get(list.size() - 1);
        list.set(index, lastElement);
        map.put(lastElement, index);
        list.remove(list.size() - 1);
        map.remove(val);

        return true; // Placeholder return statement
    }

    // Get a random element from the set.
    public int getRandom() {
        if(list.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        // Example usage
        randomizedSet.insert(1);
        randomizedSet.remove(1);
        randomizedSet.getRandom();
    }

    
}
