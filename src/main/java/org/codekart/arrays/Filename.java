package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filename {

    public static void main(String[] args) {
        Filename filename = new Filename();
        String[] names = {"pes", "fifa", "gta", "pes(2019)", "pes(2019)", "pes(2019)"};
        System.out.println(Arrays.toString(filename.getFolderNames(names)));
    }

    //leetcode 1487 - Making File Names Unique
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] result = new String[names.length];
        for(int i = 0; i < names.length; i++) {
            String name = names[i];
            if(map.containsKey(name)) {
                int count = map.get(name);
                while(map.containsKey(name + "(" + count + ")")) {
                    count++;
                }
                result[i] = name + "(" + count + ")";
                map.put(name + "(" + count + ")", 1);
                map.put(name, count + 1);
            } else {
                result[i] = name;
                map.put(name, 1);
            }
        }
        return result;
    }
}
