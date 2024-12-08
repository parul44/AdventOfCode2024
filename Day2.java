package dsa_practice.Advent2024;

import java.util.*;
import java.io.*;

public class Day2 {
    public static void main(String[] args) {
        
        int safeReports = 0;
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day2.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            while ((read = br.readLine()) != null) {
                String[] input = read.split(" ");
                int[] report = new int[input.length];
                int k = 0;
                for (String i : input) report[k++] = (Integer.parseInt(i));
                if (isReportSafe(report)) safeReports++;
            }
            br.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
        System.out.println(safeReports);
    }

    private static boolean isReportSafe(int[] report) {
        if ( isAscending(report) || isDescending(report)) return true;
        for (int i = 0; i < report.length; i++) {
            int[] subArr = getSubArray(report, i);
            if ( isAscending(subArr) || isDescending(subArr)) return true;
        }
        return false;
    }

    private static int[] getSubArray(int[] array, int skipIndex) {
        int[] newArray = new int[array.length - 1];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == skipIndex) continue;
            newArray[k++] = array[i];
        }
        return newArray;
    }
    private static boolean isAscending(int[] report) {
        for (int i = 0; i < report.length - 1; i++) {
            if ((report[i + 1] - report[i] > 3) || (report[i + 1] - report[i] < 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(int[] report) {
        for (int i = 0; i < report.length - 1; i++) {
            if ((report[i] - report[i + 1] > 3) || (report[i] - report[i + 1] < 1)) {
                return false;
            }
        }
        return true;
    }
}
