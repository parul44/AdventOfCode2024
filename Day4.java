import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day4 {

    private static long wordCount;

    private static List<Character> word = Arrays.asList('X', 'M', 'A', 'S');

    private static int m, n;

    private static int[][] directions = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    public static void main(String[] args) {
        try{
            wordCount = 0;
             m = n = 140;
            char[][] grid = new char[m][n];
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day4.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            int t = 0;
            while ((read = br.readLine()) != null) {
                grid[t++] = read.toCharArray();
            }
            findXMAS(grid);
            System.out.println(wordCount);
            br.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static void findXMAS(char[][] grid) {
        int wordIdx = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Character ch  = word.get(wordIdx);
                if (grid[i][j] - ch == 0) {
                    searchAllDirections(grid, i , j, 0, visited, 0, 0);
                }
            }
        }
    }

    private static void searchAllDirections(char[][] grid, int i, int j, int wordIdx, boolean[][] visited, int x, int y) {
        System.out.println(i + " , " + j);

        if (wordIdx == word.size()) {
            wordCount ++; return;
        }

        visited[i][j] = true;

        if (wordIdx == 0) {
            for (int[] dir : directions) {
                int r = i + dir[0];
                int c = j + dir[1];
                if (isValidCell(grid, r, c, wordIdx + 1, visited)) {
                    searchAllDirections(grid, r, c, wordIdx + 1, visited, dir[0], dir[1]);
                }
            }
        }
        else {
            if (isValidCell(grid, i + x, j + y, wordIdx + 1, visited)) {
                searchAllDirections(grid, i + x, j + y, wordIdx + 1, visited, x, y);
            }
        }
        
        visited[i][j] = false;
    }

    private static boolean isValidCell(char[][] grid, int i, int j, int wordIdx, boolean[][] visited) {
        if (wordIdx == word.size()) {
            wordCount++; 
            System.out.println("false for S");
            return false;
        }
        if (i >= 0 && i < m && j >= 0 && j < n && wordIdx < word.size() && grid[i][j] == word.get(wordIdx) && !visited[i][j]) 
            {System.out.println("true");    
        return true;}
        System.out.println("false");

        return false;
    }
}
