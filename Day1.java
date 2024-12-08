package dsa_practice.Advent2024;

import java.util.*;
import java.io.*;

public class Day1 {

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        try{
        File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String read;
        while ((read = br.readLine()) != null) {
            String[] location = read.split("\\|");
            l1.add(Integer.parseInt(location[0]));
            l2.add(Integer.parseInt(location[1]));
        }
        br.close();
    }
    catch(Exception e) {
        System.err.println(e);
    }
        // System.out.println(sumOfDistanceOf2Lists(l1, l2));
        System.out.println(similarityOfLists(l1, l2));
    }

    private static long similarityOfLists(List<Integer> l1, List<Integer>l2) {
        long similarity = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (Integer i : l2) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        for (Integer i : l1) {
            if (count.containsKey(i)) similarity+= count.get(i) * i;
        }
        return similarity;
    }

    private static long sumOfDistanceOf2Lists(List<Integer> l1, List<Integer>l2) {
        Collections.sort(l1);
        Collections.sort(l2);
        long distance = 0;
        for (int i = 0, j = 0; i < l1.size() && j < l2.size(); i++, j++) {
            distance+= Math.abs(l1.get(i) - l2.get(j));
        }
        return distance;
    }
}
