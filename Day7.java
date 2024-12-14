import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day7.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            int t = 0, u = 0;
            long ans = 0L;
            while ((read = br.readLine()) != null) {
                String[] parse = read.split(":");
                long total = Long.parseLong(parse[0]);
                String[] num = parse[1].stripLeading().split("\\s");
                int[] nums = new int[num.length];
                int i = 0;
                for (String s: num) {
                    nums[i++] = Integer.parseInt(s);
                }
                if (isOperable(nums, nums[0]*1L, 1, total) ) {
                    System.out.println(ans);
                    ans+= total;
                }
            }
            br.close();
            System.out.println(ans);
            }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static boolean isOperable(int[] nums, long prevRes, int currIdx, long total) {
        if (currIdx == nums.length) {
            return prevRes == total;
        }
        else if (prevRes > total) return false;

        boolean myAns = false;
        myAns = myAns || isOperable(nums, prevRes * nums[currIdx], currIdx + 1, total);
        myAns = myAns || isOperable(nums, prevRes + nums[currIdx], currIdx + 1, total);
        String n = prevRes+""+nums[currIdx];
        myAns = myAns || isOperable(nums, Long.parseLong(n), currIdx + 1, total);
        return myAns;
    }
    //2501605301465
    //44841370291159
}
