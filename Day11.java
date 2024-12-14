import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day11 {

    static long stoneCount;
    public static void main(String[] args) {
        stoneCount = 0L;
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day11.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] reads = br.readLine().split("\\s");
            br.close();
            System.out.println(reads.length);
            List<String> stones = new ArrayList<>();
            for (String r: reads) stones.add(r);
            blink(stones, 1);
            System.out.println(stoneCount);
        }
        catch(Exception e) {
            System.err.println("" + e);
        }
    }

    private static String removeZeros(String s) {
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '0') i++;
            else break;
        }
        return i == s.length() ? "0" : s.substring(i, s.length());
    }

    private static void blink(List<String> stones, int itr) {
        if (itr == 76) {
            stoneCount+= stones.size();
            return;
        }
        int len = stones.size();
        System.out.println("At level" + itr + "with length " + len);
        for (int i = len - 1; i >=0; i-- ) {
            String stone = stones.get(i);
            if (stone.compareTo("0") == 0) stones.set(i, "1");
            else if (stone.length() %2 == 0) {
                stones.set(i,stone.substring(0, stone.length()/2));
                stones.add(i + 1, removeZeros(stone.substring(stone.length()/2)));
            }
            else {
                Long l = Long.parseLong(stones.get(i));
                stones.set(i, (l*2024) + "");
            }
        }
        blink(stones, itr + 1);
    }
}
