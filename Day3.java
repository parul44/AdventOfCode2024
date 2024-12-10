import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day3 {

    public static class Pair {
        int number;
        int nextIdx;
        Pair(int n, int i) {
            number = n;
            nextIdx = i;
        }
    }

    private static Pair INVALID_PAIR = new Pair(-1, -1);

    private static boolean DO;
    private static long multiplication;
    public static void main(String[] args) {
        multiplication = 0;
        DO = true;
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day3.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            int t = 1;
            while ((read = br.readLine()) != null) {
                getParsedArray(read);
                System.out.println("Line#" + t++);

            }
            System.out.println(multiplication);
            br.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static void getParsedArray(String str) {
        int i = 0;
        while(i < str.length()) {
            if (i + 4 < str.length() && str.substring(i, i + 4).compareTo("do()") == 0) {
                DO = true;
            }
            else if (i + 7 < str.length() && str.substring(i, i + 7).compareTo("don't()") == 0) {
                DO = false;
            }
            if (DO && i + 4 < str.length() && str.substring(i, i + 4).compareTo("mul(") == 0) {
                i = performValidMultiplication(str, i + 4);
            }
            else i++;
        }
    }

    private static int performValidMultiplication(String str, int startIdx) {
        if (startIdx >= str.length()) return -1;

        int commaIdx = str.indexOf(",", startIdx);
        if (commaIdx == -1 || !(commaIdx <= startIdx + 3)) return startIdx;
        Pair p1 = getNum(str, startIdx, commaIdx);
        if (p1.nextIdx == -1) return startIdx;

        int nextStartIdx = p1.nextIdx;
        int closingBracketIdx = str.indexOf(")", nextStartIdx);
        if (closingBracketIdx == -1 || !(closingBracketIdx <= nextStartIdx + 3)) return nextStartIdx;
        Pair p2 = getNum(str, nextStartIdx, closingBracketIdx);
        if (p2.nextIdx == -1) return nextStartIdx;
        
        if (p1.nextIdx!= -1 && p2.nextIdx != -1) {
            multiplication+= p1.number * p2.number;
        }
        return p2.nextIdx;
    }

    private static Pair getNum(String str, int startIdx, int endIdx) {
        int num = 0;
        for (int i = startIdx; i < endIdx; i++) {
            if (Character.isDigit(str.charAt(i))) {
                num = num*10 + (str.charAt(i) - '0');
            }
            else return INVALID_PAIR;
        }
        return new Pair(num, endIdx + 1);
    }

}
