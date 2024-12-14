import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day10 {
    static int len = 49;
    static char[][] grid;
    static long[][] paths;
    static int[][] dir = {
        {0,1}, {0,-1},{-1,0},{1,0}
    };
    public static void main(String[] args) {      
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day10.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String reads; int t = 0;
            grid = new char[len][len];
            paths = new long[len][len];
            while ((reads = br.readLine()) != null) {
                grid[t++] = reads.toCharArray();
            }
            br.close();
            System.out.println(getHikeCount());
        }
        catch(Exception e) {
            System.err.println("Error while reading file" + e.getMessage());
        }
    }

    private static long getHikeCount() {
        long ans = 0L;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                paths[i][j] = -1;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == '0') {
                    ans+= getCount(i, j, '0');
                }
            }
        }
        return ans;
    }

    private static long getCount(int currRow, int currCol, char search) {
        if (search == '9') {
            return paths[currRow][currCol] = 1;
        }
        if (paths[currRow][currCol] != -1) return paths[currRow][currCol];
        long myAns = 0L;
        for (int d = 0; d < 4; d++) {
            int nextRow = currRow + dir[d][0];
            int nextCol = currCol + dir[d][1];
            if (isValid(nextRow, nextCol, (char)(search + 1))) {
                myAns+= getCount(nextRow, nextCol, (char)(search + 1));
            }
        }
        return paths[currRow][currCol] = myAns;

    }

    private static boolean isValid(int r, int c, char search) {
        if (r <0 || r == len || c < 0 || c == len || (search !=  grid[r][c])) return false;
        return true;
    }
}